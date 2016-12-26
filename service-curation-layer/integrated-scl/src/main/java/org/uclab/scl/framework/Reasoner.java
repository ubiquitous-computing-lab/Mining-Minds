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

import org.uclab.scl.framework.recbuilder.ConflictResolver;
import org.uclab.scl.framework.recbuilder.PatternMatcher;
import org.uclab.scl.framework.recbuilder.RecBuilder;

public class Reasoner {

  private AbstractRecBuilder abstractRecBuilder = null;

  private Reasoner() {
  }
  
  public static Reasoner getRuleBaseReasoner() {
    return ReasonerHolder.INSTANCE;
  }

  private static class ReasonerHolder {
    private static final Reasoner INSTANCE = new Reasoner();
  }
  
  /**
   * 
   * @param reasonerEnum
   * @return recommendationBuilder
   */
  public ISCLFramework getMatchedRules(ReasonerEnum reasonerEnum) {
    switch (reasonerEnum) {
    case SITUATION_PAREASONER:
      abstractRecBuilder = new RecBuilder(new PatternMatcher(), new ConflictResolver());
      break;
    }
    return abstractRecBuilder;
  }
}
