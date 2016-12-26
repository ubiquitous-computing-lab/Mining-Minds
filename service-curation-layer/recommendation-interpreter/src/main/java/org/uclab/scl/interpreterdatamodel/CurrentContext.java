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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
// @JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentContext implements Serializable {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("userID")
  private Long userID;

  @JsonProperty("startTime")
  private String startTime;

  @JsonProperty("endTime")
  private String endTime;

  @JsonProperty("objListUserDetectedLocation")
  private Set<CurrentLocation> objListUserDetectedLocation = new HashSet<CurrentLocation>();

  @JsonProperty("objListUserRecognizedEmotion")
  private List<CurrentEmotion> objListUserRecognizedEmotion = new ArrayList<CurrentEmotion>();

  @JsonProperty("objListUserRecognizedHLC")
  private List<CurrentHLC> objListUserRecognizedHLC = new ArrayList<CurrentHLC>();

  @JsonProperty("objListUserRecognizedActivity")
  private List<RecognizedActivity> objListUserRecognizedActivity = new ArrayList<RecognizedActivity>();

  private List<CurrentWeather> currentWeather = new ArrayList<CurrentWeather>();

  /**
   * @return the objListUserDetectedLocation
   */
  public Set<CurrentLocation> getCurrentLocation() {
    return objListUserDetectedLocation;
  }

  /**
   * @param currentLocation
   *          the objListUserDetectedLocation to set
   */
  public void setCurrentLocation(Set<CurrentLocation> currentLocation) {
    this.objListUserDetectedLocation = currentLocation;
  }

  /**
   * @return the objListUserRecognizedEmotion
   */
  public List<CurrentEmotion> getCurrentEmotion() {
    return objListUserRecognizedEmotion;
  }

  /**
   * @param currentEmotion
   *          the objListUserRecognizedEmotion to set
   */
  public void setCurrentEmotion(List<CurrentEmotion> currentEmotion) {
    this.objListUserRecognizedEmotion = currentEmotion;
  }

  /**
   * @return the objListUserRecognizedHLC
   */
  public List<CurrentHLC> getCurrentHLC() {
    return objListUserRecognizedHLC;
  }

  /**
   * @param currentHLC
   *          the objListUserRecognizedHLC to set
   */
  public void setCurrentHLC(List<CurrentHLC> currentHLC) {
    this.objListUserRecognizedHLC = currentHLC;
  }

  /**
   * @return the objListUserRecognizedActivity
   */
  public List<RecognizedActivity> getRecActivity() {
    return objListUserRecognizedActivity;
  }

  /**
   * @param recActivity
   *          the objListUserRecognizedActivity to set
   */
  public void setRecActivity(List<RecognizedActivity> recActivity) {
    this.objListUserRecognizedActivity = recActivity;
  }

  /**
   * @return the currentWeather
   */
  @JsonIgnore
  public List<CurrentWeather> getCurrentWeather() {
    return currentWeather;
  }

  /**
   * @param currentWeather
   *          the currentWeather to set
   */
  public void setCurrentWeather(List<CurrentWeather> currentWeather) {
    this.currentWeather = currentWeather;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the userID
   */
  public Long getUserID() {
    return userID;
  }

  /**
   * @param userID
   *          the userID to set
   */
  public void setUserID(Long userID) {
    this.userID = userID;
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
