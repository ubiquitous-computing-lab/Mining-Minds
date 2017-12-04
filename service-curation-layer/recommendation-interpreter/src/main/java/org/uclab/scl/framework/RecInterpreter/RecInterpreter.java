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

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.interpreterdatamodel.FiredRule;
import org.uclab.scl.interpreterdatamodel.InterpretationIngredients;
import org.uclab.scl.framework.AbstractInterpreterPacket;
import org.uclab.scl.framework.AbstractRecInterpreter;
import org.uclab.scl.framework.RecInterpreter.Explanation.ExplanationBuilder;

public class RecInterpreter extends AbstractRecInterpreter {
  private static Logger LOG = LogManager.getRootLogger();

  /**
   * This function provides a mechanism to channel the control to the required  logic based on the nature of recommendation received 
   * e.g. in case of food based recommendation this function will transfer control to "FoodRecInterpretation" component 
   */
  @Override
  public void interpretRecommendation(AbstractInterpreterPacket interpreterPacket) {
    InterpretationIngredients interpretationIngredients = (InterpretationIngredients) interpreterPacket.getRecommendationInput();
    List<FiredRule> rules = interpretationIngredients.getFiredRule();
    try {
      if (rules.get(0).getConclusionList().get(0).getConclusionKey().equalsIgnoreCase("Food")) {
        (new FoodRecInterpretations()).getFoodRecommendation(interpreterPacket);
      } else {
    	  PARecInterpretations physicalInterpretation = new PARecInterpretations();
    	  physicalInterpretation.setRecExpalanation(new ExplanationBuilder());
    	  physicalInterpretation.buildPARecInterpretations(interpreterPacket);
      }
    }
    catch (Exception exp) {
      LOG.error(exp.getMessage());
    }
  }
}
