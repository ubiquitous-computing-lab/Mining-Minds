/**
 * 
 * Copyright [2016] [Bilal Ali]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 *  ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */
package org.uclab.mm.dcl.llm.objectmodel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Rizvi
 */
public class SituationLog{

  private String UserID;
  private String SituationCategoryID;
  private String SituationDescription;
  private String SituationDate;

  /**
   * @return the UserID
   */
  public String getUserID(){
    return UserID;
  }

  /**
   * @param UserID the UserID to set
   */
  public void setUserID(String UserID){
    this.UserID = UserID;
  }

  /**
   * @return the SituationCategoryID
   */
  public String getSituationCategoryID(){
    return SituationCategoryID;
  }

  /**
   * @param SituationCategoryID the SituationCategoryID to set
   */
  public void setSituationCategoryID(String SituationCategoryID){
    this.SituationCategoryID = SituationCategoryID;
  }

  /**
   * @return the SituationDescription
   */
  public String getSituationDescription(){
    return SituationDescription;
  }

  /**
   * @param SituationDescription the SituationDescription to set
   */
  public void setSituationDescription(String SituationDescription){
    this.SituationDescription = SituationDescription;
  }

  /**
   * @return the SituationDate
   */
  public String getSituationDate(){
    return SituationDate;
  }

  /**
   * @param SituationDate the SituationDate to set
   */
  public void setSituationDate(String SituationDate){
    this.SituationDate = SituationDate;
  }

  public void setSituationDate(){
    this.SituationDate = getCurrentDate();
  }

  private String getCurrentDate(){
    String recGenerationDate = null;
    Date today = new Date();
    DateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
    //Converting date to XMLGregorianCalendar in Java
    XMLGregorianCalendar startTime = toXMLGregorianCalendar(today);

    GregorianCalendar gc = startTime.toGregorianCalendar();
    recGenerationDate = (String) sdf.format(gc.getTime());
    return recGenerationDate;
  }

  private XMLGregorianCalendar toXMLGregorianCalendar(Date date){

    GregorianCalendar gCalendar = new GregorianCalendar();

    gCalendar.setTime(date);

    XMLGregorianCalendar xmlCalendar = null;

    try{
      xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
    } catch (DatatypeConfigurationException ex){
      ex.printStackTrace();
    }

    return xmlCalendar;

  }

}
