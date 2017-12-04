/**
 * 
 * Copyright [2016] [Bilal Ali]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 *  ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */
package org.uclab.mm.dcl.llm.objectmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is used to define the object that comprises of data model list.
 * A list may contain multiple data model
 * @author Rizvi
 */
@XmlRootElement()
public class SituationEvent implements Serializable{

  private String SituationID;
  private List<SituationConditions> ListSConditions = new ArrayList<SituationConditions>();

  /**
   * This method is used to return the particular unique id of situation associated with block of condition and constraint.
   * @return the SituationID
   */
  public String getSituationID(){
    return SituationID;
  }

  /**
   * This method is used to set the particular unique id of situation associated with block of condition and constraint provided in .
   * @param SituationID the SituationID to set
   */
  public void setSituationID(String SituationID){
    this.SituationID = SituationID;
  }

  /**
   * The method is used to set the conditions in a object that consist of list of conditions.
   * @return the ListSConditions
   */
  public List<SituationConditions> getListSConditions(){
    return ListSConditions;
  }

  /**
   * The method is used to return the conditions in a object that consist of list of conditions.
   * @param ListSConditions the ListSConditions to set
   */
  public void setListSConditions(List<SituationConditions> ListSConditions){
    this.ListSConditions = ListSConditions;
  }

}
