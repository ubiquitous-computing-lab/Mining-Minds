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
package org.uclab.mm.kcl.edkat.controller.testcases;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.View;
import static org.hamcrest.Matchers.hasEntry;
import org.uclab.mm.kcl.edkat.controller.RulesController;
import org.uclab.mm.kcl.edkat.datamodel.Rule;
import org.uclab.mm.kcl.edkat.service.RuleService;

public class RulesControllerTest {

	@InjectMocks
    RulesController controller;
	
	@Mock
	RuleService mockRuleService;
	
	@Mock
    View mockView;
    MockMvc mockMvc;

	
	@Before(value = "")
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
		//List<Rule> expectedRules = new ArrayList<Rule>();
       // Mockito.when(mockRuleService.listRules()).thenReturn(expectedRules);
        //Model modelMap;
        //String viewName = controller.listRules(modelMap);
        //assertEquals("RulesDashboard", viewName);
        //assertThat(modelMap, hasEntry("RulesDashboard", (Object) expectedRules));
	}

}
