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

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class LifeLogData {

  private int activityId;

  private long duration;

  private String activityDescription;

  private long userId;

  private String activityDate;

  private String userRecognizedActivityAccumulateID;

  public int getActivityId() {
    return activityId;
  }

  public void setActivityId(int activityId) {
    this.activityId = activityId;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public String getActivityDescription() {
    return activityDescription;
  }

  public void setActivityDescription(String activityDescription) {
    this.activityDescription = activityDescription;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(String activityDate) {
    this.activityDate = activityDate;
  }

  public String getUserRecognizedActivityAccumulateID() {
    return userRecognizedActivityAccumulateID;
  }

  public void setUserRecognizedActivityAccumulateID(
      String userRecognizedActivityAccumulateID) {
    this.userRecognizedActivityAccumulateID = userRecognizedActivityAccumulateID;
  }

  @Override
  public String toString() {
    return "ClassPojo [activityId = " + activityId + ", duration = " + duration
        + ", activityDescription = " + activityDescription + ", userId = "
        + userId + ", activityDate = " + activityDate
        + ", userRecognizedActivityAccumulateID = "
        + userRecognizedActivityAccumulateID + "]";
  }
}
