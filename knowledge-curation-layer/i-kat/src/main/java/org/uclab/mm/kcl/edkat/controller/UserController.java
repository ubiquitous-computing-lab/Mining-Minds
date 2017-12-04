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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.uclab.mm.kcl.edkat.datamodel.Rule;
import org.uclab.mm.kcl.edkat.datamodel.SituationRuleWrapper;
import org.uclab.mm.kcl.edkat.datamodel.User;
import org.uclab.mm.kcl.edkat.service.UserService;

/**
 * This is controller class for the users,  this controller currently handles users' login but will implement
 * create, update, retrieve of users of the application
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

@Controller
@SessionAttributes("user")
public class UserController {

	/**
	 * Object creation of UserService
	*/
	private UserService objUserService;
	
	 private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Setter of UserService's object
	*/
	@Autowired(required=true)
    @Qualifier(value="objUserService")
	public void setObjUserService(UserService objUserService) {
		this.objUserService = objUserService;
	}
	
	/**
	 * This method render login screen
	 * @return Login string
	*/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String listRules(Model model) {
		try
		{
			User user = new User();
			model.addAttribute("user", user);
	        logger.info("login rendered successfully");
		}
		catch(Exception ex)
		{
			 logger.info("Error in rendering login");
			 
		}
		return "Login";
    }
	
	/**
	 * This method validate the user by user id and password.
	 * @param user
	 * @return string
	*/
	@RequestMapping(value= "/user/validate", method = RequestMethod.POST)
    public String validateUser(@ModelAttribute("user") User user, Model model) {
		try
		{
			user = objUserService.validateUser(user);
			if(user.getUserID() != 0)
			{
				model.addAttribute("user", user);
				logger.info("User validated successfully");
				return "redirect:/rules";
			}
			else
			{
				model.addAttribute("error", "Invalid ID or Password.");
				logger.info("User validated successfully");
				return "Login";
			}
	        
		}
		catch(Exception ex)
		{
			 logger.info("Error in validating user");
			 return "Login";
		}
	
	}

}
