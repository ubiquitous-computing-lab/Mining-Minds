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
import org.uclab.mm.datamodel.sc.UserRewards;
import org.uclab.mm.datamodel.sc.dataadapter.UserRewardsAdapter;

/**
 * This is a class for testing the insertion, fetching, and update of UserRewards
 * @author Taqdir Ali
 *
 */

public class UserRewardsAdapterTest {

	
	/**
	 * This is function for testing the insertion, fetching, and update of UserRewards 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		UserRewards objOuterUserRewards = new UserRewards();
		UserRewards objInnerUserRewards = new UserRewards();
		List<UserRewards> objListUserRewards = new  ArrayList<UserRewards>();
		
		objOuterUserRewards.setUserID(2L);
		objOuterUserRewards.setRewardPoints(4);
		objOuterUserRewards.setRewardDescription("Unit Testing Reward");
		objOuterUserRewards.setRewardDate("2016 12 12 01:30:01");
		objOuterUserRewards.setRewardTypeID(1);
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new UserRewardsAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveUserRewards(objOuterUserRewards);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserRewards.setUserID(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new UserRewardsAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserRewards = objADBridgeRetrieval.RetriveUserRewards(objInnerUserRewards);
        	 objInnerUserRewards = objListUserRewards.get(0);
         }
         
         assertEquals(objOuterUserRewards.getRewardDescription(), objInnerUserRewards.getRewardDescription());
         
         /* Testing of Update function*/
         objInnerUserRewards.setRewardDescription("Unit Testing Reward Updated");
         objInnerUserRewards.setRewardDate("2016 12 12 01:30:01");
         DataAccessInterface objDAInterfaceUpdate = new UserRewardsAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateUserRewards(objInnerUserRewards);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerUserRewards.setUserID(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new UserRewardsAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListUserRewards = objADBridgeRetrieval.RetriveUserRewards(objInnerUserRewards);
        	 objInnerUserRewards = objListUserRewards.get(0);
         }
         
         assertEquals(objInnerUserRewards.getRewardDescription(), "Unit Testing Reward Updated");
	}

}
