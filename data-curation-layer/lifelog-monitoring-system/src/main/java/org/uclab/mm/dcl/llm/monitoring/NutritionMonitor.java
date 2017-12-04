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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.uclab.mm.dcl.llm.dataaccess.DBConnection;
import org.uclab.mm.dcl.llm.objectmodel.FoodLog;
import org.uclab.mm.dcl.llm.objectmodel.SituationConditions;
import org.uclab.mm.dcl.llm.objectmodel.SituationEvent;
import org.uclab.mm.dcl.llm.objectmodel.SituationLog;
import org.uclab.mm.dcl.llm.objectmodel.SituationNotification;

/**
 * This class is responsible to monitor the nutrition. It will not only monitor the nutrition but 
 * also perform all those actions which are required to share the monitored information with the 
 * wellness services. This class implements an interface of the monitoring strategy which has a declaration 
 * of monitoring functions. 
 * @author Rizvi
 */
public class NutritionMonitor implements MonitoringStrategy{


  String situationLogID = null;
  static org.apache.log4j.Logger log = Logger.getLogger(NutritionMonitor.class.getName());

  /**
   * 
   * @param dbConn
   * @param a
   * @throws SQLException 
   * This method is to keep the format of MonitoringStrategy interface.
   */
  public void doMonitor(Connection dbConn, String a) throws SQLException{

  }
/**
 * 
 * @param ObjFoodLog 
 * This method is used to monitor the quantity of food component in a a daytime period, if a particular ingredient cross 
 * daily recommended limit...it will intimate to wellness services by absorbing the service. It uses a stored procedure
 * which is used to perform calculation of nutrition of a particular user with in a day time limit (6:00 am to 23:59 pm).
 */
  public void doMonitor(FoodLog ObjFoodLog){
    int Result = 0;
    String ActLevel = null;
    int MapId = 0;
    int ConsumeFat = 0;
    int UserId = 0;
    RestServiceNotification objRestServiceNotification = null;
    Connection conn = null;
    int c = -1;
    java.sql.CallableStatement cstmt = null;
    try{
       conn = DBConnection.getDBConnection().getConnection();
      Long a = ObjFoodLog.getUserId();
      String s = ObjFoodLog.getFoodName();
      cstmt = conn.prepareCall("{call usp_NutiritionMonitorvdemo( " + a + "," + s + "," + "?,?,?,?,?)}");
      cstmt.setInt(1, 5);
      cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
      cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
      cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
      cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
      cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
      cstmt.execute();
      System.out.println("***************Local Testing****************");
      System.out.println(" @result: " + cstmt.getInt(1));
      System.out.println(" @ActStatus: " + cstmt.getString(2));
      System.out.println(" @madId: " + cstmt.getInt(3));
      System.out.println(" @ConsumeFat: " + cstmt.getInt(4));
      System.out.println(" @UserID : " + cstmt.getInt(5));
      System.out.println("*************** End Local Testing****************");
      Result = cstmt.getInt(1);
      ActLevel = cstmt.getString(2);
      MapId = cstmt.getInt(3);
      ConsumeFat = cstmt.getInt(4);
      UserId = cstmt.getInt(5);
      String stUserId = UserId + "";
      String stConsumeFat = ConsumeFat + "";
      String stMapId = MapId + "";
      if (cstmt.getInt(1) == 0){
        System.out.println(" No need to intimate : " + cstmt.getInt(1));
      } else if (cstmt.getInt(1) == 1){
        System.out.println(" Plz intimate the SCL for Recomendation" + cstmt.getInt(1));
        objRestServiceNotification = new RestServiceNotification();
        //communicate the information to the SCL
        //objRestServiceNotification.notifyToRestService(getNotificationMessage(stMapId, stUserId, stConsumeFat), "http://163.180.116.185:8080/SCLMiningMind2.5/webresources/SCLService/PushPARecommendation");
        System.out.println(" *******************************************");
      } else{
        System.out.println(" Sorry very wrong Information" + cstmt.getInt(1));
      }
      //situationLogID = AddFoodLog(ObjFoodLog);
    } catch(SQLException sqlException){
      sqlException.printStackTrace();
    } catch (Exception exp){
      StringWriter errors = new StringWriter();
      exp.printStackTrace(new PrintWriter(errors));
    }
    //return situationLogID;
  }
/**
 * 
 * @param ObjFoodLog
 * @return 
 * This method is used to log the food information, whenever user take food and want to log it for recording and logging.
 */
  protected String AddFoodLog(FoodLog ObjFoodLog){
    try{
      ClientConfig config = new DefaultClientConfig();
      config.getClasses().add(JacksonJsonProvider.class);      
      Client client = Client.create(config);
      final String baseURI = "http://163.180.116.194:8080/MMDataCurationRestfulService/webresources/InformationCuration/AddFoodLog";
      WebResource srv = client.resource(UriBuilder.fromUri(baseURI).build());
      ClientResponse serverResponse = srv.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, ObjFoodLog);
      situationLogID = serverResponse.getEntity(new GenericType<List<String>>(){}).get(0);
      System.out.println(" SituationLog ID: " + situationLogID);
    } catch (Exception exp){
      String error = exp.getMessage();
      System.err.println(error + ": ");
      exp.printStackTrace();
    }
    return situationLogID;
  }
/**
 * 
 * @param MapId
 * @param UserId
 * @param ConsumedFat
 * @return 
 * This method is used to build notification message when a user's food particular ingredients cross a threshold value.
 * This message constitutes of the monitoring condition and constraints related to that particular monitoring condition. The building
 * of such message is quite informative to the wellness services because it constitutes of monitoring condition and related constraints.
 */
 
  protected SituationNotification getNotificationMessage(String MapId, String UserId, String ConsumedFat){
    Connection conn = null;
    Statement sta = null;
    System.out.println(" Notification SCL is called");
    conn = DBConnection.getDBConnection().getConnection();
    List<SituationConditions> objListSituationConditions = new ArrayList<SituationConditions>();
    SituationEvent objSituationEvent = new SituationEvent();
    SituationNotification objSituationNotification = new SituationNotification();
    SituationConditions objSituationConditionsMonitor = new SituationConditions();
    SituationConditions objSituationConditionsMeasuring = new SituationConditions();
    SituationConditions objSituationConditionsConstraints = new SituationConditions();
    SituationLog objSituationLog = new SituationLog();

    try{
      CallableStatement callableStatement = conn.prepareCall("{call usp_NutritionMonitoringEvents(" + MapId + ")}");
      ResultSet rs = callableStatement.executeQuery();
      if (rs != null){
        while (rs.next()){
          String stActivity = rs.getString("Activity");
          String stActivityOperator = rs.getString("ActivityOperator");
          String stActivityValue = rs.getString("Activityvalue");
          String stActivityDataType = rs.getString("ActivityDataType");
          String stMeasuringMetric = rs.getString("MeasuringMetric");
          String stMeasuringOperator = rs.getString("MeasuringOperator");
          String stMeasuringTargetValue = rs.getString("MeasuringTargetValue");
          String stMeasuringDataType = rs.getString("MeasuringDataType");

          objSituationLog.setUserID(UserId);
          objSituationLog.setSituationCategoryID("16");
          objSituationLog.setSituationDescription("Fat " + stMeasuringTargetValue);
          objSituationLog.setSituationDate();

          objSituationConditionsMeasuring.setConditionKey(stMeasuringMetric);
          objSituationConditionsMeasuring.setConditionValue(stMeasuringTargetValue);
          objSituationConditionsMeasuring.setConditionValueOperator(stMeasuringOperator);
          objSituationConditionsMeasuring.setConditionType(stMeasuringDataType);

          objSituationConditionsMonitor.setConditionKey(stActivity);
          objSituationConditionsMonitor.setConditionValue(stActivityValue);
          objSituationConditionsMonitor.setConditionValueOperator(stActivityOperator);
          objSituationConditionsMonitor.setConditionType(stActivityDataType);

          System.out.println("********** Situation Triggered **********");
          System.out.println("\t User Id:" + UserId);
          System.out.println("\t Situation Details:");
          System.out.println("\t Current Activity:" + objSituationConditionsMonitor.getConditionValue());
          System.out.println("\t Consumed Fat:" + objSituationConditionsMeasuring.getConditionValue());
          System.out.println("*****************************************\n");
          System.out.println("****** SCL Recomendation Response *******");

          objListSituationConditions.add(objSituationConditionsMonitor);
          objListSituationConditions.add(objSituationConditionsMeasuring);

        }// end of loop
      }
      ///////////////stored procedure////////////
      CallableStatement callableStatement1 = conn.prepareCall("{call usp_NutritionSituationConstraints(" + MapId + ")}");

      ResultSet rs1 = callableStatement1.executeQuery();
      if (rs1 != null){
        while (rs1.next()){
          String stConstraintKey = rs1.getString("ConstraintKey");
          String stConstraintOperator = rs1.getString("ConstraintOperator");
          String stConstraintValue = rs1.getString("ConstraintValue");
          String stConstraintDataType = rs1.getString("ConstraintDataType");
          System.out.println(stConstraintKey + " " + stConstraintOperator + " " + stConstraintValue + " " + stConstraintDataType + " ");

          objSituationConditionsConstraints.setConditionKey(stConstraintKey);
          objSituationConditionsConstraints.setConditionValue(stConstraintValue);
          objSituationConditionsConstraints.setConditionValueOperator(stConstraintOperator);
          objSituationConditionsConstraints.setConditionType(stConstraintDataType);
          objListSituationConditions.add(objSituationConditionsConstraints);

          System.out.println("********** Situation Constrainds **********");
          System.out.println("\t Mapping ID:" + situationLogID);
          System.out.println("\t User Id:" + UserId);
          System.out.println("\t Situation Details:");
          System.out.println("\t Activity Constraint:" + objSituationConditionsConstraints.getConditionKey());
          System.out.println("\t Activity Constraint Value:" + objSituationConditionsConstraints.getConditionValue());
          System.out.println("*****************************************\n");
          System.out.println("****** SCL Recomendation Response *******");
        }// end of for
      }
        situationLogID = getSituationLog(objSituationLog);
        objSituationEvent.setListSConditions(objListSituationConditions);
        objSituationEvent.setSituationID(situationLogID);
        objSituationNotification.setSituationEvent(objSituationEvent);
        objSituationNotification.setUserID(UserId);  
    } catch (SQLException sqlException){
      String error = sqlException.getMessage();
      System.err.println(error + ": ");
      sqlException.printStackTrace();
    }
    
   return objSituationNotification;
  }
/**
 * 
 * @param objSituationLog
 * @return 
 * This method is used to get the logID which is required by SCL for reasoning to obtain the appropriate recommendation. 
 */
  protected static String getSituationLog(SituationLog objSituationLog){
    String situationLogID = null;
    try{
      ClientConfig config = new DefaultClientConfig();
      config.getClasses().add(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
      Client client = Client.create(config);
      final String baseURI = "http://163.180.116.194:8080/MMDataCurationRestfulService/webresources/ServiceCuration/AddSituation";
      WebResource srv = client.resource(UriBuilder.fromUri(baseURI).build());
      ClientResponse serverResponse = srv.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, objSituationLog);
      situationLogID = serverResponse.getEntity(new GenericType<List<String>>(){
      }).get(0);
    } catch (Exception exp){
      String error = exp.getMessage();
     // System.err.println(error + ":::::: ");
     // exp.printStackTrace();
    }
    return situationLogID;
  }

}
