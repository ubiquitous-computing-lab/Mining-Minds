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
package org.uclab.mm.dcl.llm.monitoring;

import java.sql.SQLException;
import java.sql.*;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.uclab.mm.dcl.llm.objectmodel.SituationConditions;
import org.uclab.mm.dcl.llm.objectmodel.SituationEvent;
import org.uclab.mm.dcl.llm.objectmodel.SituationNotification;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.uclab.mm.dcl.llm.objectmodel.SituationLog;
import org.apache.log4j.Logger;
import org.uclab.mm.dcl.llm.objectmodel.FoodLog;

/**
 *
 * @author Rizvi
 * This class is responsible to monitor the physical activities. It will not only monitor the activities but 
 * also perform all those actions which are required to share the monitored information with the 
 * wellness services. This class implements an interface of the monitoring strategy which has a declaration 
 * of monitoring functions. 
 */
public class PhysicalActivityMonitor implements MonitoringStrategy{

  static Logger log = Logger.getLogger(PhysicalActivityMonitor.class.getName());

  /**
   *
   * @param conn
   * @param a
   * @throws SQLException 
   * This method is the manager of the steps perform to manage the monitored activities. The stored procedure is used to 
   * filter out all those activities which have crossed the mentioned threshold by the expert.  This method after obtaining the 
   * monitored activity, build the object which consist of monitored activity and constraints related to that to 
   * communicate it with wellness services.
   * 
   */
  public void doMonitor(Connection conn, String a) throws SQLException{

    List<SituationConditions> objListSituationConditions = new ArrayList<SituationConditions>();
    SituationEvent objSituationEvent = new SituationEvent();
    SituationNotification objSituationNotification = new SituationNotification();
    SituationConditions objSituationConditionsMonitor = new SituationConditions();
    SituationConditions objSituationConditionsMeasuring = new SituationConditions();
    SituationConditions objSituationConditionsConstraints = new SituationConditions();
    SituationLog objSituationLog = new SituationLog();
    String situationLogID = null;
    RestServiceNotification objRestServiceNotification = null;

    CallableStatement callableStatement = conn.prepareCall("{call usp_PhysicalActivitiesMonitor()}");
    ResultSet rs = callableStatement.executeQuery();
    if (rs != null){

      while (rs.next()){

        String stActivity = rs.getString("Activity");
        String stActivityOperator = rs.getString("ActivityOperator");
        String stActivityValue = rs.getString("Activityvalue");
        String stActivityDataType = rs.getString("ActivityDataType");

        String stMeasuringMetric = rs.getString("MeasuringMetric");
        String stMeasuringOperator = rs.getString("MeasuringOperator");
        String stMeasuringTarget = rs.getString("MeasuringTargetValue");
        int scltime = Integer.parseInt(stMeasuringTarget);
        scltime = scltime / 60;
        String stMeasuringTargetValue = scltime + "h";
        String stMeasuringDataType = rs.getString("MeasuringDataType");

        String stUserID = rs.getString("UserID");
        String stActivityID = rs.getString("ActivityID");
        String stActivityTarget = rs.getString("ActivityTargetDuration");
        int sllogtime = Integer.parseInt(stActivityTarget);
        sllogtime = sllogtime / 60;

        String stActivityTargetDuration = sllogtime + "h"; //rs.getString("ActivityTargetDuration");
        String stSituationdate = rs.getString("Situationdate");// not using due to date format.

        String stConstraintKey = rs.getString("ConstraintKey");
        String stConstraintOperator = rs.getString("ConstraintOperator");
        String stConstraintValue = rs.getString("ConstraintValue");
        String stConstraintDataType = rs.getString("ConstraintDataType");

        objSituationLog.setUserID(stUserID);
        objSituationLog.setSituationCategoryID(stActivityID);
        objSituationLog.setSituationDescription(stActivityTargetDuration);
        objSituationLog.setSituationDate();
        // situationLogID=getSituationLog(objSituationLog);

        objSituationConditionsMonitor.setConditionKey(stActivity);
        objSituationConditionsMonitor.setConditionValue(stActivityValue);
        objSituationConditionsMonitor.setConditionValueOperator(stActivityOperator);
        objSituationConditionsMonitor.setConditionType(stActivityDataType);

        objSituationConditionsMeasuring.setConditionKey(stMeasuringMetric);
        objSituationConditionsMeasuring.setConditionValue(stMeasuringTargetValue);
        objSituationConditionsMeasuring.setConditionValueOperator(stMeasuringOperator);
        objSituationConditionsMeasuring.setConditionType(stMeasuringDataType);

        objSituationConditionsConstraints.setConditionKey(stConstraintKey);
        objSituationConditionsConstraints.setConditionValue(stConstraintValue);
        objSituationConditionsConstraints.setConditionValueOperator(stConstraintOperator);
        objSituationConditionsConstraints.setConditionType(stConstraintDataType);

        System.out.println("********** Situation Triggered **********");
        System.out.println("\t User Id:" + stUserID);
        System.out.println("\t Situation Details:");
        System.out.println("\t Current Activity:" + objSituationConditionsMonitor.getConditionValue());
        System.out.println("\t Activity Duration:" + objSituationConditionsMeasuring.getConditionValue());
        System.out.println("*****************************************\n");
        System.out.println("****** SCL Recomendation Response *******");

        objListSituationConditions.add(objSituationConditionsConstraints);
        objListSituationConditions.add(objSituationConditionsMonitor);
        objListSituationConditions.add(objSituationConditionsMeasuring);

        objSituationEvent.setListSConditions(objListSituationConditions);
        objSituationEvent.setSituationID(situationLogID);
        objSituationNotification.setSituationEvent(objSituationEvent);
        objSituationNotification.setUserID(stUserID);
        objRestServiceNotification = new RestServiceNotification();
        //objRestServiceNotification.notifyToRestService(objSituationNotification, "http://163.180.116.185:8080/SCLMiningMind2.5/webresources/SCLService/PushPARecommendation");

        log.debug(stUserID + " " + stMeasuringMetric + " " + stMeasuringTarget);
        //  path of log file C:/usr/home/log4j

        System.out.println(stUserID + " " + stMeasuringMetric + " " + stMeasuringTarget);
      }
    }
  }

  /**
   * 
   * @param objFoodLog 
   */
  public void doMonitor(FoodLog objFoodLog){

  }

  /**
   *
   * @param objSituationLog
   * @return This method is used to update the information in log to get the
   * respective logid for further usage of SCL for reasoning.
   */

  protected static String getSituationLog(SituationLog objSituationLog){
    String situationLogID = null;
    try{
      ClientConfig config = new DefaultClientConfig();
      config.getClasses().add(JacksonJsonProvider.class);
      Client client = Client.create(config);
      final String baseURI = "http://163.180.116.194:8080/MMDataCurationRestfulService/webresources/ServiceCuration/AddSituation";
      WebResource srv = client.resource(UriBuilder.fromUri(baseURI).build());
      ClientResponse serverResponse = srv.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, objSituationLog);
      situationLogID = serverResponse.getEntity(new GenericType<List<String>>(){
      }).get(0);
    } catch (Exception exp){
      String error = exp.getMessage();
      System.err.println(error + ": ");
      exp.printStackTrace();
    }
    return situationLogID;
  }

}
