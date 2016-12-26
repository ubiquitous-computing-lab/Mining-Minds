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
package org.uclab.mm.kcl.edkat.service.testcases;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uclab.mm.kcl.edkat.dao.ConditionDAO;
import org.uclab.mm.kcl.edkat.datamodel.Condition;
import org.junit.runner.RunWith;
import org.junit.Test;

/**
 * This is a class for testing the insertion, fetching, and update of Condition of a rule 
 * @author Taqdir Ali
 *
 */

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ConditionServiceImplTest {

	@Autowired
	private ConditionDAO conditionDAO;
	
	/**
	 * This is function for testing the insertion, fetching, and update of Condition of a rule  
	 */
	
	@Test
	@Transactional
	@Rollback(true)
	public void test() {
		Condition objOuterCondition = new Condition();
		Condition objInnerCondition = new Condition();
		
		/* Setting the Condition Object*/
		objOuterCondition.setConditionKey("unit test 4");
		objOuterCondition.setConditionValue("unit Test Value 4");
		objOuterCondition.setConditionType("int");
		objOuterCondition.setConditionValueOperator("=");
		
		/* Testing of Insertion*/
		objInnerCondition = conditionDAO.addCondition(objOuterCondition);
		
		/* Testing of fetching the new added condition*/
		objInnerCondition = conditionDAO.getConditionById(objInnerCondition.getConditionID());
		assertEquals(objOuterCondition.getConditionKey(), objInnerCondition.getConditionKey());
		
		objInnerCondition.setConditionKey("unit test 4 updated");
		objInnerCondition.setConditionValue("unit Test Value 4 updated");
		
		/* Testing of updating*/
		Condition objInnerConditionUpdated = new Condition();
		objInnerConditionUpdated = conditionDAO.updateCondition(objInnerCondition);
		
		assertEquals(objInnerCondition.getConditionKey(), objInnerConditionUpdated.getConditionKey());
		
	}

}
