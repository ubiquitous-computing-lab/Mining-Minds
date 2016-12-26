/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.scl.outputModel;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

/**
 * 
 * @author User
 */
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
public class SCLResponse {

  List<String> result;

  public SCLResponse() {
    this.result = new ArrayList<>();
  }

  public List<String> getResult() {
    return result;
  }

  public void setResult(List<String> result) {
    this.result = result;
  }

}
