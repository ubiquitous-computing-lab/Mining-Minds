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
package org.uclab.mm.rs.datacuration;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uclab.mm.datamodel.AbstractDataBridge;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.DatabaseStorage;
import org.uclab.mm.datamodel.dc.PhysiologicalFactors;
import org.uclab.mm.datamodel.dc.UserDisabilities;
import org.uclab.mm.datamodel.dc.UserFacilities;
import org.uclab.mm.datamodel.dc.UserRiskFactors;
import org.uclab.mm.datamodel.dc.UserSchedule;
import org.uclab.mm.datamodel.dc.Users;
import org.uclab.mm.datamodel.dc.dataadapter.PhysiologicalFactorsAdapter;
import org.uclab.mm.datamodel.dc.dataadapter.UserDataAdapter;
import org.uclab.mm.datamodel.dc.dataadapter.UserDisabilitiesAdapter;
import org.uclab.mm.datamodel.dc.dataadapter.UserFacilitiesAdapter;
import org.uclab.mm.datamodel.dc.dataadapter.UserRiskFactorsAdapter;
import org.uclab.mm.datamodel.dc.dataadapter.UserScheduleAdapter;
import org.uclab.mm.datamodel.sl.ActiveSession;
import org.uclab.mm.datamodel.sl.HashClass;
import org.uclab.mm.datamodel.sl.dataadapter.ActiveSessionAdapter;

import com.google.gson.Gson;



/**
 * Facade for the Restful Web service to handle the data curation functions 
 *
 * @author Taqdir Ali
 */
@Path("DataCuration")
public class DataCurationResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DataCurationResource
     */
    public DataCurationResource() {
    }
    
    private static final Logger logger = LoggerFactory.getLogger(DataCurationResource.class);
    
    /**
     * This function is using to Register user 
     * @param objOuterUser
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RegisterUser")
    public List<String> RegisterUser(Users objOuterUser) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            objOuterUser.setRequestType("UserValidate");
            DataAccessInterface objDAInterface = new UserDataAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUser(objOuterUser);
            logger.info("User saved successfully, User Details="+objOuterUser);
        }
        catch(Exception ex)
        {
        	logger.info("Error in saving user, User Details=");
        	response.add("Error in saving user");
        }
        return response;
    }
    
    /**
     * This function is using to update user 
     * @param objOuterUser
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUser")
    public List<String> UpdateUser(Users objOuterUser) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserDataAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUser(objOuterUser);
            logger.info("User udpated successfully, User Details="+objOuterUser);
        }
        catch(Exception ex)
        { 
        	logger.info("Error in updating user, User Details=");
        	response.add("Error in saving user");
        }
        return response;
    }
    

    /**
     * This function is using to get user by ID 
     * @param UserID
     * @return a list of object Users with "Error", "No Error" and new added ID
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("User/{UserID}")
    public List<Users> GetUser(@PathParam ("UserID") String UserID) {
        Users objOuterUser = new Users();
        List<Users> objListUsers = new  ArrayList<Users>();
        try
        {
            objOuterUser.setUserID(Long.parseLong(UserID));
            objOuterUser.setRequestType("UserData");

            DataAccessInterface objDAInterface = new UserDataAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUsers = objADBridge.RetriveUser(objOuterUser);
            logger.info("Get user successfully, User Details="+objOuterUser);
        }
        catch(Exception ex)
        {
        	logger.info("Error in getting user");
        }
        return objListUsers;
    }
    
    /**
     * This function is  using to retrieves Users List by ExpertID 
     * @param UserID
     * @return an instance of list of Users
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserListByExpertID/{UserID}")
    public List<Users> GetUserListByExpertID(@PathParam ("UserID") String UserID) {
        Users objOuterUser = new Users();
        List<Users> objListUsers = new  ArrayList<Users>();
        try
        {
            objOuterUser.setUserID(Long.parseLong(UserID));
            objOuterUser.setRequestType("UsersListByExpert");

            DataAccessInterface objDAInterface = new UserDataAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUsers = objADBridge.RetriveUser(objOuterUser);
            logger.info("Get user successfully by expert id, User Details="+objOuterUser);
        }
        catch(Exception ex)
        {
        	logger.info("Error in getting user");
        }
        return objListUsers;
    }
    
    /**
     * Authenticate user by user login and password
     * @param objOuterUser
     * @return an instance of list of Users
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("ValidateUser")
    public List<Users> ValidateUser(Users objOuterUser) {
        
        List<Users> objListUsers = new  ArrayList<Users>();
        try
        {
            objOuterUser.setRequestType("UserValidate");
            DataAccessInterface objDAInterface = new UserDataAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUsers = objADBridge.RetriveUser(objOuterUser);
            logger.info("User authenticated successfully, User Details="+objOuterUser);
        }
        catch(Exception ex)
        {
        	logger.info("Error in getting user");
        }
        return objListUsers;
    }
    
    /**
     * Check the user is already exist by email address
     * @param objOuterUser
     * @return an instance of list of Users
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("IsUserExist")
    public List<Users> IsUserExist(Users objOuterUser) {
        
        List<Users> objListUsers = new  ArrayList<Users>();
        try
        {
            objOuterUser.setRequestType("IsUserExist");
            DataAccessInterface objDAInterface = new UserDataAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUsers = objADBridge.RetriveUser(objOuterUser);
            logger.info("User checked successfully, User Details=");
        }
        catch(Exception ex)
        {
        	logger.info("Error in getting user");
        }
        return objListUsers;
    }
    
    /**
     * This function is adding user risk factors
     * @param objOuterUserRiskFactors
     * @return an instance of list of string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserRiskFactors")
    
    public List<String> AddUserRiskFactors(UserRiskFactors objOuterUserRiskFactors) {
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRiskFactorsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserRiskFactors(objOuterUserRiskFactors);
            logger.info("Risk factor saved successfully, User Details=" + objOuterUserRiskFactors);
        }
        catch(Exception ex)
        {
        	logger.info("Error in saving risk factor");
        	response.add("Error in saving risk factor");
        }
        return response;
    }
    
    /**
     * This function is retrieving the Risk factors by UserID 
     * @param UserID
     * @return an instance of list of UserRiskFactors
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("RiskFactors/{UserID}")
    public List<UserRiskFactors> GetRiskFactors(@PathParam ("UserID") String UserID) {
        UserRiskFactors objOuterUserRiskFactors = new UserRiskFactors();
        List<UserRiskFactors> objListUserRiskFactors = new  ArrayList<UserRiskFactors>();
        try
        {
            objOuterUserRiskFactors.setUserId(Long.parseLong(UserID));

            DataAccessInterface objDAInterface = new UserRiskFactorsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserRiskFactors = objADBridge.RetriveUserRiskFactors(objOuterUserRiskFactors);
            logger.info("Risk factor loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in getting risk factor");
        }
        return objListUserRiskFactors;
    }
    
    /**
     * This function is using to Update user risk factors
     * @param objOuterUserRiskFactors
     * @return an instance of list of string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserRiskFactors")
    public List<String> UpdateUserRiskFactors(UserRiskFactors objOuterUserRiskFactors) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserRiskFactorsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserRiskFactors(objOuterUserRiskFactors);
            logger.info("Risk factor saved successfully, User Details="+objOuterUserRiskFactors);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updating risk factor");
        	response.add("Error in updating risk factor");
        }
        return response;
    }
    
    /**
     * This function is used to Add user Physiological factors
     * @param objOuterPhysiologicalFactors
     * @return an instance of list of string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddPhysiologicalFactors")
    public List<String> AddPhysiologicalFactors(PhysiologicalFactors objOuterPhysiologicalFactors) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new PhysiologicalFactorsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SavePhysiologicalFactors(objOuterPhysiologicalFactors);
            logger.info("Physiological factor saved successfully, User Details="+objOuterPhysiologicalFactors);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding Physiological factors");
        	response.add("Error in adding Physiological factors");
        }
        return response;
    }
    
    /**
     * This function is implemented to Retrieve Physiological Factors by UserID 
     * @param UserID
     * @return an instance of list of PhysiologicalFactors
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("PhysiologicalFactors/{UserID}")
    public List<PhysiologicalFactors> GetPhysiologicalFactors(@PathParam ("UserID") String UserID) {
        PhysiologicalFactors objOuterPhysiologicalFactors = new PhysiologicalFactors();
        List<PhysiologicalFactors> objListPhysiologicalFactors = new  ArrayList<PhysiologicalFactors>();
        try
        {
            objOuterPhysiologicalFactors.setUserId(Long.parseLong(UserID));

            DataAccessInterface objDAInterface = new PhysiologicalFactorsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListPhysiologicalFactors = objADBridge.RetrivePhysiologicalFactors(objOuterPhysiologicalFactors);
            logger.info("Physiological is loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in Loading Physiological factors");
        }
        return objListPhysiologicalFactors;
    }
    
    /**
     * This function is using to Update user Physiological factors
     * @param objOuterPhysiologicalFactors
     * @return list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdatePhysiologicalFactors")
    public List<String> UpdatePhysiologicalFactors(PhysiologicalFactors objOuterPhysiologicalFactors) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new PhysiologicalFactorsAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdatePhysiologicalFactors(objOuterPhysiologicalFactors);
            logger.info("Physiological factor updated successfully, User Details="+objOuterPhysiologicalFactors);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated Physiological factors");
        	response.add("Error in updated Physiological factors");
        }
        return response;
    }
    
    /**
     *  This function is using to Add user Facilities
     * @param objOuterUserFacilities
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserFacilities")
    public List<String> AddUserFacilities(UserFacilities objOuterUserFacilities) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserFacilitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserFacilities(objOuterUserFacilities);
            logger.info("user Facilities saved successfully, User Details="+objOuterUserFacilities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding user Facilities");
        	response.add("Error in adding user Facilities");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves users Facilities by UserID 
     * @param UserID
     * @return an instance of list of UserFacilities
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserFacilities/{UserID}")
    public List<UserFacilities> GetUserFacilities(@PathParam ("UserID") String UserID) {
        UserFacilities objOuterUserFacilities = new UserFacilities();
        List<UserFacilities> objListUserFacilities = new  ArrayList<UserFacilities>();
        try
        {
            objOuterUserFacilities.setUserId(Long.parseLong(UserID));

            DataAccessInterface objDAInterface = new UserFacilitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserFacilities = objADBridge.RetriveUserFacilities(objOuterUserFacilities);
            logger.info("user Facilities loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading user Facilities");
        }
        return objListUserFacilities;
    }
    
    /**
     * This function is using to Update user Facilities
     * @param objOuterUserFacilities
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserFacilities")
    public List<String> UpdateUserFacilities(UserFacilities objOuterUserFacilities) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserFacilitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserFacilities(objOuterUserFacilities);
            logger.info("user Facilities saved successfully, User Details="+objOuterUserFacilities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated user Facilities");
        	response.add("Error in updated user Facilities");
        }
        return response;
    }
    
    /**
     * This function is using to Authenticate user by user login and password
     * @param objUsers
     * @return an instance of list of Users
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AuthenticateUser")
    public List<String> AuthenticateUser(Users objUsers) {
        List<String> objResponse = new ArrayList<String>();
        List<Users> objListUsers = new  ArrayList<Users>();
        try
        {
           objUsers.setRequestType("UserValidate");
            DataAccessInterface objDAInterface = new UserDataAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUsers = objADBridge.RetriveUser(objUsers);
            if(objListUsers.size() >= 1)
            {
                ActiveSession objActiveSession = new ActiveSession();
                HashClass objHashClass = new HashClass(objListUsers.get(0).getUserID().toString());
                String strMD5Result = objHashClass.getHashMD5();
                objActiveSession.setUserID(objListUsers.get(0).getUserID());
                objActiveSession.setHashCode(strMD5Result);
                objActiveSession.setStatus(1);

                DataAccessInterface objDAInterfaceSession = new ActiveSessionAdapter();
                objADBridge.setDataAdapter(objDAInterfaceSession);
                objResponse = objADBridge.SaveActiveSession(objActiveSession);
                objResponse.add(objListUsers.get(0).getUserID().toString());
                objResponse.add("ValidUser");
                objResponse.add(strMD5Result);

            }
            else
            {
                objResponse.add("InvalidUser");
            }
            logger.info("User authenticated successfully, User Details="+objUsers);
        }
        catch(Exception ex)
        {
        	logger.info("Error in authenticating user");
        	objResponse.add("Error in authenticating user");
        }
        return objResponse;
    }
    
    /**
     * This function is using to Add User Disabilities
     * @param objOuterUserDisabilities
     * @return a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserDisabilities")
    public List<String> AddUserDisabilities(UserDisabilities objOuterUserDisabilities) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserDisabilitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserDisabilities(objOuterUserDisabilities);
            logger.info("User Disabilities saved successfully, User Details="+objOuterUserDisabilities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Disabilities");
        	response.add("Error in adding User Disabilities");
        }
        return response;
    }
    
    /**
     * This function is using to Update User Disabilities
     * @param objOuterUserDisabilities
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserDisabilities")
    public List<String> UpdateUserDisabilities(UserDisabilities objOuterUserDisabilities) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserDisabilitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserDisabilities(objOuterUserDisabilities);
            logger.info("User Disabilities updated successfully, User Details="+objOuterUserDisabilities);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated User Disabilities");
        	response.add("Error in updated User Disabilities");
        }
        return response;
    }
    
    /**
     * This function is using to Retrieves User Disabilities by UserID 
     * @param UserID
     * @return an instance of list of UserDisabilities
     */
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UserDisabilities/{UserID}")
    public List<UserDisabilities> GetUserDisabilities(@PathParam ("UserID") String UserID) {
        UserDisabilities objOuterUserDisabilities = new UserDisabilities();
        List<UserDisabilities> objListUserDisabilities = new  ArrayList<UserDisabilities>();
        try
        {
            objOuterUserDisabilities.setUserID(Long.parseLong(UserID));

            DataAccessInterface objDAInterface = new UserDisabilitiesAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserDisabilities = objADBridge.RetriveUserDisabilities(objOuterUserDisabilities);
            logger.info("User Disabilities loaded successfully, User Details=");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading User Disabilities");
        }
        return objListUserDisabilities;
    }
    
    /**
     * This function is using to Add User Schedule
     * @param objOuterUserSchedule
     * @return  a list of object string with "Error", "No Error" and new added ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("AddUserSchedule")
    public List<String> AddUserSchedule(UserSchedule objOuterUserSchedule) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserScheduleAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.SaveUserSchedule(objOuterUserSchedule);
            logger.info(" User Schedule saved successfully, User Details="+objOuterUserSchedule);
        }
        catch(Exception ex)
        {
        	logger.info("Error in adding User Schedule");
        	response.add("Error in adding User Schedule");
        }
       return response;
    }

    /**
     * This function is using to Update User Schedule
     * @param objOuterUserSchedule
     * @return a list of object string with "Error", "No Error" and updated ID
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("UpdateUserSchedule")
    public List<String> UpdateUserSchedule(UserSchedule objOuterUserSchedule) {
        
        List<String> response = new ArrayList<String>();
        try
        {
            DataAccessInterface objDAInterface = new UserScheduleAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            response = objADBridge.UpdateUserSchedule(objOuterUserSchedule);
            logger.info("User Schedule saved successfully, User Details="+objOuterUserSchedule);
        }
        catch(Exception ex)
        {
        	logger.info("Error in updated User Schedule");
        	response.add("Error in updated User Schedule");
        }
        return response;
    }
    
    /**
     * This function is using to  Retrieves User Schedule by user id, start time and end time
     * @param objOuterUserSchedule
     * @return an instance of list of UserSchedule
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("GetUserScheduleByIDandTime")
    public List<UserSchedule> GetUserScheduleByIDandTime(UserSchedule objOuterUserSchedule) {
        
        List<UserSchedule> objListUserSchedule = new ArrayList<UserSchedule>();
        try
        {
            DataAccessInterface objDAInterface = new UserScheduleAdapter();
            AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
            objListUserSchedule = objADBridge.RetriveUserSchedule(objOuterUserSchedule);
            logger.info("User Schedule loaded successfully");
        }
        catch(Exception ex)
        {
        	logger.info("Error in loading User Schedule");
        }
        return objListUserSchedule;
    }
    
}
