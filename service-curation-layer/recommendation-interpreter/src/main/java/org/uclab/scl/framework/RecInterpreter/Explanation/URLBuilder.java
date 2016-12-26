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

package org.uclab.scl.framework.RecInterpreter.Explanation;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class URLBuilder {
  private static Logger LOG = LogManager.getRootLogger();
  
  HashMap<String, String> mapExpURL = new HashMap<>();
  public String recURLBuilder(String rec) throws Exception {

    ExpURLMapper mapperExpURL = new ExpURLMapper();
    mapExpURL = mapperExpURL.mapExpURL();
    String retrievedURLList;

    retrievedURLList = mapExpURL.containsKey(rec) ? mapExpURL.get(rec): mapExpURL.get(null);
    String[] retrievedURLArr = null;
    String selectedURL = null;

    if (retrievedURLList == null)
      LOG.debug("Recommendation NOT FOUND in the URL repository");
    else {
      retrievedURLArr = retrievedURLList.split(",");
      selectedURL = retrievedURLArr[(ThreadLocalRandom.current().nextInt(0,retrievedURLArr.length))];
    }
    return (selectedURL);
  }
}
