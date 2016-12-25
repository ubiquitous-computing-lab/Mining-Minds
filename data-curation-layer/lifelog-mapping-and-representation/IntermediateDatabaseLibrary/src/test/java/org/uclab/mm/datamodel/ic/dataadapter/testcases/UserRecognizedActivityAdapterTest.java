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
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;
import org.uclab.mm.datamodel.ic.dataadapter.UserRecognizedActivityAdapter;


/**
 * This is a class for testing the insertion, fetching, and update of UserRecognizedActivity
 * @author Taqdir Ali
 *
 */
public class UserRecognizedActivityAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of UserRecognizedActivity 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		UserRecognizedActivity objOuterUserRecognizedActivity = new UserRecognizedActivity();
		UserRecognizedActivity objInnerUserRecognizedActivity = new UserRecognizedActivity();
		List<UserRecognizedActivity> objListUserRecognizedActivity = new  ArrayList<UserRecognizedActivity>();
		
		objOuterUserRecognizedActivity.setUserId(2L);
		objOuterUserRecognizedActivity.setActivityId(4);
		objOuterUserRecognizedActivity.setStartTime("2016 12 12 19:16:01");
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new UserRecognizedActivityAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveUserRecognizedActivity(objOuterUserRecognizedActivity);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserRecognizedActivity.setUserId(2L);
        	 objInnerUserRecognizedActivity.setRequestType("LatestByUser");
        	 DataAccessInterface objDAInterfaceRetrieval = new UserRecognizedActivityAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserRecognizedActivity = objADBridgeRetrieval.RetriveUserRecognizedActivity(objInnerUserRecognizedActivity);
        	 objInnerUserRecognizedActivity = objListUserRecognizedActivity.get(0);
         }
         
         assertEquals(objOuterUserRecognizedActivity.getActivityId(), objInnerUserRecognizedActivity.getActivityId());
	}

}
