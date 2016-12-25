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

package org.uclab.mm.datamodel.dc.dataadapter;

//import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import org.uclab.mm.datamodel.dc.Users;

/**
 * This is UserDataAdapter class which implements the Data Access Interface for CRUD operations
 * @author Taqdir
 */
public class UserDataAdapter implements DataAccessInterface{

    private Connection objConn;
    
    private static final Logger logger = LoggerFactory.getLogger(UserDataAdapter.class);
    
    public UserDataAdapter()
    {
        
    }

    /**
     * This is implementation function for saving User.
     * @param objUser
     * @return List of String 
     */
    @Override
    public List<String> Save(Object objUser) {
        
        Users objInnerUser = new Users();
        objInnerUser =  (Users) objUser;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Add_User(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setString("FirstName", objInnerUser.getFirstName());
            objCallableStatement.setString("LastName", objInnerUser.getLastName());
            objCallableStatement.setString("MiddleName", objInnerUser.getMiddleName());
            objCallableStatement.setInt("GenderID", objInnerUser.getGenderId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
             Date dtDateOfBirth = sdf.parse(objInnerUser.getDateOfBirth());
            Timestamp tsDateOfBirth = new Timestamp(dtDateOfBirth.getYear(),dtDateOfBirth.getMonth(), dtDateOfBirth.getDate(), 00, 00, 00, 00);
            objCallableStatement.setTimestamp("DateOfBirth", tsDateOfBirth);
            
            objCallableStatement.setString("ContactNumber", objInnerUser.getContactNumber());
            objCallableStatement.setString("EmailAddress", objInnerUser.getEmailAddress());
            objCallableStatement.setString("Password", objInnerUser.getPassword());
            objCallableStatement.setInt("MaritalStatusID", objInnerUser.getMartialStatusId());
            objCallableStatement.setInt("ActivityLevelID", objInnerUser.getActivityLevelId());
            objCallableStatement.setInt("OccupationID", objInnerUser.getOccupationId());
            objCallableStatement.setInt("UserTypeID", objInnerUser.getUserTypeID());
            
            objCallableStatement.registerOutParameter("UserID", Types.BIGINT);
            objCallableStatement.execute();
            
            int intUserID = objCallableStatement.getInt("UserID");
            objDbResponse.add(String.valueOf(intUserID));
            objDbResponse.add("No Error");
            

            objConn.close();
            logger.info("User saved successfully, User Details="+objUser);
        }
        catch (Exception e)
        {
        	logger.info("Error in saving user, User Details=");
        	objDbResponse.add("Error in saving user");
        }   
        
        return objDbResponse;
    }

    /**
     * This is implementation function for updating User.
     * @param objUser
     * @return List of string
     *  
     */
    @Override
    public List<String> Update(Object objUser) {
        
        Users objInnerUser = new Users();
        objInnerUser =  (Users) objUser;
        List<String> objDbResponse = new ArrayList<>();
        
        try
        {

            CallableStatement objCallableStatement = objConn.prepareCall("{call dbo.usp_Update_User(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            objCallableStatement.setLong("UserID", objInnerUser.getUserID());
            objCallableStatement.setString("FirstName", objInnerUser.getFirstName());
            objCallableStatement.setString("LastName", objInnerUser.getLastName());
            objCallableStatement.setString("MiddleName", objInnerUser.getMiddleName());
            objCallableStatement.setInt("GenderID", objInnerUser.getGenderId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss"); // month updated to MM from mm
            Date dtDateOfBirth = sdf.parse(objInnerUser.getDateOfBirth());
            Timestamp tsDateOfBirth = new Timestamp(dtDateOfBirth.getYear(),dtDateOfBirth.getMonth(), dtDateOfBirth.getDate(), 00, 00, 00, 00);
            objCallableStatement.setTimestamp("DateOfBirth", tsDateOfBirth);
            
            objCallableStatement.setString("ContactNumber", objInnerUser.getContactNumber());
            objCallableStatement.setString("EmailAddress", objInnerUser.getEmailAddress());
            objCallableStatement.setString("Password", objInnerUser.getPassword());
            objCallableStatement.setInt("MaritalStatusID", objInnerUser.getMartialStatusId());
            objCallableStatement.setInt("ActivityLevelID", objInnerUser.getActivityLevelId());
            objCallableStatement.setInt("OccupationID", objInnerUser.getOccupationId());
            objCallableStatement.setInt("UserTypeID", objInnerUser.getUserTypeID());
            
            objCallableStatement.execute();
            
            objDbResponse.add(String.valueOf(objInnerUser.getUserID()));
            objDbResponse.add("No Error");
            
            objConn.close();
            logger.info("User udpated successfully, User Details="+objUser);
        }
        catch (Exception e)
        {
        	logger.info("Error in updating user, User Details=");
        	objDbResponse.add("Error in saving user");
        }   
        
        return objDbResponse;
    }

    /**
     * This is implementation function for retrieving User. 
     * @param objUser
     * @return List of Users
     */
    @Override
    public List<Users> RetriveData(Object objUser) {
        
        Users objOuterUser = new Users();
        List<Users> objListInnerUser = new ArrayList<Users>();
        objOuterUser =  (Users) objUser;
        
        try
        {
            CallableStatement objCallableStatement = null;
            if(objOuterUser.getRequestType() == "UserData")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UserByID(?)}");
                objCallableStatement.setLong("UserID", objOuterUser.getUserID());
            }
            else if(objOuterUser.getRequestType() == "UserValidate")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Validate_User(?, ?)}");
                objCallableStatement.setString("EmailAddress", objOuterUser.getEmailAddress());
                objCallableStatement.setString("Password", objOuterUser.getPassword());
            }
            else if(objOuterUser.getRequestType() == "IsUserExist")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_IsExist_User(?)}");
                objCallableStatement.setString("EmailAddress", objOuterUser.getEmailAddress());
            }
            else if(objOuterUser.getRequestType() == "UsersListByExpert")
            {
                objCallableStatement = objConn.prepareCall("{call dbo.usp_Get_UsersListByExpertID(?)}");
                objCallableStatement.setLong("UserID", objOuterUser.getUserID());
            }
                
            
            ResultSet objResultSet = objCallableStatement.executeQuery();

            while(objResultSet.next())
            {
                Users objInnerUser = new Users();
                objInnerUser.setUserID(objResultSet.getLong("UserID"));
                objInnerUser.setFirstName(objResultSet.getString("FirstName"));
                objInnerUser.setLastName(objResultSet.getString("LastName"));
                objInnerUser.setMiddleName(objResultSet.getString("MiddleName"));
                objInnerUser.setGenderId(objResultSet.getInt("GenderID"));
                
                Timestamp tsDateOfBirth = objResultSet.getTimestamp("DateOfBirth");
                objInnerUser.setDateOfBirth(tsDateOfBirth.toString());
               
                objInnerUser.setContactNumber(objResultSet.getString("ContactNumber"));
                objInnerUser.setEmailAddress(objResultSet.getString("EmailAddress"));
                objInnerUser.setPassword(objResultSet.getString("Password"));
                objInnerUser.setMartialStatusId(objResultSet.getInt("MaritalStatusID"));
                objInnerUser.setActivityLevelId(objResultSet.getInt("ActivityLevelID"));
                objInnerUser.setOccupationId(objResultSet.getInt("OccupationID"));
                                
                objInnerUser.setUserTypeID(objResultSet.getInt("UserTypeID"));
                if(objResultSet.getString("UserTypeDescription") != null)
                {
                    objInnerUser.setUserTypeDescription(objResultSet.getString("UserTypeDescription"));
                }
                
                objInnerUser.setGenderDescription(objResultSet.getString("GenderDescription"));
                objInnerUser.setMaritalStatusDescription(objResultSet.getString("MaritalStatusDescription"));
                objInnerUser.setOccupationDescription(objResultSet.getString("OccupationDescription"));
                
                objListInnerUser.add(objInnerUser);
            }
            objConn.close();
            logger.info("User loaded successfully.");
        }
        catch (Exception e)
        {
        	logger.info("Error in getting user");
        }   
        return objListInnerUser;
    }
    public <T> List<T> convert(List list, T t){ return list; }

    @Override
    public List<Object> Delete(Object objConn) {
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
