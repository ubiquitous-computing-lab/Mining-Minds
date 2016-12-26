/* Copyright [2016] [Syed Imran Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.uclab.scl.framework.RecInterpreter.PARecommender;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.datamodel.contextMapper.PreferenceMapper;

public class PreferenceTesting {
  private static Logger LOG = LogManager.getRootLogger();
  static HashMap<String, String> mapPreference = new HashMap<>();
  
  /**
   * Preference testing for the case of a single received recommendation and alternatively more than one applications are applicable 
   * 
   * @param matResultVector
   * @param userid
   * @param context
   * @return
   * @throws Exception
   */
  public static int[] mainPrefTest(int[] matResultVector, String userid, String context) throws Exception {
    int[] ARP = PreferenceTesting.preferenceEvaluation(matResultVector,userid, context);
    int[] prefAggregate = new int[matResultVector.length];
    for (int i = 0; i < matResultVector.length; i++) {
      if ((matResultVector[i] == 1) && (ARP[i] == 1))
        prefAggregate[i] = 1;
      else
        prefAggregate[i] = 0;
    }
    return (prefAggregate);
  }
  
  /**
   * Main Preference evaluation logic for the case of a single received recommendation and alternatively more than one applications are applicable 
   * 
   * @param matResultVector
   * @param userid
   * @param context
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public static int[] preferenceEvaluation(int[] matResultVector, String userid, String context)
      throws Exception {
    // Check Context with Preference
    PreferenceMapper mapPref = new PreferenceMapper();

    mapPreference = mapPref.preferMapper();

    String getPref = mapPreference.containsKey(userid) ? mapPreference.get(userid) : mapPreference.get(null);
    if (getPref == null) {
      LOG.debug("Preference NOT FOUND in the repository");
      LOG.debug("Default case applied");
      getPref = mapPreference.get("dc");
    }
    
    //converting string array into int array
    String[] prefer1 = getPref.split(",");
    int[] prefer = new int[prefer1.length];
    for (int i = 0; i < prefer1.length; i++) {
      String numberAsString = prefer1[i];
      prefer[i] = Integer.parseInt(numberAsString);
    }

    for (int i = 0; i < matResultVector.length; i++)
      if ((matResultVector[i] == 1) && (prefer[i]) == 1) {
        matResultVector[i] = 1;
      } else {
        matResultVector[i] = 0;
      }
    return (matResultVector);
  } // testPreference
}

