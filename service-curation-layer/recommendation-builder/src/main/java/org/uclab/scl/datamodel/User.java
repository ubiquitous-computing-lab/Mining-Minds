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
package org.uclab.scl.datamodel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.uclab.scl.Utilities.ComputationUtilityMethods;

/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class User implements Serializable{
  
  @JsonProperty("id")
  private long id;
  
  @JsonProperty("requestType")
  private String requestType;
  
  @JsonProperty("middleName")
  private String middleName;

  @JsonProperty("dateOfBirth")
  private String dateOfBirth;

  @JsonProperty("userID")
  private long userID;
  
  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("contactNumber")
  private String contactNumber;

  @JsonProperty("activityLevelId")
  private int activityLevelId;

  @JsonProperty("martialStatusId")
  private int martialStatusId;
  
  @JsonProperty("genderId")
  private int genderId;
  @JsonProperty("emailAddress")
  private String emailAddress;
  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("password")
  private String password;
  @JsonProperty("occupationId")
  private int occupationId;
  @JsonProperty("userTypeID")
  private int userTypeID;
  @JsonProperty("userTypeDescription")
  private String userTypeDescription;
  @JsonProperty("genderDescription")
  private String genderDescription;
  @JsonProperty("maritalStatusDescription")
  private String maritalStatusDescription;
  @JsonProperty("occupationDescription")
  private String occupationDescription;
  @JsonProperty("activityLevelDescription")
  private String activityLevelDescription;
  public int getUserTypeID() {
    return userTypeID;
  }

  public void setUserTypeID(int userTypeID) {
    this.userTypeID = userTypeID;
  }

  public String getUserTypeDescription() {
    return userTypeDescription;
  }

  public void setUserTypeDescription(String userTypeDescription) {
    this.userTypeDescription = userTypeDescription;
  }

  public String getGenderDescription() {
    return genderDescription;
  }

  public void setGenderDescription(String genderDescription) {
    this.genderDescription = genderDescription;
  }

  public String getMaritalStatusDescription() {
    return maritalStatusDescription;
  }

  public void setMaritalStatusDescription(String maritalStatusDescription) {
    this.maritalStatusDescription = maritalStatusDescription;
  }

  public String getOccupationDescription() {
    return occupationDescription;
  }

  public void setOccupationDescription(String occupationDescription) {
    this.occupationDescription = occupationDescription;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public long getUserID() {
    return userID;
  }

  public void setUserID(long userID) {
    this.userID = userID;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public int getActivityLevelId() {
    return activityLevelId;
  }

  public void setActivityLevelId(int activityLevelId) {
    this.activityLevelId = activityLevelId;
  }

  public int getMartialStatusId() {
    return martialStatusId;
  }

  public void setMartialStatusId(int martialStatusId) {
    this.martialStatusId = martialStatusId;
  }

  public int getGenderId() {
    return genderId;
  }

  public void setGenderId(int genderId) {
    this.genderId = genderId;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getOccupationId() {
    return occupationId;
  }

  public void setOccupationId(int occupationId) {
    this.occupationId = occupationId;
  }
  
  //
  // @Override
  // public String toString()
  // {
  // return
  // "User [middleName = "+middleName+", dateOfBirth = "+dateOfBirth+", userID = "+userID+", lastName = "+lastName+", contactNumber = "+contactNumber+", activityLevelId = "+activityLevelId+", martialStatusId = "+martialStatusId+", genderId = "+genderId+", emailAddress = "+emailAddress+", firstName = "+firstName+", password = "+password+", occupationId = "+occupationId+"]";
  // }

  public String getActivityLevelDescription() {
    return activityLevelDescription;
  }

  public void setActivityLevelDescription(String activityLevelDescription) {
    this.activityLevelDescription = activityLevelDescription;
  }
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRequestType() {
    return requestType;
  }

  public void setRequestType(String requestType) {
    this.requestType = requestType;
  }

  @Override
  public String toString() {
    String keyValue = "";
    try {

      ComputationUtilityMethods cUtility = ComputationUtilityMethods
          .getComputationUtilityMethods();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
      Date dob = new Date();
      dob = sdf.parse(dateOfBirth);

      keyValue += "\"Age Group\":\"" + cUtility.getAgeGroup(dob) + "\",\n";

      keyValue += "\"Gender\":\"" + cUtility.GenderConversion(genderId) + "\",";

    } catch (ParseException pExp) {
      pExp.printStackTrace();
    }

    return keyValue;
  }

}
