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
/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class Conclusion {
  String conclusionKey;
  String conclusionValue;
  String conclusionOperator;

  public Conclusion() {
  }

  public Conclusion(String conclusionKey) {
    this.conclusionKey = conclusionKey;
  }

  public String getConclusionKey() {
    return conclusionKey;
  }

  public void setConclusionKey(String conclusionKey) {
    this.conclusionKey = conclusionKey;
  }

  public String getConclusionValue() {
    return conclusionValue;
  }

  public void setConclusionValue(String conclusionValue) {
    this.conclusionValue = conclusionValue;
  }

  public String getConclusionOperator() {
    return conclusionOperator;
  }

  public void setConclusionOperator(String conclusionOperator) {
    this.conclusionOperator = conclusionOperator;
  }

}
