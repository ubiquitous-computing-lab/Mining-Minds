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
public class CurrentWeather implements Serializable {

  private Long duration;
  private String weatherLabel;
  private String startTime;
  private String endTime;
  private Long userId;
  private Long userWeatherId;

  /**
   * @return the duration
   */
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
   * @return the weatherLabel
   */
  public String getWeatherLabel() {
    return weatherLabel;
  }

  /**
   * @param weatherLabel
   *          the weatherLabel to set
   */
  public void setWeatherLabel(String weatherLabel) {
    this.weatherLabel = weatherLabel;
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
   * @return the userWeatherId
   */
  public Long getUserWeatherId() {
    return userWeatherId;
  }

  /**
   * @param userWeatherId
   *          the userWeatherId to set
   */
  public void setUserWeatherId(Long userWeatherId) {
    this.userWeatherId = userWeatherId;
  }
}
