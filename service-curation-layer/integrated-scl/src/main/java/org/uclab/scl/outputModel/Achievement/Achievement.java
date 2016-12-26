/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.scl.outputModel.Achievement;

/**
 * 
 * @author User
 */
public class Achievement {

  private String achievementDate;

  private long userID;

  private String achievementValue;

  private String achievementDescription;

  private String achievementStatusDescription;

  private int achievementStatusID;

  private long achievementID;

  private String supportingLink;

  public String getAchievementDate() {
    return achievementDate;
  }

  public void setAchievementDate(String achievementDate) {
    this.achievementDate = achievementDate;
  }

  public long getUserID() {
    return userID;
  }

  public void setUserID(long userID) {
    this.userID = userID;
  }

  public String getAchievementValue() {
    return achievementValue;
  }

  public void setAchievementValue(String achievementValue) {
    this.achievementValue = achievementValue;
  }

  public String getAchievementDescription() {
    return achievementDescription;
  }

  public void setAchievementDescription(String achievementDescription) {
    this.achievementDescription = achievementDescription;
  }

  public String getAchievementStatusDescription() {
    return achievementStatusDescription;
  }

  public void setAchievementStatusDescription(
      String achievementStatusDescription) {
    this.achievementStatusDescription = achievementStatusDescription;
  }

  public int getAchievementStatusID() {
    return achievementStatusID;
  }

  public void setAchievementStatusID(int achievementStatusID) {
    this.achievementStatusID = achievementStatusID;
  }

  public long getAchievementID() {
    return achievementID;
  }

  public void setAchievementID(long achievementID) {
    this.achievementID = achievementID;
  }

  public String getSupportingLink() {
    return supportingLink;
  }

  public void setSupportingLink(String supportingLink) {
    this.supportingLink = supportingLink;
  }

  @Override
  public String toString() {
    return "ClassPojo [achievementDate = " + achievementDate + ", userID = "
        + userID + ", achievementValue = " + achievementValue
        + ", achievementDescription = " + achievementDescription
        + ", achievementStatusDescription = " + achievementStatusDescription
        + ", achievementStatusID = " + achievementStatusID
        + ", achievementID = " + achievementID + ", supportingLink = "
        + supportingLink + "]";
  }

}
