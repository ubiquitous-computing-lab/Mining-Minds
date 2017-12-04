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

import org.uclab.scl.datamodel.contextMapper.*;

public class MultiRecContentTesting {

  static HashMap<String, String> mapWeatherRec = new HashMap<>();
  static HashMap<String, String> mapLocationRec = new HashMap<>();
  static HashMap<String, String> mapHLCRec = new HashMap<>();
  static HashMap<String, String> mapEmotionRec = new HashMap<>();
  static HashMap<String, String> mapDisableRec = new HashMap<>();
  static HashMap<String, String> mapPreferRec = new HashMap<>();

  @SuppressWarnings("unchecked")
  public static int[] multiRecContextTest(String receivedContext) {
    int[] result = null; 
    try {
      WeatherMapper weatherMapper = new WeatherMapper();
      DisableMapper disableMapper = new DisableMapper();
      EmotionMapper emotionMapper = new EmotionMapper();
      HLCMapper hlcMapper = new HLCMapper();
      LocationMapper locationMapper = new LocationMapper();
      PreferenceMapper preferenceMapper = new PreferenceMapper();

      mapWeatherRec = weatherMapper.weatherMapper();
      mapLocationRec = locationMapper.locationMapper();
      mapHLCRec = hlcMapper.hlcMapper();
      mapEmotionRec = emotionMapper.emotionMapper();
      mapDisableRec = disableMapper.disableMapper();
      mapPreferRec = preferenceMapper.preferMapper();

      String context[] = receivedContext.split(",");
      char[] loc = mapLocationRec.get(context[0]).replaceAll(",", "").toCharArray();
      char[] hlc = mapHLCRec.get(context[1]).replaceAll(",", "").toCharArray();
      char[] weather = mapWeatherRec.get(context[2]).replaceAll(",", "").toCharArray();
      char[] emotion = mapEmotionRec.get(context[3]).replaceAll(",", "").toCharArray();
      char[] disable = mapDisableRec.get(context[4]).replaceAll(",", "").toCharArray();

      result = new int[loc.length];
      // CONTEXTUAL MATRIX
      for (int i = 0; i < loc.length; i++)
        if ((loc[i] == '1') && (hlc[i] == '1') && (weather[i] == '1') && (emotion[i] == '1') && (disable[i] == '1')) { 
          result[i] = 1;
        } else {
          result[i] = 0;
        }      
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();      
    }    
    return (result);
  }
}
