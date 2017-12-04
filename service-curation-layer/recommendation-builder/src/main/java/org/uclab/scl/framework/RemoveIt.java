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
package org.uclab.scl.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class RemoveIt {
  private static Logger LOG = LogManager.getRootLogger();

  public static void main(String[] args) throws IOException {
    BufferedReader br = null;
    
    br = new BufferedReader(new FileReader(new File("krf_knowledge_base.json")));
    
    StringBuilder sb = new StringBuilder();
    
    String line = null;
    while((line = br.readLine()) != null){
      sb.append(line.trim());
    }
    
    Map<String, List<Map<String, Object>>> rules = buildRules(sb.toString());
    LOG.debug(rules.get("rules").size());
    LOG.debug(sb.toString().replace("\"", "\\\""));
    //. LOG.debug();
    
  }
  
  public static Map<String, List<Map<String, Object>>> buildRules(String jsonRules) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, List<Map<String, Object>>> rules = null;
    try {
      rules = mapper.readValue(jsonRules, new TypeReference<Map<String, List<Map<String, Object>>>>() {});
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return rules;
  }

}
