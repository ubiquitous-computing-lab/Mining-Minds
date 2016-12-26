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

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.uclab.scl.datamodel.FiredRule;

public class RecResultGenerator {

  private List<FiredRule> firedRules;
  
  /**
   * instantiates Recommendation Result Generator
   * 
   */
  public RecResultGenerator() {
  }
  
  /**
   * instantiates Recommendation Result Generator
   * 
   * @param firedRules
   */
  public RecResultGenerator(List<FiredRule> firedRules) {
    this.firedRules = firedRules;
  }
  
  /**
   * builds complex json object
   * <p>
   * 
   * @return JSON
   */
  public String generateResults() {
    String jsonResult = null;
    try {
      jsonResult = new ObjectMapper().writeValueAsString(firedRules);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return jsonResult;
  }
}
