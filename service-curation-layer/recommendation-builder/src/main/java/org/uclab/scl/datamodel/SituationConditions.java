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
package org.uclab.scl.datamodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
@XmlRootElement()
public class SituationConditions implements Serializable {

  private String conditionKey;
  private String conditionValueOperator;
  private String conditionValue;
  private String conditionType;

  public SituationConditions() {

  }

  /**
   * SituationCondions
   * 
   * @param conditionKey
   * @param conditionValue
   * @param conditionType
   * @param conditionValueOperator
   */
  public SituationConditions(String conditionKey, String conditionValue,
      String conditionType, String conditionValueOperator) {
    this.conditionKey = conditionKey;
    this.conditionValue = conditionValue;
    this.conditionType = conditionType;
    this.conditionValueOperator = conditionValueOperator;
  }

  /**
   * @return the conditionKey
   */
  public String getConditionKey() {
    return conditionKey;
  }

  /**
   * @param conditionKey
   *          the conditionKey to set
   */
  public void setConditionKey(String conditionKey) {
    this.conditionKey = conditionKey;
  }

  /**
   * @return the conditionValueOperator
   */
  public String getConditionValueOperator() {
    return conditionValueOperator;
  }

  /**
   * @param ConditionValueOperator
   *          the conditionValueOperator to set
   */
  public void setConditionValueOperator(String ConditionValueOperator) {
    this.conditionValueOperator = ConditionValueOperator;
  }

  /**
   * @return the conditionValue
   */
  public String getConditionValue() {
    return conditionValue;
  }

  /**
   * @param ConditionValue
   *          the conditionValue to set
   */
  public void setConditionValue(String ConditionValue) {
    this.conditionValue = ConditionValue;
  }

  /**
   * @return the conditionType
   */
  public String getConditionType() {
    return conditionType;
  }

  /**
   * @param ConditionType
   *          the conditionType to set
   */
  public void setConditionType(String ConditionType) {
    this.conditionType = ConditionType;
  }

  @Override
  public String toString() {
    String keyValue = "\"" + conditionKey + "\":\"" + conditionValue + "\"";

    return keyValue;
  }

}
