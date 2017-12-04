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

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class FoodLog implements Serializable {
  
  @JsonProperty("id")
  private long id;
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

  @JsonProperty("requestType")
  private String requestType;
  
  @JsonProperty("eatingTime")
  private String eatingTime;
  @JsonProperty("foodName")
  private String foodName;
  @JsonProperty("totalCarbohydrate")
  private long totalCarbohydrate;
  @JsonProperty("totalFat")
  private long totalFat;
  @JsonProperty("totalFoodItem")
  private long totalFoodItem;
  @JsonProperty("totalProtein")
  private long totalProtein;
  @JsonProperty("userId")
  private long userId;

  // private String totalCarbs;
  // private String totalProts;

  /**
   * 
   * @return The eatingTime
   */
  @JsonProperty("eatingTime")
  public String getEatingTime() {
    return eatingTime;
  }

  /**
   * 
   * @param eatingTime
   *          The eatingTime
   */
  @JsonProperty("eatingTime")
  public void setEatingTime(String eatingTime) {
    this.eatingTime = eatingTime;
  }

  /**
   * 
   * @return The foodName
   */
  @JsonProperty("foodName")
  public String getFoodName() {
    return foodName;
  }

  /**
   * 
   * @param foodName
   *          The foodName
   */
  @JsonProperty("foodName")
  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  /**
   * 
   * @return The totalCarbohydrate
   */
  @JsonProperty("totalCarbohydrate")
  public long getTotalCarbohydrate() {
    return totalCarbohydrate;
  }

  /**
   * 
   * @param totalCarbohydrate
   *          The totalCarbohydrate
   */
  @JsonProperty("totalCarbohydrate")
  public void setTotalCarbohydrate(long totalCarbohydrate) {
    this.totalCarbohydrate = totalCarbohydrate;
  }

  /**
   * 
   * @return The totalFat
   */
  @JsonProperty("totalFat")
  public long getTotalFat() {
    return totalFat;
  }

  /**
   * 
   * @param totalFat
   *          The totalFat
   */
  @JsonProperty("totalFat")
  public void setTotalFat(long totalFat) {
    this.totalFat = totalFat;
  }

  /**
   * 
   * @return The totalFoodItem
   */
  @JsonProperty("totalFoodItem")
  public long getTotalFoodItem() {
    return totalFoodItem;
  }

  /**
   * 
   * @param totalFoodItem
   *          The totalFoodItem
   */
  @JsonProperty("totalFoodItem")
  public void setTotalFoodItem(long totalFoodItem) {
    this.totalFoodItem = totalFoodItem;
  }

  /**
   * 
   * @return The totalProtein
   */
  @JsonProperty("totalProtein")
  public long getTotalProtein() {
    return totalProtein;
  }

  /**
   * 
   * @param totalProtein
   *          The totalProtein
   */
  @JsonProperty("totalProtein")
  public void setTotalProtein(long totalProtein) {
    this.totalProtein = totalProtein;
  }

  /**
   * 
   * @return The userId
   */
  @JsonProperty("userId")
  public long getUserId() {
    return userId;
  }

  /**
   * 
   * @param userId
   *          The userId
   */
  @JsonProperty("userId")
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * @return the totalCarbs
   */
  // public String getTotalCarbs() {
  // return totalCarbs;
  // }
  //
  // /**
  // * @param totalCarbs the totalCarbs to set
  // */
  // public void setTotalCarbs(String totalCarbs) {
  // this.totalCarbs = totalCarbs;
  // }
  //
  // /**
  // * @return the totalProts
  // */
  // public String getTotalProts() {
  // return totalProts;
  // }
  //
  // /**
  // * @param totalProts the totalProts to set
  // */
  // public void setTotalProts(String totalProts) {
  // this.totalProts = totalProts;
  // }

  @Override
  public String toString() {
    String keyValue = "\"Consumed Fat\":\"" + this.getTotalFat() + "\",\n";
    keyValue += "\"Consumed Protein\":\"" + this.getTotalProtein() + "\",\n";
    keyValue += "\"Consumed Carbohydrate\":\"" + this.getTotalCarbohydrate()
        + "\",";

    return keyValue;
  }

}
