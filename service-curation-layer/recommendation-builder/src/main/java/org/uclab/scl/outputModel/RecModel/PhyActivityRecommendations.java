/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.scl.outputModel.RecModel;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.uclab.scl.datamodel.User;
import org.uclab.scl.framework.recbuilder.RecIdentifierEnum;

/**
 * 
 * @author User
 */
public class PhyActivityRecommendations {

  // @JsonIgnore
  // private int testInt;
  //
  // @JsonProperty
  // public int getTestInt() {
  // return testInt;
  // }
  //
  // @JsonProperty
  // public void setTestInt(int testInt) {
  // this.testInt = testInt;
  // }

  private long recommendationID;

  private String conditionValue;

  private String recommendationIdenfier;

  private String recommendationDate;

  private int recommendationLevelID;

  private String recommendationDescription;

  private long situationID;

  private int recommendationStatusID;

  private int recommendationTypeID;

  private String recommendationLevelDescription;

  private String recommendationStatusDescription;

  private String recommendationTypeDescription;

  public String getRecommendationLevelDescription() {
    return recommendationLevelDescription;
  }

  public void setRecommendationLevelDescription(
      String recommendationLevelDescription) {
    this.recommendationLevelDescription = recommendationLevelDescription;
  }

  public String getRecommendationStatusDescription() {
    return recommendationStatusDescription;
  }

  public void setRecommendationStatusDescription(
      String recommendationStatusDescription) {
    this.recommendationStatusDescription = recommendationStatusDescription;
  }

  public String getRecommendationTypeDescription() {
    return recommendationTypeDescription;
  }

  public void setRecommendationTypeDescription(
      String recommendationTypeDescription) {
    this.recommendationTypeDescription = recommendationTypeDescription;
  }

  public String getRecommendationIdentifier() {
    return recommendationIdenfier;
  }

  public void setRecommendationIdentifier(String recommendationIdentifier) {
    this.recommendationIdenfier = recommendationIdentifier;
  }

  public String getConditionValue() {
    return conditionValue;
  }

  public void setConditionValue(String conditionValue) {
    this.conditionValue = conditionValue;
  }

  public String getRecommendationDate() {
    return recommendationDate;
  }

  public void setRecommendationDate(String recommendationDate) {
    this.recommendationDate = recommendationDate;
  }

  public int getRecommendationLevelID() {
    return recommendationLevelID;
  }

  public void setRecommendationLevelID(int recommendationLevelID) {
    this.recommendationLevelID = recommendationLevelID;
  }

  public String getRecommendationDescription() {
    return recommendationDescription;
  }

  public void setRecommendationDescription(String recommendationDescription) {
    this.recommendationDescription = recommendationDescription;
  }

  public long getSituationID() {
    return situationID;
  }

  public void setSituationID(long situationID) {
    this.situationID = situationID;
  }

  public int getRecommendationStatusID() {
    return recommendationStatusID;
  }

  public void setRecommendationStatusID(int recommendationStatusID) {
    this.recommendationStatusID = recommendationStatusID;
  }

  public int getRecommendationTypeID() {
    return recommendationTypeID;
  }

  public void setRecommendationTypeID(int recommendationTypeID) {
    this.recommendationTypeID = recommendationTypeID;
  }

  public long getRecommendationID() {
    return recommendationID;
  }

  public void setRecommendationID(long recommendationID) {
    this.recommendationID = recommendationID;
  }

  @Override
  public String toString() {
    return "ClassPojo [conditionValue = " + conditionValue
        + ", recommendationIdentifier = "
        + RecIdentifierEnum.SittingOneHour01
        + ", recommendationDate = " + recommendationDate
        + ", recommendationLevelID = " + recommendationLevelID
        + ", recommendationDescription = " + recommendationDescription
        + ", situationID = " + situationID + ", recommendationStatusID = "
        + recommendationStatusID + ", recommendationTypeID = "
        + recommendationTypeID + "]";
  }

}
