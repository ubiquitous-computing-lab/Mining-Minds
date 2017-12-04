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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.datamodel.contextMapper.DisableMapper;
import org.uclab.scl.datamodel.contextMapper.EmotionMapper;
import org.uclab.scl.datamodel.contextMapper.HLCMapper;
import org.uclab.scl.datamodel.contextMapper.LocationMapper;
import org.uclab.scl.datamodel.contextMapper.PreferenceMapper;
import org.uclab.scl.datamodel.contextMapper.WeatherMapper;

public class ContextSelector {
  
  private static Logger LOG = LogManager.getRootLogger();
  
  static HashMap<String, String> mapWeatherRec = new HashMap<>();
  static HashMap<String, String> mapLocationRec = new HashMap<>();
  static HashMap<String, String> mapHLCRec = new HashMap<>();
  static HashMap<String, String> mapEmotionRec = new HashMap<>();
  static HashMap<String, String> mapDisableRec = new HashMap<>();
  static HashMap<String, String> mapPreferRec = new HashMap<>();

  /** ContextSelector
   * <p>
   * This function evaluates User's Interruptibility status. 
   * This function uses information provided by generateContextMatrix function
   * for deciding whether to provide user with the recommended physical activity as the present moment or note
   * <p>
   * @param userid
   * @param receivedContext
   * @return boolean
   * @author Imran
   * @version 2.5
   * @Since September 2015
   */
  public boolean selectContext(String receivedContext){   
    int[] matResultVec = new int[5];
    int sum=0;
    boolean flag;
    try {
      //matResultVec= ContextEvaluator.generateContextMatrix(receivedContext);
    	matResultVec= generateContextMatrix(receivedContext);
    } catch (Exception e) {
      e.printStackTrace();
    }    
    for (int i = 0; i < matResultVec.length; i++) {
      sum += matResultVec[i];
    }    
    switch(sum){
    case 0:
     flag = false;
    break;    
    default:
      flag = true;
    }
    return flag; 
  }
  
  @SuppressWarnings({ "unchecked", "unused" })
  private static int[] generateContextMatrix(String receivedContext) throws Exception {
	LOG.debug("Generating matrix...");
    DisableMapper mapDisable = new DisableMapper();
    EmotionMapper mapEmotion = new EmotionMapper();
    HLCMapper mapHLC = new HLCMapper();
    LocationMapper mapLoc = new LocationMapper();
    PreferenceMapper mapPref = new PreferenceMapper();
    WeatherMapper mapWeather = new WeatherMapper();

    mapWeatherRec = mapWeather.weatherMapper();
    mapLocationRec = mapLoc.locationMapper();
    mapHLCRec = mapHLC.hlcMapper();
    mapEmotionRec = mapEmotion.emotionMapper();
    mapDisableRec = mapDisable.disableMapper();
    mapPreferRec = mapPref.preferMapper();

    String context[] = receivedContext.split(",");
    LOG.debug("Rec: Walking,Running,Stretching,Cycling,Sitting");

    char[] loc = mapLocationRec.get(context[0]).replaceAll(",", "").toCharArray();    
    LOG.debug("Location		"  + mapLocationRec.get(context[0]));    
    
    char[] hlc = mapHLCRec.get(context[1]).replaceAll(",", "").toCharArray();
    LOG.debug("HLC			"  + mapHLCRec.get(context[1]));    
    
    char[] weather = mapWeatherRec.get(context[2]).replaceAll(",", "").toCharArray();
    LOG.debug("Weather		"  + mapWeatherRec.get(context[2]));    
    
    char[] emotion = mapEmotionRec.get(context[3]).replaceAll(",", "").toCharArray();
    LOG.debug("Emotion		"  + mapEmotionRec.get(context[3]));    
    
    char[] disable = mapDisableRec.get(context[4]).replaceAll(",", "").toCharArray();
    LOG.debug("Injury		"  + mapDisableRec.get(context[4]));    
    
    // CONTEXTUAL MATRIX
    int[] aggregateVector = new int[loc.length];
    for (int i = 0; i < aggregateVector.length; i++)
      if ((loc[i] == '1') && (hlc[i] == '1') && (weather[i] == '1') && (emotion[i] == '1') && (disable[i] == '1')) {
        aggregateVector[i] = 1;
      } else {
        aggregateVector[i] = 0;
        
    }    
    return (aggregateVector);
  }
}
