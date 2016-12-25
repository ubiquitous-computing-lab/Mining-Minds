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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.dc.Users;
import org.uclab.mm.datamodel.sl.ActiveSession;

/**
 * This is ActiveSessionAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class ActiveSessionAdapter implements DataAccessInterface{

     private Connection objConn;
     private static final Logger logger = LoggerFactory.getLogger(ActiveSessionAdapter.class);
    public ActiveSessionAdapter()
    {
        
    }
    
    /**
     * This is implementation function for saving ActiveSession. 
     * @param objActiveSession
     * @return List of String
     */
     @Override
    public List<String> Save(Object objActiveSession) {
        
        ActiveSession objInnerActiveSession = new ActiveSession();
        objInnerActiveSession =  (ActiveSession) objActiveSession;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_ActiveSession(?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerActiveSession.getUserID());
            objCallableStatement.setString("HashCode", objInnerActiveSession.getHashCode());
            objCallableStatement.setInt("Status", objInnerActiveSession.getStatus());
                       
            
            objCallableStatement.registerOutParameter("ActiveSessionID", Types.BIGINT);
            objCallableStatement.execute();
            
            int intActiveSessionID = objCallableStatement.getInt("ActiveSessionID");
            objConn.close();
            logger.info("Active Session saved successfully.");
        }
        catch (Exception e)
        {
        	logger.info("Error in adding Active Session");
        }   
        
        return objDbResponse;
    }

     /**
      * This is implementation function for Update ActiveSession. 
      * Not Implemented
      */
    @Override
    public <T> List<String> Update(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This is implementation function for retrieve ActiveSession. 
     * Not Implemented
     */
    @Override
    public <T> List<T> RetriveData(T objEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
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
