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
package org.uclab.scl.Utilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.uclab.scl.datamodel.Disibilities;
import org.uclab.scl.datamodel.RiskFactors;

/**
 * 
 * @author User
 */
public class DisibilitiesUtility {

  private DisibilitiesUtility() {
  }

  public static DisibilitiesUtility getDisibilitiesUtility() {
    return DisibilitiesHolder.INSTANCE;
  }

  public static Map<String, String> getDisabilitiesMap(List<Disibilities> disibilities) {
    Map<String, String> disibilityDictionary = new HashMap<String, String>();

    Iterator<Disibilities> itr = disibilities.iterator();

    while (itr.hasNext()) {

      switch (itr.next().getDisabilityID()) {
      case 1:

        disibilityDictionary.put("Visual Impairment", "Yes");
        break;

      case 2:

        disibilityDictionary.put("Deep", "Yes");
        break;

      case 3:

        disibilityDictionary.put("Social and Emotional", "Yes");
        break;

      case 4:

        disibilityDictionary.put("Physical Disability", "Yes");
        break;

      case 5:

        disibilityDictionary.put("Musculoskeletal", "Yes");
        break;

      case 6:

        disibilityDictionary.put("None", "Yes");
        break;

      }
    }
    return disibilityDictionary;
  }

  private static class DisibilitiesHolder {

    private static final DisibilitiesUtility INSTANCE = new DisibilitiesUtility();
  }
}
