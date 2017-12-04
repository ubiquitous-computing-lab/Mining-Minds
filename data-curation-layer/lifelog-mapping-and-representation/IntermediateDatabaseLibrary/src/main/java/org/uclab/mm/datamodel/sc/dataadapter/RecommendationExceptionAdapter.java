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
import org.uclab.mm.datamodel.sc.RecommendationException;

/**
 * This is RecommendationExceptionAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir Ali
 */
public class RecommendationExceptionAdapter implements DataAccessInterface {

    private Connection objConn;
    private static final Logger logger = LoggerFactory.getLogger(RecommendationExceptionAdapter.class);
    public RecommendationExceptionAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving RecommendationException.
     * @param objRecommendationException
     * @return List of String 
     */
    @Override
    public List<String> Save(Object objRecommendationException) {
        RecommendationException objInnerRecommendationException = new RecommendationException();
        objInnerRecommendationException =  (RecommendationException) objRecommendationException;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_RecommendationException(?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("RecommendationID", objInnerRecommendationException.getRecommendationID());
            objCallableStatement.setString("Exception", objInnerRecommendationException.getException());
            objCallableStatement.setString("CustomRule", objInnerRecommendationException.getCustomRule());
            objCallableStatement.setString("ExceptionReason", objInnerRecommendationException.getExceptionReason());
            
            objCallableStatement.registerOutParameter("RecommendationExceptionID", Types.BIGINT);
            objCallableStatement.execute();
            
            Long intRecommendationExceptionID = objCallableStatement.getLong("RecommendationExceptionID");
            objDbResponse.add(String.valueOf(intRecommendationExceptionID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("RecommendationException saved successfully, RecommendationException Details="+objRecommendationException);
        }
        catch (Exception e)
        {
        	logger.info("Error in adding RecommendationException");
        	objDbResponse.add("Error in adding RecommendationException");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for updating RecommendationException.
     * @param objRecommendationException
     * @return List of RecommendationException 
     */
    @Override
    public List<String> Update(Object objRecommendationException) {
        
        RecommendationException objInnerRecommendationException = new RecommendationException();
        objInnerRecommendationException =  (RecommendationException) objRecommendationException;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_RecommendationException(?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("RecommendationExceptionID", objInnerRecommendationException.getRecommendationExceptionID());
            objCallableStatement.setLong("RecommendationID", objInnerRecommendationException.getRecommendationID());
            objCallableStatement.setString("Exception", objInnerRecommendationException.getException());
            objCallableStatement.setString("CustomRule", objInnerRecommendationException.getCustomRule());
            objCallableStatement.setString("ExceptionReason", objInnerRecommendationException.getExceptionReason());
            
            objCallableStatement.execute();
            
            Long intRecommendationExceptionID = objInnerRecommendationException.getRecommendationExceptionID();
            objDbResponse.add(String.valueOf(intRecommendationExceptionID));
            objDbResponse.add("No Error");

            objConn.close();
            logger.info("RecommendationException updated successfully, RecommendationException Details="+objRecommendationException);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating RecommendationException");
        	objDbResponse.add("Error in updating RecommendationException");
        } 
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving RecommendationException.
     * @param objRecommendationException
     * @return List of RecommendationException 
     */
    @Override
    public List<RecommendationException> RetriveData(Object objRecommendationException) {
        RecommendationException objOuterRecommendationException = new RecommendationException();
        List<RecommendationException> objListInnerRecommendationException = new ArrayList<RecommendationException>();
        objOuterRecommendationException =  (RecommendationException) objRecommendationException;
        
        try
        {
            CallableStatement objCallableStatement = null;
            objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_RecommendationExceptionByRecommendationID(?)}");
                
            objCallableStatement.setLong("RecommendationID", objOuterRecommendationException.getRecommendationID());
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                RecommendationException objInnerRecommendationException = new RecommendationException();
                
                objInnerRecommendationException.setRecommendationExceptionID(objResultSet.getLong("RecommendationExceptionID"));
                objInnerRecommendationException.setRecommendationID(objResultSet.getLong("RecommendationID"));
                objInnerRecommendationException.setException(objResultSet.getString("Exception"));
                objInnerRecommendationException.setCustomRule(objResultSet.getString("CustomRule"));
                objInnerRecommendationException.setExceptionReason(objResultSet.getString("ExceptionReason"));
                
                objListInnerRecommendationException.add(objInnerRecommendationException);
            }
            objConn.close();
            logger.info("Recommendation Exception loaded successfully");
        }
        catch (Exception e)
        {
        	logger.info("Error in loading Recommendation Exception");
        }   
        return objListInnerRecommendationException;
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
