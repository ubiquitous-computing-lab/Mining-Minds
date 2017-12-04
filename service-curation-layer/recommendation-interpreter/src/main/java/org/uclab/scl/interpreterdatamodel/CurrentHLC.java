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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class CurrentHLC implements Serializable {

  private Long duration;
  private String hLCLabel;
  private String startTime;
  private String endTime;
  private Long userId;
  private Long userRecognizedHLCId;

  /**
   * @return the duration
   */
  @JsonIgnore
  public Long getDuration() {
    return duration;
  }

  /**
   * @param duration
   *          the duration to set
   */
  public void setDuration(Long duration) {
    this.duration = duration;
  }

  /**
   * @return the hLCLabel
   */
  public String gethLCLabel() {
    return hLCLabel;
  }

  /**
   * @param hLCLabel
   *          the hLCLabel to set
   */
  public void sethLCLabel(String hLCLabel) {
    this.hLCLabel = hLCLabel;
  }

  /**
   * @return the startTime
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * @param startTime
   *          the startTime to set
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * @return the userId
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * @param userId
   *          the userId to set
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * @return the userRecognizedHLCId
   */
  public Long getUserRecognizedHLCId() {
    return userRecognizedHLCId;
  }

  /**
   * @param userRecognizedHLCId
   *          the userRecognizedHLCId to set
   */
  public void setUserRecognizedHLCId(Long userRecognizedHLCId) {
    this.userRecognizedHLCId = userRecognizedHLCId;
  }

  /**
   * @return the endTime
   */
  @JsonIgnore
  public String getEndTime() {
    return endTime;
  }

  /**
   * @param endTime
   *          the endTime to set
   */
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }
}
