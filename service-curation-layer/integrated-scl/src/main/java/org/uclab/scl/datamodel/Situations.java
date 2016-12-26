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
import java.util.HashSet;
import java.util.Set;

public class Situations implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 7222498736081149676L;
  private String situationID;
  private String situationDescription;
  private Set<SituationConditions> listSConditions = new HashSet<SituationConditions>();

  public Situations() {

  }

  public Situations(String situationID) {
    this.situationID = situationID;
  }

  public String getSituationID() {
    return situationID;
  }

  public void setSituationID(String situationID) {
    this.situationID = situationID;
  }

  public String getSituationDescription() {
    return situationDescription;
  }

  public void setSituationDescription(String situationDescription) {
    this.situationDescription = situationDescription;
  }

  public Set<SituationConditions> getListSConditions() {
    return listSConditions;
  }

  public void setListSConditions(Set<SituationConditions> listSConditions) {
    this.listSConditions = listSConditions;
  }

  public void addSituationCondition(SituationConditions situationCondition) {
    this.listSConditions.add(situationCondition);
  }

}
