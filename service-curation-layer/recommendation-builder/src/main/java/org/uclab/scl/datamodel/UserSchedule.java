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

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class UserSchedule {

  private String startTime;

  private long userID;

  private String scheduledTask;

  private String endTime;

  private long userScheduleID;

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public long getUserID() {
    return userID;
  }

  public void setUserID(long userID) {
    this.userID = userID;
  }

  public String getScheduledTask() {
    return scheduledTask;
  }

  public void setScheduledTask(String scheduledTask) {
    this.scheduledTask = scheduledTask;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public long getUserScheduleID() {
    return userScheduleID;
  }

  public void setUserScheduleID(long userScheduleID) {
    this.userScheduleID = userScheduleID;
  }

  @Override
  public String toString() {
    return "ClassPojo [startTime = " + startTime + ", userID = " + userID
        + ", scheduledTask = " + scheduledTask + ", endTime = " + endTime
        + ", userScheduleID = " + userScheduleID + "]";
  }
}
