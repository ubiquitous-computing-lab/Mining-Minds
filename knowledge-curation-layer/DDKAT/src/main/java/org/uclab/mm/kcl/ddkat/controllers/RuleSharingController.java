/**
* Copyright [2017] [Maqbool Ali, Maqbool Hussain]
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
* http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.uclab.mm.kcl.ddkat.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uclab.mm.kcl.ddkat.productionmodel.Rule;
import org.uclab.mm.kcl.ddkat.services.IClassificationModelTranslator;

//TODO: Auto-generated Javadoc
/**
* This class controls the translation tasks of the classification model.
*/
@Controller
public class RuleSharingController {
	
	/** The translation interface */	
	private IClassificationModelTranslator iClassificationModelTranslator;
    
	/** The Constant log. */
	private static final Logger logger = LoggerFactory.getLogger(RuleSharingController.class);
    
	
	 /**
		 * Method to configure/set the model translation interface
		 */	
    @Autowired(required=true)
    @Qualifier(value="iClassificationModelTranslator")
    public void setIClassificationModelTranslator(IClassificationModelTranslator iClassificationModelTranslator){
        this.iClassificationModelTranslator = iClassificationModelTranslator;
    }	    
   
    
    /**
	 * Method to communicate the request/response of Web interface with iClassificationModelTranslator interface.
	 *
	 * @return the rules list
	 * @throws Exception the exception
	 */	
	// generates the rules
    @RequestMapping(value = "/shareRules", method = RequestMethod.GET)
    public @ResponseBody List<Rule> extractRules() {
    	List<Rule> rulesList = iClassificationModelTranslator.translateModel();
    	return rulesList;
    }

	public static Logger getLogger() {
		return logger;
	}
}
