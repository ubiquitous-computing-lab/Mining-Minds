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
package org.uclab.scl.Utilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.uclab.scl.datamodel.RiskFactors;

/**
 * 
 * @author User
 */
public class RiskFactorFinderUtility {

  private List<String> riskFactors;
  
  private RiskFactorFinderUtility(){}
  public static RiskFactorFinderUtility getRiskFactorFinderUtility(){
    return RiskFactorFinderUtilityHolder.INSTANCE;
  }
  /**
   * @return the riskFactors
   */
  public Map<String, String> getRiskFactors(List<RiskFactors> rf) {

    Map<String, String> riskDictionary = new HashMap<String, String>();

    Iterator<RiskFactors> itr = rf.iterator();

    while (itr.hasNext()) {

      switch (itr.next().getRiskFactorId()) {
      case 1:

        riskDictionary.put("Diabetes", "Yes");
        // riskFactors.add("Diabetes");
        break;

      case 2:

        riskDictionary.put("Cardiovascular", "Yes");
        // riskFactors.add("Cardiovascular");
        break;

      case 3:

        riskDictionary.put("Hypertension", "Yes");
        // riskFactors.add("Hypertension");
        break;

      case 4:

        riskDictionary.put("Pregnant", "Yes");
        // riskFactors.add("Pregnant");
        break;

      case 5:
        riskDictionary.put("Normal", "Yes");
        // riskFactors.add("Normal");
        break;

      case 6:

        riskDictionary.put("None", "Yes");
        // riskFactors.add("Normal");
        break;
      case 7:
        riskDictionary.put("Back Pain", "Yes");
        break;

      // default:
      //
      // riskDictionary.put("None", "Yes");
      // riskFactors.add("None");
      // break;
      }

    }

    return riskDictionary;
  }

  /**
   * @param riskFactors
   *          the riskFactors to set
   */
  public void setRiskFactors(List<String> riskFactors) {
    this.riskFactors = riskFactors;
  }
  
  private static class RiskFactorFinderUtilityHolder{
    private static final RiskFactorFinderUtility INSTANCE = new RiskFactorFinderUtility();
  }
}
