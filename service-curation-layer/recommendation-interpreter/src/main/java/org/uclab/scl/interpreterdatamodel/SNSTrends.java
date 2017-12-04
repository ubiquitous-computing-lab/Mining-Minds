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
package org.uclab.scl.interpreterdatamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;
//import javax.xml.bind.annotation.XmlRootElement;
//
/**
 * @version MM v2.5
 * @author Afzal
 *
 */
@XmlRootElement
public class SNSTrends implements Serializable {

  @JsonProperty("name")
  private List<String> name = new ArrayList<>();

  @JsonProperty("total")
  private List<String> total = new ArrayList<>();

  /**
   * 
   * @return The name
   */
  @JsonProperty("name")
  public List<String> getName() {
    return name;
  }

  /**
   * 
   * @param name
   *          The name
   */
  @JsonProperty("name")
  public void setName(List<String> name) {
    this.name = name;
  }

  /**
   * 
   * @return The total
   */
  @JsonProperty("total")
  public List<String> getTotal() {
    return total;
  }

  /**
   * 
   * @param total
   *          The total
   */
  @JsonProperty("total")
  public void setTotal(List<String> total) {
    this.total = total;
  }

}