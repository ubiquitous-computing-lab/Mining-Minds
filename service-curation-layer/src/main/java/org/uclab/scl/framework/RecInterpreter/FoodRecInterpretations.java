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
import org.uclab.scl.datamodel.Conclusion;
import org.uclab.scl.datamodel.FiredRule;
import org.uclab.scl.datamodel.InterpretationIngredients;
import org.uclab.scl.framework.AbstractInterpreterPacket;
import org.uclab.scl.framework.RecInterpreter.Explanation.AbstractRecExplanation;
import org.uclab.scl.framework.RecInterpreter.FoodItemRecommender.SNSTrendIdentifier;
import org.uclab.scl.outputModel.InterpretationModel.InterpretedRecommendations;

public class FoodRecInterpretations {
  private static Logger LOG = LogManager.getRootLogger();
  
	private AbstractRecExplanation recExplanation;
	
/**
 * This function receives a nutrition based recommendation given by [RB] and gets a recommended list of foods against the given nutrient type
 * from SNSTrendIdentifier function, processes the received list according to the preferences of the user
 * 
 * @param interpreterPacket
 * @throws Exception
 */
  public void getFoodRecommendation(AbstractInterpreterPacket interpreterPacket) throws Exception {
    InterpretationIngredients interpretationIngredients = (InterpretationIngredients) interpreterPacket.getRecommendationInput();
    List<FiredRule> firedrule = interpretationIngredients.getFiredRule();
    String userID = interpretationIngredients.getUserID();
    String nutrientLabel = "";
    String description = "";
    String uid = userID;
    String finalList = "";
    String key = "";
    String value = "";
    for (FiredRule f : firedrule) {
      description = f.getRuleConclusion();
      List<Conclusion> firedConclusionList = f.getConclusionList();
      for (Conclusion c : firedConclusionList) {
        key = c.getConclusionKey();
        if (key.equalsIgnoreCase("Food")) {
          value = c.getConclusionValue();
          nutrientLabel = value;
        }
      }
    }
    
    SNSTrendIdentifier identifyTrend = new SNSTrendIdentifier();
    try{
    	finalList = identifyTrend.processFoodRec(nutrientLabel, uid);    	
    }
    catch (Exception e) {
        e.printStackTrace();
        LOG.debug("Problems encountered in Identifying Trends");
      }  
    
    LOG.debug("Final List for " + nutrientLabel + ": " + finalList);
    ComposeRec rec = new ComposeRec(finalList, description, uid);
    LOG.debug("Test Uid: " + rec.getUserid());
    LOG.debug("Test Fooditems: " + rec.getRecFooditems());

    List<InterpretedRecommendations> lstInterpretedRecs = new ArrayList<InterpretedRecommendations>();
    InterpretedRecommendations interpretedRec = new InterpretedRecommendations();
    interpretedRec.setFoodItemList(finalList);
    interpretedRec.setRecDescription(description);
    lstInterpretedRecs.add(interpretedRec);
    interpreterPacket.setRecommendationOutput(lstInterpretedRecs);
  }
  public AbstractRecExplanation getRecExplanation() {
	return recExplanation;
  }
  public void setRecExplanation(AbstractRecExplanation recExplanation) {
	this.recExplanation = recExplanation;
  } 
}
