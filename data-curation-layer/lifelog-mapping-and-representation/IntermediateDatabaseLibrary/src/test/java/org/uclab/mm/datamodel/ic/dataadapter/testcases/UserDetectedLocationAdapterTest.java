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
package org.uclab.mm.datamodel.ic.dataadapter.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.uclab.mm.datamodel.AbstractDataBridge;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.DatabaseStorage;
import org.uclab.mm.datamodel.ic.UserDetectedLocation;
import org.uclab.mm.datamodel.ic.dataadapter.UserDetectedLocationAdapter;

/**
 * This is a class for testing the insertion, fetching, and update of UserDetectedLocation
 * @author Taqdir Ali
 *
 */
public class UserDetectedLocationAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of UserDetectedLocation 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		UserDetectedLocation objOuterUserDetectedLocation = new UserDetectedLocation();
		UserDetectedLocation objInnerUserDetectedLocation = new UserDetectedLocation();
		List<UserDetectedLocation> objListUserDetectedLocation = new  ArrayList<UserDetectedLocation>();
		
		objOuterUserDetectedLocation.setUserId(2L);
		objOuterUserDetectedLocation.setLocationLabel("kor k yam");
		objOuterUserDetectedLocation.setStartTime("2016 12 12 02:30:01");
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new UserDetectedLocationAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveUserDetectedLocation(objOuterUserDetectedLocation);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserDetectedLocation.setUserId(2L);
        	 objInnerUserDetectedLocation.setRequestType("LatestByUser");
        	 DataAccessInterface objDAInterfaceRetrieval = new UserDetectedLocationAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserDetectedLocation = objADBridgeRetrieval.RetriveUserDetectedLocation(objInnerUserDetectedLocation);
        	 objInnerUserDetectedLocation = objListUserDetectedLocation.get(0);
         }
         
         assertEquals(objOuterUserDetectedLocation.getLocationLabel(), objInnerUserDetectedLocation.getLocationLabel());
	}

}
