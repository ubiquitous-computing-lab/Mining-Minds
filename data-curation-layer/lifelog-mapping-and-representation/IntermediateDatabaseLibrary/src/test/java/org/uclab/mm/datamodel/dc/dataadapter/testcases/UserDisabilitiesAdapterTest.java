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
import org.uclab.mm.datamodel.dc.UserDisabilities;
import org.uclab.mm.datamodel.dc.dataadapter.UserDisabilitiesAdapter;
/**
 * This is a class for testing the insertion, fetching, and update of UserDisabilities
 * @author Taqdir Ali
 *
 */
public class UserDisabilitiesAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of UserDisabilities 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		UserDisabilities objOuterUserDisabilities = new UserDisabilities();
		UserDisabilities objInnerUserDisabilities = new UserDisabilities();
		List<UserDisabilities> objListUserDisabilities = new  ArrayList<UserDisabilities>();
		
		objOuterUserDisabilities.setUserID(2L);
		objOuterUserDisabilities.setDisabilityID(1);
		objOuterUserDisabilities.setStatusID(1);
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new UserDisabilitiesAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveUserDisabilities(objOuterUserDisabilities);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserDisabilities.setUserID(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new UserDisabilitiesAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserDisabilities = objADBridgeRetrieval.RetriveUserDisabilities(objInnerUserDisabilities);
        	 objInnerUserDisabilities = objListUserDisabilities.get(0);
         }
         
         assertEquals(objOuterUserDisabilities.getDisabilityID(), objInnerUserDisabilities.getDisabilityID());
         
         /* Testing of Update function*/
         objInnerUserDisabilities.setDisabilityID(2);
         DataAccessInterface objDAInterfaceUpdate = new UserDisabilitiesAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateUserDisabilities(objInnerUserDisabilities);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserDisabilities.setUserID(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new UserDisabilitiesAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserDisabilities = objADBridgeRetrieval.RetriveUserDisabilities(objInnerUserDisabilities);
        	 objInnerUserDisabilities = objListUserDisabilities.get(0);
         }
         
         assertEquals(objInnerUserDisabilities.getDisabilityID(), 2);
	}

}
