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
import java.util.Map;

import org.uclab.scl.datamodel.FiredRule;

/**
 * AbstractPatternMatcher
 * <p>
 * enables developers to provide rule selection strategy for a particular situation or condition
 * 
 * @author Sadiq
 * @version 2.5
 * @since july 2014
 *
 */
public abstract class AbstractPatternMatcher {
  
  /**
   * matches rules/conditions against user data and returns list of all applicable rules 
   * <p>
   * 
   * @param conditionsValue
   * @param rules
   * @return List Of FiredRule
   */
  public abstract List<FiredRule> fireRule(Map<String, Object> conditionsValue, List<Map<String, Object>> rules) throws UnsupportedTypeException;
}
