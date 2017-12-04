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

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.uclab.mm.dcl.llm.dataaccess.DBConnection;

/**
 * This class provides the access to details of different kind of violations in
 * term of respective user id, log id and violations details.
 * @author Rizvi
 */
public class VoilationNotification implements Serializable{

  private List<Violations> listViolations = new ArrayList<Violations>();
  /**
   * The function provides the 10 latest violation of users either physical or nutrition based on abnormal conditions identified.
   * select top 10 
   * ActivityID,
   * ActivityTargetDuration, 
   * RecordedTime,
   * Log_Id, 
   * UserID 
   * from tbllog_CurrentLifeLog 
   * where ActivityStatus!='NM'
   * order by RecordedTime desc;
   * 
   */
  
  public List<Violations> getViolations(){
    Connection conn = null;
    
    try{
      conn = DBConnection.getDBConnection().getConnection();
      CallableStatement callableStatement = conn.prepareCall("{call usp_GetTopViolations()}");
      ResultSet rs = callableStatement.executeQuery();

      if (rs != null){
        while (rs.next()){
          String UserID = rs.getString("UserID");
          String ActivityID = rs.getString("ActivityID");
          String StartTime = rs.getString("RecordedTime");
          String ActivityTargetDuration = rs.getString("ActivityTargetDuration");
          String LogID = rs.getString("Log_Id");
          Violations objViolations = new Violations();
          objViolations.setActivityID(ActivityID);
          objViolations.setActivityTargetDuration(ActivityTargetDuration);
          objViolations.setStartTime(StartTime);
          objViolations.setLogID(LogID);
          objViolations.setUserID(UserID);
          listViolations.add(objViolations);
        }
      }
    } catch (Exception e){
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
    }
    return listViolations;
  }

  /**
   * The function provides the 10 latest violation of users other than previously shown, either physical or nutrition based on abnormal conditions identified.
   * select top 10 ActivityID,
   * ActivityTargetDuration,
   * RecordedTime,
   * Log_Id,
   * UserID from
   * tbllog_CurrentLifeLog 
   * where ActivityStatus!='NM' and Log_id> maxlgid 
   * order by RecordedTime desc;
   *
   * @param maxlgid
   * @return
   */
  public List<Violations> getMaxViolations(String maxlgid){
    Connection conn = null;
    try{
      conn = DBConnection.getDBConnection().getConnection();
      CallableStatement callableStatement = conn.prepareCall("{call usp_GetMaxViolationsLog( " + maxlgid + ")}");
      ResultSet rs = callableStatement.executeQuery();

      if (rs != null){
        while (rs.next()){
          String UserID = rs.getString("UserID");
          String ActivityID = rs.getString("ActivityID");
          String StartTime = rs.getString("RecordedTime");
          String ActivityTargetDuration = rs.getString("ActivityTargetDuration");
          String LogID = rs.getString("Log_Id");
          Violations objViolations = new Violations();
          objViolations.setActivityID(ActivityID);
          objViolations.setActivityTargetDuration(ActivityTargetDuration);
          objViolations.setStartTime(StartTime);
          objViolations.setLogID(LogID);
          objViolations.setUserID(UserID);
          listViolations.add(objViolations);
        }
      }
    } catch (Exception e){
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
    }
    return listViolations;
  }

  /**
   * The function provides toal count of violation of every users either physical or nutrition based on abnormal conditions identified.
   * It projects  userid,
   * COUNT(userid)TotalViolations
   * from  Table tbllog_CurrentLifeLog 
   * where ActivityStatus!='NM' 
   * group by UserId);
   *
   * @return
   */
  
  
  public List<Violations> getTotalViolations(){
    Connection conn = null;
      try{
      conn = DBConnection.getDBConnection().getConnection();
      CallableStatement callableStatement = conn.prepareCall("{call usp_GetTotalViolations()}");
      ResultSet rs = callableStatement.executeQuery();

      if (rs != null){
          while (rs.next()){
          String UserID = rs.getString("UserID");
          String TotalViolations = rs.getString("TotalViolations");
          Violations objViolations = new Violations();
          objViolations.setUserID(UserID);
          objViolations.setTotalViolations(TotalViolations);
          listViolations.add(objViolations);
        }
      }
    } catch (Exception e){
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
    }
    return listViolations;
  }

  /**
   * The function provides  violation of the user either physical or nutrition based on abnormal conditions identified.
   * It projects ActivityID,
   * ActivityTargetDuration,
   * RecordedTime,
   * Log_Id, 
   * UserID from  TABLE tbllog_CurrentLifeLog
   * where
   * ActivityStatus!='NM' 
   * and UserId= uid ;
   *
   * @param userId
   * @return
   */

  public List<Violations> getUserViolations(String userId){

    Connection conn = null;
  
    try{
      conn = DBConnection.getDBConnection().getConnection();
      CallableStatement callableStatement = conn.prepareCall("{call usp_GetUserViolations( " + userId + ")}");
      ResultSet rs = callableStatement.executeQuery();

      if (rs != null){
          while (rs.next()){
          String UserID = rs.getString("UserID");
          String ActivityID = rs.getString("ActivityID");
          String StartTime = rs.getString("RecordedTime");
          String ActivityTargetDuration = rs.getString("ActivityTargetDuration");
          String LogID = rs.getString("Log_Id");
          Violations objViolations = new Violations();
          objViolations.setActivityID(ActivityID);
          objViolations.setActivityTargetDuration(ActivityTargetDuration);
          objViolations.setStartTime(StartTime);
          objViolations.setLogID(LogID);
          objViolations.setUserID(UserID);
          listViolations.add(objViolations);
        }
      }
    } catch (Exception e){
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
    }
    return listViolations;
  }

  /**
   * The function provides the details of a the violation of users either physical or nutrition based on abnormal conditions identified.
   * select ActivityID,
   * ActivityTargetDuration,
   * RecordedTime,
   * Log_Id, UserID 
   * from tbllog_CurrentLifeLog
   * where log_Id= lgid ;
   *
   * @param logId
   * @return
   */
  public List<Violations> getLogDeatails(String logId){

    Connection conn = null;
   
    try{
      conn = DBConnection.getDBConnection().getConnection();
      CallableStatement callableStatement = conn.prepareCall("{call usp_GetLogDeatails( " + logId + ")}");
      ResultSet rs = callableStatement.executeQuery();

      if (rs != null){
        while (rs.next()){
          String UserID = rs.getString("UserID");
          String ActivityID = rs.getString("ActivityID");
          String StartTime = rs.getString("RecordedTime");
          String ActivityTargetDuration = rs.getString("ActivityTargetDuration");
          String LogID = rs.getString("Log_Id");
          Violations objViolations = new Violations();
          objViolations.setActivityID(ActivityID);
          objViolations.setActivityTargetDuration(ActivityTargetDuration);
          objViolations.setStartTime(StartTime);
          objViolations.setLogID(LogID);
          objViolations.setUserID(UserID);
          listViolations.add(objViolations);
        }
      }
    } catch (Exception e){
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
    }
    return listViolations;
  }

}
