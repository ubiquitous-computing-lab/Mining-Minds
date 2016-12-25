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
import org.uclab.mm.datamodel.sc.Recommendation;
import org.uclab.mm.datamodel.sc.dataadapter.RecommendationAdapter;


/**
 * This is a class for testing the insertion, fetching, and update of Recommendation
 * @author Taqdir Ali
 *
 */
public class RecommendationAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of Recommendation 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		Recommendation objOuterRecommendation = new Recommendation();
		Recommendation objInnerRecommendation = new Recommendation();
		List<Recommendation> objListRecommendation = new  ArrayList<Recommendation>();
		
		objOuterRecommendation.setRecommendationIdentifier("A0001");
		objOuterRecommendation.setSituationID(22244L);
		objOuterRecommendation.setRecommendationDescription("testing for unit testing");
		objOuterRecommendation.setRecommendationTypeID(1);
		objOuterRecommendation.setConditionValue("condValue");
		objOuterRecommendation.setRecommendationLevelID(1);
		objOuterRecommendation.setRecommendationStatusID(1);
		objOuterRecommendation.setRecommendationDate("2016 12 12 01:30:01");
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new RecommendationAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveRecommendation(objOuterRecommendation);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerRecommendation.setUserID(2L);
        	 objInnerRecommendation.setRequestType("ByUserOnly");
        	 DataAccessInterface objDAInterfaceRetrieval = new RecommendationAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListRecommendation = objADBridgeRetrieval.RetriveRecommendation(objInnerRecommendation);
        	 objInnerRecommendation = objListRecommendation.get(0);
         }
         
         assertEquals(objOuterRecommendation.getRecommendationDescription(), objInnerRecommendation.getRecommendationDescription());
         
         /* Testing of Update function*/
         objInnerRecommendation.setRecommendationDescription("testing for unit testing updated");
         objInnerRecommendation.setRecommendationDate("2016 12 12 01:30:01");
         DataAccessInterface objDAInterfaceUpdate = new RecommendationAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateRecommendation(objInnerRecommendation);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerRecommendation.setUserID(2L);
        	 objInnerRecommendation.setRequestType("ByUserOnly");
        	 DataAccessInterface objDAInterfaceRetrieval = new RecommendationAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListRecommendation = objADBridgeRetrieval.RetriveRecommendation(objInnerRecommendation);
        	 objInnerRecommendation = objListRecommendation.get(0);
         }
         
         assertEquals(objInnerRecommendation.getRecommendationDescription(), "testing for unit testing updated");
	}

}
