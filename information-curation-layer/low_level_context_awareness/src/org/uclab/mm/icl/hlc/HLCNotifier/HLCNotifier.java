/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.hlc.HLCNotifier;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.uclab.mm.icl.hlc.ContextOntologyManager.ContextHandler;
import org.uclab.mm.icl.hlc.HLCBuilder.ContextMapper;
import org.uclab.mm.icl.hlc.OntologyTools.ContextOntology;
import org.uclab.mm.icl.hlc.OntologyTools.NutritionContext;
import org.uclab.mm.icl.hlc.OntologyTools.PhysicalActivityContext;
import org.uclab.mm.icl.llc.LLCManager.LLCNotifier;
import org.uclab.mm.icl.llc.restservices.RestClients;
import org.uclab.mm.icl.utils.FileUtil;
import org.uclab.mm.icl.utils.TimeUtil;

import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.rdf.model.Literal;

/**
 * HLC Notifier: Component of HLCA which communicates the newly recognized High
 * Level Context instance to Data Curation Layer and persists it into the
 * Context Ontology Storage.
 * 
 * @author Claudia Villalonga
 * @version 2.0
 * @since 2015-10-28
 */
public class HLCNotifier {
	private final static Logger logger = Logger.getLogger(HLCNotifier.class);
	
	TimeUtil tutil = new TimeUtil();
	/**
	 * Context Ontology which represents the Mining Minds Context Model.
	 */
	private ContextOntology ont;

	/**
	 * Constructor of a new HLC Notifier.
	 * 
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 */
	public HLCNotifier(ContextOntology ont) {
		this.ont = ont;

	}

	/**
	 * Method that receives the newly classified High Level Context instance,
	 * stores it into the Context Ontology Storage, and if the new instance
	 * belongs to a different High Level Context type than the previous one,
	 * notifies Data Curation Layer about the detection of a new High Level
	 * Context.
	 * 
	 * @param newHlc
	 *            Newly classified High Level Context instance.
	 */
	public void notify(PhysicalActivityContext newHlc) {

		if (newHlc.isValidInstanceOfHlc()) {
			// New High Level Context Instance is valid.

			// Blocking operation: Retrieve previous HLC Instance from the
			// Triplestore. Set end time of previous HLC Instance. And store the
			// two instances to the Triplestore
			PhysicalActivityContext previousHlc = ContextHandler.retrievePreviousHlcAndStoreNew(newHlc);

			if (previousHlc == null || !newHlc.getCtxTypeName().equals(previousHlc.getCtxTypeName()))

				notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
						newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));

		}
	}
	//////////*************************   MMV2.5 to Handler NutritionContext changes are made Start **********/////////////////////////////////////
	
	/**
	 * Method that receives the newly classified High Level Context instance,
	 * stores it into the Context Ontology Storage, and if the new instance
	 * belongs to a different High Level Context type than the previous one,
	 * notifies Data Curation Layer about the detection of a new High Level
	 * Context.
	 * 
	 * @param newHlc
	 *            Newly classified High Level Context instance.
	 */
	public void notify(NutritionContext  newHlc) {

		if (newHlc.isValidInstanceOfHlc()) {
			// New High Level Context Instance is valid.

			// Blocking operation: Retrieve previous HLC Instance from the
			// Triplestore. Set end time of previous HLC Instance. And store the
			// two instances to the Triplestore
			NutritionContext previousHlc = ContextHandler.retrievePreviousHlcAndStoreNew(newHlc);

			if (previousHlc == null || !newHlc.getCtxTypeName().equals(previousHlc.getCtxTypeName()))

				notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
						newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));

		}
	}
	
	
	//////////*************************   MMV2.5 to Handler NutritionContext changes are made End **********/////////////////////////////////////
	/**
	 * Test method that applies the same business logic than notify but prints
	 * messages through the standard output stream.
	 * 
	 * @param newHlc
	 *            Newly classified High Level Context instance.
	 */
	public void notifyTest(PhysicalActivityContext newHlc) {

		if (newHlc.isValidInstanceOfHlc()) {
			// New High Level Context Instance is valid.

			

			
			// Blocking operation: Retrieve previous HLC Instance from the
			// Triplestore. Set end time of previous HLC Instance. And store the
			// two instances to the Triplestore
			PhysicalActivityContext previousHlc = ContextHandler.retrievePreviousHlcAndStoreNew(newHlc);

			if (previousHlc != null) {

				if (!newHlc.getCtxTypeName().equals(previousHlc.getCtxTypeName())) {
					logger.info("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Notify DCL.");
					
										
					notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
							newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
							newHlc.getDataPropertyValue(ont.getStartTimeProp()));
				}

				else{
					logger.info("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Do not notify DCL.");
					
				}
			}

			else {
				logger.info("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
						+ newHlc.getCtxTypeLocalName() + ". Previous Instance: none" + ". Notify DCL.");
				
				notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
						newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));

			}

		}

		else{
			
			logger.info("[HLC Notifier: PhysicalActivityContext: ] New High Level Context Instance is not valid.");
		}
	}
	//////////*************************   MMV2.5 to Handler NutritionContext changes are made Start **********/////////////////////////////////////

	public void notifyTest (NutritionContext newHlc) {

		if (newHlc.isValidInstanceOfHlc()) {
			// New High Level Context Instance is valid.

			

			
			// Blocking operation: Retrieve previous HLC Instance from the
			// Triplestore. Set end time of previous HLC Instance. And store the
			// two instances to the Triplestore
			NutritionContext previousHlc = ContextHandler.retrievePreviousHlcAndStoreNew(newHlc);

			if (previousHlc != null) {

				if (!newHlc.getCtxTypeName().equals(previousHlc.getCtxTypeName())) {
					logger.info("[HLC Notifier: NutritionContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Notify DCL.");
										
					notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
							newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
							newHlc.getDataPropertyValue(ont.getStartTimeProp()));
				}

				else{
					logger.info("[HLC Notifie: NutritionContext: r] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Do not notify DCL.");
					
				}
			}

			else {
				logger.info("[HLC Notifier: NutritionContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
						+ newHlc.getCtxTypeLocalName() + ". Previous Instance: none" + ". Notify DCL.");
				
				notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
						newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));

			}

		}

		else{
			logger.info("[HLC Notifier: NutritionContext: ] New High Level Context Instance is not valid.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	//////////*************************   MMV2.5 to Handler NutritionContext changes are made End   **********/////////////////////////////////////	
	
	
	
	
	
	
	
	/**
	 * Method to notify Data Curation Layer about the detection of a new High
	 * Level Context.
	 * 
	 * @param id
	 * @param label
	 * @param user
	 * @param timestamp
	 */
	private void notifyDCL(String id, String label, String user, Literal timestamp) {
		
		
		Scanner scan = null;
		scan = new Scanner(user).useDelimiter("_|\n");;
		String userID = scan.next();
		userID = scan.next();
		TimeUtil t = new TimeUtil();
		try {
			System.err.println("HLC sending to Demo " + label);
			RestClients.sendDemo(userID, LLCNotifier.noActivity, LLCNotifier.noEmotion, LLCNotifier.noLocation, LLCNotifier.noFood, label, LLCNotifier.noCat, t.parseCal(((XSDDateTime) timestamp.getValue()).asCalendar()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		System.err.println("userID = " + userID);
		System.err.println("time = " + tutil.parseCal(tutil.parseStringGMT(timestamp)));
		*/
		Calendar dateStart = ((XSDDateTime) timestamp.getValue()).asCalendar();
		
		String timestampS = tutil.parseCal(dateStart);
		// TODO
		logger.info(
				"[HLC Notifier] DCL Notification Message: " + id + ", " + label + ", " + userID + ", " + timestampS);
		/////////////////////////
		/**/
		
		try {
			System.out.println("********---------------------*******************************------------------********************");
			System.out.println(label);
			System.out.println("rest hlc of label "+ label + " " +RestClients.addUserRecognizedHLC(Long.parseLong(userID), label, timestampS));
			System.out.println("********---------------------*******************************------------------********************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///////////////////////
	}

}
