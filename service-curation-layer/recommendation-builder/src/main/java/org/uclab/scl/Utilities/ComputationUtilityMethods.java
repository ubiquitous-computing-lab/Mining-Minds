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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * @author Afzal
 */
public class ComputationUtilityMethods {
  private static Logger LOG = LogManager.getRootLogger();
  
  private ComputationUtilityMethods() {
  }

  public static ComputationUtilityMethods getComputationUtilityMethods() {
    return ComputationUtilityMethodsHolder.INSTANCE;
  }

  private AgeGroupEnum ageGroup;
  private String recGenerationDate;

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

  /**
   * @param ageGroup
   *          the ageGroup to set
   */
  public void setAgeGroup(AgeGroupEnum ageGroup) {
    this.ageGroup = ageGroup;
  }

  /**
   * @return the recGenerationDate
   */
  public String getCurrentDate() {

    Date today = new Date();
    DateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
    // Converting date to XMLGregorianCalendar in Java
    XMLGregorianCalendar startTime = toXMLGregorianCalendar(today);

    GregorianCalendar gc = startTime.toGregorianCalendar();
    recGenerationDate = (String) sdf.format(gc.getTime());
    return recGenerationDate;
  }

  public List<String> getStartandEndTimes() {
    List<String> startEndTime = new ArrayList<String>();

    Date today = new Date();

    DateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

    // Converting date to XMLGregorianCalendar in Java
    XMLGregorianCalendar startTime = toXMLGregorianCalendar(today);

    startTime.setHour(00);
    startTime.setMinute(00);
    startTime.setSecond(00);

    XMLGregorianCalendar endTime = toXMLGregorianCalendar(today);

    GregorianCalendar gc;
    // Start Time coversion to String
    gc = startTime.toGregorianCalendar();
    String sTime = (String) sdf.format(gc.getTime());
    startEndTime.add(sTime);

    gc = endTime.toGregorianCalendar();
    String eTime = (String) sdf.format(gc.getTime());
    startEndTime.add(eTime);

    return startEndTime;
  }

  // utility method for date conversion
  private XMLGregorianCalendar toXMLGregorianCalendar(Date date) {

    GregorianCalendar gCalendar = new GregorianCalendar();

    gCalendar.setTime(date);

    XMLGregorianCalendar xmlCalendar = null;

    try {
      xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(
          gCalendar);
    } catch (DatatypeConfigurationException ex) {
      ex.printStackTrace();
    }

    return xmlCalendar;

  }

  private static class ComputationUtilityMethodsHolder {

    private static final ComputationUtilityMethods INSTANCE = new ComputationUtilityMethods();
  }

  public String GenderConversion(int gender) {
    if (gender == 1)
      return "Male";
    else if (gender == 2)
      return "Female";
    else
      return "Unknown";
  }
  
  /**
   * Converts stringJson to map and list rules from it.
   * 
   * @param stringRules
   * @return List rules
   */
  public static Map<String, Object> getMap(String json) {
    Map<String, Object> map = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {});
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }
  
  /**
   * converts String time to seconds and returns
   * 
   * @param time
   * @return seconds
   */
  public int timeToSec(String time) {
    time = time.replaceAll(" ", "").toLowerCase();
    if (time.length() < 2) {
      LOG.error("Invalid Time Format: " + time);
      return 0;
    }
    String[] timeParts = time.split(":");
    int seconds = 0;
    for (String part : timeParts) {
      if (part == null || part.length() == 1) {
        continue;
      }
      seconds += getSeconds(part);
    }
    return seconds;
  }
  
  /**
   * converts String part of time to seconds and returns
   * <p>
   * 
   * @param timePart
   * @return seconds
   */
  public int getSeconds(String timePart) {
    int secs = 0;
    if (timePart.contains("h")) {
      timePart = timePart.replace("h", "");
      if (timePart.length() < 1) {
        LOG.error("Invalid hours: " + timePart);
        return 0;
      }
      int hour = Integer.parseInt(timePart);
      secs = hour * 3600;
    } else if (timePart.contains("m")) {
      timePart = timePart.replace("m", "");
      if (timePart.length() < 1) {
        LOG.error("Invalid minutes: " + timePart);
        return 0;
      }
      int minute = Integer.parseInt(timePart);
      secs = minute * 60;
    } else {
      if (timePart.contains("s")) {
        timePart = timePart.replace("s", "");
        if (timePart.length() < 1) {
          LOG.error("Invalid seconds: " + timePart);
          return 0;
        }
        secs = Integer.parseInt(timePart);
      }
    }
    return secs;
  }
}
