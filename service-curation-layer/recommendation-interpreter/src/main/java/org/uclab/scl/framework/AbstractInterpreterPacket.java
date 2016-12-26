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
package org.uclab.scl.framework;
/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public abstract class AbstractInterpreterPacket<T, U> implements ISCLDataPacket<T, U> {
  protected T interpretationIngredients;
  protected U InterpretedRecommendations;
  
  @Override
  public abstract T getRecommendationInput();
  @Override
  public abstract void setRecommendationInput(T recommendationInput);
  
  @Override
  public abstract U getRecommendationOutput();
  @Override
  public abstract void setRecommendationOutput(U recommendationOutput);
}
