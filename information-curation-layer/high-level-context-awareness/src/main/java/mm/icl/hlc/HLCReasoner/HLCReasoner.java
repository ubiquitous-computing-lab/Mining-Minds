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
package mm.icl.hlc.HLCReasoner;
import java.util.Iterator;

import mm.icl.hlc.OntologyTools.Context;
import mm.icl.hlc.OntologyTools.InferredContextOntology;
import mm.icl.hlc.OntologyTools.NutritionContext;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;
import mm.icl.utils.FileUtil;
import org.apache.log4j.Logger;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
/**
 * HLC Reasoner: Component of HLCA which performs a consistency check on the
 * unclassified PhysicalActivity and Nutrition Context instance and classifies it in order to
 * identify the context type to which the instance belongs.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class HLCReasoner extends AbstractReasoner{
	
	private static Logger logger = Logger.getLogger(HLCReasoner.class);
	/**
	 * Context Classifier which is a subcomponent of the HLC Reasoner.
	 */
	private ContextClassifier cc;
	/**
	 * Context Classifier which is a subcomponent of the HLC Reasoner.
	 */
	private ContextVerifier cv;
	/**
	 * Constructor of a new HLC Reasoner.
	 * 
	 * @param ont
	 *            Inferred version of the Context Ontology which represents the
	 *            Mining Minds Context Model.
	 */
	
	private Context context;
	public HLCReasoner(InferredContextOntology ont) {
		this.cc = new ContextClassifier(ont);
		this.cv = new ContextVerifier(ont);
	}
	/**
	 * Method to infer PhysicalActivity Context, i.e., to perform a consistency check
	 * on the unclassified PhysicalActivity Context instance and in case it is valid,
	 * to identify the context type to which the instance belongs.
	 * 
	 * @param unclassified
	 *            Unclassified PhysicalActivity Context instance.
	 * @return Classified PhysicalActivity Context instance.
	 */
	public PhysicalActivityContext inferHlc(PhysicalActivityContext unclassified) {
		if (cv.isValid(unclassified))
			return cc.classify(unclassified);
		return unclassified;
	}
	/**
	 * Method to infer Nutrition Context, i.e., to perform a consistency check
	 * on the unclassified Nutrition Context instance and in case it is valid,
	 * to identify the context type to which the instance belongs.
	 * 
	 * @param unclassified
	 *            Unclassified Nutrition Context instance.
	 * @return Classified Nutrition Context instance.
	 */
	public NutritionContext inferHlc(NutritionContext unclassified) {
		if (cv.isValid(unclassified))
			return cc.classify(unclassified);
		return unclassified;
	}
	/**
	 * Test method that applies the same business logic than inferHlc but prints
	 * messages through the standard output stream.
	 * 
	 * @param unclassified
	 *            Unclassified PhysicalActivity Context instance.
	 * @return Classified PhysicalActivity Context instance.
	 */
	public PhysicalActivityContext inferTest(PhysicalActivityContext unclassified) {
		ValidityReport report = cv.validate(unclassified);
		if (report.isValid()) {
			PhysicalActivityContext classified = cc.classify(unclassified);
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classified as " 
					+ classified.getCtxTypeLocalName());
			logger.info ("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classified as "
					+ classified.getCtxTypeLocalName()); 
			return classified;
		} else {
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] The HLC is not valid."); 
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] The HLC is not valid.");   
			Iterator<Report> i = report.getReports();
			while (i.hasNext())
				logger.info(i.next());
			return unclassified;
		}
	}
	/**
	 * Test method that applies the same business logic than inferHlc but prints
	 * messages through the standard output stream.
	 * 
	 * @param unclassified
	 *            Unclassified Nutrition Context instance.
	 * @return Classified Nutrition Context instance.
	 */
	public NutritionContext inferTest(NutritionContext unclassified) {
		ValidityReport report = cv.validate(unclassified);
		if (report.isValid()) {
			NutritionContext classified = cc.classify(unclassified);
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classified as " 
					+ classified.getCtxTypeLocalName());
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classified as "
					+ classified.getCtxTypeLocalName());
			return classified;
		} else {
			logger.info("[HLC Reasoner: NutritionContext: ] The NutritionContext HLC is not valid.");  
			logger.info("[HLC Reasoner: NutritionContext: ] The NutritionContext HLC is not valid.");
			Iterator<Report> i = report.getReports();
			while (i.hasNext())
				logger.info(i.next());
			return unclassified;
		}
	}
	/**
	 * Test method that applies the same business logic than inferHlc but
	 * calculates the elapsed time and prints messages through the standard
	 * output stream.
	 * 
	 * @param unclassified
	 *            Unclassified PhysicalActivity Context instance.
	 * @return Classified PhysicalActivity Context instance.
	 */
	public PhysicalActivityContext inferTestElapsedTime(PhysicalActivityContext unclassified) {
		long s1 = System.currentTimeMillis();
		ValidityReport report = cv.validate(unclassified);
		long e1 = System.currentTimeMillis();
		logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + unclassified.getCtxInstanceLocalName() + " validation lasted "
				+ (e1 - s1) + " ms.");
		logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + unclassified.getCtxInstanceLocalName() + " validation lasted "
				+ (e1 - s1) + " ms.");  //FileUtil.WriteHLCLog
		if (report.isValid()) {
			long s2 = System.currentTimeMillis();
			PhysicalActivityContext classified = cc.classify(unclassified);
			long e2 = System.currentTimeMillis();
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classification lasted "
					+ (e2 - s2) + " ms.");
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classification lasted "
					+ (e2 - s2) + " ms.");//FileUtil.WriteHLCLog
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName()
					+ " HLC Reasoner overall process lasted " + (e2 - s1) + " ms.");
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName()
			+ " HLC Reasoner overall process lasted " + (e2 - s1) + " ms.");
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classified as "
					+ classified.getCtxTypeLocalName());
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classified as "
					+ classified.getCtxTypeLocalName());
			return classified;
		} else {
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] The HLC is not valid.");
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] The HLC is not valid.");
			Iterator<Report> i = report.getReports();
			while (i.hasNext()){
				logger.info(i.next());
				logger.info(i.next().toString());  
			}
			return unclassified;
		}
	}
	/**
	 * Test method that applies the same business logic than inferHlc but
	 * calculates the elapsed time and prints messages through the standard
	 * output stream.
	 * 
	 * @param unclassified
	 *            Unclassified Nutrition Context instance.
	 * @return Classified Nutrition Context instance.
	 */
	public NutritionContext inferTestElapsedTime(NutritionContext unclassified) {
		long s1 = System.currentTimeMillis();
		ValidityReport report = cv.validate(unclassified);
		long e1 = System.currentTimeMillis();
		logger.info("[HLC Reasoner: NutritionContext: ] " + unclassified.getCtxInstanceLocalName() + " validation lasted "
				+ (e1 - s1) + " ms.");
		logger.info("[HLC Reasoner: NutritionContext: ] " + unclassified.getCtxInstanceLocalName() + " validation lasted "
				+ (e1 - s1) + " ms.");
		if (report.isValid()) {
			long s2 = System.currentTimeMillis();
			NutritionContext classified = cc.classify(unclassified);
			long e2 = System.currentTimeMillis();
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classification lasted "
					+ (e2 - s2) + " ms.");
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classification lasted "
					+ (e2 - s2) + " ms.");
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName()
					+ " HLC Reasoner overall process lasted " + (e2 - s1) + " ms.");
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName()
			+ " HLC Reasoner overall process lasted " + (e2 - s1) + " ms.");
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classified as "
					+ classified.getCtxTypeLocalName());
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classified as "
					+ classified.getCtxTypeLocalName());
			return classified;
		} else {
			logger.info("[HLC Reasoner: NutritionContext: ] The NutritionContext is not valid.");
			logger.info("[HLC Reasoner: NutritionContext: ] The NutritionContext is not valid.");
			Iterator<Report> i = report.getReports();
			while (i.hasNext()){
				logger.info(i.next());
				logger.info(i.next().toString());
			}
			return unclassified;
		}
	}
}
