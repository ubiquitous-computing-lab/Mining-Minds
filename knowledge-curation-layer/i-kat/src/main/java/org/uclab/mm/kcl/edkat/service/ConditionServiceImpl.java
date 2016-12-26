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
package org.uclab.mm.kcl.edkat.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.transaction.annotation.Transactional;
import org.uclab.mm.kcl.edkat.dao.ConditionDAO;
import org.uclab.mm.kcl.edkat.dao.RuleDAOImpl;
import org.uclab.mm.kcl.edkat.datamodel.Condition;

/**
* This is service implementation for the Condition it provides implementation to 
* create, update, retrieve of condition of a specific rule
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

public class ConditionServiceImpl implements ConditionService {

	private ConditionDAO conditionDAO;
	/**
	 * This creation of static object of logger for Condition 
	*/
	private static final Logger logger = LoggerFactory.getLogger(RuleDAOImpl.class);
	
	
	/**
	 * getter of ConditionDAO
	*/
	public ConditionDAO getConditionDAO() {
		return conditionDAO;
	}

	/**
	 * setter of ConditionDAO
	*/
	public void setConditionDAO(ConditionDAO conditionDAO) {
		this.conditionDAO = conditionDAO;
	}

	/**
	 * This function is for add new condition
	 * @param objCondition
	 * @return object of Condition
	*/
	@Transactional
	public Condition addCondition(Condition objCondition) {
		try
		{
			Condition objInnerCondition = new Condition();
			objInnerCondition = this.conditionDAO.addCondition(objCondition);
	        logger.info("Condition saved successfully, Condition Details="+objInnerCondition);
	        return objInnerCondition;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in saving new condition, Error Details="+ex.getMessage());
			return objCondition;
		}
	}

	/**
	 *  This function is for update the existing condition
	 * @param objCondition
	 * @return object of Condition
	*/
	@Transactional
	public Condition updateCondition(Condition objCondition) {
		
		try
		{
			Condition objInnerCondition = new Condition();
			objInnerCondition = this.conditionDAO.updateCondition(objCondition);
	        logger.info("Condition updated successfully, Condition Details="+objCondition);
	        return objInnerCondition;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in updating existing condition, Error Details="+ex.getMessage());
			return objCondition;
		}
	}

	/**
	 * This function get all condition of the rules
	 * @return List of Condition
	*/
	public List<Condition> listCondition() {
		
		try
		{
	        List<Condition> conditionList = this.conditionDAO.listCondition();
	        return conditionList;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retrieving condition list, Error Details="+ex.getMessage());
			return null;
		}
	}

	/**
	 * This function get a specific condition by condition id (primary key)
	 * @param id
	 * @return Condition
	*/
	public Condition getConditionById(int id) {
		return this.conditionDAO.getConditionById(id);
	}
	
	/**
	 * This function delete a specific condition by condition id (primary key)
	 * @param id
	*/
	public void removeCondition(int id) {
		this.conditionDAO.removeCondition(id);
	}

	/**
	 * This function delete a all condition of the rules by key string
	 * @param objCondition
	 * @return Condition
	*/
	public Condition getConditionByFields(Condition objCondition) {
		return this.conditionDAO.getConditionByFields(objCondition);
	}

}
