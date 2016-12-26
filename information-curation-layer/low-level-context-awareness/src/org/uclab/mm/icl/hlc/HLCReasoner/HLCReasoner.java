/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.HLCReasoner;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.uclab.mm.icl.hlc.HLCNotifier.HLCNotifier;
import org.uclab.mm.icl.hlc.OntologyTools.InferredContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.NutritionContext;
import org.uclab.mm.icl.hlc.OntologyTools.PhysicalActivityContext;
import org.uclab.mm.icl.utils.FileUtil;

import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;

/**
 * HLC Reasoner: Component of HLCA which performs a consistency check on the
 * unclassified High Level Context instance and classifies it in order to
 * identify the context type to which the instance belongs.
 * 
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-10-28
 */
public class HLCReasoner {
	
	private final static Logger logger = Logger.getLogger(HLCReasoner.class);
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
	public HLCReasoner(InferredContextOntology ont) {

		this.cc = new ContextClassifier(ont);
		this.cv = new ContextVerifier(ont);

	}

	/**
	 * Method to infer High Level Context, i.e., to perform a consistency check
	 * on the unclassified High Level Context instance and in case it is valid,
	 * to identify the context type to which the instance belongs.
	 * 
	 * @param unclassified
	 *            Unclassified High Level Context instance.
	 * @return Classified High Level Context instance.
	 */
	public PhysicalActivityContext inferHlc(PhysicalActivityContext unclassified) {

		if (cv.isValid(unclassified))
			return cc.classify(unclassified);

		return unclassified;
	}


	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly Start ********************************
	/**
	 * Method to infer High Level Context, i.e., to perform a consistency check
	 * on the unclassified High Level Context instance and in case it is valid,
	 * to identify the context type to which the instance belongs.
	 * 
	 * @param unclassified
	 *            Unclassified High Level Context instance.
	 * @return Classified High Level Context instance.
	 */
	public NutritionContext inferHlc(NutritionContext unclassified) {

		if (cv.isValid(unclassified))
			return cc.classify(unclassified);

		return unclassified;
	}

	
	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly End ********************************

	
	
	
	
	/**
	 * Test method that applies the same business logic than inferHlc but prints
	 * messages through the standard output stream.
	 * 
	 * @param unclassified
	 *            Unclassified High Level Context instance.
	 * @return Classified High Level Context instance.
	 */
	public PhysicalActivityContext inferTest(PhysicalActivityContext unclassified) {

		ValidityReport report = cv.validate(unclassified);

		
/*		
		System.out.println("I am here in Reasoner");  //Asif V2.5
		System.out.println(report);  //Asif V2.5
		System.out.println(unclassified.getCtxIndiv());  //Asif V2.5 Null
		System.out.println(report.equals(getClass()));  //Asif V2.5     False
		
		*/
		
		
		if (report.isValid()) {

			
			PhysicalActivityContext classified = cc.classify(unclassified);
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classified as "  /////MMV2.5
					+ classified.getCtxTypeLocalName());
			
			
			
/*			System.out.println("I am in Reasoner and returning  Classified");  //Asif V2.5
			System.out.println(classified.getCtxTypeLocalName() );  //Asif V2.5
*/			
			return classified;

		} else {
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] The HLC is not valid.");  //MMV2.5
			
			Iterator<Report> i = report.getReports();
			while (i.hasNext()){
				logger.info("[HLC Reasoner: PhysicalActivityContext: ] The HLC is not valid.");
			}

			return unclassified;
		}

	}
	
	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly Start ********************************

	
	/**
	 * Test method that applies the same business logic than inferHlc but prints
	 * messages through the standard output stream.
	 * 
	 * @param unclassified
	 *            Unclassified High Level Context instance.
	 * @return Classified High Level Context instance.
	 */
	public NutritionContext inferTest(NutritionContext unclassified) {

		ValidityReport report = cv.validate(unclassified);

		
/*		
		System.out.println("I am here in Reasoner");  //Asif V2.5
		System.out.println(report);  //Asif V2.5
		System.out.println(unclassified.getCtxIndiv());  //Asif V2.5 Null
		System.out.println(report.equals(getClass()));  //Asif V2.5     False
		
		*/
		
		
		if (report.isValid()) {

			
			NutritionContext classified = cc.classify(unclassified);
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classified as " //MMV2.5
					+ classified.getCtxTypeLocalName());
			
			
/*			System.out.println("I am in Reasoner and returning  Classified");  //Asif V2.5
			System.out.println(classified.getCtxTypeLocalName() );  //Asif V2.5
*/			
			return classified;

		} else {
			logger.info("[HLC Reasoner: NutritionContext: ] The NutritionContext HLC is not valid.");  //MMV2.5
			Iterator<Report> i = report.getReports();

			while (i.hasNext())
				logger.info(i.next());

			return unclassified;
		}

	}
	
	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly End ********************************

	
	

	/**
	 * Test method that applies the same business logic than inferHlc but
	 * calculates the elapsed time and prints messages through the standard
	 * output stream.
	 * 
	 * @param unclassified
	 *            Unclassified High Level Context instance.
	 * @return Classified High Level Context instance.
	 */
	public PhysicalActivityContext inferTestElapsedTime(PhysicalActivityContext unclassified) {

		long s1 = System.currentTimeMillis();

		ValidityReport report = cv.validate(unclassified);

		long e1 = System.currentTimeMillis();

		logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + unclassified.getCtxInstanceLocalName() + " validation lasted "
				+ (e1 - s1) + " ms.");
		
		if (report.isValid()) {

			long s2 = System.currentTimeMillis();

			PhysicalActivityContext classified = cc.classify(unclassified);

			long e2 = System.currentTimeMillis();

			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classification lasted "
					+ (e2 - s2) + " ms.");
			
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName()
					+ " HLC Reasoner overall process lasted " + (e2 - s1) + " ms.");
			
			logger.info("[HLC Reasoner: PhysicalActivityContext: ] " + classified.getCtxInstanceLocalName() + " classified as "
					+ classified.getCtxTypeLocalName());
			
			return classified;

		} else {

			logger.info("[HLC Reasoner: PhysicalActivityContext: ] The HLC is not valid.");
			Iterator<Report> i = report.getReports();

			while (i.hasNext()){
				logger.info(i.next());
			}
			return unclassified;
		}

	}
	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly Start ********************************
	


	/**
	 * Test method that applies the same business logic than inferHlc but
	 * calculates the elapsed time and prints messages through the standard
	 * output stream.
	 * 
	 * @param unclassified
	 *            Unclassified High Level Context instance.
	 * @return Classified High Level Context instance.
	 */
	public NutritionContext inferTestElapsedTime(NutritionContext unclassified) {

		long s1 = System.currentTimeMillis();

		ValidityReport report = cv.validate(unclassified);

		long e1 = System.currentTimeMillis();

		logger.info("[HLC Reasoner: NutritionContext: ] " + unclassified.getCtxInstanceLocalName() + " validation lasted "
				+ (e1 - s1) + " ms.");
		if (report.isValid()) {

			long s2 = System.currentTimeMillis();

			NutritionContext classified = cc.classify(unclassified);

			long e2 = System.currentTimeMillis();

			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classification lasted "
					+ (e2 - s2) + " ms.");
			
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName()
					+ " HLC Reasoner overall process lasted " + (e2 - s1) + " ms.");
			
			logger.info("[HLC Reasoner: NutritionContext: ] " + classified.getCtxInstanceLocalName() + " classified as "
					+ classified.getCtxTypeLocalName());
			return classified;

		} else {

			logger.info("[HLC Reasoner: NutritionContext: ] The NutritionContext is not valid.");
			Iterator<Report> i = report.getReports();

			while (i.hasNext()){
				logger.info(i.next());
			}
			return unclassified;
		}

	}
	// ***********************************MM V2.5 for Nutrition Context (High Level) Additionallly End ********************************

	
	
	
}
