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

import java.util.Date;

public class AgeGroupUtility {
  public static enum AgeGroupEnum {
    Adult, Silver_Age, Older_Adult, Young_Adult
  }
  
  private AgeGroupEnum ageGroup;
  private AgeGroupUtility(){}
  
  public static AgeGroupUtility getAgeGroupUtility(){
    return AgeGroupUtilityHolder.INSTANCE;
  }
  
  public AgeGroupEnum getAgeGroup(Date dob) {

    Date today = new Date(); // current date
    int age = today.getYear() - dob.getYear();// .get(Calendar.YEAR);

    if (today.getMonth() < dob.getMonth()) {
      age--;
    } else if (today.getMonth() == dob.getMonth()
        && (today.getDate() < dob.getDate())) {
      age--;
    }

    if (age >= 19 && age <= 45)
      ageGroup = AgeGroupEnum.Adult;
    else if (age > 45)
      ageGroup = AgeGroupEnum.Older_Adult;
    else if (age < 20)
      ageGroup = AgeGroupEnum.Young_Adult;

    return ageGroup;
  }
  
  public String GenderConversion(int gender) {
    if (gender == 1)
      return "Male";
    else if (gender == 2)
      return "Female";
    else
      return "Unknown";
  }
  
  public void setgetAgeGroup(AgeGroupEnum ageGroup){
    this.ageGroup = ageGroup;
  }
  
  private static class AgeGroupUtilityHolder{
    private static final AgeGroupUtility INSTANCE = new AgeGroupUtility();
  }
}
