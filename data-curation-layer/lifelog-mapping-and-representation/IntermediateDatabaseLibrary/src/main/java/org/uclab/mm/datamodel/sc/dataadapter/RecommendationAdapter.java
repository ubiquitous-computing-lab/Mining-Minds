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
import org.uclab.mm.datamodel.sc.Recommendation;
import org.uclab.mm.datamodel.sc.Situation;

/**
 * This is RecommendationAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class RecommendationAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(RecommendationAdapter.class);
    public RecommendationAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving Recommendation. 
     * @param objRecommendation
     * @return List of String
     */
    @Override
    public List<String> Save(Object objRecommendation) {
        Recommendation objInnerRecommendation = new Recommendation();
        objInnerRecommendation =  (Recommendation) objRecommendation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_Recommendation(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setString("RecommendationIdentifier", objInnerRecommendation.getRecommendationIdentifier());
            objCallableStatement.setLong("SituationID", objInnerRecommendation.getSituationID());
            objCallableStatement.setString("RecommendationDescription", objInnerRecommendation.getRecommendationDescription());
            objCallableStatement.setString("ConditionValue", objInnerRecommendation.getConditionValue());
            objCallableStatement.setInt("RecommendationTypeID", objInnerRecommendation.getRecommendationTypeID());
            objCallableStatement.setInt("RecommendationLevelID", objInnerRecommendation.getRecommendationLevelID());
            objCallableStatement.setInt("RecommendationStatusID", objInnerRecommendation.getRecommendationStatusID());
            
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerRecommendation.getRecommendationDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("RecommendationDate", tsDate);
            
            objCallableStatement.registerOutParameter("RecommendationID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intRecommendationID = objCallableStatement.getLong("RecommendationID");
            objDbResponse.add(String.valueOf(intRecommendationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Recommendation saved successfully, Recommendation Details="+objRecommendation);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Recommendation");
        	objDbResponse.add("Error in adding Recommendation");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating Recommendation. 
     * @param objRecommendation
     * @return List of String
     */
    @Override
    public List<String> Update(Object objRecommendation) {
        Recommendation objInnerRecommendation = new Recommendation();
        objInnerRecommendation =  (Recommendation) objRecommendation;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_Recommendation(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("RecommendationID", objInnerRecommendation.getRecommendationID());
            objCallableStatement.setString("RecommendationIdentifier", objInnerRecommendation.getRecommendationIdentifier());
            objCallableStatement.setLong("SituationID", objInnerRecommendation.getSituationID());
            objCallableStatement.setString("RecommendationDescription", objInnerRecommendation.getRecommendationDescription());
            objCallableStatement.setString("ConditionValue", objInnerRecommendation.getConditionValue());
            objCallableStatement.setInt("RecommendationTypeID", objInnerRecommendation.getRecommendationTypeID());
            objCallableStatement.setInt("RecommendationLevelID", objInnerRecommendation.getRecommendationLevelID());
            objCallableStatement.setInt("RecommendationStatusID", objInnerRecommendation.getRecommendationStatusID());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerRecommendation.getRecommendationDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("RecommendationDate", tsDate);
            
           objCallableStatement.execute();
            
            Long intRecommendationID = objInnerRecommendation.getRecommendationID();
            objDbResponse.add(String.valueOf(intRecommendationID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Recommendation saved successfully, Recommendation Details="+objRecommendation);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating Recommendation");
        	objDbResponse.add("Error in updating Recommendation");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving Recommendation. 
     * @param objRecommendation
     * @return List of Recommendation
     */
    @Override
    public List<Recommendation> RetriveData(Object objRecommendation) {
        Recommendation objOuterRecommendation = new Recommendation();
        List<Recommendation> objListInnerRecommendation = new ArrayList<Recommendation>();
        objOuterRecommendation =  (Recommendation) objRecommendation;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterRecommendation.getRequestType() == "ByUserOnly")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_RecommendationByUser(?)}");
                objCallableStatement.setLong("UserID", objOuterRecommendation.getUserID());
            }
            else if(objOuterRecommendation.getRequestType() == "ByUserandDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_RecommendationByUserIDDate(?, ?, ?)}");
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtStartDate = sdf.parse(objOuterRecommendation.getStartDate());
               Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

               Date dtEndDate =sdf.parse( objOuterRecommendation.getEndDate()); //.getTime();
               Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
            
                objCallableStatement.setLong("UserID", objOuterRecommendation.getUserID());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
            }
            else if(objOuterRecommendation.getRequestType() == "ByActivityIDsAndDate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_RecommendationsByDateRangeActivityIDs(?, ?, ?)}");
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
               Date dtStartDate = sdf.parse(objOuterRecommendation.getStartDate());
               Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

               Date dtEndDate =sdf.parse( objOuterRecommendation.getEndDate()); //.getTime();
               Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);
            
                objCallableStatement.setString("ActivityIDs", objOuterRecommendation.getSituationCategoryIDs());
                objCallableStatement.setTimestamp("StartDate", tsStartDate);
                objCallableStatement.setTimestamp("EndDate", tsEndDate);
            }
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                Recommendation objInnerRecommendation = new Recommendation();
                objInnerRecommendation.setRecommendationID(objResultSet.getLong("RecommendationID"));
                objInnerRecommendation.setRecommendationIdentifier(objResultSet.getString("RecommendationIdentifier"));
                objInnerRecommendation.setSituationID(objResultSet.getLong("SituationID"));
                objInnerRecommendation.setRecommendationDescription(objResultSet.getString("RecommendationDescription"));
                objInnerRecommendation.setRecommendationTypeID(objResultSet.getInt("RecommendationTypeID"));
                objInnerRecommendation.setConditionValue(objResultSet.getString("ConditionValue"));
                objInnerRecommendation.setRecommendationLevelID(objResultSet.getInt("RecommendationLevelID"));
                objInnerRecommendation.setRecommendationStatusID(objResultSet.getInt("RecommendationStatusID"));
                
                if(objResultSet.getTimestamp("RecommendationDate") != null)
                {
                    Timestamp tsRecommendationDate = objResultSet.getTimestamp("RecommendationDate");
                    objInnerRecommendation.setRecommendationDate(tsRecommendationDate.toString());
                }
                   
               objInnerRecommendation.setRecommendationTypeDescription(objResultSet.getString("RecommendationTypeDescription"));
               objInnerRecommendation.setRecommendationLevelDescription(objResultSet.getString("RecommendationLevelDescription"));
               objInnerRecommendation.setRecommendationStatusDescription(objResultSet.getString("RecommendationStatusDescription"));
               
               objInnerRecommendation.setSituationCategoryID(objResultSet.getInt("SituationCategoryID"));
               objInnerRecommendation.setSituationCategoryDescription(objResultSet.getString("SituationCategoryDescription"));
                              
               objListInnerRecommendation.add(objInnerRecommendation);
            }
            objConn.close();
            logger.info("Recommendation loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Recommendation");
        }   
        return objListInnerRecommendation;
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
