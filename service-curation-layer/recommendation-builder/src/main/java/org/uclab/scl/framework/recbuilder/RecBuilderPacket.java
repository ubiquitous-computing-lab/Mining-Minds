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

import java.util.List;

import org.uclab.scl.datamodel.FiredRule;
import org.uclab.scl.datamodel.RecommendationIngredients;
import org.uclab.scl.framework.AbstractRecBuilderPacket;

public class RecBuilderPacket extends AbstractRecBuilderPacket<RecommendationIngredients, List<FiredRule>, String> {
  
  /**
   * prepares and returns recommendation input
   * <p>
   * 
   * @return RecommendationIngredients
   */
  @Override
  public RecommendationIngredients getRecommendationInput(){
    return this.recommendationInput;
  }
  
  /**
   * sets recommendation input
   * <p>
   * 
   * @param RecommendationIngredients
   * @return void
   */
  @Override
  public void setRecommendationInput(RecommendationIngredients recommendationInput){
    this.recommendationInput = recommendationInput;
  }
  
  /**
   * returns recommendation output
   * <p>
   * 
   * @return List<FiredRule>
   */
  @Override
  public List<FiredRule> getRecommendationOutput(){
    return this.recommendationOutput;
  }
  
  /**
   * sets recommendation output
   * <p>
   * 
   * @return void
   */
  @Override
  public void setRecommendationOutput(List<FiredRule> recommendationOutput){
    this.recommendationOutput = recommendationOutput;
  }
  
  /**
   * returns recommendation facts
   * <p>
   * 
   * @return String
   */
  @Override
  public String getRecommendationFacts(){
    return this.recommendationFacts;
  }
  
  /**
   * sets recommendation facts
   * <p>
   * 
   * @return void
   */
  @Override
  public void setRecommendationFacts(String recommendationFacts){
    this.recommendationFacts = recommendationFacts;
  }
}
