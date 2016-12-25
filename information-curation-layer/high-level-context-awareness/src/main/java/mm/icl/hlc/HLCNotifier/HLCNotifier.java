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
package mm.icl.hlc.HLCNotifier;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.rdf.model.Literal;

import mm.hlca.TestHLCA;
import mm.icl.hlc.ContextOntologyManager.ContextHandler;
import mm.icl.hlc.OntologyTools.ContextOntology;
import mm.icl.hlc.OntologyTools.NutritionContext;
import mm.icl.hlc.OntologyTools.PhysicalActivityContext;
import mm.icl.rest.RestClients;
import mm.icl.utils.FileUtil;
import mm.icl.utils.TimeUtil;
import org.apache.log4j.Logger;
/**
 * HLC Notifier: Component of HLCA which communicates the newly recognized 
 * PhysicalActivity and Nutrition instance to Data Curation Layer and persists it into the
 * Context Ontology Storage.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class HLCNotifier {
	private static Logger logger = Logger.getLogger(HLCNotifier.class);
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
	
	private ContextHandler contextHandler = ContextHandler.getContextHandler();
	
	public HLCNotifier(ContextOntology ont) {
		this.ont = ont;
	}
	/**
	 * Method that receives the newly classified High Level Context instance,
	 * stores it into the Context Ontology Storage, and if the new instance
	 * belongs to a different PhysicalActivity and Nutrition Context type than the previous one,
	 * notifies Data Curation Layer about the detection of a new High Level
	 * Context.
	 * 
	 * @param newHlc
	 *            Newly classified PhysicalActivity Context instance.
	 */
	public void notify(PhysicalActivityContext newHlc) {
		if (newHlc.isValidInstanceOfHlc()) {
			PhysicalActivityContext previousHlc = contextHandler.retrievePreviousHlcAndStoreNew(newHlc);
			if (previousHlc == null || !newHlc.getCtxTypeName().equals(previousHlc.getCtxTypeName()))
				notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
						newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));
		}
	}
	/**
	 * Method that receives the newly classified Nutrition Context instance,
	 * stores it into the Context Ontology Storage, and if the new instance
	 * belongs to a different Nutrition Context type than the previous one,
	 * notifies Data Curation Layer about the detection of a new High Level
	 * Context.
	 * 
	 * @param newHlc
	 *            Newly classified Nutrition Context instance.
	 */
	public void notify(NutritionContext  newHlc) {
		if (newHlc.isValidInstanceOfHlc()) {
			NutritionContext previousHlc = contextHandler.retrievePreviousHlcAndStoreNew(newHlc);
			if (previousHlc == null || !newHlc.getCtxTypeName().equals(previousHlc.getCtxTypeName()))
				notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
						newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));
		}
	}
	/**
	 * Test method that applies the same business logic than notify but prints
	 * messages through the standard output stream.
	 * 
	 * @param newHlc
	 *            Newly PhysicalActivity Level Context instance.
	 */
	public void notifyTest(PhysicalActivityContext newHlc) {
		if (newHlc.isValidInstanceOfHlc()) {
			PhysicalActivityContext previousHlc = contextHandler.retrievePreviousHlcAndStoreNew(newHlc);
			if (previousHlc != null) {
				if (!newHlc.getCtxTypeName().equals(previousHlc.getCtxTypeName())) {
					logger.info("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Notify DCL.");
					FileUtil.WriteHLCLog("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Notify DCL.");
					notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
							newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
							newHlc.getDataPropertyValue(ont.getStartTimeProp()));
				}
				else{
					FileUtil.WriteHLCLog("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Do not notify DCL.");
					logger.info("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Do not notify DCL.");
				}
			}
			else {
				logger.info("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
						+ newHlc.getCtxTypeLocalName() + ". Previous Instance: none" + ". Notify DCL.");
				FileUtil.WriteHLCLog("[HLC Notifier: PhysicalActivityContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
						+ newHlc.getCtxTypeLocalName() + ". Previous Instance: none" + ". Notify DCL.");
				notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
						newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));
			}
		}
		else{
			logger.info("[HLC Notifier: PhysicalActivityContext: ] New High Level Context Instance is not valid.");
			FileUtil.WriteHLCLog("[HLC Notifier: PhysicalActivityContext: ] New High Level Context Instance is not valid.");
		}
	}
	/**
	 * Test method that applies the same business logic than notify but prints
	 * messages through the standard output stream.
	 * 
	 * @param newHlc
	 *            Newly Nutrition Context instance.
	 */
	public void notifyTest (NutritionContext newHlc) {
		if (newHlc.isValidInstanceOfHlc()) {
			NutritionContext previousHlc = contextHandler.retrievePreviousHlcAndStoreNew(newHlc);
			if (previousHlc != null) {
				if (!newHlc.getCtxTypeName().equals(previousHlc.getCtxTypeName())) {
					logger.info("[HLC Notifier: NutritionContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Notify DCL.");
					FileUtil.WriteHLCLog("[HLC Notifier: NutritionContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Notify DCL.");
					notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
							newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
							newHlc.getDataPropertyValue(ont.getStartTimeProp()));
				}
				else{
					FileUtil.WriteHLCLog("[HLC Notifie: NutritionContext: r] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Do not notify DCL.");
					logger.info("[HLC Notifier: NutritionContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
							+ newHlc.getCtxTypeLocalName() + ". Previous Instance: "
							+ previousHlc.getCtxInstanceLocalName() + " - " + previousHlc.getCtxTypeLocalName()
							+ ". Do not notify DCL.");
				}
			}
			else {
				logger.info("[HLC Notifier: NutritionContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
						+ newHlc.getCtxTypeLocalName() + ". Previous Instance: none" + ". Notify DCL.");
				FileUtil.WriteHLCLog("[HLC Notifier: NutritionContext: ] New instance: " + newHlc.getCtxInstanceLocalName() + " - "
						+ newHlc.getCtxTypeLocalName() + ". Previous Instance: none" + ". Notify DCL.");
				notifyDCL(newHlc.getCtxInstanceLocalName(), newHlc.getCtxTypeLocalName(),
						newHlc.getObjectPropertyValue(ont.getContextOfProp()).getLocalName(),
						newHlc.getDataPropertyValue(ont.getStartTimeProp()));
			}
		}
		else{
			logger.info("[HLC Notifier: NutritionContext: ] New Nutrition Context Instance is not valid.");
			FileUtil.WriteHLCLog("[HLC Notifier: NutritionContext: ] New Nutrition Context Instance is not valid.");
		}
	}
	/**
	 * Method to notify Data Curation Layer about the detection of a new 
	 * PhysicalActivity and Nutrition Context.
	 * 
	 * @param id
	 * @param label
	 * @param user
	 * @param timestamp
	 */
	private void notifyDCL(String id, String label, String user, Literal timestamp) {
		TimeUtil t = new TimeUtil();
		try {
			RestClients.sendDemo("no", "no", "no", label, t.parseCal(((XSDDateTime) timestamp.getValue()).asCalendar()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scan = null;
		scan = new Scanner(user).useDelimiter("_|\n");;
		String userID = scan.next();
		userID = scan.next();
		Calendar dateStart = ((XSDDateTime) timestamp.getValue()).asCalendar();
		String timestampS = tutil.parseCal(dateStart);

		FileUtil.WriteHLCLog("[HLC Notifier] DCL Notification Message: " + id + ", " + userID + ", " + user + ", " + timestampS);
		logger.info(
				"[HLC Notifier] DCL Notification Message: " + id + ", " + label + ", " + userID + ", " + timestampS);
		try {
			logger.info("********---------------------*******************************------------------********************");
			logger.info(label);
			logger.info("rest hlc of label "+ label + " " + RestClients.addUserRecognizedHLC(35l, label, timestampS));
			logger.info("********---------------------*******************************------------------********************");
		} catch (Exception e) {
			logger.error("Error while Notifying HLC.  Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public ContextHandler getContextHandler() {
		return contextHandler;
	}
	public void setContextHandler(ContextHandler contextHandler) {
		this.contextHandler = contextHandler;
	}
}