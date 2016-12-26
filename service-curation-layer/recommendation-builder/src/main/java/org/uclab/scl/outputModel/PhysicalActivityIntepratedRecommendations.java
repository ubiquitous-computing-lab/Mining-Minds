/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.scl.outputModel;

import java.util.ArrayList;
import java.util.List;

import org.uclab.scl.outputModel.RecModel.PhyActivityFacts;
import org.uclab.scl.outputModel.RecModel.PhyActivityRecommendations;

/**
 * 
 * @author User
 */
public class PhysicalActivityIntepratedRecommendations {

  private List<PhyActivityRecommendations> phyActRecommendation = new ArrayList<PhyActivityRecommendations>();
  private List<PhyActivityFacts> phyActivityFacts = new ArrayList<PhyActivityFacts>();

  public List<PhyActivityRecommendations> getPhyActRecommendation() {
    return phyActRecommendation;
  }

  public void setPhyActRecommendation(
      List<PhyActivityRecommendations> phyActRecommendation) {
    this.phyActRecommendation = phyActRecommendation;
  }

  /**
   * @return the phyActivityFacts
   */
  public List<PhyActivityFacts> getPhyActivityFacts() {
    return phyActivityFacts;
  }

  /**
   * @param phyActivityFacts
   *          the phyActivityFacts to set
   */
  public void setPhyActivityFacts(List<PhyActivityFacts> phyActivityFacts) {
    this.phyActivityFacts = phyActivityFacts;
  }

}
