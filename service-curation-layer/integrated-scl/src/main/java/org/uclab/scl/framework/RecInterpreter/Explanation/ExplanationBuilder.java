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

package org.uclab.scl.framework.RecInterpreter.Explanation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.outputModel.InterpretationModel.InterpretedRecommendations;

public class ExplanationBuilder extends AbstractRecExplanation{
  private static Logger LOG = LogManager.getRootLogger();
  
  static HashMap<String, String> mapRecExp = new HashMap<>();
  static HashMap<String, String> mapExpURL = new HashMap<>();
  static HashMap<String, String> mapPostProcess = new HashMap<>();

  /**
   * Receives an input vector and provides corresponding recommendation 
   */
  public String parseAggregateVec(int[] aggregateVec) throws Exception {
    String interpRec = null;
    if (aggregateVec[0] == 1)
      interpRec = "Walking";
    else if (aggregateVec[1] == 1)
      interpRec = "Running";
    else if (aggregateVec[2] == 1)
      interpRec = "Stretching";
    else if (aggregateVec[3] == 1)
      interpRec = "Cycling";
    else if (aggregateVec[4] == 1)
      interpRec = "Sitting";
    else
      interpRec = "Default";
    
    return interpRec;
    
  }
  
  public String buildUrl(String recValue ) throws Exception{
	  return (new URLBuilder().recURLBuilder(recValue));
  }
  
  /**
   * Generates explanatory statement for the received recommendation in case the received recommendation is not accompanied with 
   * an explanatory statement moreover, post processing adds contextual information to the statement for special contexts
   * 
   * @author Imran
   * @version 2.5
   * @since September 2015
   * 
   */
  @SuppressWarnings("unchecked")
  public InterpretedRecommendations handleExplanation(int[] interpRec, String duration, String context, String description) throws Exception {
	LOG.debug("Explanation Builder");
    RecExpMapper recExpMap = new RecExpMapper();
    InterpretedRecommendations interpretedRec = new InterpretedRecommendations();
    List<String> explanationArr = new ArrayList<String>();
    String rec = this.parseAggregateVec(interpRec);
    
    LOG.debug("Recommended Activity: " + rec);
    explanationArr.add(rec);
    interpretedRec.setRecActivity(rec);
    interpretedRec.setCurrentContext(context);

    LOG.debug("Duration (mins): " + duration);
    explanationArr.add(duration);
    interpretedRec.setActDuration(duration);

    mapRecExp = recExpMap.recExpMapper();
    String recExplanationLists;
    recExplanationLists = mapRecExp.containsKey(rec) ? mapRecExp.get(rec) : mapRecExp.get(null);
 
    String[] selectExplanation = null;
    String selectedExplanation = null;

    if (recExplanationLists == null){
      LOG.debug("Recommendation NOT FOUND in the Explanation repository");
    }
    else {
      selectExplanation = recExplanationLists.split(",");
      selectedExplanation = selectExplanation[(ThreadLocalRandom.current().nextInt(0, selectExplanation.length))];
      selectedExplanation = this.explanationSentenceTemplate(rec, duration, selectedExplanation, context);
      if (description == null || description == "") {
        explanationArr.add(selectedExplanation);
        interpretedRec.setRecDescription(selectedExplanation);
      }
      else {
        explanationArr.add(description);
        interpretedRec.setRecDescription(description);
      }
    }

    return interpretedRec;
  }

  @SuppressWarnings("unchecked")
  public String explanationSentenceTemplate(String rec, String duration, String sentence, String context) throws Exception {
    PostProcessingMapper mapPostProcessing = new PostProcessingMapper();
    String template = null;
    
    mapPostProcess = mapPostProcessing.expPreprocessMapper();
    String[] contextArray = context.split(",");
    String cweatherCondition = contextArray[2];
    String cemotionCondition = contextArray[3];
    String currentWeather = null;
    String currentEmotion = null;
    
    if (cweatherCondition.equals("Rainy"))
      currentWeather = mapPostProcess.containsKey(cweatherCondition) ? mapPostProcess.get(cweatherCondition) : mapPostProcess.get(null);
    else if (cweatherCondition.equals("Sunny"))
      currentWeather = mapPostProcess.containsKey(cweatherCondition) ? mapPostProcess.get(cweatherCondition) : mapPostProcess.get(null);
    else
      currentWeather = "";
    template = sentence + " " + rec + " for "+ duration + "" + " " + currentWeather;

    if (cemotionCondition.equals("Anger"))
      currentEmotion = mapPostProcess.containsKey(cemotionCondition) ? mapPostProcess.get(cemotionCondition) : mapPostProcess.get(null);
    else if (cemotionCondition.equals("Sadness"))
      currentEmotion = mapPostProcess.containsKey(cemotionCondition) ? mapPostProcess.get(cemotionCondition) : mapPostProcess.get(null);
    else
      currentEmotion = "";
    
    template = template + " " + currentEmotion;
    return template;
  }
}
