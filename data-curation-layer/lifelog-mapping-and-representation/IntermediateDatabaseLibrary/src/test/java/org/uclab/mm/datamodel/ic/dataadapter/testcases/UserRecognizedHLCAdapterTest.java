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
import org.uclab.mm.datamodel.ic.UserRecognizedHLC;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedHLCAdapter;

/**
 * This is a class for testing the insertion, fetching, and update of UserRecognizedHLC
 * @author Taqdir Ali
 *
 */
public class UserRecognizedHLCAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of UserRecognizedHLC 
	 */
	@Test
	public void test() {
		
		/* Preparing input object for insertion*/
		UserRecognizedHLC objOuterUserRecognizedHLC = new UserRecognizedHLC();
		UserRecognizedHLC objInnerUserRecognizedHLC = new UserRecognizedHLC();
		List<UserRecognizedHLC> objListUserRecognizedHLC = new  ArrayList<UserRecognizedHLC>();
		
		objOuterUserRecognizedHLC.setUserId(2L);
		objOuterUserRecognizedHLC.sethLCLabel("Dinner");
		objOuterUserRecognizedHLC.setStartTime("2016 12 12 02:45:05");
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new UserRecognizedHLCAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveUserRecognizedHLC(objOuterUserRecognizedHLC);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserRecognizedHLC.setUserId(2L);
        	 objInnerUserRecognizedHLC.setRequestType("LatestByUser");
        	 DataAccessInterface objDAInterfaceRetrieval = new UserRecognizedHLCAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserRecognizedHLC = objADBridgeRetrieval.RetriveUserRecognizedHLC(objInnerUserRecognizedHLC);
        	 objInnerUserRecognizedHLC = objListUserRecognizedHLC.get(0);
         }
         
         assertEquals(objOuterUserRecognizedHLC.gethLCLabel(), objInnerUserRecognizedHLC.gethLCLabel());
	}

}
