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
import org.uclab.mm.datamodel.sl.RecommendationFeedback;
import org.uclab.mm.datamodel.sl.dataadapter.RecommendationFeedbackAdapter;

/**
 * This is a class for testing the insertion, fetching, and update of RecommendationFeedback
 * @author Taqdir Ali
 *
 */
public class RecommendationFeedbackAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of RecommendationFeedback 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		RecommendationFeedback objOuterRecommendationFeedback = new RecommendationFeedback();
		RecommendationFeedback objInnerRecommendationFeedback = new RecommendationFeedback();
		List<RecommendationFeedback> objListRecommendationFeedback = new  ArrayList<RecommendationFeedback>();
		
		objOuterRecommendationFeedback.setUserID(2L);
		objOuterRecommendationFeedback.setRecommendationID(11179L);
		objOuterRecommendationFeedback.setRate(1);
		objOuterRecommendationFeedback.setReason("I like it");
		objOuterRecommendationFeedback.setFeedbackDate("2016 12 12 02:30:01");
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new RecommendationFeedbackAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveRecommendationFeedback(objOuterRecommendationFeedback);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerRecommendationFeedback.setUserID(2L);
        	 objInnerRecommendationFeedback.setRequestType("ByUserID");
        	 DataAccessInterface objDAInterfaceRetrieval = new RecommendationFeedbackAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListRecommendationFeedback = objADBridgeRetrieval.RetriveRecommendationFeedback(objInnerRecommendationFeedback);
        	 objInnerRecommendationFeedback = objListRecommendationFeedback.get(0);
         }
         
         assertEquals(objOuterRecommendationFeedback.getReason(), objInnerRecommendationFeedback.getReason());
         
         /* Testing of Update function*/
         objInnerRecommendationFeedback.setReason("I like it updated");
         objInnerRecommendationFeedback.setFeedbackDate("2016 12 12 02:30:01");
         DataAccessInterface objDAInterfaceUpdate = new RecommendationFeedbackAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateRecommendationFeedback(objInnerRecommendationFeedback);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerRecommendationFeedback.setUserID(2L);
        	 objInnerRecommendationFeedback.setRequestType("ByUserID");
        	 DataAccessInterface objDAInterfaceRetrieval = new RecommendationFeedbackAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListRecommendationFeedback = objADBridgeRetrieval.RetriveRecommendationFeedback(objInnerRecommendationFeedback);
        	 objInnerRecommendationFeedback = objListRecommendationFeedback.get(0);
         }
         
         assertEquals(objInnerRecommendationFeedback.getReason(), "I like it updated");
	}

}
