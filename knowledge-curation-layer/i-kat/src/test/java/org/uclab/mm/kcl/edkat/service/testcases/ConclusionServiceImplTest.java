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
import org.uclab.mm.kcl.edkat.dao.ConclusionDAO;
import org.uclab.mm.kcl.edkat.datamodel.Conclusion;

/**
 * This is a class for testing the insertion, fetching, and update of Conclusion of a rule 
 * @author Taqdir Ali
 *
 */
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ConclusionServiceImplTest {

	@Autowired
	private ConclusionDAO conclusionDAO;
	
	/**
	 * This is function for testing the insertion, fetching, and update of Conclusion of a rule  
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void test() {
		Conclusion objOuterConclusion = new Conclusion();
		Conclusion objInnerConclusion = new Conclusion();
		
		/* Setting the Conclusion Object*/
		objOuterConclusion.setConclusionKey("unit test 4");
		objOuterConclusion.setConclusionValue("unit Test Value 4");
		objOuterConclusion.setRuleID(30110);
		objOuterConclusion.setConclusionOperator("=");
		
		/* Testing of Insertion*/
		objInnerConclusion = conclusionDAO.addConclusion(objOuterConclusion);
		
		/* Testing of fetching the new added Conclusion*/
		objInnerConclusion = conclusionDAO.getConclusionById(objInnerConclusion.getConclusionID());
		assertEquals(objOuterConclusion.getConclusionKey(), objInnerConclusion.getConclusionKey());
		
		objInnerConclusion.setConclusionKey("unit test 4 updated");
		objInnerConclusion.setConclusionValue("unit Test Value 4 updated");
		
		/* Testing of updating*/
		Conclusion objInnerConclusionUpdated = new Conclusion();
		objInnerConclusionUpdated = conclusionDAO.updateConclusion(objInnerConclusion);
		
		assertEquals(objInnerConclusion.getConclusionKey(), objInnerConclusionUpdated.getConclusionKey());
	}

}
