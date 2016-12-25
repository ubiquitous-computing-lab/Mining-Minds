/**
* 
* Copyright [2016] [Claudia Villalonga & Muhammad Asif Razzaq]
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed under 
* the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
*  ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and limitations under the License.
*/
package mm.icl.hlc.HLCBuilder;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.log4j.Logger;
import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import mm.icl.hlc.ContextOntologyManager.ContextHandler;
import mm.icl.hlc.HLCNotifier.HLCNotifier;
import mm.icl.hlc.HLCReasoner.HLCReasoner;
import mm.icl.hlc.OntologyTools.ContextOntology;
import mm.icl.hlc.OntologyTools.LowLevelContext;
import mm.icl.hlc.OntologyTools.NutritionContext;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;
import mm.icl.rest.RestClients;
import mm.icl.utils.FileUtil;
/**
 * Context Synchronizer: Subcomponent of the HLC Builder which aligns concurrent
 * low-level contexts of the same user.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-11-06
 */
public class ContextSynchronizer {
	private static Logger logger = Logger.getLogger(ContextSynchronizer.class);
	
	/**
	 * Context Ontology which represents the Mining Minds Context Model.
	 */
	private ContextOntology ont;
	HLCReasoner r;
	HLCNotifier n;
	ExecutorService es;
	
	private ContextHandler contextHandler = ContextHandler.getContextHandler();
	
	/**
	 * Context Instantiator which is which is in charge of generating the
	 * unclassified HLC instances.
	 */
	private ContextInstantiator instantiator;
	/**
	 * Constructor of a new Context Synchronizer.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 * @param instantiator
	 *            Context Instantiator which is in charge of generating the
	 *            unclassified HLC instances.
	 * 
	 */
	public ContextSynchronizer(ContextOntology ont, ContextInstantiator instantiator, HLCReasoner r, HLCNotifier n, ExecutorService es) {
		this.ont = ont;
		this.instantiator = instantiator;
		this.r = r;
		this.n = n;
	}
	/**
	 * Method to align concurrent LLC in a given time window.
	 * 
	 * @param windowStart
	 *            Calendar representing the start time of the window.
	 * @param windowEnd
	 *            Calendar representing the end time of the window.
	 */
	public void synchronizeLlcInWindow(Calendar windowStart, Calendar windowEnd) {
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		Literal start = model.createTypedLiteral(windowStart);
		Literal end = model.createTypedLiteral(windowEnd);
		synchronizeLlcInWindow(start, end);
	}
	/**
	 * Method to align concurrent LLC in a given time window.
	 * 
	 * @param windowStart
	 *            xsd:dateTime typed Literal representing the start time of the
	 *            window
	 * @param windowEnd
	 *            xsd:dateTime typed Literal representing the start time of the
	 *            window
	 */
	private void runReasonerNotifier(PhysicalActivityContext hlc){
		es.execute(new Runnable(){
			@Override
			public void run() {
				PhysicalActivityContext classifiedHlc = r.inferHlc(hlc);
				n.notify(classifiedHlc);
			}
		});
	}
	private void runReasonerNotifier(NutritionContext Nuthlc){
		es.execute(new Runnable(){
			@Override
			public void run() {
				NutritionContext     classifiedNutHlc = r.inferHlc(Nuthlc);		 
				n.notify(classifiedNutHlc);						
			}
		});
	}
	private void synchronizeLlcInWindow(Literal windowStart, Literal windowEnd) {
		List<LowLevelContext> llcStartList = contextHandler.retrieveLlcStartingWithinWindow(windowStart, windowEnd);
		List<LowLevelContext> llcEndList = contextHandler.retrieveLlcEndingWithinWindow(windowStart, windowEnd);
		Iterator<LowLevelContext> itStart = llcStartList.iterator();
		Iterator<LowLevelContext> itEnd = llcEndList.iterator();
		if (itStart.hasNext() && itEnd.hasNext()) {
			LowLevelContext llcStart = itStart.next();
			Date dateStart = ((XSDDateTime) llcStart.getDataPropertyValue(ont.getStartTimeProp()).getValue())
					.asCalendar().getTime();
			LowLevelContext llcEnd = itEnd.next();
			Date dateEnd = ((XSDDateTime) llcEnd.getDataPropertyValue(ont.getEndTimeProp()).getValue()).asCalendar()
					.getTime();
			boolean finished = false;
			while (!finished) {
				if (!dateStart.after(dateEnd)) {
					Literal endTimeLlcStart = llcStart.getDataPropertyValue(ont.getEndTimeProp());
					if (endTimeLlcStart == null
							|| !dateStart.equals(((XSDDateTime) endTimeLlcStart.getValue()).asCalendar().getTime())) {
						List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtStartTime(llcStart);
						PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
						NutritionContext Nuthlc =  instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);   
						runReasonerNotifier(hlc);
						runReasonerNotifier(Nuthlc);  
					}
					if (itStart.hasNext()) {
						llcStart = itStart.next();
						dateStart = ((XSDDateTime) llcStart.getDataPropertyValue(ont.getStartTimeProp()).getValue())
								.asCalendar().getTime();
					} else {
						List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);
						PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
						NutritionContext Nuthlc  =  instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);   
						runReasonerNotifier(hlc);
						runReasonerNotifier(Nuthlc);  
						finished = true;
					}
				}
				else {
					List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);
					PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
					NutritionContext Nuthlc  = instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);    
					runReasonerNotifier(hlc);
					runReasonerNotifier(Nuthlc);   
					if (itEnd.hasNext()) {
						llcEnd = itEnd.next();
						dateEnd = ((XSDDateTime) llcEnd.getDataPropertyValue(ont.getEndTimeProp()).getValue())
								.asCalendar().getTime();
					} else {
						Literal endTimeLlcStart = llcStart.getDataPropertyValue(ont.getEndTimeProp());
						if (endTimeLlcStart == null || !dateStart
								.equals(((XSDDateTime) endTimeLlcStart.getValue()).asCalendar().getTime())) {
							listLlcConc = contextHandler.retrieveConcurrentLlcAtStartTime(llcStart);
							hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
							Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);  
							runReasonerNotifier(hlc);
							runReasonerNotifier(Nuthlc);   
						}
						finished = true;
					}
				}
			}
		}
		while (itStart.hasNext()) {
			LowLevelContext llcStart = itStart.next();
			Date dateStart = ((XSDDateTime) llcStart.getDataPropertyValue(ont.getStartTimeProp()).getValue())
					.asCalendar().getTime();
			Literal endTimeLlcStart = llcStart.getDataPropertyValue(ont.getEndTimeProp());
			if (endTimeLlcStart == null
					|| !dateStart.equals(((XSDDateTime) endTimeLlcStart.getValue()).asCalendar().getTime())) {
				List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtStartTime(llcStart);
				PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
				NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc); 
				runReasonerNotifier(hlc);
				runReasonerNotifier(Nuthlc);   
			}
		}
		while (itEnd.hasNext()) {
			LowLevelContext llcEnd = itEnd.next();
			List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);
			PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
			NutritionContext Nuthlc =  instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);
			runReasonerNotifier(hlc);
			runReasonerNotifier(Nuthlc);  
		}
	}
	/**
	 * Test method that applies the same business logic than
	 * synchronizeLlcInWindow but prints messages through the standard output
	 * stream.
	 * 
	 * @param windowStart
	 *            Calendar representing the start time of the window.
	 * @param windowEnd
	 *            Calendar representing the end time of the window.
	 * @param r
	 *            PhysicalActivity and Nutrition Reasoner.
	 * @param n
	 *            PhysicalActivity and Nutrition Context Notifier.
	 */
	public void synchronizeTest(Calendar windowStart, Calendar windowEnd, HLCReasoner r, HLCNotifier n) {
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		Literal start = model.createTypedLiteral(windowStart);
		Literal end = model.createTypedLiteral(windowEnd);
		synchronizeTest(start, end, r, n);
	}
	/**
	 * Test method that applies the same business logic than
	 * synchronizeLlcInWindow but prints messages through the standard output
	 * stream.
	 * 
	 * @param windowStart
	 *            xsd:dateTime typed Literal representing the start time of the
	 *            window
	 * @param windowEnd
	 *            xsd:dateTime typed Literal representing the start time of the
	 *            window
	 * @param r
	 *            PhysicalActivity and Nutrition Context Reasoner.
	 * @param n
	 *            PhysicalActivity and Nutrition Context Notifier.
	 */
	private void synchronizeTest(Literal windowStart, Literal windowEnd, HLCReasoner r, HLCNotifier n) {
		List<LowLevelContext> llcStartList = null;
		List<LowLevelContext> llcEndList = null;
		try{
			if(contextHandler == null){
				logger.info("======================== NULL============");
			}
			llcStartList = contextHandler.retrieveLlcStartingWithinWindow(windowStart, windowEnd);
			llcEndList = contextHandler.retrieveLlcEndingWithinWindow(windowStart, windowEnd);
		}catch(Exception e){
			PrintWriter writer;
			try {
				writer = new PrintWriter("E:/ICL_LOG/errlog.txt", "UTF-8");
				writer.println(e.getMessage());
				writer.println(windowStart.toString() + " and " + windowEnd);
				e.printStackTrace(writer);
				writer.close();
				logger.error("Failed to synchornize HLC with following err.  Message: " + e.getMessage());
				e.printStackTrace();
				return;
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					logger.error("Error while Synchronizing.  Message: " + e1.getMessage());
					e1.printStackTrace();
				}

		}
		Iterator<LowLevelContext> itStart = llcStartList.iterator();
		Iterator<LowLevelContext> itEnd = llcEndList.iterator();
		if (itStart.hasNext() && itEnd.hasNext()) {
			LowLevelContext llcStart = itStart.next();
			Date dateStart = ((XSDDateTime) llcStart.getDataPropertyValue(ont.getStartTimeProp()).getValue())
					.asCalendar().getTime();
			LowLevelContext llcEnd = itEnd.next();
			Date dateEnd = ((XSDDateTime) llcEnd.getDataPropertyValue(ont.getEndTimeProp()).getValue()).asCalendar()
					.getTime();
			boolean finished = false;
			while (!finished) {
				if (!dateStart.after(dateEnd)) {
					Literal endTimeLlcStart = llcStart.getDataPropertyValue(ont.getEndTimeProp());
					if (endTimeLlcStart == null
							|| !dateStart.equals(((XSDDateTime) endTimeLlcStart.getValue()).asCalendar().getTime())) {
						List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtStartTime(llcStart);
						Iterator<LowLevelContext> itConc = listLlcConc.iterator();
						String concurrent = "";
						while (itConc.hasNext()) {
							LowLevelContext llcConc = itConc.next();
							concurrent += llcConc.getCtxInstanceLocalName();
							concurrent += " ";
						}
						logger.info("------------------------------------------------------------");
						logger.info("[Context Synchronizer] Instantiate new HLC for "
								+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
						+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
								+ ". Concurrent LLC: " + concurrent);
						logger.info("");
						logger.info("[Context Synchronizer] Instantiate new HLC for "
								+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
								+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
								+ ". Concurrent LLC: " + concurrent);
						
						
						PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
						NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);    

						PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
						NutritionContext  classifiedNutHlc = r.inferTest(Nuthlc);											 
						n.notifyTest(classifiedHlc);
						n.notifyTest(classifiedNutHlc);					
					} else {
						logger.info("");
						logger.info("[Context Synchronizer] Do not instantiate a new HLC for "
								+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
								+ " due to the start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
								+ ". This LLC has equal start and end time.");
						logger.info("------------------------------------------------------------");
						logger.info("[Context Synchronizer] Do not instantiate a new HLC for "
								+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
								+ " due to the start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
								+ ". This LLC has equal start and end time.");
					}
					if (itStart.hasNext()) {
						llcStart = itStart.next();
						dateStart = ((XSDDateTime) llcStart.getDataPropertyValue(ont.getStartTimeProp()).getValue())
								.asCalendar().getTime();
					} else {
						List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);
						Iterator<LowLevelContext> itConc = listLlcConc.iterator();
						String concurrent = "";
						while (itConc.hasNext()) {
							LowLevelContext llcConc = itConc.next();
							concurrent += llcConc.getCtxInstanceLocalName();
							concurrent += " ";
						}
						logger.info("");
						logger.info("[Context Synchronizer] Instantiate new HLC for "
								+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
								+ ". Trigger: end of " + llcEnd.getCtxInstanceLocalName() + " at " + dateEnd
								+ ". Concurrent LLC: " + concurrent);
						logger.info("------------------------------------------------------------");
						logger.info("[Context Synchronizer] Instantiate new HLC for "
								+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName() +  " ("+ llcStart.getCtxTypeName() +") "  
								+ ". Trigger: end of " + llcEnd.getCtxInstanceLocalName() + " at " + dateEnd
								+ ". Concurrent LLC: " + concurrent);
						PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
						NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);     
						// Invoke HLC Reasoner and Notifier
						PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
						NutritionContext classifiedNutHlc = r.inferTest(Nuthlc);      
						n.notifyTest(classifiedHlc);
						n.notifyTest(classifiedNutHlc);		
						finished = true;
					}
				}
				else {
					List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);
					Iterator<LowLevelContext> itConc = listLlcConc.iterator();
					String concurrent = "";
					while (itConc.hasNext()) {
						LowLevelContext llcConc = itConc.next();
						concurrent += llcConc.getCtxInstanceLocalName();
						concurrent += " ";
					}
					logger.info("");
					logger.info("[Context Synchronizer] Instantiate new HLC for "
							+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
							+ ". Trigger: end of " + llcEnd.getCtxInstanceLocalName() + " at " + dateEnd
							+ ". Concurrent LLC: " + concurrent);
					logger.info("------------------------------------------------------------");
					logger.info("[Context Synchronizer] Instantiate new HLC for "
							+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName() +  " ("+ llcStart.getCtxTypeName() +") "   
							+ ". Trigger: end of " + llcEnd.getCtxInstanceLocalName() + " at " + dateEnd
							+ ". Concurrent LLC: " + concurrent);
					PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd   (llcEnd, listLlcConc);
					NutritionContext Nuthlc     = instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);     
					// Invoke HLC Reasoner and Notifier
					PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
					NutritionContext classifiedNutHlc = r.inferTest(Nuthlc);     
					n.notifyTest(classifiedHlc);
					n.notifyTest(classifiedNutHlc);			
					if (itEnd.hasNext()) {
						llcEnd = itEnd.next();
						dateEnd = ((XSDDateTime) llcEnd.getDataPropertyValue(ont.getEndTimeProp()).getValue())
								.asCalendar().getTime();
					} else {
						Literal endTimeLlcStart = llcStart.getDataPropertyValue(ont.getEndTimeProp());
						if (endTimeLlcStart == null || !dateStart
								.equals(((XSDDateTime) endTimeLlcStart.getValue()).asCalendar().getTime())) {
							listLlcConc = contextHandler.retrieveConcurrentLlcAtStartTime(llcStart);
							itConc = listLlcConc.iterator();
							concurrent = "";
							while (itConc.hasNext()) {
								LowLevelContext llcConc = itConc.next();
								concurrent += llcConc.getCtxInstanceLocalName();
								concurrent += " ";
							}
							logger.info("");
							logger.info("[Context Synchronizer] Instantiate new HLC for "
									+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
									+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
									+ ". Concurrent LLC: " + concurrent);
							logger.info("------------------------------------------------------------");
							logger.info("[Context Synchronizer] Instantiate new HLC for "
									+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName() +  " ("+ llcStart.getCtxTypeName() +") "  
									+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
									+ ". Concurrent LLC: " + concurrent);
							hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
							Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);      
							classifiedHlc = r.inferTest(hlc);
							classifiedNutHlc = r.inferTest(Nuthlc); 
							n.notifyTest(classifiedHlc);
							n.notifyTest(classifiedNutHlc);	
						}
						else {
							logger.info("");
							logger.info("[Context Synchronizer] Do not instantiate a new HLC for "
									+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
									+ " due to the start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
									+ ". This LLC has equal start and end time.");
							logger.info("------------------------------------------------------------");
							logger.info("[Context Synchronizer] Do not instantiate a new HLC for "
									+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName() +  " ("+ llcStart.getCtxTypeName() +") "  
									+ " due to the start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
									+ ". This LLC has equal start and end time.");
						}
						finished = true;
					}
				}
			}
		}
		while (itStart.hasNext()) {
			LowLevelContext llcStart = itStart.next();
			Date dateStart = ((XSDDateTime) llcStart.getDataPropertyValue(ont.getStartTimeProp()).getValue())
					.asCalendar().getTime();
			Literal endTimeLlcStart = llcStart.getDataPropertyValue(ont.getEndTimeProp());
			if (endTimeLlcStart == null
					|| !dateStart.equals(((XSDDateTime) endTimeLlcStart.getValue()).asCalendar().getTime())) {
				List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtStartTime(llcStart);
				Iterator<LowLevelContext> itConc = listLlcConc.iterator();
				String concurrent = "";
				String   llc_Conc_Value = ""; 
    			String newLine = System.getProperty("line.separator");
				while (itConc.hasNext()) {
					LowLevelContext llcConc = itConc.next();
					concurrent += llcConc.getCtxInstanceLocalName();
					concurrent += " ";
				llc_Conc_Value += llcConc.getCtxTypeLocalName(); 
				llc_Conc_Value += " ";                         
				}
				logger.info("");
				logger.info("[Context Synchronizer] Instantiate new HLC for "
						+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
						+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
						+ ". Concurrent LLC: " + concurrent);
				logger.info("------------------------------------------------------------");
				logger.info("[Context Synchronizer ] Trigger: start of " + llcStart.getCtxInstanceLocalName()  
				+ " & Concurrent LLC: " + concurrent );   
				logger.info("[Context Synchronizer ] Trigger: start of " + llcStart.getCtxTypeLocalName()  
				+ " & Concurrent LLC: " + llc_Conc_Value + newLine);   
				PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc); 
				NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);   
				// Invoke HLC Reasoner and Notifier
				PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
				NutritionContext classifiedNutHlc = r.inferTest(Nuthlc);   
				n.notifyTest(classifiedHlc);								
				n.notifyTest(classifiedNutHlc);		 
			} else {
				logger.info("");
				logger.info("[Context Synchronizer] Do not instantiate a new HLC for "
						+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
						+ " due to the start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
						+ ". This LLC has equal start and end time.");
				logger.info("------------------------------------------------------------");
				logger.info("[Context Synchronizer] Do not instantiate a new HLC for "
						+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName() +  " ("+ llcStart.getCtxTypeName() +") "    
						+ " due to the start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart 
						+ ". This LLC has equal start and end time.");
			}
		}
		while (itEnd.hasNext()) {
			LowLevelContext llcEnd = itEnd.next();
			List<LowLevelContext> listLlcConc = contextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);
			Date dateEnd = ((XSDDateTime) llcEnd.getDataPropertyValue(ont.getEndTimeProp()).getValue()).asCalendar()
					.getTime();
			Iterator<LowLevelContext> itConc = listLlcConc.iterator();
			String concurrent = "";
			while (itConc.hasNext()) {
				LowLevelContext llcConc = itConc.next();
				concurrent += llcConc.getCtxInstanceLocalName();
				concurrent += " ";
			}
			logger.info("");
			logger.info("Instantiate new HLC for "
					+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName() + ". Trigger: end of "
					+ llcEnd.getCtxInstanceLocalName() + " at " + dateEnd + ". Concurrent LLC: " + concurrent);
			logger.info("------------------------------------------------------------");
			logger.info("Instantiate new HLC for "
					+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName() + ". Trigger: end of "
					+ llcEnd.getCtxInstanceLocalName() + " at " + dateEnd + ". Concurrent LLC: " + concurrent);
			PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
			NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);    
			PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
			NutritionContext     classifiedNutHlc = r.inferTest(Nuthlc);    
			n.notifyTest(classifiedHlc);
			n.notifyTest(classifiedNutHlc);			
		}
	}
	
	public ContextHandler getContextHandler() {
		return contextHandler;
	}
	public void setContextHandler(ContextHandler contextHandler) {
		this.contextHandler = contextHandler;
	}
}
