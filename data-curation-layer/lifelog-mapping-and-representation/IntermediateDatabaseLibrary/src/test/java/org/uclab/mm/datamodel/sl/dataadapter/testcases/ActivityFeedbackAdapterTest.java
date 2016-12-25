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
package org.uclab.mm.datamodel.sl.dataadapter.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.uclab.mm.datamodel.AbstractDataBridge;
import org.uclab.mm.datamodel.DataAccessInterface;

import org.uclab.mm.datamodel.DatabaseStorage;
import org.uclab.mm.datamodel.sl.ActivityFeedback;
import org.uclab.mm.datamodel.sl.dataadapter.ActivityFeedbackAdapter;
/**
 * This is a class for testing the insertion, fetching, and update of ActivityFeedback
 * @author Taqdir Ali
 *
 */
public class ActivityFeedbackAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of ActivityFeedback 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		ActivityFeedback objOuterActivityFeedback = new ActivityFeedback();
		ActivityFeedback objInnerActivityFeedback = new ActivityFeedback();
		List<ActivityFeedback> objListActivityFeedback = new  ArrayList<ActivityFeedback>();
		
		objOuterActivityFeedback.setUserID(2L);
		objOuterActivityFeedback.setRecognizedActivityID(98866L);
		objOuterActivityFeedback.setRate(3);
		objOuterActivityFeedback.setReason("I like it, unit testing");
		objOuterActivityFeedback.setFeedbackDate("2016 12 12 02:30:01");
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new ActivityFeedbackAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveActivityFeedback(objOuterActivityFeedback);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerActivityFeedback.setUserID(2L);
        	 objInnerActivityFeedback.setRequestType("ByUserID");
        	 DataAccessInterface objDAInterfaceRetrieval = new ActivityFeedbackAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListActivityFeedback = objADBridgeRetrieval.RetriveActivityFeedback(objInnerActivityFeedback);
        	 objInnerActivityFeedback = objListActivityFeedback.get(0);
         }
         
         assertEquals(objOuterActivityFeedback.getReason(), objInnerActivityFeedback.getReason());
         
         /* Testing of Update function*/
         objInnerActivityFeedback.setReason("I like it, unit testing updated");
         objInnerActivityFeedback.setFeedbackDate("2016 12 12 02:30:01");
         DataAccessInterface objDAInterfaceUpdate = new ActivityFeedbackAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateActivityFeedback(objInnerActivityFeedback);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerActivityFeedback.setUserID(2L);
        	 objInnerActivityFeedback.setRequestType("ByUserID");
        	 DataAccessInterface objDAInterfaceRetrieval = new ActivityFeedbackAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListActivityFeedback = objADBridgeRetrieval.RetriveActivityFeedback(objInnerActivityFeedback);
        	 objInnerActivityFeedback = objListActivityFeedback.get(0);
         }
         
         assertEquals(objInnerActivityFeedback.getReason(), "I like it, unit testing updated");
	}

}
