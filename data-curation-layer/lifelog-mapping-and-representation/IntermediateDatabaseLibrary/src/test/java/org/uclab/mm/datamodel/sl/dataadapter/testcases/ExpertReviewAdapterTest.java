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
import org.uclab.mm.datamodel.sl.ExpertReview;
import org.uclab.mm.datamodel.sl.dataadapter.ExpertReviewAdapter;

/**
 * This is a class for testing the insertion, fetching, and update of ExpertReview
 * @author Taqdir Ali
 *
 */

public class ExpertReviewAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of ExpertReview 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		ExpertReview objOuterExpertReview = new ExpertReview();
		ExpertReview objInnerExpertReview = new ExpertReview();
		List<ExpertReview> objListExpertReview = new  ArrayList<ExpertReview>();
		
		objOuterExpertReview.setUserID(2L);
		objOuterExpertReview.setUserExpertID(35L);
		objOuterExpertReview.setReviewDescription("You are doing well");
		objOuterExpertReview.setReviewDate("2016 12 12 02:30:01");
		objOuterExpertReview.setReviewStatusID(1);
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new ExpertReviewAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveExpertReview(objOuterExpertReview);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerExpertReview.setUserID(2L);
        	 objInnerExpertReview.setRequestType("ByUserID");
        	 DataAccessInterface objDAInterfaceRetrieval = new ExpertReviewAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListExpertReview = objADBridgeRetrieval.RetriveExpertReview(objInnerExpertReview);
        	 objInnerExpertReview = objListExpertReview.get(0);
         }
         
         assertEquals(objOuterExpertReview.getReviewDescription(), objInnerExpertReview.getReviewDescription());
         
         /* Testing of Update function*/
         objInnerExpertReview.setReviewStatusID(2);
         objInnerExpertReview.setReviewDate("2016 12 12 02:30:01");
         DataAccessInterface objDAInterfaceUpdate = new ExpertReviewAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateExpertReview(objInnerExpertReview);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerExpertReview.setUserID(2L);
        	 objInnerExpertReview.setRequestType("ByUserID");
        	 DataAccessInterface objDAInterfaceRetrieval = new ExpertReviewAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListExpertReview = objADBridgeRetrieval.RetriveExpertReview(objInnerExpertReview);
        	 objInnerExpertReview = objListExpertReview.get(0);
         }
         
         assertEquals(objInnerExpertReview.getReviewStatusID(), 2);
	}

}
