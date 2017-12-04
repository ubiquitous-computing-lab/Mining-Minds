/**
 * Copyright [2016] [Muhammad Sadiq]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uclab.scl.framework.recbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.Utilities.UtilityFacade;
import org.uclab.scl.datamodel.Conclusion;
import org.uclab.scl.datamodel.FiredRule;

/**
 * PatternMatcher
 * An implementation for the org.uclab.scl.framework.recbuilder.AbstractPatternMatcher
 * <p>
 * PatternMatcher uses forward chaining strategy to select all applicable rules for the particular situation.
 *  
 * @author Sadiq
 * @version 2.5
 * @since july 2014
 *
 */
public class PatternMatcher extends AbstractPatternMatcher{
  private static Logger LOG = LogManager.getRootLogger();
  
  /**
   * fires rule for a specific situation after reasoning
   * <p>
   * 
   * @param conditionsValue
   * @param rules
   * @return listOfMatchedRules
   * @throws UnsupportedTypeException 
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<FiredRule> fireRule(Map<String, Object> conditionsValue, List<Map<String, Object>> rules) throws UnsupportedTypeException {
    List<FiredRule> firedRules = new ArrayList<FiredRule>();

    for (Map<String, Object> rule : rules) {
      List<Map<String, Object>> conditionsKeyList = (List<Map<String, Object>>) rule.get("conditionList");
      if (matchRule(conditionsValue, conditionsKeyList)) {
        List<Map<String, Object>> conclusionsList = (ArrayList<Map<String, Object>>) rule.get("conclusionList");
        int numberOfConditions = ((List<Map<String, Object>>) rule.get("conditionList")).size();
        FiredRule firedRule = new FiredRule(rule.get("ruleID").toString());
        firedRule.setRuleConclusion(rule.get("ruleConclusion").toString());
        firedRule.setConditionsKey(numberOfConditions);
        for (Map<String, Object> conclusionMap : conclusionsList) {
          Conclusion conclusion = new Conclusion(conclusionMap.get("conclusionKey").toString());
          conclusion.setConclusionValue(conclusionMap.get("conclusionValue").toString());
          conclusion.setConclusionOperator(conclusionMap.get("conclusionOperator").toString());
          firedRule.addConclusion(conclusion);
        }
        firedRules.add(firedRule);
      }
    }
    return firedRules;
  }
  
  /**
   * returns true if all conditions matched false other wise
   * 
   * @param conditionsValue
   * @param conditionsKeyList
   * @return boolean
   * @throws UnsupportedTypeException 
   */
  private boolean matchRule(Map<String, Object> conditionsValue, List<Map<String, Object>> conditionsKeyList) throws UnsupportedTypeException {
    boolean CONDITION_MATCHED = false;
    for (Map<String, Object> conditionsKey : conditionsKeyList) {
      CONDITION_MATCHED = false;
      for (String FactKey : conditionsValue.keySet()) {
        if (matchCondition(FactKey, conditionsValue.get(FactKey), conditionsKey)) {
          CONDITION_MATCHED = true;
          break;
        }
      }
      if (!CONDITION_MATCHED) {
        return false;
      }
    }
    return true;
  }

  /**
   * returns TRUE if FactValue and RuleValue matched according to the operator
   * defined in the rule, FALSE elsewhere.
   * 
   * @param factKey
   * @param factValue
   * @param condition
   * @return boolean
   */
  private boolean matchCondition(String factKey, Object factValue, Map<String, Object> condition) throws UnsupportedTypeException{
    Boolean matched = false;
    try {
      if(!factKey.equalsIgnoreCase(condition.get("conditionKey").toString()))
        return false;
      
      String conditionType = null;
      try {
        conditionType = condition.get("conditionType").toString();
      } catch (Exception ex) {
        LOG.error("ConditionType is null! Setting to 'String'");
        conditionType = "String";
      }
      
      switch (conditionType) {
      case "string":
      case "String":
        matched = TypeComparator.compare(factValue.toString(), condition.get("conditionValue").toString(), condition.get("conditionValueOperator").toString());
      break;
      case "Integer":
      case "integer":
      case "Int":
      case "int":
        matched = TypeComparator.compare(Integer.parseInt(factValue.toString()), Integer.parseInt(condition.get("conditionValue").toString()), condition.get("conditionValueOperator").toString());
      break;
      case "boolean":
      case "Boolean":
        matched = TypeComparator.compare((boolean) factValue.toString().equalsIgnoreCase("yes"), (boolean) condition.get("conditionValue").toString().equalsIgnoreCase("yes"), condition.get("conditionValueOperator").toString());
      break;
      case "Time":
      case "time":
        int factTimeSec = UtilityFacade.getComputationUtility().timeToSec(factValue.toString());
        int ruleTimeSec = UtilityFacade.getComputationUtility().timeToSec(condition.get("conditionValue").toString());
        matched = TypeComparator.compare(factTimeSec, ruleTimeSec, condition.get("conditionValueOperator").toString());
      break;
      default:
        //. LOG.error("Type: " + condition.get("conditionType") + " is not defined!");
        throw new UnsupportedTypeException("Type[" + conditionType.toString()+"] is not supported!");
      }
    } catch (Exception ex) {
      matched = false;
      LOG.error("Invalid type conversion " + ex.getMessage());
    }

    return matched;
  }
}
