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
import java.util.Map;

import org.uclab.scl.outputModel.InterpretationModel.PhyActivityExplaination;

/**
 * 
 * @author User
 */
public class FactsDictionary {
  public static Map<FactsKeyEnum, PhyActivityExplaination> factsDictionary = new HashMap<>();

  private FactsDictionary() {

    PhyActivityExplaination fact1 = new PhyActivityExplaination();
    fact1.setFactCategoryDescription("Your heart will thank you: Those who took more breaks - regardless of their total sedentary time - had lower waist circumference and C-reactive protein, and inflammatory marker linked to cardiovascular disease");
    fact1.setFactCategoryID(0);
    fact1.setFactExplanation(null);

    factsDictionary.put(FactsKeyEnum.SittingOneHour01F01, fact1);

    PhyActivityExplaination fact2 = new PhyActivityExplaination();
    fact2
        .setFactCategoryDescription("Short periods of light activity - even just a minute at a time - could reduce waistline, increse levels of good cholesterol, and even increase insulin resistance");
    fact2.setFactCategoryID(0);
    fact2.setFactExplanation(null);

    factsDictionary.put(FactsKeyEnum.SittingOneHour01F02, fact2);

    PhyActivityExplaination fact3 = new PhyActivityExplaination();
    fact3
        .setFactCategoryDescription("Taking a couple of seconds of static stretching, can literally save your life");
    fact3.setFactCategoryID(0);
    fact3.setFactExplanation("http://eldergym.com/elderly-flexibility.html");

    factsDictionary.put(FactsKeyEnum.SittingOneHour02F01, fact3);

    PhyActivityExplaination fact4 = new PhyActivityExplaination();
    fact4
        .setFactCategoryDescription("Static Stretching creats lasting lengthening of a muscle and surrounding tissue; increases your available range of motion.");
    fact4.setFactCategoryID(0);
    fact4.setFactExplanation("null");

    factsDictionary.put(FactsKeyEnum.SittingOneHour02F01, fact4);

    PhyActivityExplaination fact5 = new PhyActivityExplaination();
    fact5.setFactCategoryDescription("Stretching is an excellent way to relax and relieve tension if you incorporate breathing exercises and good posture in your stretching program.");
    fact5.setFactCategoryID(0);
    fact5.setFactExplanation("null");

    factsDictionary.put(FactsKeyEnum.SittingOneHour02F01, fact5);
  }

  public PhyActivityExplaination getFact(FactsKeyEnum factKey) {
    return factsDictionary.get(factKey);
  }

  public static FactsDictionary getFactsDictionary() {
    return FactsDictionaryHolder.INSTANCE;
  }

  private static class FactsDictionaryHolder {

    private static final FactsDictionary INSTANCE = new FactsDictionary();
  }
}
