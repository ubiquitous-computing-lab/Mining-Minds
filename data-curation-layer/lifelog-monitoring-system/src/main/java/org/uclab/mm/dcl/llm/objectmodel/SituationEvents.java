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
 * This class is to manage the multiple complex object model which may consist of list of object.
 * @author Rizvi
 */
@XmlRootElement()
public class SituationEvents implements Serializable{

  private List<SituationEvent> listSEvents = new ArrayList<SituationEvent>();

  /**
   * This method is used to get the object from the list of objects. 
   * @return the listSEvents
   */
  public List<SituationEvent> getListSEvents(){
    return listSEvents;
  }

  /**
   * This method is used to set the object in the list of objects. 
   * Set the values of situations that need to be monitored and monitored from the Lifelog.
   * @param listSEvents the listSEvents to set
   */
  public void setListSEvents(List<SituationEvent> listSEvents){
    this.listSEvents = listSEvents;
  }
}
