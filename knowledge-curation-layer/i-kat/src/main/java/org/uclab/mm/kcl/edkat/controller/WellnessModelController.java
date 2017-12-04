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
package org.uclab.mm.kcl.edkat.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uclab.mm.kcl.edkat.datamodel.WellnessConceptsModel;
import org.uclab.mm.kcl.edkat.datamodel.WellnessConceptsRelationships;
import org.uclab.mm.kcl.edkat.service.WellnessConceptsModelService;

/**
 * This is controller class for the Wellness model,  this controller currently handles on fetching wellness concepts based
 * on searching key,  but will implement for create, update, retrieve of wellness model
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

@Controller
public class WellnessModelController {

	/**
	 * Object creation of WellnessConceptsModelService
	*/
	private WellnessConceptsModelService objWellnessConceptsModelService;
	 private static final Logger logger = LoggerFactory.getLogger(WellnessModelController.class);

	/**
	 * Setter WellnessConceptsModelService' object
	*/
	@Autowired(required=true)
    @Qualifier(value="objWellnessConceptsModelService")
	public void setObjWellnessConceptsModelService(WellnessConceptsModelService objWellnessConceptsModelService) {
		this.objWellnessConceptsModelService = objWellnessConceptsModelService;
	}
	
	/**
	 * This function get all wellness concepts
	 * @param query
	 * @return List of WellnessConceptsModel 
	*/
	@RequestMapping(value = "/getAllWellnessConcepts", method = RequestMethod.GET)
    public @ResponseBody List<WellnessConceptsModel> listWellnessConceptsModel(@RequestParam("term") String query) {
		List<WellnessConceptsModel> result = new ArrayList<WellnessConceptsModel>();
		try
		{
			result = this.objWellnessConceptsModelService.listWellnessConceptsModel(query);
	        logger.info("WellnessConceptsModel are loaded successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in loading WellnessConceptsModel");
		}
        return result;
    }
	
	/**
	 * This function get all wellness concepts search by some selected key.
	 * @param query
	 * @param strSelectedKey
	 * @return List of WellnessConceptsModel 
	*/
	@RequestMapping(value = "/getWellnessConceptsByKey/{selectedKey}/", method = RequestMethod.GET)
    public @ResponseBody List<WellnessConceptsRelationships> getWellnessConceptsByKey(@RequestParam("term") String query, @PathVariable("selectedKey") String strSelectedKey) {
		List<WellnessConceptsRelationships> result = new ArrayList<WellnessConceptsRelationships>();
		
		try
		{
			result = this.objWellnessConceptsModelService.listWellnessConceptsByKey(strSelectedKey, query);
	        logger.info("WellnessConceptsModel are loaded successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in loading WellnessConceptsModel");
		}
        return result;
    }
}
