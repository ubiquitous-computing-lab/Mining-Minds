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
package org.uclab.mm.datamodel.sl.dataadapter;

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
import org.uclab.mm.datamodel.sl.ExpertReview;

/**
 * This is ExpertReviewAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class ExpertReviewAdapter implements DataAccessInterface {
    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(ExpertReviewAdapter.class);
    public ExpertReviewAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving ExpertReview. 
     * @param objExpertReview
     * @return List of String
     */
    @Override
    public List<String> Save(Object objExpertReview) {
        ExpertReview objInnerExpertReview = new ExpertReview();
        objInnerExpertReview =  (ExpertReview) objExpertReview;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_ExpertReview(?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerExpertReview.getUserID());
            objCallableStatement.setLong("UserExpertID", objInnerExpertReview.getUserExpertID());
            objCallableStatement.setString("ReviewDescription", objInnerExpertReview.getReviewDescription());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
            Date dtDate = sdf.parse(objInnerExpertReview.getReviewDate());
            
            Timestamp tsDate = new Timestamp(dtDate.getYear(),dtDate.getMonth(), dtDate.getDate(), dtDate.getHours(), dtDate.getMinutes(), dtDate.getSeconds(), 00);
            objCallableStatement.setTimestamp("ReviewDate", tsDate);
            
            objCallableStatement.setInt("ReviewStatusID", objInnerExpertReview.getReviewStatusID());
            
            objCallableStatement.registerOutParameter("ExpertReviewID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intExpertReviewID = objCallableStatement.getLong("ExpertReviewID");
            objDbResponse.add(String.valueOf(intExpertReviewID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Expert Review saved successfully, Expert Review Details="+objExpertReview);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Expert Review");
        	objDbResponse.add("Error in adding Expert Review");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating ExpertReview. 
     * @param objExpertReview
     * @return List of String
     */
    @Override
    public List<String> Update(Object objExpertReview) {
        ExpertReview objInnerExpertReview = new ExpertReview();
        objInnerExpertReview =  (ExpertReview) objExpertReview;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_ExpertReview(?, ?)}");
            
            objCallableStatement.setLong("ExpertReviewID", objInnerExpertReview.getExpertReviewID());
            objCallableStatement.setInt("ReviewStatusID", objInnerExpertReview.getReviewStatusID());
                        
            objCallableStatement.execute();
            
            Long intExpertReviewID = objInnerExpertReview.getExpertReviewID();
            objDbResponse.add(String.valueOf(intExpertReviewID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("Expert Review updated successfully, Expert Review Details="+objExpertReview);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating Expert Review");
        	objDbResponse.add("Error in updating Expert Review");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving ExpertReview. 
     * @param objExpertReview
     * @return List of ExpertReview
     */
    @Override
    public List<ExpertReview> RetriveData(Object objExpertReview) {
        ExpertReview objOuterExpertReview = new ExpertReview();
        List<ExpertReview> objListInnerExpertReview = new ArrayList<ExpertReview>();
        objOuterExpertReview =  (ExpertReview) objExpertReview;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if( objOuterExpertReview.getRequestType() == "ByUserID")
             {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ExpertReviewByUserID(?)}");
                objCallableStatement.setLong("UserID", objOuterExpertReview.getUserID());
             }
             else if(objOuterExpertReview.getRequestType() == "ByUserAndDate")
             {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_ExpertReviewByUserIDDate(?, ?, ?)}");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm	
                Date dtStartDate = sdf.parse( objOuterExpertReview.getStartDate());
                Timestamp tsStartDate = new Timestamp(dtStartDate.getYear(),dtStartDate.getMonth(), dtStartDate.getDate(), dtStartDate.getHours(), dtStartDate.getMinutes(),dtStartDate.getSeconds(),0);

                Date dtEndDate =sdf.parse(objOuterExpertReview.getEndDate()); //.getTime();
                Timestamp tsEndDate = new Timestamp(dtEndDate.getYear(),dtEndDate.getMonth(), dtEndDate.getDate(), dtEndDate.getHours(), dtEndDate.getMinutes(),dtEndDate.getSeconds(),0);

                objCallableStatement.setLong("UserID", objOuterExpertReview.getUserID());
                objCallableStatement.setTimestamp("StartTime", tsStartDate);
                objCallableStatement.setTimestamp("EndTime", tsEndDate);
                
             }
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                ExpertReview objInnerExpertReview = new ExpertReview();
                
                objInnerExpertReview.setExpertReviewID(objResultSet.getLong("ExpertReviewID"));
                objInnerExpertReview.setUserID(objResultSet.getLong("UserID"));
                objInnerExpertReview.setUserExpertID(objResultSet.getLong("UserExpertID"));
                objInnerExpertReview.setReviewDescription(objResultSet.getString("ReviewDescription"));
                
                 if(objResultSet.getTimestamp("ReviewDate") != null)
                {
                    Timestamp tsReviewDate = objResultSet.getTimestamp("ReviewDate");
                    objInnerExpertReview.setReviewDate(tsReviewDate.toString());
                }
                objInnerExpertReview.setReviewStatusID(objResultSet.getInt("ReviewStatusID"));
                objListInnerExpertReview.add(objInnerExpertReview);
            }
            objConn.close();
            logger.info("Expert Review loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Expert Review");
        }   
        return objListInnerExpertReview;
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
