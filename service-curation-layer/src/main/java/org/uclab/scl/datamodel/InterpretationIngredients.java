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
import java.util.List;

import org.uclab.scl.outputModel.InterpretationModel.PhyActivityException;
/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class InterpretationIngredients {

  private List<UserSchedule> userSchedule = new ArrayList<UserSchedule>();
  private List<FiredRule> firedRule = new ArrayList<FiredRule>();
  private long recommendationID;
  private String userID;

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  /**
   * @return the userSchedule
   */
  public List<UserSchedule> getUserSchedule() {
    return userSchedule;
  }

  /**
   * @param userSchedule
   *          the userSchedule to set
   */
  public void setUserSchedule(List<UserSchedule> userSchedule) {
    this.userSchedule = userSchedule;
  }

  public List<FiredRule> getFiredRule() {
    return firedRule;
  }

  public void setFiredRule(List<FiredRule> firedRule) {
    this.firedRule = firedRule;
  }

  /**
   * @return the recommendationID
   */
  public long getRecommendationID() {
    return recommendationID;
  }

  /**
   * @param recommendationID
   *          the recommendationID to set
   */
  public void setRecommendationID(long recommendationID) {
    this.recommendationID = recommendationID;
  }
}
