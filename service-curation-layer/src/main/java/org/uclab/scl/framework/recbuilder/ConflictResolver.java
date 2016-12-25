/**
 * Copyright [2016] [Muhammad Sadiq]
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
package org.uclab.scl.framework.recbuilder;

import java.util.ArrayList;
import java.util.List;
import org.uclab.scl.datamodel.FiredRule;

/**
 * ConflictResolver
 * A default implementation for the org.uclab.scl.framework.recbuilder.AbstractConflictResolver
 * <p>
 * This implementation uses maximum specificity strategy to resolve conflict among multiple rules.
 *  
 * @author Sadiq
 * @version 2.5
 * @since july 2014
 *
 */

public class ConflictResolver extends AbstractConflictResolver{
  
  /**
   * Resolves conflict by using maximum specificity technique and returns the final resolved rule/s
   * 
   * @param firedRules
   * @return finalResolvedRules
   */
  @Override
  public List<FiredRule> resolveConflict(List<FiredRule> firedRules) {
    if (firedRules == null || firedRules.size() < 2) {
      return firedRules;
    }
    List<FiredRule> resolvedRules = new ArrayList<FiredRule>();
    int max = 0;
    for (FiredRule rule : firedRules) {
      int numConditions = rule.getConditionsKey();

      if (max == numConditions) {
        resolvedRules.add(rule);
      } else if (numConditions > max) {
        resolvedRules.clear();
        resolvedRules.add(rule);
        max = numConditions;
      }
    }
    return resolvedRules;
  }
}
