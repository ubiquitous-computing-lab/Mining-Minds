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
 * AbstractRecommendationBuilder
 * <p>
 * provides recommendation for a particular situation
 *  
 * @author Sadiq
 * @version 2.5
 * @since july 2014
 *
 */
public abstract class AbstractRecBuilder implements ISCLFramework<AbstractRecBuilderPacket> {
  @Override
  public void interpretRecommendation(AbstractRecBuilderPacket inputData) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }
  @Override
  public abstract void buildRecommendation(AbstractRecBuilderPacket inputData);

}
