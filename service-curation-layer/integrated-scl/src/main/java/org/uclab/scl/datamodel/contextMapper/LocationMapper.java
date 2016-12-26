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
public class LocationMapper {
  private static Logger LOG = LogManager.getRootLogger();
  
  static HashMap<String, String> mapLocation = new HashMap<>();
  private static final String locatoinPath = "Location.txt";
  private static Scanner scanFile;
  
  public LocationMapper() {
    try {
      scanFile = new Scanner(this.getClass().getClassLoader().getResourceAsStream(locatoinPath));
    } catch (Exception exp) {
      LOG.debug(exp.getMessage());
    }
  }

  public HashMap locationMapper() throws FileNotFoundException {
    String readFile = null;
    String key = null;
    String value = "";
    int flag = 0;
    while (scanFile.hasNextLine()) {
      readFile = scanFile.nextLine();
      String array[] = readFile.split(",");
      key = array[0];
      while (flag < array.length) {
        if (flag == 0) {
          flag += 1;
        } else if (flag == 1) {
          value = array[flag];
          flag += 1;
        } else {
          value = value + "," + array[flag];
          flag += 1;
        }
        mapLocation.put(key, value);
      } // inner-while
      flag = 0;
      key = null;
      value = "";
    } // outer-while
    scanFile.close();
    return mapLocation;
  }// main
}
