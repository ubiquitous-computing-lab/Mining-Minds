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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uclab.mm.kcl.edkat.dao.ConditionDAO;
import org.uclab.mm.kcl.edkat.dao.RuleDAO;
import org.uclab.mm.kcl.edkat.dao.SituationDAO;
import org.uclab.mm.kcl.edkat.datamodel.Conclusion;
import org.uclab.mm.kcl.edkat.datamodel.Condition;
import org.uclab.mm.kcl.edkat.datamodel.Rule;
import org.uclab.mm.kcl.edkat.datamodel.Situations;

/**
 * This is a class for testing the insertion, fetching, and update of Rule with combination of Condition, Conclusion, and Situation 
 * @author Taqdir Ali
 *
 */
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleServiceImplTest {

	@Autowired
	private ConditionDAO conditionDAO;
	
	@Autowired
	private SituationDAO situationDAO;
	
	@Autowired
	private RuleDAO ruleDAO;
	/**
	 * This is function for testing the insertion, fetching, and update of Rule with combination of Condition, Conclusion, and Situation  
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void test() {
		
		Condition objOuterCondition = new Condition();
		Condition objOuterCondition2 = new Condition();
		Condition objOuterCondition3 = new Condition();
		List<Condition> objListOuterCondition = new ArrayList<Condition>();
		
		/* Setting the Condition Object*/
		objOuterCondition.setConditionKey("unit test 6");
		objOuterCondition.setConditionValue("unit Test Value 6");
		objOuterCondition.setConditionType("int");
		objOuterCondition.setConditionValueOperator("=");
		
		/* Setting the Condition Object*/
		objOuterCondition2.setConditionKey("unit test 7");
		objOuterCondition2.setConditionValue("unit Test Value 7");
		objOuterCondition2.setConditionType("int");
		objOuterCondition2.setConditionValueOperator("=");
		
		/* Setting the Condition Object*/
		objOuterCondition3.setConditionKey("unit test 8");
		objOuterCondition3.setConditionValue("unit Test Value 8");
		objOuterCondition3.setConditionType("int");
		objOuterCondition3.setConditionValueOperator("=");
		
		//objListOuterCondition.add(objOuterCondition);
		//objListOuterCondition.add(objOuterCondition2);
		objListOuterCondition.add(objOuterCondition3);
		
		/* Setting the Situation Object*/
		
		Situations objSituations = new Situations();
		Situations objAddedSituations = new Situations();
		Situations objInnerSituations = new Situations();
		
		objSituations.setSituationDescription("First unit test situation");
		objSituations.setSituationConditionList(objListOuterCondition);
		/* Testing of Situation Insertion*/
		objAddedSituations = situationDAO.addSituation(objSituations);
		
		/* Testing of Situation fetching*/
		objInnerSituations = situationDAO.getSituationById(objAddedSituations.getSituationID());
		
		assertEquals(objAddedSituations.getSituationDescription(), objInnerSituations.getSituationDescription());
		
		
		/* Setting the Rule Object*/
		Rule objRule = new Rule();
		Rule objInnerRule = new Rule();
		
		objRule.setRuleTitle("UnitTitle");
		objRule.setInstitution("UnitInstitution");
		objRule.setRuleDescription("UnitRuleDescription");
		objRule.setRuleConclusion("UnitConclusion");
		Date today = Calendar.getInstance().getTime();
		objRule.setRuleCreatedDate(today);
		objRule.setRuleCreatedBy(4);
		objRule.setSpecialistName("UnitSpecialist");
		objRule.setRuleTypeID(1);
		objRule.setSituationID(objAddedSituations.getSituationID());
		
		/* Setting Rule Condition List */
		objListOuterCondition.clear();
		objListOuterCondition.add(objOuterCondition);
		objListOuterCondition.add(objOuterCondition2);
		objListOuterCondition.add(objOuterCondition3);
				
		objRule.setConditionList(objListOuterCondition);
		
		/* Setting Rule Conclusion List */
		Conclusion objConclusion = new Conclusion();
		List<Conclusion> objListConclusion = new ArrayList<Conclusion>();
		
		objConclusion.setConclusionKey("unit testing conclution");
		objConclusion.setConclusionOperator("=");
		objConclusion.setConclusionValue("unit testing value");
		
		objListConclusion.add(objConclusion);
		
		objRule.setConclusionList(objListConclusion);
		
		/* Testing of Rule Insertion*/
		objInnerRule = ruleDAO.addRule(objRule);
		
		/* Testing of Rule fetching*/
		objInnerRule = ruleDAO.getRuleById(objInnerRule.getRuleID());
		
		assertEquals(objRule.getRuleTitle(), objInnerRule.getRuleTitle());
		
		
	}

}
