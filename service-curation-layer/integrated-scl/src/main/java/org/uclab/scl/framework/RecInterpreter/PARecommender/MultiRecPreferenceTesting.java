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

//import static content_interpreter_v1.TestContent.mapPrefer_rec;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import org.uclab.scl.datamodel.contextMapper.PreferenceMapper;

public class MultiRecPreferenceTesting {
  static HashMap<String, String> mapPreferRec = new HashMap<>();

  @SuppressWarnings("unchecked")
public static int[] mrecPreferenceTest(String userid, String currentContext)
      throws Exception {
    // Check Context with Preference
    PreferenceMapper preferenceMapper = new PreferenceMapper();

    mapPreferRec = preferenceMapper.preferMapper();

    String[] prefer1 = mapPreferRec.get(userid).split(",");
    int[] prefer = new int[prefer1.length];

    for (int i = 0; i < prefer1.length; i++) {
      String numberAsString = prefer1[i];
      prefer[i] = Integer.parseInt(numberAsString);
    }

    int receive[] = MultiRecContentTesting.multiRecContextTest(currentContext);
    int[] result = new int[receive.length];

    for (int i = 0; i < receive.length; i++)
      if ((receive[i] == 1) && (prefer[i] == 1)) {
        result[i] = 1;
      } else {
        result[i] = 0;
      }
    return (result);
  }

}
