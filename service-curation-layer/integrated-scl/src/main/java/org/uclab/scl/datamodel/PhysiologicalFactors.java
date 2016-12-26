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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhysiologicalFactors implements Serializable{

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

  private float weight;

  private float idealWeight;

  private float height;

  private long userId;

  private float targetWeight;

  private int physiologicalFactorId;

  private String date;

  private String weightStatus;

  private float bmi = 0;

  public PhysiologicalFactors() {
    this.weightStatus = "None";
    weightStatus = CalculateWeightStatus();
  }

  private String CalculateWeightStatus() {
    String weightStatus1 = "";
    // this.setBmi(this.getWeight()/(float)Math.pow((this.getHeight() * 0.01),
    // 2));
    this.bmi = this.getWeight()
        / (float) Math.pow((this.getHeight() * 0.01), 2);
    // setBmi((float) Math.round(getBmi() * 100) / 100);
    this.setBmi((float) Math.round(this.bmi));

    if (getBmi() < 18.5) {
      weightStatus1 = "Overweight";
    } else if (getBmi() >= 18.5 && getBmi() <= 24.9) {
      weightStatus1 = "Normal";
    } else if (getBmi() >= 25.0 && getBmi() <= 29.9) {
      weightStatus1 = "Overweight";
    } else {
      weightStatus1 = "Obese";
    }

    return weightStatus1;
  }

  public String getWeightStatus() {
    return this.weightStatus;
  }

  public float getIdealWeight() {
    return idealWeight;
  }

  public void setIdealWeight(float idealWeight) {
    this.idealWeight = idealWeight;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public float getTargetWeight() {
    return targetWeight;
  }

  public void setTargetWeight(float targetWeight) {
    this.targetWeight = targetWeight;
  }

  public int getPhysiologicalFactorId() {
    return physiologicalFactorId;
  }

  public void setPhysiologicalFactorId(int physiologicalFactorId) {
    this.physiologicalFactorId = physiologicalFactorId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "ClassPojo [weight = " + getWeight() + ", idealWeight = "
        + idealWeight + ", height = " + getHeight() + ", userId = " + userId
        + ", targetWeight = " + targetWeight + ", physiologicalFactorId = "
        + physiologicalFactorId + ", date = " + date + "]";
  }

  /**
   * @param weightStatus
   *          the weightStatus to set
   */
  public void setWeightStatus(String weightStatus) {
    this.weightStatus = weightStatus;
  }

  /**
   * @return the bmi
   */
  public float getBmi() {
    return bmi;
  }

  /**
   * @param bmi
   *          the bmi to set
   */
  public void setBmi(float bmi) {
    this.bmi = bmi;
  }

  /**
   * @return the weight
   */
  public float getWeight() {
    return weight;
  }

  /**
   * @param weight
   *          the weight to set
   */
  public void setWeight(float weight) {
    this.weight = weight;
  }

  /**
   * @return the height
   */
  public float getHeight() {
    return height;
  }

  /**
   * @param height
   *          the height to set
   */
  public void setHeight(float height) {
    this.height = height;
  }

}