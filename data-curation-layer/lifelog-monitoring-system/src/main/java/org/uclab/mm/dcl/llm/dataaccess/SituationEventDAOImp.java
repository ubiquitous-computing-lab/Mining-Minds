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
package org.uclab.mm.dcl.llm.dataaccess;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import org.apache.log4j.BasicConfigurator;
import org.uclab.mm.dcl.llm.objectmodel.SituationConditions;
import org.uclab.mm.dcl.llm.objectmodel.SituationEvent;
import org.uclab.mm.dcl.llm.objectmodel.SituationEvents;
import org.apache.log4j.Logger;

/**
 *The class is used to provide the functionality for managing the monitoring 
 * conditions and the related constraints shared by experts.
 * @author Rizvi
 */
public class SituationEventDAOImp implements SituationEventDAO{

  static org.apache.log4j.Logger log = Logger.getLogger(SituationEventDAOImp.class.getName());
  /**
   * This method is used to differentiate between monitor-able event and
   * constraints. After dividing the situation event into two parts it stores
   * persistently the monitoring and constraints information in configuration
   * data for the guidance of monitoring process..
   *
   * @param objSituationEvents
   */
  @Override
  public void persistentSituation(SituationEvents objSituationEvents)
  {
    BasicConfigurator.configure();
    String mapperid = null;
    String Activity = null;
    String ActivityOperator = null;
    String ActivityValue = null;
    String ActivityDataType = null;
    String MeasuringMetric = null;
    String MeasuringOperator = null;
    String MeasuringTargetValue = null;
    String MeasuringDataType = null;
    String sitID = null;
    Connection conn = null;
    Statement sta = null;
    try{

      conn = DBConnection.getDBConnection().getConnection();

      sta = conn.createStatement();
      Iterator<SituationEvent> itSituationEvent = objSituationEvents.getListSEvents().iterator();
      int counter = 0;
      while (itSituationEvent.hasNext()){
        SituationEvent objSituationEvent = itSituationEvent.next();
        counter = 0;
        Iterator<SituationConditions> itSituationConditions = objSituationEvent.getListSConditions().iterator();
        while (itSituationConditions.hasNext()){
          SituationConditions objSituationCondition = itSituationConditions.next();

          if (objSituationCondition.getConditionKey().equals("Current Activity"))
          {
            mapperid = objSituationEvent.getSituationID();
            Activity = objSituationCondition.getConditionKey();
            ActivityOperator = objSituationCondition.getConditionValueOperator();
            ActivityValue = objSituationCondition.getConditionValue();
            ActivityDataType = objSituationCondition.getConditionType();
            counter++;
          } else if (objSituationCondition.getConditionKey().equals("Activity Duration") || objSituationCondition.getConditionKey().equals("Consumed Fat"))
          {
            MeasuringMetric = objSituationCondition.getConditionKey();
            MeasuringOperator = objSituationCondition.getConditionValueOperator();
            MeasuringTargetValue = objSituationCondition.getConditionValue();
            MeasuringDataType = objSituationCondition.getConditionType();
            counter++;
          } else 
          {            
            /**
             * Insert the information of constraints of Situation in table
             * SItuation Constraints where it is persisted as key value pair.
             * Stored procedure "usp_SituationConstraintsInsertion" is called.
             */
              java.sql.CallableStatement cstmt = conn.prepareCall("{call usp_SituationConstraintsInsertion('" + objSituationEvent.getSituationID() + "','" + objSituationCondition.getConditionKey() + "','" + objSituationCondition.getConditionValueOperator() + "','" + objSituationCondition.getConditionValue() + "','" + objSituationCondition.getConditionType() + "','" + objSituationEvent.getSituationID() + "')}");
              cstmt.execute();
          }
          if (counter == 2)
          {
            /**
             * Insert the information of Monitoring Situation in table
             * monitoring Events where it is persisted as key value pair. Stored
             * procedure "usp_MonitoringSituationInsertion" is called.
             */
            java.sql.CallableStatement cstmt = conn.prepareCall("{call usp_MonitoringSituationInsertion('" + mapperid + "','" + Activity + "','" + ActivityOperator + "','" + ActivityValue + "','" + ActivityDataType + "','" + MeasuringMetric + "','" + MeasuringOperator + "','" + timeCal(MeasuringTargetValue) + "','" + MeasuringDataType + "')}");
            cstmt.execute();
            counter = 0;
          }
         }//. inner while loop
            
        log.info("********** New Situation Event Received From KCL **********");
        log.info("\tSituation Event Details:");
        log.info("\t Current Activity: " + ActivityValue);
        log.info("\t Activity Duration: " + MeasuringTargetValue);
        log.info("********** New Situation Saved Successfully **********");
        
      } //. main while loop
    }catch(SQLException sqlException){
      sqlException.printStackTrace();
    } 
    catch (Exception e){
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
      sitID = errors.toString();
      sitID = sitID.replaceAll("\\s", "");
    }    
  }

  /**
   * 
   * @return 
   */
  @Override
  public SituationEvents retriveSituationEvents(){
    return null;
  }

  /**
   * This method is used to Convert time into minutes for physical activity monitoring also manage amount 
   * having no unit for handling fats in nutrition.
   * @param measuringParameter
   * @return 
   */
  public String timeCal(String measuringParameter){
    String[] tokens = measuringParameter.split(":");
    int hrmin = 0;
    int minmin = 0;
    int grams = 0;
    String total1 = "";
    int total = 0;
    for (String t : tokens)
    {
      if ((t.charAt(t.length() - 1)) == ('h'))
      {
        String hr = removeLastChar(t);
        hrmin = Integer.parseInt(hr);
        hrmin = 60 * hrmin;
      }
      if ((t.charAt(t.length() - 1)) == ('m'))
      {
        String hr = removeLastChar(t);
        minmin = Integer.parseInt(hr);
      }
      if ((t.charAt(t.length() - 1)) != 'h' && (t.charAt(t.length() - 1)) != 'm')
      {
        String gm = t;
        grams = Integer.parseInt(gm);
      }
      System.out.println(t);
    }
    total = hrmin + minmin + grams;
    total1 = total + "";
    return total1;
  }
  
  private String removeLastChar(String str){
    return str.substring(0, str.length() - 1);
  }
}
