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
package org.uclab.mm.datamodel.sc.dataadapter.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.uclab.mm.datamodel.AbstractDataBridge;
import org.uclab.mm.datamodel.DataAccessInterface;
import org.uclab.mm.datamodel.DatabaseStorage;
import org.uclab.mm.datamodel.sc.UserPreferredActivities;
import org.uclab.mm.datamodel.sc.dataadapter.UserPreferredActivitiesAdapter;

/**
 * This is a class for testing the insertion, fetching, and update of UserPreferredActivities
 * @author Taqdir Ali
 *
 */

public class UserPreferredActivitiesAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of UserPreferredActivities 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		UserPreferredActivities objOuterUserPreferredActivities = new UserPreferredActivities();
		UserPreferredActivities objInnerUserPreferredActivities = new UserPreferredActivities();
		List<UserPreferredActivities> objListUserPreferredActivities = new  ArrayList<UserPreferredActivities>();
		
		objOuterUserPreferredActivities.setUserId(2L);
		objOuterUserPreferredActivities.setActivityId(1);
		objOuterUserPreferredActivities.setPreferenceLevelId(1);
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new UserPreferredActivitiesAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveUserPreferredActivities(objOuterUserPreferredActivities);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserPreferredActivities.setUserId(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new UserPreferredActivitiesAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserPreferredActivities = objADBridgeRetrieval.RetriveUserPreferredActivities(objInnerUserPreferredActivities);
        	 objInnerUserPreferredActivities = objListUserPreferredActivities.get(0);
         }
         
         assertEquals(objOuterUserPreferredActivities.getActivityId(), objInnerUserPreferredActivities.getActivityId());
         
         /* Testing of Update function*/
         objInnerUserPreferredActivities.setActivityId(3);
         DataAccessInterface objDAInterfaceUpdate = new UserPreferredActivitiesAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateUserPreferredActivities(objInnerUserPreferredActivities);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserPreferredActivities.setUserId(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new UserPreferredActivitiesAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserPreferredActivities = objADBridgeRetrieval.RetriveUserPreferredActivities(objInnerUserPreferredActivities);
        	 objInnerUserPreferredActivities = objListUserPreferredActivities.get(0);
         }
         
         assertEquals(objInnerUserPreferredActivities.getActivityId(), 3);
	}

}
