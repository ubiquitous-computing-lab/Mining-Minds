/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.scl.outputModel.RecModel;

/**
 * 
 * @author User
 */
public class PhyActivityFacts {

  private String factDate;

  private long factID;

  private String factDescription;

  private long situationID;

  private String supportingLinks;

  private int factStatusID;

  private String factStatusDescription;

  public String getFactDate() {
    return factDate;
  }

  public void setFactDate(String factDate) {
    this.factDate = factDate;
  }

  public long getFactID() {
    return factID;
  }

  public void setFactID(long factID) {
    this.factID = factID;
  }

  public String getFactDescription() {
    return factDescription;
  }

  public void setFactDescription(String factDescription) {
    this.factDescription = factDescription;
  }

  public long getSituationID() {
    return situationID;
  }

  public void setSituationID(long situationID) {
    this.situationID = situationID;
  }

  public String getSupportingLinks() {
    return supportingLinks;
  }

  public void setSupportingLinks(String supportingLinks) {
    this.supportingLinks = supportingLinks;
  }

  public int getFactStatusID() {
    return factStatusID;
  }

  public void setFactStatusID(int factStatusID) {
    this.factStatusID = factStatusID;
  }

  public String getFactStatusDescription() {
    return factStatusDescription;
  }

  public void setFactStatusDescription(String factStatusDescription) {
    this.factStatusDescription = factStatusDescription;
  }

  @Override
  public String toString() {
    return "ClassPojo [factDate = " + factDate + ", factID = " + factID
        + ", factDescription = " + factDescription + ", situationID = "
        + situationID + ", supportingLinks = " + supportingLinks
        + ", factStatusID = " + factStatusID + ", factStatusDescription = "
        + factStatusDescription + "]";
  }

}
