/*
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */
package org.uclab.mm.datamodel.sc.dataadapter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.sc.Situation;
import org.uclab.mm.datamodel.sc.UserGoal;

/**
 * This is SituationAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class SituationAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(SituationAdapter.class);
    public SituationAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving Situation. 
     * @param objSituation
     * @return List of String
     */
    @Override
    public List<String> Save(Object objSituation) {
        Situation objInnerSituation = new Situation();
        objInnerSituation =  (Situation) objSituation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_Situation(?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerSituation.getUserID());
            objCallableStatement.setInt("SituationCategoryID", objInnerSituation.getSituationCategoryID());
            objCallableStatement.setString("SituationDescription", objInnerSituation.getSituationDescription());
          
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerSituation.getSituationDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("SituationDate", tsDate);
                        
            objCallableStatement.registerOutParameter("SituationID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intSituationID = objCallableStatement.getLong("SituationID");
            objDbResponse.add(String.valueOf(intSituationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("situation saved successfully, User Details="+objSituation);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding situation");
        	objDbResponse.add("Error in adding situation");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating Situation. 
     * @param objSituation
     * @return List of String
     */
    @Override
    public List<String> Update(Object objSituation) {
        
        Situation objInnerSituation = new Situation();
        objInnerSituation =  (Situation) objSituation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.[usp_Update_Situation](?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("SituationID", objInnerSituation.getSituationID());
            objCallableStatement.setLong("UserID", objInnerSituation.getUserID());
            objCallableStatement.setInt("SituationCategoryID", objInnerSituation.getSituationCategoryID());
            objCallableStatement.setString("SituationDescription", objInnerSituation.getSituationDescription());
          
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerSituation.getSituationDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("SituationDate", tsDate);

            objCallableStatement.execute();
            
            Long intSituationID = objInnerSituation.getSituationID();
            objDbResponse.add(String.valueOf(intSituationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("situation saved successfully, situation Details="+objSituation);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating situation");
        	objDbResponse.add("Error in updating situation");
        } 
        return objDbResponse;
        
    }

    /**
     * This is implementation function for retrieving Situation.
     * @param objSituation
     * @return List of Situation 
     */
    @Override
    public List<Situation> RetriveData(Object objSituation) {
        
        Situation objOuterSituation = new Situation();
        List<Situation> objListInnerSituation = new ArrayList<Situation>();
        objOuterSituation =  (Situation) objSituation;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterSituation.getRequestType() == "ByUserOnly")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_SituationByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterSituation.getUserID());
            }
            else if(objOuterSituation.getRequestType() == "ByUserandDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_SituationByUserIDDate(?, ?, ?)}");
                
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtStartDate = sdf.parse(objOuterSituation.getStartDate());
               Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

               Date dtEndDate =sdf.parse( objOuterSituation.getEndDate()); //.getTime();
               Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
            
                objCallableStatement.setLong("UserID", objOuterSituation.getUserID());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            else if(objOuterSituation.getRequestType() == "BySituationID")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_SituationBySituationID(?)}");
                objCallableStatement.setLong("SituationID", objOuterSituation.getSituationID());
            }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                Situation objInnerSituation = new Situation();
                objInnerSituation.setSituationID(objResultSet.getLong("SituationID"));
                objInnerSituation.setUserID(objResultSet.getLong("UserID"));
                objInnerSituation.setSituationCategoryID(objResultSet.getInt("SituationCategoryID"));
                objInnerSituation.setSituationDescription(objResultSet.getString("SituationDescription"));
                if(objResultSet.getTimestamp("SituationDate") != null)
                {
                    Timestamp tsSituationDate = objResultSet.getTimestamp("SituationDate");
                    objInnerSituation.setSituationDate(tsSituationDate.toString());
                }
                   
               objInnerSituation.setSituationCategoryDescription(objResultSet.getString("SituationCategoryDescription"));
               objListInnerSituation.add(objInnerSituation);
            }
            objConn.close();
            logger.info("situation loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading situation");
        }   
        return objListInnerSituation;
        
    }

    @Override
    public <T> List<T> Delete(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for connection database.
     * @param objConf 
     */
    @Override
     public void ConfigureAdapter(Object objConf) {
         try
         {
            objConn = (Connection)objConf;
            logger.info("Database connected successfully");
         }
         catch(Exception ex)
         {
        	 logger.info("Error in connection to Database");
         }
    }
    
}
