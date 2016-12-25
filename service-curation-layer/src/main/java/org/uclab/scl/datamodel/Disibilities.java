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

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class Disibilities implements Serializable{
  
  private long id;
  private String requestType;
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRequestType() {
    return requestType;
  }

  public void setRequestType(String requestType) {
    this.requestType = requestType;
  }

  private String disabilityDescription;
  private int disabilityID;
  private String statusDescription;
  private int statusID;
  private int userDisabilityID;
  private int userID;

  /**
   * @return the disabilityDescription
   */
  public String getDisabilityDescription() {
    return disabilityDescription;
  }

  /**
   * @param disabilityDescription
   *          the disabilityDescription to set
   */
  public void setDisabilityDescription(String disabilityDescription) {
    this.disabilityDescription = disabilityDescription;
  }

  /**
   * @return the disabilityID
   */
  public int getDisabilityID() {
    return disabilityID;
  }

  /**
   * @param disabilityID
   *          the disabilityID to set
   */
  public void setDisabilityID(int disabilityID) {
    this.disabilityID = disabilityID;
  }

  /**
   * @return the statusDescription
   */
  public String getStatusDescription() {
    return statusDescription;
  }

  /**
   * @param statusDescription
   *          the statusDescription to set
   */
  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }

  /**
   * @return the statusID
   */
  public int getStatusID() {
    return statusID;
  }

  /**
   * @param statusID
   *          the statusID to set
   */
  public void setStatusID(int statusID) {
    this.statusID = statusID;
  }

  /**
   * @return the userDisabilityID
   */
  public int getUserDisabilityID() {
    return userDisabilityID;
  }

  /**
   * @param userDisabilityID
   *          the userDisabilityID to set
   */
  public void setUserDisabilityID(int userDisabilityID) {
    this.userDisabilityID = userDisabilityID;
  }

  /**
   * @return the userID
   */
  public int getUserID() {
    return userID;
  }

  /**
   * @param userID
   *          the userID to set
   */
  public void setUserID(int userID) {
    this.userID = userID;
  }

}
