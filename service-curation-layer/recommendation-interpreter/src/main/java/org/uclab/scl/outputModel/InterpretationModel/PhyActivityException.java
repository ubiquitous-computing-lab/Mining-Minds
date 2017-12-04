/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.scl.outputModel.InterpretationModel;

/**
 * 
 * @author User
 */
public class PhyActivityException {

  private long recommendationExceptionID;

  private String exception;

  private String customRule;

  private long recommendationID;

  private String exceptionReason;

  public long getRecommendationExceptionID() {
    return recommendationExceptionID;
  }

  public void setRecommendationExceptionID(long recommendationExceptionID) {
    this.recommendationExceptionID = recommendationExceptionID;
  }

  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public String getCustomRule() {
    return customRule;
  }

  public void setCustomRule(String customRule) {
    this.customRule = customRule;
  }

  public long getRecommendationID() {
    return recommendationID;
  }

  public void setRecommendationID(long recommendationID) {
    this.recommendationID = recommendationID;
  }

  public String getExceptionReason() {
    return exceptionReason;
  }

  public void setExceptionReason(String exceptionReason) {
    this.exceptionReason = exceptionReason;
  }

  @Override
  public String toString() {
    return "ClassPojo [recommendationExceptionID = "
        + recommendationExceptionID + ", exception = " + exception
        + ", customRule = " + customRule + ", recommendationID = "
        + recommendationID + ", exceptionReason = " + exceptionReason + "]";
  }

}
