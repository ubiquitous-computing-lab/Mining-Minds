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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Rizvi
 */
@XmlRootElement()
public class SituationEvent implements Serializable {
  private String SituationID;
  private Set<SituationConditions> ListSConditions = new HashSet<SituationConditions>();

  /**
   * @return the SituationID
   */
  public String getSituationID() {
    return SituationID;
  }

  /**
   * @param SituationID
   *          the SituationID to set
   */
  public void setSituationID(String SituationID) {
    this.SituationID = SituationID;
  }

  /**
   * @return the ListSConditions
   */
  public Set<SituationConditions> getListSConditions() {
    return ListSConditions;
  }

  /**
   * @param ListSConditions
   *          the ListSConditions to set
   */
  public void setListSConditions(Set<SituationConditions> ListSConditions) {
    this.ListSConditions = ListSConditions;
  }

  @Override
  public String toString() {
    String keyValue = "";

    for (SituationConditions sc : ListSConditions)
      keyValue += sc.toString() + ",\n";

    return keyValue;
  }

}
