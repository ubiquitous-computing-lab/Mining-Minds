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

import static org.uclab.scl.framework.RecInterpreter.PARecommender.MultiRecAlternativeRecEval.recFiltrationAlternative;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.datamodel.contextMapper.RecommendationMapper;
import org.uclab.scl.framework.RecInterpreter.AbstractRecEvaluator;

public class MultipleRecEvaluator extends AbstractRecEvaluator {
	private static Logger LOG = LogManager.getRootLogger();

  static HashMap<String, String> mapWeatherRec = new HashMap<>();
  static HashMap<String, String> mapDisableRec = new HashMap<>();
  static HashMap<String, String> mapRec = new HashMap<>();
  
  @SuppressWarnings("unchecked")
  private static int[] evalRecommendations(String rec, String userid, String context) throws Exception {
    return null;
  }
  
/**
 * Main purpose of this function is to evaluate user's preferences against the applicable recommendations in the given context
 * 
 * @param AR
 * @param userid
 * @param context_string
 * @return
 * @throws Exception
 */
  private static int[] multiRecPreference(int[] AR, String userid, String context_string) throws Exception {
    int[] ARP = PreferenceTesting.mainPrefTest (AR, userid, context_string);
    int[] prefAggregate = new int[AR.length];
    for (int i = 0; i < AR.length; i++) {
      if ((AR[i] == 1) && (ARP[i] == 1))
        prefAggregate[i] = 1;
      else
        prefAggregate[i] = 0;
    }
    return (prefAggregate);
  }
/**
 * Returns evaluations for the multi-recommendation case
 * <p>
 * 
 * @param rec
 * @param userid
 * @param context
 * @return evaluations
 */
  @SuppressWarnings("unchecked")
  @Override
  public int[] evaluate(String rec, String userid, String context){
    RecommendationMapper recommendationMapper = new RecommendationMapper();
    LOG.debug("Context String: " + context);
    int[] contentResult = null;
    try {
      contentResult = MultiRecContentTesting.multiRecContextTest(context); //IMP
    } catch (Exception e) {
      e.printStackTrace();
    }
    LOG.debug("Received Rec_List contains: " + rec);
    String[] temp = rec.split(",");
    try {
      mapRec = recommendationMapper.recMapper();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    int[] alternativeRecList = new int[mapRec.size() - 1];
    int[] finalRec = new int[mapRec.size() - 1];
    java.util.Arrays.fill(alternativeRecList, 0);
    LOG.debug("Content Result vector after ANDing Contextual Matrix");
    for (int i = 0; i < contentResult.length; i++)
      LOG.debug(contentResult[i] + " ");
    LOG.debug(" ");
    for (int i = 0; i < temp.length; i++) {
      String temp1 = mapRec.containsKey(temp[i]) ? mapRec.get(temp[i]) : mapRec.get(null);
     	LOG.debug(temp1);
      int index = 0;
      if (temp1 != null)
        index = Integer.parseInt(temp1);
      else {
        LOG.debug("Recommendation NOT FOUND in the repository");
        LOG.debug("Default case applied");
        index = Integer.parseInt(mapRec.get("D"));
        LOG.debug("index value " + index);
      }
      if (contentResult[index] == 1)
        alternativeRecList[index] = 1;
    }
    LOG.debug("AR_list after Context Checking with Recommendation(s)");
    for (int i = 0; i < alternativeRecList.length; i++)
      LOG.debug(alternativeRecList[i] + " ");
    LOG.debug(" ");
    int sum = 0;
    int prefSum = 0;
    for (int i = 0; i < alternativeRecList.length; i++)
      sum += alternativeRecList[i];
    if (sum != 0)
    {
      LOG.debug("AR_LIST IS NOT EMPTY");
      if (sum == 1)
      {
        LOG.debug("AR LIST COINTAINS ONLY ONE REC :");
        System.arraycopy(alternativeRecList, 0, finalRec, 0, alternativeRecList.length);
      } else { 
        LOG.debug("AR LIST CONTAINS MULTIPLE RECs");
        int[] ARP=null;
        try {
          ARP = multiRecPreference(alternativeRecList, userid, context); //IMP
        } catch (Exception e) {
          e.printStackTrace();
        }
        for (int i = 0; i < ARP.length; i++) {
          LOG.debug(ARP[i] + " ");
          prefSum += ARP[i];
        }
        if (prefSum == 0) {
          LOG.debug("ARP is Empty so back to AR_List");
          System.arraycopy(alternativeRecList, 0, finalRec, 0, alternativeRecList.length);
        } else {
          LOG.debug("Forwarding ARP list");
          System.arraycopy(ARP, 0, finalRec, 0, ARP.length);
        }
      }
    } else {
      LOG.debug("AR list is Empty");
      String R1 = temp[0];
      try {
        finalRec = recFiltrationAlternative(R1, context, userid); //IMP
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    int[] tempArr = new int[mapRec.size() - 1];
    boolean flag = true;
    for (int i = 0; i < finalRec.length; i++) {
      if ((finalRec[i] == 1) && (flag == true)) {
        tempArr[i] = 1;
        flag = false;
      } else
        tempArr[i] = 0;
    }
    return (tempArr);
  }
}
