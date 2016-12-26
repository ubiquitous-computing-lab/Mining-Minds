/**
 * Copyright [2016] [Muhammad Afzal]
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
package org.uclab.scl.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.uclab.scl.Utilities.AgeGroupEnum;
import org.uclab.scl.Utilities.DisibilitiesUtility;
import org.uclab.scl.Utilities.RiskFactorFinderUtility;

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class RecommendationIngredients {

  // private Situation situation;
  private SituationNotification sNotification;
  private User user;
  private Map<String, String> riskFactors = new HashMap<>();
  private Map<String, String> disibilities = new HashMap<>();
  private AgeGroupEnum AgeGroup;
  private List<UserSchedule> listUserSchedle = new ArrayList<UserSchedule>();
  private List<PhysiologicalFactors> physFactors = new ArrayList<PhysiologicalFactors>();
  private FoodLog foodLog = new FoodLog();

  public Map<String, String> getRiskFactors() {
    return riskFactors;
  }

  public void setRiskFactors(Map<String, String> riskFactors) {
    this.riskFactors = riskFactors;
  }

  public void setRiskFactors(List<RiskFactors> riskFactorsList) {

    RiskFactorFinderUtility rfUtility = RiskFactorFinderUtility.getRiskFactorFinderUtility();

    this.riskFactors = rfUtility.getRiskFactors(riskFactorsList);
  }

  // /**
  // * @return the situation
  // */
  // public Situation getSituation() {
  // return situation;
  // }
  //
  // /**
  // * @param situation the situation to set
  // */
  // public void setSituation(Situation situation) {
  // this.situation = situation;
  // }

  /**
   * @return the userProfile
   */
  public User getUser() {
    return user;
  }

  /**
   * @param userProfile
   *          the userProfile to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * @return the AgeGroup
   */
  public AgeGroupEnum getAgeGroup() {
    return AgeGroup;
  }

  /**
   * @param AgeGroup
   *          the AgeGroup to set
   */
  public void setAgeGroup(AgeGroupEnum AgeGroup) {
    this.AgeGroup = AgeGroup;
  }

  /**
   * @return the disibilities
   */
  public Map<String, String> getDisibilities() {
    return disibilities;
  }

  /**
   * @param disibilities
   *          the disibilities to set
   */
  public void setDisibilities(Map<String, String> disibilities) {
    this.disibilities = disibilities;
  }

  public void setDisibilities(List<Disibilities> disibilities) {
    // Risk Factors Utility

    this.disibilities = DisibilitiesUtility.getDisabilitiesMap(disibilities);

  }

  /**
   * @return the listUserSchedle
   */
  public List<UserSchedule> getUserSchedle() {
    return listUserSchedle;
  }

  /**
   * @param listUserSchedle
   *          the listUserSchedle to set
   */
  public void setUserSchedle(List<UserSchedule> listUserSchedle) {
    this.listUserSchedle = listUserSchedle;
  }

  /**
   * @return the sNotification
   */
  public SituationNotification getsNotification() {
    return sNotification;
  }

  /**
   * @param sNotification
   *          the sNotification to set
   */
  public void setsNotification(SituationNotification sNotification) {
    this.sNotification = sNotification;
  }

  /**
   * getJSONKeyValue Scope: In current status this method provide key value of
   * main data (such as User, Situation (partial values)) required for rules in
   * form of JSON This JSON is required for recommendation builder to perform
   * reasoning
   * 
   * @return Single Object of Key Value in JSON format
   */

  public String getJSONKeyValue() {
    String keyValueJSON = "{\n" + this.toString() + "\n}";
    return keyValueJSON;
  }

  @Override
  public String toString() {
    String keyValue = "";

    keyValue += sNotification.toString();
    keyValue += user.toString() + "\n";
    keyValue += foodLog.toString() + "\n";

    // for(String key: riskFactors.keySet())
    // keyValue += "\"Risk Factor\":\"" + key + "\",\n";

    for (String key : riskFactors.keySet())
      keyValue += "\"" + key + "\":\"Yes\",\n";

    for (String key : disibilities.keySet())
      keyValue += "\"Disability\":\"" + key + "\",\n";

    for (PhysiologicalFactors pFactor : physFactors)
      keyValue += "\"Weight Status\":\"" + pFactor.getWeightStatus() + "\"\n";

    return keyValue;

  }

  /**
   * @return the physFactors
   */
  public List<PhysiologicalFactors> getPhysFactors() {
    return physFactors;
  }

  /**
   * @param physFactors
   *          the physFactors to set
   */
  public void setPhysFactors(List<PhysiologicalFactors> physFactors) {
    this.physFactors = physFactors;
  }

  /**
   * @return the foodLog
   */
  public FoodLog getFoodLog() {
    return foodLog;
  }

  /**
   * @param foodLog
   *          the foodLog to set
   */
  public void setFoodLog(FoodLog foodLog) {
    this.foodLog = foodLog;
  }
}
