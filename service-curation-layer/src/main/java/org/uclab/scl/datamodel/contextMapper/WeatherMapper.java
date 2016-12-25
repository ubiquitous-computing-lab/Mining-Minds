/**
 * Copyright [2016] [Syed Imran Ali]
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
package org.uclab.scl.datamodel.contextMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WeatherMapper {
  private static Logger LOG = LogManager.getRootLogger();
  
  static HashMap<String, String> mapWeather = new HashMap<>();
  private static final String weatherPath = "Weather.txt";
  private static Scanner scanFile;

  public WeatherMapper() {
    try {
      scanFile = new Scanner(this.getClass().getClassLoader().getResourceAsStream(weatherPath));
    } catch (Exception exp) {
      LOG.debug(exp.getMessage());
    }
  }

  public HashMap weatherMapper() throws FileNotFoundException {
    String readFile = null;
    String key = null;
    String value = "";
    int flat = 0;
    while (scanFile.hasNextLine()) {
      readFile = scanFile.nextLine();
      String array[] = readFile.split(",");
      key = array[0];
      while (flat < array.length) {
        if (flat == 0) {
          flat += 1;
        } else if (flat == 1) {
          value = array[flat];
          flat += 1;
        } else {
          value = value + "," + array[flat];
          flat += 1;
        }
        mapWeather.put(key, value);
      } // inner-while
      flat = 0;
      key = null;
      value = "";
    } // outer-while
    scanFile.close();
    return mapWeather;
  }// main
}
