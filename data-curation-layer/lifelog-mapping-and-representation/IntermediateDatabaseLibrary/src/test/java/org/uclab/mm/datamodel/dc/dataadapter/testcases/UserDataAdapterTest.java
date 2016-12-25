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
package org.uclab.mm.datamodel.dc.dataadapter.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.uclab.mm.datamodel.AbstractDataBridge;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.DatabaseStorage;
import org.uclab.mm.datamodel.dc.Users;
import org.uclab.mm.datamodel.dc.dataadapter.UserDataAdapter;


/**
 * This is a class for testing the insertion, fetching, and update of User profile Data 
 * @author Taqdir Ali
 *
 */
public class UserDataAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of PhysiologicalFactorsAdapter 
	 */
	@Test
	public void testCRUDUserProfile() {
		/* Preparing input object for insertion*/
		Users objOuterUsers = new Users();
		Users objInnerUsers = new Users();
		List<Users> objListUsers = new  ArrayList<Users>();
		
		objOuterUsers.setFirstName("TestFirstName");
		objOuterUsers.setLastName("TestLastName");
		objOuterUsers.setMiddleName("TestmiddleName");
		objOuterUsers.setGenderId(1);
		objOuterUsers.setDateOfBirth("1983 05 02 00:00:00");
		objOuterUsers.setContactNumber("2136548");
		objOuterUsers.setEmailAddress("testemail@gmail.com");
		objOuterUsers.setMartialStatusId(1);
		objOuterUsers.setActivityLevelId(1);
		objOuterUsers.setOccupationId(1);
		objOuterUsers.setPassword("12345");
		objOuterUsers.setUserTypeID(1);
		
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new UserDataAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveUser(objOuterUsers);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUsers.setUserID(Long.parseLong(lstResponse.get(0)));
        	 objInnerUsers.setRequestType("UserData");
        	 DataAccessInterface objDAInterfaceRetrieval = new UserDataAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUsers = objADBridgeRetrieval.RetriveUser(objInnerUsers);
        	 objInnerUsers = objListUsers.get(0);
         }
         
         assertEquals(objOuterUsers.getFirstName(), objInnerUsers.getFirstName());
         
         /* Testing of Update function*/
         objInnerUsers.setFirstName("TestFirstNameUpdated");
         DataAccessInterface objDAInterfaceUpdate = new UserDataAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateUser(objInnerUsers);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUsers.setUserID(Long.parseLong(lstResponse.get(0)));
        	 DataAccessInterface objDAInterfaceRetrieval = new UserDataAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUsers = objADBridgeRetrieval.RetriveUser(objInnerUsers);
        	 objInnerUsers = objListUsers.get(0);
         }
         
         assertEquals(objInnerUsers.getFirstName(), "TestFirstNameUpdated");
	}

}
