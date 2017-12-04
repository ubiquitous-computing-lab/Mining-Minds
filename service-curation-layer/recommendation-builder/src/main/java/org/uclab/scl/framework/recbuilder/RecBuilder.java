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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.communication.ConditionsValueService;
import org.uclab.scl.communication.RulesService;
import org.uclab.scl.datamodel.FiredRule;
import org.uclab.scl.datamodel.RecommendationIngredients;
import org.uclab.scl.datamodel.SituationEvent;
import org.uclab.scl.datamodel.Situations;
import org.uclab.scl.framework.AbstractRecBuilder;
import org.uclab.scl.framework.AbstractRecBuilderPacket;

public class RecBuilder extends AbstractRecBuilder {
  private static Logger LOG = LogManager.getRootLogger();
  
  private AbstractPatternMatcher patternMatcher;
  private AbstractConflictResolver conflictResolver;
  private RulesService ruleService;
  
  /**
   * Instantiates RecommendationBuilder by using patternMatcher & conflictResolver
   * 
   * <p>
   * @param patternMatcher
   * @param conflictResolver
   */
  public RecBuilder(AbstractPatternMatcher patternMatcher, AbstractConflictResolver conflictResolver){
    this.patternMatcher = patternMatcher;
    this.conflictResolver = conflictResolver;
  }
  /**
   * generates recommendation based on the ISCLDataPacket
   * <p> 
   * 
   * @param ISCLDataPacket
   * @return
   */
  @Override
  public void buildRecommendation(AbstractRecBuilderPacket abstractRecBuilderPacket) {
    LOG.debug("building recommendation...");
    RecBuilderPacket recBuilderPacket = (RecBuilderPacket) abstractRecBuilderPacket;
    RecommendationIngredients recIngredients = new RecommendationIngredients();
    recIngredients = recBuilderPacket.getRecommendationInput();
    SituationEvent situationEvent = recIngredients.getsNotification().getSituationEvent();

    Situations situation = new Situations(situationEvent.getSituationID());
    situation.setListSConditions(situationEvent.getListSConditions());

    List<FiredRule> resolvedRules = getFinalResolvedRules(situation, recIngredients.getJSONKeyValue());
    recBuilderPacket.setRecommendationOutput(resolvedRules);
  }
  
  /**
   * returns the final resolved rules after resolving conflict
   * <p>
   * 
   * @param situation
   * @param conditionValueJSON
   * @return
   */
  public List<FiredRule> getFinalResolvedRules(Situations situation, String conditionValueJSON) {
 
    LOG.debug("Firing rules...");
     List<FiredRule> finalResolvedRules = null;
    try{
      List<Map<String, Object>> rules = getRuleService().setSituationsData(situation).connect().getResponse().parse();
      Map<String, Object> conditionsValue = ConditionsValueService.parse(conditionValueJSON, 1);
      List<FiredRule> firedRules = patternMatcher.fireRule(conditionsValue, rules);
      LOG.debug("Resolving conflict...");
      finalResolvedRules = conflictResolver.resolveConflict(firedRules);
    }catch(Exception e){
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      LOG.error(sw.toString());
    }
    return finalResolvedRules;
  }
  
  /**
   * sets the rule service
   * 
   * @param ruleService
   * @return
   */
  public void setRulesService(RulesService ruleService){
    this.ruleService = ruleService;
  }
  
  /**
   * returns the rule service instance
   * 
   * @return
   */
  public RulesService getRuleService(){
    if(ruleService == null)
      ruleService = new RulesService();
    
    return ruleService;
  }
}
