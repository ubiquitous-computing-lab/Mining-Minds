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

import java.util.List;

import org.uclab.scl.datamodel.FiredRule;

/**
 * AbstractConflictResolver
 * <p>
 * enables developers to provide conflict resolution strategy when org.uclab.scl.framework.recbuilder.AbstractPatternMatcher matches or
 * fires more than one rule for a particular situation or condition
 * <p>
 * AbstractConflictResolver provides a way through which the user can provide the implementation for 
 * conflict resolution strategy strategy. 
 * 
 * @author Sadiq
 * @version 2.5
 * @since July 2014
 *
 */

public abstract class AbstractConflictResolver {
  
  /**
   * resolve's conflict by selecting the most appropriate rule from the set of all applicable rules
   * 
   * @param firedRules
   * @return List FiredRules
   */
  public abstract List<FiredRule> resolveConflict(List<FiredRule> firedRules);
}
