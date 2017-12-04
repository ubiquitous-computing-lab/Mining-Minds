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
public class RiskFactors implements Serializable{

  private long id;
  private String requestType;
  private int riskFactorId;
  private int statusId;
  private long userId;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public int getUserRiskFactorId() {
    return userRiskFactorId;
  }

  public void setUserRiskFactorId(int userRiskFactorId) {
    this.userRiskFactorId = userRiskFactorId;
  }

  private int userRiskFactorId;

  /**
   * @return the riskFactorId
   */
  public int getRiskFactorId() {
    return riskFactorId;
  }

  /**
   * @param riskFactorId
   *          the riskFactorId to set
   */
  public void setRiskFactorId(int riskFactorId) {
    this.riskFactorId = riskFactorId;
  }

  /**
   * @return the statusId
   */
  public int getStatusId() {
    return statusId;
  }

  /**
   * @param statusId
   *          the statusId to set
   */
  public void setStatusId(int statusId) {
    this.statusId = statusId;
  }

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

}
