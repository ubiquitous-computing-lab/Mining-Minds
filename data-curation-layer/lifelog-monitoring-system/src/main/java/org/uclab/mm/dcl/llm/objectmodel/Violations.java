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

/**
 *This class provide the data model for the representation of the violations 
 * that are persisted in log to the help and view of expert.
 * @author Rizvi
 */
public class Violations{

  private String UserID;
  private String ActivityID;
  private String RecordedTime;
  private String ActivityTargetDuration;
  private String LogID;

  private String TotalViolations;

  /**
   * @return the UserID
   */
  public String getUserID(){
    return UserID;
  }

  /**
   * @param UserID the UserID to set
   */
  public void setUserID(String UserID){
    this.UserID = UserID;
  }

  /**
   * @return the ActivityID
   */
  public String getActivityID(){
    return ActivityID;
  }

  /**
   * @param ActivityID the ActivityID to set
   */
  public void setActivityID(String ActivityID){
    this.ActivityID = ActivityID;
  }

  /**
   * @return the StartTime
   */
  public String getStartTime(){
    return RecordedTime;
  }

  /**
   * @param StartTime the StartTime to set
   */
  public void setStartTime(String RecordedTime){
    this.RecordedTime = RecordedTime;
  }

  /**
   * @return the ActivityTargetDuration
   */
  public String getActivityTargetDuration(){
    return ActivityTargetDuration;
  }

  /**
   * @param ActivityTargetDuration the ActivityTargetDuration to set
   */
  public void setActivityTargetDuration(String ActivityTargetDuration){
    this.ActivityTargetDuration = ActivityTargetDuration;
  }

  /**
   * @return the LogID
   */
  public String getLogID(){
    return LogID;
  }

  /**
   * @param LogID the LogID to set
   */
  public void setLogID(String LogID){
    this.LogID = LogID;
  }

  /**
   * @return the TotalViolations
   */
  public String getTotalViolations(){
    return TotalViolations;
  }

  /**
   * @param TotalViolations the TotalViolations to set
   */
  public void setTotalViolations(String TotalViolations){
    this.TotalViolations = TotalViolations;
  }

}
