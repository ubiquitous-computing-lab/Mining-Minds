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
public class PhyActivityExplaination {

  private String factCategoryDescription;

  private long recommendationExplanationID;

  private String factExplanation;

  private String recommendationID;

  private int factCategoryID;

  public String getFactCategoryDescription() {
    return factCategoryDescription;
  }

  public void setFactCategoryDescription(String factCategoryDescription) {
    this.factCategoryDescription = factCategoryDescription;
  }

  public long getRecommendationExplanationID() {
    return recommendationExplanationID;
  }

  public void setRecommendationExplanationID(long recommendationExplanationID) {
    this.recommendationExplanationID = recommendationExplanationID;
  }

  public String getFactExplanation() {
    return factExplanation;
  }

  public void setFactExplanation(String factExplanation) {
    this.factExplanation = factExplanation;
  }

  public String getRecommendationID() {
    return recommendationID;
  }

  public void setRecommendationID(String recommendationID) {
    this.recommendationID = recommendationID;
  }

  public int getFactCategoryID() {
    return factCategoryID;
  }

  public void setFactCategoryID(int factCategoryID) {
    this.factCategoryID = factCategoryID;
  }

  @Override
  public String toString() {
    return "ClassPojo [factCategoryDescription = " + factCategoryDescription
        + ", recommendationExplanationID = " + recommendationExplanationID
        + ", factExplanation = " + factExplanation + ", recommendationID = "
        + recommendationID + ", factCategoryID = " + factCategoryID + "]";
  }

}
