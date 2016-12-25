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
import org.uclab.mm.datamodel.sc.Situation;
import org.uclab.mm.datamodel.sc.dataadapter.SituationAdapter;


/**
 * This is a class for testing the insertion, fetching, and update of Situation
 * @author Taqdir Ali
 *
 */
public class SituationAdapterTest {

	/**
	 * This is function for testing the insertion, fetching, and update of Situation 
	 */
	@Test
	public void test() {
		/* Preparing input object for insertion*/
		Situation objOuterSituation = new Situation();
		Situation objInnerSituation = new Situation();
		List<Situation> objListSituation = new  ArrayList<Situation>();
		
		objOuterSituation.setUserID(2L);
		objOuterSituation.setSituationCategoryID(2);
		objOuterSituation.setSituationDescription("5hoursSitting");
		objOuterSituation.setSituationDate("2016 12 12 01:30:01");
	
		/* Testing of insertion function*/
		 DataAccessInterface objDAInterface = new SituationAdapter();
         AbstractDataBridge objADBridge = new DatabaseStorage(objDAInterface);
         List<String> lstResponse = objADBridge.SaveSituation(objOuterSituation);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerSituation.setUserID(2L);
        	 objInnerSituation.setRequestType("ByUserOnly");
        	 DataAccessInterface objDAInterfaceRetrieval = new SituationAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListSituation = objADBridgeRetrieval.RetriveSituation(objInnerSituation);
        	 objInnerSituation = objListSituation.get(0);
         }
         
         assertEquals(objOuterSituation.getSituationDescription(), objInnerSituation.getSituationDescription());
         
         /* Testing of Update function*/
         objInnerSituation.setSituationDescription("5hoursSittingUpdated");
         DataAccessInterface objDAInterfaceUpdate = new SituationAdapter();
         AbstractDataBridge objADBridgeUpdate = new DatabaseStorage(objDAInterfaceUpdate);
         lstResponse = objADBridgeUpdate.UpdateSituation(objInnerSituation);
         if(lstResponse.size() == 2)
         {
        	 /* Testing of fetching function*/
        	 objInnerSituation.setUserID(2L);
        	 DataAccessInterface objDAInterfaceRetrieval = new SituationAdapter();
             AbstractDataBridge objADBridgeRetrieval = new DatabaseStorage(objDAInterfaceRetrieval);
        	 objListSituation = objADBridgeRetrieval.RetriveSituation(objInnerSituation);
        	 objInnerSituation = objListSituation.get(0);
         }
         
         assertEquals(objInnerSituation.getSituationDescription(), "5hoursSittingUpdated");
	}

}
