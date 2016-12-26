/* Copyright [2016] [Syed Imran Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.uclab.scl.framework.RecInterpreter;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.communication.CommunicationManager;
import org.uclab.scl.interpreterdatamodel.Conclusion;
import org.uclab.scl.interpreterdatamodel.CurrentContext;
import org.uclab.scl.interpreterdatamodel.CurrentLocation;
import org.uclab.scl.interpreterdatamodel.FiredRule;
import org.uclab.scl.interpreterdatamodel.InterpretationIngredients;
import org.uclab.scl.framework.AbstractInterpreterPacket;
import org.uclab.scl.framework.RecInterpreter.Explanation.AbstractRecExplanation;
import org.uclab.scl.framework.RecInterpreter.PARecommender.ContextSelector;
import org.uclab.scl.framework.RecInterpreter.PARecommender.MultipleRecEvaluator;
import org.uclab.scl.framework.RecInterpreter.PARecommender.SingleRecEvaluator;
import org.uclab.scl.outputModel.InterpretationModel.InterpretedRecommendations;

public class PARecInterpretations {
	private static Logger LOG = LogManager.getLogger(PARecInterpretations.class);
	public static String description = "";
	public static String durationValue = "";
	private CurrentContext currentContext = new CurrentContext();
    CommunicationManager commManager = CommunicationManager.getCommunicationManager();
	private AbstractRecExplanation recExplanation;

	/**
	 * Receives control flow for Physical Recommendation and evaluates its
	 * contextually. If required generates an alternative recommendation. This
	 * function serves as a control unit for Recommendation Interpreter
	 * 
	 * @param interpreterPacket
	 */
	public void buildPARecInterpretations(AbstractInterpreterPacket interpreterPacket) {
		LOG.debug("Interpreting...");
		InterpretationIngredients interpretationIngredients = (InterpretationIngredients) interpreterPacket.getRecommendationInput();
		List<FiredRule> firedrule = interpretationIngredients.getFiredRule();
		String userID = interpretationIngredients.getUserID();
		String receivedContext = null;
		String recList = "";
		String key = "";
		String value = "";
		String operator = "";
		
		LOG.debug("Looping through rules...");
		for (FiredRule f : firedrule) {
			description = f.getRuleConclusion() + ",";
			LOG.debug("Received Description:", description);
			List<Conclusion> fireConclusionList = f.getConclusionList();
			for (Conclusion c : fireConclusionList) {
				key = c.getConclusionKey();
				LOG.debug("key: " + key);
				//. if (key.equalsIgnoreCase("Current Activity")) {
				if (key.equalsIgnoreCase("Recommended Activity")) {
					value = c.getConclusionValue() + ",";
					operator = c.getConclusionOperator() + ",";
					recList += value;
				} else if(key.equalsIgnoreCase("Activity Duration")){
					durationValue = c.getConclusionValue();
					LOG.debug("Activity Duration", durationValue);
				}
				else{
					//..Nothing
				}
			}
		}

		String lastCh = recList.substring(recList.length() - 1);
		if (lastCh.contains(","))
			recList = recList.substring(0, recList.length() - 1);
		LOG.debug("Received Recommendation:  {}", recList);
		CurrentContext currentContexts = new CurrentContext();
		//commManager.getCurrentContext(Long.parseLong(userID));
		if (!(currentContexts.getCurrentLocation().isEmpty())) {
			currentContext.setCurrentLocation(currentContexts.getCurrentLocation());
			for (CurrentLocation cl : currentContext.getCurrentLocation()) {
				receivedContext = cl.getLocationLabel();
				break;
			}
		} else {
			receivedContext = "UnidentifiedLocation";
			if (!(currentContexts.getCurrentHLC().isEmpty())) {
				currentContext.setCurrentHLC(currentContexts.getCurrentHLC());
				receivedContext += "," + currentContext.getCurrentHLC().get(0).gethLCLabel();
			} else {
				receivedContext += "," + "UnidentifiedHLC";
			}
			if (!(currentContexts.getCurrentWeather().isEmpty())) {
				currentContext.setCurrentWeather(currentContexts.getCurrentWeather());
				receivedContext += "," + currentContext.getCurrentWeather().get(0).getWeatherLabel();
			} else {
				receivedContext += "," + "UnidentifiedWeather";
			}
			if (!(currentContexts.getCurrentEmotion().isEmpty())) {
				currentContext.setCurrentEmotion(currentContexts.getCurrentEmotion());
				receivedContext += "," + currentContext.getCurrentEmotion().get(0).getEmotionLabel();
			} else {
				receivedContext += "," + "UnidentifiedEmotion";
			}
			receivedContext += ",No";
			
			LOG.debug("Current Context:"+ receivedContext);

			List<InterpretedRecommendations> interpretedRecs = new ArrayList<InterpretedRecommendations>();
			InterpretedRecommendations interpretedRec = new InterpretedRecommendations();
			LOG.debug("Before context selector");
			ContextSelector contextSelector = new ContextSelector();
			boolean userInterrupt = contextSelector.selectContext(receivedContext);
			if (!userInterrupt) {
				LOG.debug("User can't be Interrupted Now");
				interpretedRec.setCurrentContext(receivedContext);
			} else {
				LOG.debug("Recommendation Forwarded for Content Evaluation");
				AbstractRecEvaluator evaluator = getEvaluator(recList);
				int[] validRec = evaluator.evaluate(recList, userID, receivedContext);

				int[] tempRecVec = new int[validRec.length];
				System.arraycopy(validRec, 0, tempRecVec, 0, validRec.length);

				for (int i = 0; i < tempRecVec.length; i++) {
					if (validRec[i] == 1) {
						tempRecVec[i] = 1;
						try {
							interpretedRec = recExplanation.handleExplanation(tempRecVec, PARecInterpretations.durationValue, receivedContext, description);
						} catch (Exception e) {
							e.printStackTrace();
						}
						String ActType = null;						
						String url = null;
						try {
							ActType = recExplanation.parseAggregateVec(tempRecVec);						    
						    LOG.debug("URL Builder");
							url = recExplanation.buildUrl(ActType);
						} catch (Exception e) {
							e.printStackTrace();
						}

						interpretedRec.setRecURL(url);
						tempRecVec[i] = 0;
						interpretedRecs.add(interpretedRec);
					}
				}
			}
			interpreterPacket.setRecommendationOutput(interpretedRecs);
		} //. comment out
	}

	public AbstractRecExplanation getRecExpalanation() {
		return recExplanation;
	}

	public void setRecExpalanation(AbstractRecExplanation recExpalanation) {
		this.recExplanation = recExpalanation;
	}

	public AbstractRecEvaluator getEvaluator(String rec) {
		int contentLength = rec.split(",").length;
		AbstractRecEvaluator evaluator = null;

		switch (contentLength) {
		case 1:
			evaluator = new SingleRecEvaluator();
			break;
		default:
			evaluator = new MultipleRecEvaluator();
		}

		return evaluator;
	}

}
