/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.scl.outputModel.InterpretationModel;

import java.util.List;

/**
 * 
 * @author User
 */
public class InterpretedRecommendations {

  private String recActivity;
  private String actDuration;
  private String recDescription;
  private String recURL;
  private String currentContext;

  private String foodItemList;
  private String foodDescription;

  /**
   * @return the recActivity
   */
  public String getRecActivity() {
    return recActivity;
  }

  /**
   * @param recActivity
   *          the recActivity to set
   */
  public void setRecActivity(String recActivity) {
    this.recActivity = recActivity;
  }

  /**
   * @return the actDuration
   */
  public String getActDuration() {
    return actDuration;
  }

  /**
   * @param actDuration
   *          the actDuration to set
   */
  public void setActDuration(String actDuration) {
    this.actDuration = actDuration;
  }

  /**
   * @return the recDescription
   */
  public String getRecDescription() {
    return recDescription;
  }

  /**
   * @param recDescription
   *          the recDescription to set
   */
  public void setRecDescription(String recDescription) {
    this.recDescription = recDescription;
  }

  /**
   * @return the recURL
   */
  public String getRecURL() {
    return recURL;
  }

  /**
   * @param recURL
   *          the recURL to set
   */
  public void setRecURL(String recURL) {
    this.recURL = recURL;
  }

  /**
   * @return the currentContext
   */
  public String getCurrentContext() {
    return currentContext;
  }

  /**
   * @param currentContext
   *          the currentContext to set
   */
  public void setCurrentContext(String currentContext) {
    this.currentContext = currentContext;
  }

  /**
   * @return the foodItemList
   */
  public String getFoodItemList() {
    return foodItemList;
  }

  /**
   * @param foodItemList
   *          the foodItemList to set
   */
  public void setFoodItemList(String foodItemList) {
    this.foodItemList = foodItemList;
  }

  /**
   * @return the foodDescription
   */
  public String getFoodDescription() {
    return foodDescription;
  }

  /**
   * @param foodDescription
   *          the foodDescription to set
   */
  public void setFoodDescription(String foodDescription) {
    this.foodDescription = foodDescription;
  }

}
