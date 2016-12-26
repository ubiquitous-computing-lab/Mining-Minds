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

public class TypeComparator {
  
  /**
   * Compares v1 and v2 based on the operator defined e.g. op and returns TRUE on success and FALSE on failure
   * <p>
   * 
   * @param v1
   * @param v2
   * @param op
   * @return boolean
   */
  public static boolean compare(String v1, String v2, String op) {
    boolean matched = false;

    if (op.equals("=")) {
      matched = v1.equalsIgnoreCase(v2);
    } else if (op.equals("!=")) {
      matched = !v1.equalsIgnoreCase(v2);
    }

    return matched;
  }
  
  /**
   * Compares v1 and v2 based on the operator defined e.g. op and returns TRUE on success and FALSE on failure
   * <p>
   * 
   * @param v1
   * @param v2
   * @param op
   * @return boolean
   */
  public static boolean compare(int v1, int v2, String op) {
    boolean matched = false;

    switch (op) {

    case "=":
      matched = v1 == v2;
      break;
    case "!=":
      matched = v1 != v2;
      break;
    case "<":
      matched = v1 < v2;
      break;
    case ">":
      matched = v1 > v2;
      break;
    case "<=":
      matched = v1 <= v2;
      break;
    case ">=":
      matched = v1 >= v2;
    }

    return matched;
  }
  
  /**
   * Compares v1 and v2 based on the operator defined e.g. op and returns TRUE on success and FALSE on failure
   * <p>
   * 
   * @param v1
   * @param v2
   * @param op
   * @return boolean
   */
  public static boolean compare(boolean v1, boolean v2, String op) {
    boolean matched = false;

    if (op.equals("=")) {
      matched = v1 == v2;
    } else if (op.equals("!=")) {
      matched = v1 != v2;
    }

    return matched;
  }
}
