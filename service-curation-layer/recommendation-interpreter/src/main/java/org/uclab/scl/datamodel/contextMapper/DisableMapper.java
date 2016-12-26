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

public class DisableMapper {
  private static Logger LOG = LogManager.getRootLogger();
  
  static HashMap<String, String> mapDisable = new HashMap<>();
  private static final String disablePath = "Disable.txt";
  private static Scanner scanFile;

  public DisableMapper() {
    try {
      scanFile = new Scanner(this.getClass().getClassLoader().getResourceAsStream(disablePath));
    } catch (Exception exp) {
      LOG.debug(exp.getMessage());
    }
  }

  public HashMap disableMapper() throws FileNotFoundException {

    File readFile = new File("Disable.txt");
    String temp = null;
    String key = null;
    String value = "";

    int test = 0;
    while (scanFile.hasNextLine()) {
      temp = scanFile.nextLine();
      String array[] = temp.split(",");
      key = array[0];
      while (test < array.length) {
        if (test == 0) {
          test += 1;
        } else if (test == 1) {
          value = array[test];
          test += 1;
        } else {
          value = value + "," + array[test];
          test += 1;
        }
        mapDisable.put(key, value);
      } // inner-while
      test = 0;
      key = null;
      value = "";
    } // outer-while
    scanFile.close();
    return mapDisable;
  }// main
}
