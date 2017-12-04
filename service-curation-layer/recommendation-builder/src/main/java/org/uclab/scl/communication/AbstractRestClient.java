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
package org.uclab.scl.communication;
/**
 * This is an abstract class which supports two methods: GetData and PostData.
 * @author Afzal
 *
 * @param <T>
 * @param <V>
 */
public abstract class AbstractRestClient<T, V> {
  protected String baseURI;

  public AbstractRestClient(String baseURI) {
    this.baseURI = baseURI;
  }
  /**
   * 
   * @param GET_ResourceURI
   * @return
   */
  public abstract T GetData(String GET_ResourceURI);
  /**
   * 
   * @param POST_ResourceURI
   * @param postedResource
   * @return
   */
  public abstract V PostData(String POST_ResourceURI, T postedResource);
}
