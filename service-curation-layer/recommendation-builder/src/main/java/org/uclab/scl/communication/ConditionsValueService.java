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
package org.uclab.scl.communication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class ConditionsValueService {
  private static Logger LOG = LogManager.getRootLogger();
  
  /**
   * Reads and Returns file contents
   * 
   * @param filePath
   * @return file contents
   */
  public static String readFromFile(String filePath) {
    BufferedReader br = null;
    StringBuilder ruleBuilder = new StringBuilder();
    try {
      br = new BufferedReader(new FileReader(filePath));
      String line = null;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }
        ruleBuilder.append(line);
      }
    } catch (IOException ioex) {
      LOG.error(ioex.getMessage());
    } finally {
      try {
        br.close();
      } catch (IOException iox) {
        LOG.error(iox.getMessage());
      }
    }
    return ruleBuilder.toString();
  }
  
  /**
   * parse file contents into HashMap object
   * 
   * @param filePath
   * @return HashMap
   */
  public static Map<String, Object> parse(String filePath) {
    String response = readFromFile(filePath);
    Map<String, Object> facts = null;
    ObjectMapper mapper = new ObjectMapper();
    try {
      facts = mapper.readValue(response, new TypeReference<HashMap<String, Object>>() {});
    } catch (Exception e) {
      e.printStackTrace();
    }
    return facts;
  }
  /**
   * 
   * @param parse string into json format
   * 
   * @param format
   * @return HashMap
   */
  public static Map<String, Object> parse(String text, int format) {
    Map<String, Object> keyValueConditions = null;
    if (format == 1) {
      ObjectMapper mapper = new ObjectMapper();
      try {
        keyValueConditions = mapper.readValue(text, new TypeReference<HashMap<String, Object>>() {});
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return keyValueConditions;
  }
}
