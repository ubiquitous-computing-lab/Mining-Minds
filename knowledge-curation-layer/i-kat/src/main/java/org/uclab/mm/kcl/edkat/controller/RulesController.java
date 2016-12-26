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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.uclab.mm.kcl.edkat.dao.RuleDAOImpl;
import org.uclab.mm.kcl.edkat.datamodel.Condition;
import org.uclab.mm.kcl.edkat.datamodel.Rule;
import org.uclab.mm.kcl.edkat.datamodel.SituationRuleWrapper;
import org.uclab.mm.kcl.edkat.datamodel.Situations;
import org.uclab.mm.kcl.edkat.datamodel.User;
import org.uclab.mm.kcl.edkat.datamodel.dclcommunication.SituationConditions;
import org.uclab.mm.kcl.edkat.datamodel.dclcommunication.SituationEvent;
import org.uclab.mm.kcl.edkat.datamodel.dclcommunication.SituationEvents;
import org.uclab.mm.kcl.edkat.service.ConditionService;
import org.uclab.mm.kcl.edkat.service.RuleService;
import org.uclab.mm.kcl.edkat.service.SituationService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * This is controller class for the rule this controller handles 
 * create, update, retrieve of rules, conditions, conclusions, and situation
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
*/

@Controller
@SessionAttributes("user")
public class RulesController {

	/**
	 * Object creation of RuleService, SituationService, and ConditionService
	*/
	private RuleService objRuleService;
	private SituationService objSituationService;
	private ConditionService objConditionService;
	
	 private static final Logger logger = LoggerFactory.getLogger(RulesController.class);

	/**
	 * Getter of Object of ConditionService
	*/
	public ConditionService getObjConditionService() {
		return objConditionService;
	}

	
	/**
	 * Setter of Object of ConditionService
	*/
	@Autowired(required=true)
	@Qualifier(value="objConditionService")
	public void setObjConditionService(ConditionService objConditionService) {
		this.objConditionService = objConditionService;
	}

	/**
	 * Getter of Object of RuleService
	*/
	public RuleService getObjRuleService() {
		return objRuleService;
	}

	/**
	 * Getter of Object of SituationService
	*/
	public SituationService getObjSituationService() {
		return objSituationService;
	}

	/**
	 * Setter of Object of SituationService
	*/
	@Autowired(required=true)
	@Qualifier(value="objSituationService")
	public void setObjSituationService(SituationService objSituationService) {
		this.objSituationService = objSituationService;
	}
	
	/**
	 * Setter of Object of SituationService
	*/
	@Autowired(required=true)
    @Qualifier(value="objRuleService")
	public void setObjRuleService(RuleService objRuleService) {
		this.objRuleService = objRuleService;
	}
	
	/**
	 * This function renders and lists all rules on the dashboard
	 * @return view of dashboard to show rules
	*/
	@RequestMapping(value = "/rules", method = RequestMethod.GET)
    public String listRules(Model model) {
		try
		{
			Rule rule = new Rule();
			model.addAttribute("situationRuleWrapper", rule);
	        model.addAttribute("listRules", this.objRuleService.listRules());
	        logger.info("Rules are loaded successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in loading rules");
		}
		return "RulesDashboard";
    }
	
	
	/**
	 * This function get all rules in list
	 * @return List of rules
	*/
	@RequestMapping(value = "/allrules", method = RequestMethod.GET)
    public @ResponseBody List<Rule> listRules() {
		List<Rule> rule = new ArrayList<Rule>();
		try
		{
			rule =  this.objRuleService.listRules();
	        logger.info("Rules are loaded successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in loading rules");
		}
        return rule;
    }
	
  /**
	 * This method is physically add the rule with corresponding conditions, conclusions and situations into the knowledge base
	 * but this function internally also check the duplication for conditions, conclusions and situations.
	 * @param situationRuleWrapper 
	 * @return view for rule in edit mode.
	*/
    @RequestMapping(value= "/rules/add", method = RequestMethod.POST)
    public String addRule(@ModelAttribute("situationRuleWrapper") SituationRuleWrapper situationRuleWrapper,  Model model){
    	Rule objRule = new Rule();
        Situations objSituation = new Situations();
        User objUser = new User();
        ObjectMapper mapper = new ObjectMapper();
    	try
		{
            objSituation = situationRuleWrapper.getSituations();
            objRule = situationRuleWrapper.getRule();
            objUser = situationRuleWrapper.getUser();
            
            // Start Preparation of Situation and Existing conditions
            
            String strSituationDesc = "";
            boolean blnIsSituationAttached = false;
        	for(int i = 0; i < objRule.getConditionList().size(); i++){
        		Condition objCondition = objRule.getConditionList().get(i);
        		boolean blnIsItSituation = objCondition.getIsItSituation();
            	objCondition = objConditionService.getConditionByFields(objCondition);
            	objRule.getConditionList().get(i).setConditionID(objCondition.getConditionID());
            	objRule.getConditionList().get(i).setConditionKey(objCondition.getConditionKey());
            	objRule.getConditionList().get(i).setConditionValue(objCondition.getConditionValue());
            	objRule.getConditionList().get(i).setConditionValueOperator(objCondition.getConditionValueOperator());
            	
            	if(blnIsItSituation)
            	{
            		blnIsSituationAttached = true;
            		objSituation.getSituationConditionList().add(objCondition);
            		if(strSituationDesc.equals(""))
            		{
            			strSituationDesc = objCondition.getConditionKey() + " " + objCondition.getConditionValueOperator() + " " + objCondition.getConditionValue();
            		}
            		else
            		{
            			strSituationDesc = strSituationDesc + " and " +  objCondition.getConditionKey() + " " + objCondition.getConditionValueOperator() + " " + objCondition.getConditionValue();
            		}
            	}
            	
            }
          
            // End Preparation of Situation and Existing conditions
               	
        	if(blnIsSituationAttached)
        	{ 
        		objSituation = objSituationService.getExistingSituation(objSituation);
        		Situations objNewSituation = new Situations();
        		objSituation.setSituationDescription(strSituationDesc);
        		if(objSituation.getSituationID() == 0){
                    //new rule, add it
            		objNewSituation = this.objSituationService.addSituation(objSituation);
            		
            		/* Client for the DCL to add situations-- Start */
            		SituationEvent objSituationEvent = new SituationEvent();
            		SituationEvents objSituationEvents = new SituationEvents();
            		List<SituationConditions> objListSituationConditions = new ArrayList<SituationConditions>();
            		List<SituationEvent> objListSituationEvent = new ArrayList<SituationEvent>();
            		
            		
            		try
            		{
            			for(int k = 0; k < objNewSituation.getSituationConditionList().size(); k++)
            			{
            				SituationConditions  objSituationConditions  = new SituationConditions();
            				objSituationConditions.setConditionKey(objNewSituation.getSituationConditionList().get(k).getConditionKey());
            				objSituationConditions.setConditionValue(objNewSituation.getSituationConditionList().get(k).getConditionValue());
            				objSituationConditions.setConditionValueOperator(objNewSituation.getSituationConditionList().get(k).getConditionValueOperator());
            				objSituationConditions.setConditionType("String");
            				objListSituationConditions.add(objSituationConditions);
            			}
            			
            			objSituationEvent.setListSConditions(objListSituationConditions);
            			objSituationEvent.setSituationID( Integer.toString(objNewSituation.getSituationID()));
            			objListSituationEvent.add(objSituationEvent);
            			objSituationEvents.setListSEvents(objListSituationEvent);
            			
            	         
            		}
            		catch(Exception ex)
            		{
            			System.out.println("****** Exception **********" + ex.getMessage());
            		}
            		
                }else{
                    //existing rule, call update
                	objNewSituation = objSituation;
                }
        		objRule.setSituationID(objNewSituation.getSituationID()); 
        		for(int i = 0; i < objRule.getConditionList().size(); i++){
            		Condition objCondition = objRule.getConditionList().get(i);
                	objCondition = objConditionService.getConditionByFields(objCondition);
                	if(objCondition.getConditionID() != 0)
                	{ 
                		 objRule.getConditionList().set(i, objCondition);
                	}
        		}
        	}
        	else
        	{ objRule.setSituationID(0); }
        	
            if(objRule.getRuleID() == 0){
                //new rule, add it
            	objRule.setRuleCreatedBy(objUser.getUserID());
            	objRule = this.objRuleService.addRule(objRule);
            }else{
                //existing rule, call update
            	objRule.setRuleUpdatedBy(objUser.getUserID());
            	Date todayDate = new Date();
            	objRule.setRuleLastUpdatedDate(todayDate);
            	objRule = this.objRuleService.updateRule(objRule);
            }
            
	        logger.info("Rule is saved successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in saving rule");
		}
        
        model.addAttribute("isSaved", "yes");
        return "redirect:/edit/" + objRule.getRuleID();
    }
    
    
    /**
	 * This method is physically add the rule with corresponding conditions, conclusions and situations into the knowledge base
	 * but this function internally also check the duplication for conditions, conclusions and situations.
	 * @param situationRuleWrapper 
	 * @return successfully added rule id.
	*/
    @RequestMapping(value= "/rules/addRule", method = RequestMethod.POST)
    public @ResponseBody  String addRuleForService(@RequestBody SituationRuleWrapper situationRuleWrapper){
    	Rule objRule = new Rule();
        Situations objSituation = new Situations();
        User objUser = new User();
        
    	try
		{
            objSituation = situationRuleWrapper.getSituations();
            objRule = situationRuleWrapper.getRule();
            objUser = situationRuleWrapper.getUser();
            
            String strSituationDesc = "";
            boolean blnIsSituationAttached = false;
        	for(int i = 0; i < objRule.getConditionList().size(); i++){
        		Condition objCondition = objRule.getConditionList().get(i);
        		boolean blnIsItSituation = objCondition.getIsItSituation();
            	objCondition = objConditionService.getConditionByFields(objCondition);
            	objRule.getConditionList().get(i).setConditionID(objCondition.getConditionID());
            	objRule.getConditionList().get(i).setConditionKey(objCondition.getConditionKey());
            	objRule.getConditionList().get(i).setConditionValue(objCondition.getConditionValue());
            	objRule.getConditionList().get(i).setConditionValueOperator(objCondition.getConditionValueOperator());
            	
            	if(blnIsItSituation)
            	{
            		blnIsSituationAttached = true;
            		objSituation.getSituationConditionList().add(objCondition);
            		if(strSituationDesc.equals(""))
            		{
            			strSituationDesc = objCondition.getConditionKey() + " " + objCondition.getConditionValueOperator() + " " + objCondition.getConditionValue();
            		}
            		else
            		{
            			strSituationDesc = strSituationDesc + " and " +  objCondition.getConditionKey() + " " + objCondition.getConditionValueOperator() + " " + objCondition.getConditionValue();
            		}
            	}
            	
            }
          
            // End Preparation of Situation and Existing conditions
               	
        	if(blnIsSituationAttached)
        	{ 
        		objSituation = objSituationService.getExistingSituation(objSituation);
        		Situations objNewSituation = new Situations();
        		objSituation.setSituationDescription(strSituationDesc);
        		if(objSituation.getSituationID() == 0){
                    //new rule, add it
            		objNewSituation = this.objSituationService.addSituation(objSituation);
            		
            		/* Client for the DCL to add situations-- Start */
            		SituationEvent objSituationEvent = new SituationEvent();
            		SituationEvents objSituationEvents = new SituationEvents();
            		List<SituationConditions> objListSituationConditions = new ArrayList<SituationConditions>();
            		List<SituationEvent> objListSituationEvent = new ArrayList<SituationEvent>();
            		
            		
            		try
            		{
            			for(int k = 0; k < objNewSituation.getSituationConditionList().size(); k++)
            			{
            				SituationConditions  objSituationConditions  = new SituationConditions();
            				objSituationConditions.setConditionKey(objNewSituation.getSituationConditionList().get(k).getConditionKey());
            				objSituationConditions.setConditionValue(objNewSituation.getSituationConditionList().get(k).getConditionValue());
            				objSituationConditions.setConditionValueOperator(objNewSituation.getSituationConditionList().get(k).getConditionValueOperator());
            				objSituationConditions.setConditionType("String");
            				objListSituationConditions.add(objSituationConditions);
            			}
            			
            			objSituationEvent.setListSConditions(objListSituationConditions);
            			objSituationEvent.setSituationID( Integer.toString(objNewSituation.getSituationID()));
            			objListSituationEvent.add(objSituationEvent);
            			objSituationEvents.setListSEvents(objListSituationEvent);
            			
            	         
            		}
            		catch(Exception ex)
            		{
            			System.out.println("****** Exception **********" + ex.getMessage());
            		}
            		
                }else{
                    //existing rule, call update
                	objNewSituation = objSituation;
                }
        		objRule.setSituationID(objNewSituation.getSituationID()); 
        		for(int i = 0; i < objRule.getConditionList().size(); i++){
            		Condition objCondition = objRule.getConditionList().get(i);
                	objCondition = objConditionService.getConditionByFields(objCondition);
                	if(objCondition.getConditionID() != 0)
                	{ 
                		 objRule.getConditionList().set(i, objCondition);
                	}
        		}
        	}
        	else
        	{ objRule.setSituationID(0); }
        	
            if(objRule.getRuleID() == 0){
                //new rule, add it
            	objRule.setRuleCreatedBy(objUser.getUserID());
            	objRule = this.objRuleService.addRule(objRule);
            }else{
                //existing rule, call update
            	objRule.setRuleUpdatedBy(objUser.getUserID());
            	Date todayDate = new Date();
            	objRule.setRuleLastUpdatedDate(todayDate);
            	objRule = this.objRuleService.updateRule(objRule);
            }
            
	        logger.info("Rule saved successfully");
	        return "Rule saved succussfully with ID: " + objRule.getRuleID();
		}
		catch(Exception ex)
		{
			 logger.info("Error in saving rule");
			 return "Error in saving rule";
		}
    }
    
    /**
	 * This function delete the selected rule and render the updated list of rules
	 * @param id
	 * @return redirect to rules with updated list
	*/
    @RequestMapping("/remove/{id}")
    public String removeRule(@PathVariable("id") int id){
    	try
		{
    		 this.objRuleService.removeRule(id);
	        logger.info("Rule is deleted successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in deleting rule");
		}
        return "redirect:/rules";
    }
    
    /**
	 * This function edits the exiting selected rule with updated conditions, conclusions, and situations.
	 * @param id
	 * @param user
	 * @return String Rule Editor
	*/
    @RequestMapping("/edit/{id}")
    public String editRule(@PathVariable("id") int id, @ModelAttribute("user") User user, Model model){
    	SituationRuleWrapper situationRuleWrapper = new SituationRuleWrapper();
    	Rule objRule = new Rule();
    	Situations objSituations = new Situations();
    	try
		{
    		objRule = this.objRuleService.getRuleById(id);
        	situationRuleWrapper.setRule(objRule);
        	situationRuleWrapper.setUser(user);
        	if(situationRuleWrapper.getRule().getSituationID() != 0)
        	{
        		objSituations = this.objSituationService.getSituationById(objRule.getSituationID());
        		situationRuleWrapper.setSituations(objSituations);
        	}
        	for(int i = 0; i < objRule.getConditionList().size(); i++)
        	{
        		for(int j = 0; j < objSituations.getSituationConditionList().size(); j++)
        		{
        			if(objRule.getConditionList().get(i).getConditionID() == objSituations.getSituationConditionList().get(j).getConditionID())
        			{
        				objRule.getConditionList().get(i).setIsItSituation(true);
        			}
        		}
        	}
	        logger.info("Rule is updated successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in updating rule");
		}
        model.addAttribute("situationRuleWrapper", situationRuleWrapper);
        return "RuleEditor";
    }
    
    /**
	 * This function renders the Rule Editor screen in add mode, when clicks the "add new rule" button. 
	 * @param user
	 * @return string RuleEditor
	 * 
	*/
    @RequestMapping("/addNewRule")
    public String addNewRule(@ModelAttribute("user") User user, Model model){
    	SituationRuleWrapper situationRuleWrapper = new SituationRuleWrapper();
    	try
		{
        	Rule objRule = new Rule();
        	Date todayDate = new Date();
        	objRule.setRuleCreatedDate(todayDate);
        	situationRuleWrapper.setRule(objRule);
        	situationRuleWrapper.setUser(user);
	        logger.info("Rule editor is opened successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in opening Rule editor");
		}
    	model.addAttribute("situationRuleWrapper", situationRuleWrapper);
        return "RuleEditor";
    }
    
    /**
	 * This function calls at initialization of application and set some custom settings for Date, String and long
	*/
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	try
		{
    		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(true);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
            binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
            binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
            
	        logger.info("Initial binding successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in initial binding");
		}
        
    }
    
	
}
