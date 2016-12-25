/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.HLCBuilder;

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
import org.uclab.mm.icl.hlc.ContextOntologyManager.ContextHandler;
import org.uclab.mm.icl.hlc.HLCNotifier.HLCNotifier;
import org.uclab.mm.icl.hlc.HLCReasoner.HLCReasoner;
import org.uclab.mm.icl.hlc.OntologyTools.ContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.HighLevelContext;
import org.uclab.mm.icl.hlc.OntologyTools.LowLevelContext;
import org.uclab.mm.icl.hlc.OntologyTools.NutritionContext;
import org.uclab.mm.icl.hlc.OntologyTools.PhysicalActivityContext;
import org.uclab.mm.icl.utils.FileUtil;

import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Context Synchronizer: Subcomponent of the HLC Builder which aligns concurrent
 * low-level contexts of the same user.
 * 
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-11-06
 */
public class ContextSynchronizer {
	private final static Logger logger = Logger.getLogger(ContextMapper.class);
	/**
	 * Context Ontology which represents the Mining Minds Context Model.
	 */
	private ContextOntology ont;
	HLCReasoner r;
	HLCNotifier n;
	ExecutorService es;
	
	
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
	
	
	
	//////////*************************   MMV2.5 to Handler NutritionContext changes are made Start **********/////////////////////////////////////
	
	private void runReasonerNotifier(NutritionContext Nuthlc){
		es.execute(new Runnable(){
			@Override
			public void run() {

				NutritionContext     classifiedNutHlc = r.inferHlc(Nuthlc);											// MM V2.5 for Nutrition Context (High Level) Additionallly 
				

				n.notify(classifiedNutHlc);							// MM V2.5 for Nutrition Context (High Level) Additionallly
				
				
				
			}
			
		});
		
	}
	
	
	
	
	
	
	//////////*************************   MMV2.5 to Handler NutritionContext changes are made End **********/////////////////////////////////////
	
	
	
	private void synchronizeLlcInWindow(Literal windowStart, Literal windowEnd) {

		List<LowLevelContext> llcStartList = ContextHandler.retrieveLlcStartingWithinWindow(windowStart, windowEnd);
		List<LowLevelContext> llcEndList = ContextHandler.retrieveLlcEndingWithinWindow(windowStart, windowEnd);

		
		
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

						List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtStartTime(llcStart);

						PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
						NutritionContext Nuthlc =  instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);     // MM V2.5 for Nutrition Context (High Level) Additionallly 
						
						
						// TODO call HLC Reasoner and Notifier
						runReasonerNotifier(hlc);
						runReasonerNotifier(Nuthlc);  // MM V2.5 for Nutrition Context (High Level) Additionallly 
					}

					if (itStart.hasNext()) {

						llcStart = itStart.next();
						dateStart = ((XSDDateTime) llcStart.getDataPropertyValue(ont.getStartTimeProp()).getValue())
								.asCalendar().getTime();

					} else {

						List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);

						PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
						NutritionContext Nuthlc  =  instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);     // MM V2.5 for Nutrition Context (High Level) Additionallly 
						// TODO call HLC Reasoner and Notifier
						
						runReasonerNotifier(hlc);
						runReasonerNotifier(Nuthlc);  // MM V2.5 for Nutrition Context (High Level) Additionallly 

						finished = true;
					}

				}

				else {

					List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);

					PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
					NutritionContext Nuthlc  = instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);    // MM V2.5 for Nutrition Context (High Level) Additionallly 

					// TODO call HLC Reasoner and Notifier
					runReasonerNotifier(hlc);
					runReasonerNotifier(Nuthlc);  // MM V2.5 for Nutrition Context (High Level) Additionallly 
					
					
					if (itEnd.hasNext()) {

						llcEnd = itEnd.next();
						dateEnd = ((XSDDateTime) llcEnd.getDataPropertyValue(ont.getEndTimeProp()).getValue())
								.asCalendar().getTime();

					} else {

						Literal endTimeLlcStart = llcStart.getDataPropertyValue(ont.getEndTimeProp());

						if (endTimeLlcStart == null || !dateStart
								.equals(((XSDDateTime) endTimeLlcStart.getValue()).asCalendar().getTime())) {

							listLlcConc = ContextHandler.retrieveConcurrentLlcAtStartTime(llcStart);

							hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
							Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc); // MM V2.5 for Nutrition Context (High Level) Additionallly 

							// TODO call HLC Reasoner and Notifier
							runReasonerNotifier(hlc);
							runReasonerNotifier(Nuthlc);  // MM V2.5 for Nutrition Context (High Level) Additionallly 
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

				List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtStartTime(llcStart);

				PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
				NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);   ///MMV2.5
				
				
				// TODO call HLC Reasoner and Notifier
				runReasonerNotifier(hlc);
				runReasonerNotifier(Nuthlc);  // MM V2.5 for Nutrition Context (High Level) Additionallly 

			}

		}

		while (itEnd.hasNext()) {

			LowLevelContext llcEnd = itEnd.next();

			List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);

			PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
			NutritionContext Nuthlc =  instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);

			// TODO call HLC Reasoner and Notifier
			runReasonerNotifier(hlc);
			runReasonerNotifier(Nuthlc);  // MM V2.5 for Nutrition Context (High Level) Additionallly 

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
	 *            High Level Context Reasoner.
	 * @param n
	 *            High Level Context Notifier.
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
	 *            High Level Context Reasoner.
	 * @param n
	 *            High Level Context Notifier.
	 */
	private void synchronizeTest(Literal windowStart, Literal windowEnd, HLCReasoner r, HLCNotifier n) {

		List<LowLevelContext> llcStartList = null;
		List<LowLevelContext> llcEndList = null;
		try{
						
			llcStartList = ContextHandler.retrieveLlcStartingWithinWindow(windowStart, windowEnd);
			
			llcEndList = ContextHandler.retrieveLlcEndingWithinWindow(windowStart, windowEnd);
			
			
		}catch(Exception e){
			logger.error("Exception occured at synchronizing high lvl context");
			logger.error(e);
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

						List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtStartTime(llcStart);

						Iterator<LowLevelContext> itConc = listLlcConc.iterator();

						String concurrent = "";

						while (itConc.hasNext()) {

							LowLevelContext llcConc = itConc.next();
							concurrent += llcConc.getCtxInstanceLocalName();
							concurrent += " ";

						}

						logger.info("[Context Synchronizer] Instantiate new HLC for "
								+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
						+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart 	//Asif MM V2.5	
						+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " ("+ llcStart.getCtxTypeName() +") " +  " at " + dateStart  //Asif MM2.5								
								+ ". Concurrent LLC: " + concurrent);
						
						PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
						NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);     // MM V2.5 for Nutrition Context (High Level) Additionallly 
//						System.out.println("I am in Context Synchronizer for NutritionContext L387");
//						System.out.println(Nuthlc);                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 
						// Invoke HLC Reasoner and Notifier
						PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
						NutritionContext  classifiedNutHlc = r.inferTest(Nuthlc);											// MM V2.5 for Nutrition Context (High Level) Additionallly 
						
						n.notifyTest(classifiedHlc);
						n.notifyTest(classifiedNutHlc);							// MM V2.5 for Nutrition Context (High Level) Additionallly
						
					} else {
						
						logger.error("[Context Synchronizer] Do not instantiate a new HLC for "
								+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
								+ " due to the start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
								+ ". This LLC has equal start and end time.");
					}

					if (itStart.hasNext()) {

						llcStart = itStart.next();
						dateStart = ((XSDDateTime) llcStart.getDataPropertyValue(ont.getStartTimeProp()).getValue())
								.asCalendar().getTime();

					} else {

						List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);

						Iterator<LowLevelContext> itConc = listLlcConc.iterator();

						String concurrent = "";

						while (itConc.hasNext()) {

							LowLevelContext llcConc = itConc.next();
							concurrent += llcConc.getCtxInstanceLocalName();
							concurrent += " ";

						}
						
						logger.info("[Context Synchronizer] Instantiate new HLC for "
								+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
								+ ". Trigger: end of " + llcEnd.getCtxInstanceLocalName() + " at " + dateEnd
								+ ". Concurrent LLC: " + concurrent);
						

						
						
						PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
						NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);    // MM V2.5 for Nutrition Context (High Level) Additionallly 
//						System.out.println("I am in Context Synchronizer for NutritionContext L442");
//						System.out.println(Nuthlc);                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 
						
						// Invoke HLC Reasoner and Notifier
						PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
						NutritionContext classifiedNutHlc = r.inferTest(Nuthlc);                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 
						
						
						n.notifyTest(classifiedHlc);
						n.notifyTest(classifiedNutHlc);							// MM V2.5 for Nutrition Context (High Level) Additionallly
						
						finished = true;
					}

				}

				else {

					List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);

					Iterator<LowLevelContext> itConc = listLlcConc.iterator();

					String concurrent = "";

					while (itConc.hasNext()) {

						LowLevelContext llcConc = itConc.next();
						concurrent += llcConc.getCtxInstanceLocalName();
						concurrent += " ";

					}
					
					logger.info("[Context Synchronizer] Instantiate new HLC for "
							+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
							+ ". Trigger: end of " + llcEnd.getCtxInstanceLocalName() + " at " + dateEnd
							+ ". Concurrent LLC: " + concurrent);
					
					PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd   (llcEnd, listLlcConc);
					NutritionContext Nuthlc     = instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);     // MM V2.5 for Nutrition Context (High Level) Additionallly 
//					System.out.println("I am in Context Synchronizer for NutritionContext L487");
//					System.out.println(Nuthlc);                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 

					
					

					// Invoke HLC Reasoner and Notifier
					PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
					NutritionContext classifiedNutHlc = r.inferTest(Nuthlc);                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 

					n.notifyTest(classifiedHlc);
					n.notifyTest(classifiedNutHlc);							// MM V2.5 for Nutrition Context (High Level) Additionallly
					
					
					if (itEnd.hasNext()) {

						llcEnd = itEnd.next();
						dateEnd = ((XSDDateTime) llcEnd.getDataPropertyValue(ont.getEndTimeProp()).getValue())
								.asCalendar().getTime();

					} else {

						Literal endTimeLlcStart = llcStart.getDataPropertyValue(ont.getEndTimeProp());

						if (endTimeLlcStart == null || !dateStart
								.equals(((XSDDateTime) endTimeLlcStart.getValue()).asCalendar().getTime())) {

							listLlcConc = ContextHandler.retrieveConcurrentLlcAtStartTime(llcStart);

							itConc = listLlcConc.iterator();

							concurrent = "";

							while (itConc.hasNext()) {

								LowLevelContext llcConc = itConc.next();
								concurrent += llcConc.getCtxInstanceLocalName();
								concurrent += " ";

							}
							
							logger.info("[Context Synchronizer] Instantiate new HLC for "
									+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
									+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
									+ ". Concurrent LLC: " + concurrent);
							
							hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);
							Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);     // MM V2.5 for Nutrition Context (High Level) Additionallly 

							//							System.out.println("I am in Context Synchronizer for NutritionContext L387");
//							System.out.println(Nuthlc);       // MM V2.5 for Nutrition Context (High Level) Additionallly 

							
							
							// Invoke HLC Reasoner and Notifier
							classifiedHlc = r.inferTest(hlc);
							classifiedNutHlc = r.inferTest(Nuthlc); // MM V2.5 for Nutrition Context (High Level) Additionallly
							
							n.notifyTest(classifiedHlc);
							n.notifyTest(classifiedNutHlc);							// MM V2.5 for Nutrition Context (High Level) Additionallly
							
						}

						else {
							
							logger.error("[Context Synchronizer] Do not instantiate a new HLC for "
									+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
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

				List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtStartTime(llcStart);

				Iterator<LowLevelContext> itConc = listLlcConc.iterator();

				String concurrent = "";
				String   llc_Conc_Value = "";     //Asif MMV2.5
    			String newLine = System.getProperty("line.separator");//This will retrieve line separator dependent on OS.        //Asif MMV2.5

				while (itConc.hasNext()) {

					LowLevelContext llcConc = itConc.next();
					concurrent += llcConc.getCtxInstanceLocalName();
					concurrent += " ";

					
/*				System.out.println("ContextSynch Line 582");	
				System.out.println(llcStart.getCtxTypeLocalName() + " & Concurrent 2.5 : " +"="+  llcConc.getCtxTypeLocalName() );      /////////Asif MMV2.5
*/
				llc_Conc_Value += llcConc.getCtxTypeLocalName(); ///Asif MMV2.5
				llc_Conc_Value += " ";                         ///Asif MMV2.5
				
				}
				
				logger.info("[Context Synchronizer] Instantiate new HLC for "
						+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
						+ ". Trigger: start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
						+ ". Concurrent LLC: " + concurrent);


///Asif MM V2.5  System.out.println("[Context Synchronizer getCtxInstanceLocalName] Trigger: start of " + llcStart.getCtxInstanceLocalName()
				
				logger.info("[Context Synchronizer ] Trigger: start of " + llcStart.getCtxInstanceLocalName()  
				+ " & Concurrent LLC: " + concurrent );         ////////Asif + " + "+  llc_Conc_Value    MM V2.5;
				logger.info("[Context Synchronizer ] Trigger: start of " + llcStart.getCtxTypeLocalName()  
				+ " & Concurrent LLC: " + llc_Conc_Value + newLine);         ////////Asif + " + "+  llc_Conc_Value    MM V2.5
///Asif MM V2.5  System.out.println("[Context Synchronizer getCtxTypeLocalName] Trigger: start of " + llcStart.getCtxTypeLocalName()
	
				/*	Asif MMV2.5  see above two lines
 *  
 				System.out.println("[Context Synchronizer] Instantiate new HLC for "
						+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()   //+ newLine   Asif MMV2.5 
						+ ". Trigger: start of " + newLine + llcStart.getCtxInstanceLocalName() +  " ("+ llcStart.getCtxTypeLocalName() +") " +" at " + dateStart     //" ("+ llcStart.getCtxTypeName() +") " Added Asif MMV2.5
						+ ". Concurrent LLC: " + concurrent + " + "+  llc_Conc_Value );         ////////Asif + " + "+  llc_Conc_Value    MM V2.5
*/ 
				PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcStart(llcStart, listLlcConc);    //Context instantiator works perfect for Activity, Emotion and Locations
				NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcStart(llcStart, listLlcConc);     // MM V2.5 for Nutrition Context (High Level) Additionallly 

			
				// Invoke HLC Reasoner and Notifier
				PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
				NutritionContext classifiedNutHlc = r.inferTest(Nuthlc);                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 

				
//				System.out.println("I am in Context Synchronizer for NutritionContext classifiedHlc & classifiedNutHlc L633");
//				System.out.println(classifiedHlc.getCtxInstanceLocalName() +" & " +classifiedNutHlc.getCtxInstanceLocalName());                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 
				
				n.notifyTest(classifiedHlc);								
				n.notifyTest(classifiedNutHlc);							// MM V2.5 for Nutrition Context (High Level) Additionallly 
				
				

			} else {
				logger.error("[Context Synchronizer] Do not instantiate a new HLC for "
						+ llcStart.getObjectPropertyValue(ont.getContextOfProp()).getLocalName()
						+ " due to the start of " + llcStart.getCtxInstanceLocalName() + " at " + dateStart
						+ ". This LLC has equal start and end time.");
			}

		}

		while (itEnd.hasNext()) {

			LowLevelContext llcEnd = itEnd.next();

			List<LowLevelContext> listLlcConc = ContextHandler.retrieveConcurrentLlcAtEndTime(llcEnd);

			Date dateEnd = ((XSDDateTime) llcEnd.getDataPropertyValue(ont.getEndTimeProp()).getValue()).asCalendar()
					.getTime();

			Iterator<LowLevelContext> itConc = listLlcConc.iterator();

			String concurrent = "";

			while (itConc.hasNext()) {

				LowLevelContext llcConc = itConc.next();
				concurrent += llcConc.getCtxInstanceLocalName();
				concurrent += " ";

			}
			logger.info("Instantiate new HLC for "
					+ llcEnd.getObjectPropertyValue(ont.getContextOfProp()).getLocalName() + ". Trigger: end of "
					+ llcEnd.getCtxInstanceLocalName() + " at " + dateEnd + ". Concurrent LLC: " + concurrent);
			
			
			PhysicalActivityContext hlc = instantiator.instantiateNewHlcDueToLlcEnd(llcEnd, listLlcConc);
			
			NutritionContext Nuthlc = instantiator.instantiateNewNutHlcDueToLlcEnd(llcEnd, listLlcConc);     // MM V2.5 for Nutrition Context (High Level) Additionallly 
			//System.out.println("I am in Context Synchronizer for NutritionContext L633");
			//System.out.println(Nuthlc);                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 



			// Invoke HLC Reasoner and Notifier
			PhysicalActivityContext classifiedHlc = r.inferTest(hlc);
			NutritionContext     classifiedNutHlc = r.inferTest(Nuthlc);                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 

//			System.out.println("I am in Context Synchronizer for NutritionContext classifiedHlc & classifiedNutHlc L714");
//			System.out.println(classifiedHlc.getCtxInstanceLocalName() +" & " + classifiedNutHlc.getCtxInstanceLocalName());                      													// MM V2.5 for Nutrition Context (High Level) Additionallly 
			
			
			n.notifyTest(classifiedHlc);
			n.notifyTest(classifiedNutHlc);							// MM V2.5 for Nutrition Context (High Level) Additionallly			
			

			
			
			
		}

	}

}
