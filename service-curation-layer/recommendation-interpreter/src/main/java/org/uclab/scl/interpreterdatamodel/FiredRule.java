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
package org.uclab.scl.interpreterdatamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FiredRule implements Serializable {
  /**
	 * 
	 */
  private static final long serialVersionUID = -6568646491998798139L;

  private String ruleId;
  private String ruleConclusion;
  private List<Conclusion> conclusionList = new ArrayList<Conclusion>();
  private int conditionsKey = 0;

  public FiredRule() {
  }

  public FiredRule(String ruleId) {
    this.ruleId = ruleId;
  }

  public String getRuleId() {
    return ruleId;
  }

  public void setRuleId(String ruleId) {
    this.ruleId = ruleId;
  }

  public List<Conclusion> getConclusionList() {
    return conclusionList;
  }

  public void setConclusionList(List<Conclusion> conclusionList) {
    this.conclusionList = conclusionList;
  }

  public void addConclusion(Conclusion conclusion) {
    conclusionList.add(conclusion);
  }

  public String getRuleConclusion() {
    return ruleConclusion;
  }

  public void setRuleConclusion(String ruleConclusion) {
    this.ruleConclusion = ruleConclusion;
  }

  public int getConditionsKey() {
    return conditionsKey;
  }

  public void setConditionsKey(int conditionsKey) {
    this.conditionsKey = conditionsKey;
  }
}
