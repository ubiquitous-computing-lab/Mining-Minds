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

import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.datamodel.contextMapper.*;

public class MultiRecAlternativeRecEval {
	private static Logger LOG = LogManager.getRootLogger();

  static HashMap<String, String> mapWeatherRec = new HashMap<>();
  static HashMap<String, String> mapDisableRec = new HashMap<>();
  static HashMap<String, String> mapRec_rec = new HashMap<>();

  @SuppressWarnings("unchecked")
  public static int[] recFiltrationAlternative(String rec, String currentContext, String userid) throws Exception {

    RecommendationMapper recommendationMapper = new RecommendationMapper();

    LOG.debug("NOW IN MultiRecAltRec Func");
    mapRec_rec = recommendationMapper.recMapper();
    int[] contentResult = ContextEvaluator.generateContextMatrix(currentContext);

    String temp = mapRec_rec.containsKey(rec) ? mapRec_rec.get(rec) : mapRec_rec.get(null);
    int index = 0;
    if (temp != null)
      index = Integer.parseInt(temp);
    else {
      LOG.debug("Recommendation NOT FOUND in the repository");
      LOG.debug("Default case applied");
      index = Integer.parseInt(mapRec_rec.get("D"));
      LOG.debug("index value " + index);
    }

    int[] aggregate = new int[contentResult.length];
    System.arraycopy(contentResult, 0, aggregate, 0, contentResult.length);
    int sum = 0;
    int prefSum = 0;

    // Displaying AR_list
    LOG.debug("Displaying AR_List");
    for (int i = 0; i < aggregate.length; i++) {
      LOG.debug(aggregate[i] + " ");
      sum += aggregate[i];
    }
    LOG.debug(" ");

    int[] originalRec = new int[aggregate.length];
    java.util.Arrays.fill(originalRec, 0);

    if (aggregate[index] == 0) {
      LOG.debug("Alternative Rec needed");
      if (sum == 0) {
        LOG.debug("okay neither original rec is fine nor we have an alternative rec !!!");
        int temp_critical_ctxt[] = multiRecCriticalContext(currentContext);
        System.arraycopy(temp_critical_ctxt, 0, aggregate, 0,
            temp_critical_ctxt.length);
      } else if ((sum > 1) && (sum != 0)) { // PREFERENCE CHKING
        LOG.debug("okay now we have multiple alternative rec; now Preference check needed");
        int[] ARP = evalMultiRecPreferences(aggregate, userid, currentContext);
        LOG.debug("Displaying ARP_List");
        for (int i = 0; i < ARP.length; i++) {
          LOG.debug(ARP[i] + " ");
          prefSum += ARP[i];
          // aggregate[i] = ARP[i];
        }
        if (prefSum == 0) {
          LOG.debug("Well ARP is 0 because Preference chk made it so, GO BACK TO AR !!!");
          System.arraycopy(contentResult, 0, aggregate, 0, contentResult.length); // forward aggregate
        } else if ((prefSum > 1) && (prefSum != 0)) {
          LOG.debug("now we got multiple preference based recommendations, forwarding");
          System.arraycopy(ARP, 0, aggregate, 0, ARP.length);
        } else {
          LOG.debug("after Preference chk left with only 1 recommendation, forwarding");
          System.arraycopy(ARP, 0, aggregate, 0, ARP.length);
        }
      } else
        LOG.debug("we have only one alternative rec, so forward that to Explanation Generator");
    }
    // Alternative Rec code here
    else {
      LOG.debug("Recommendation is Fine as per the current context; so Forward to Explanation Generator");
      // Forward recommendation to explanation generator
      originalRec[index] = 1;
      System.arraycopy(originalRec, 0, aggregate, 0, originalRec.length);
    }
    return (aggregate);
  }

  private static int[] evalMultiRecPreferences(int[] AR, String userid,String receivedContext) throws Exception {
    int[] ARP = PreferenceTesting.mainPrefTest(AR, userid, receivedContext);
    int[] prefAggregate = new int[AR.length];
    for (int i = 0; i < AR.length; i++) {
      if ((AR[i] == 1) && (ARP[i] == 1))
        prefAggregate[i] = 1;
      else
        prefAggregate[i] = 0;
    }
    return (prefAggregate);
  }

  @SuppressWarnings("unchecked")
  private static int[] multiRecCriticalContext(String context_string) throws Exception {

    WeatherMapper weatherMapper = new WeatherMapper();
    DisableMapper disableMapper = new DisableMapper();

    mapWeatherRec = weatherMapper.weatherMapper();
    mapDisableRec = disableMapper.disableMapper();
    String context[] = context_string.split(",");

    char[] weather = mapWeatherRec.get(context[2]).replaceAll(",", "").toCharArray();
    char[] disable = mapDisableRec.get(context[4]).replaceAll(",", "").toCharArray();
    // disability and weather(rainy)
    int ccSum = 0;
    int[] result = new int[disable.length];
    boolean weatherRainy = context_string.contains("rainy");
    // CONTEXTUAL MATRIX
    for (int i = 0; i < disable.length; i++)
      if (weatherRainy) {
        // LOG.debugln("Rainy weather flag on");
        if ((weather[i] == '1') && (disable[i] == '1'))
          result[i] = 1;
        else
          result[i] = 0;
      } else {        
        result[i] = Character.getNumericValue(disable[i]);        
      }
    if (weatherRainy) {
      LOG.debug("Critical Context array for weather=rainy when CC vector ->  0");
      for (int i = 0; i < disable.length; i++) {
        ccSum += result[i];
        LOG.debug(result[i] + " ");
      }
    }
    int ccTemp;
    if (ccSum == 0) {
      // System.arraycopy( disable, 0, result, 0, disable.length );
      LOG.debug("Critical Context array for diability when CC vector is 0");
      for (int i = 0; i < disable.length; i++) {
        ccTemp = Character.getNumericValue(disable[i]);
        result[i] = ccTemp;
        LOG.debug(result[i] + " ");
      }
    }
    return (result);

  }
}
