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
package org.uclab.mm.kcl.edkat.dao;

import java.util.List;

import org.uclab.mm.kcl.edkat.datamodel.Condition;


/**
* This is Data Access Object interface for the condition this DAO provides interfaces to 
* create, update, retrieve of conditions of a specific rule
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

public interface ConditionDAO {

	/**
	 * This interface function is for add new condition
	 * @param objCondition
	 * @return object of Condition
	*/
	public Condition addCondition(Condition objCondition);
	
	/**
	 *  This interface function is for update the existing condition
	 *  @param objCondition
	 *  @return object of Condition
	*/
	public Condition updateCondition(Condition objCondition);
	
	/**
	 * This function get all condition of the rules
	 * @return List of Condition
	*/
	public List<Condition> listCondition();
	
	/**
	 * This function get a specific condition by condition id (primary key)
	 * @param id
	 * @return Condition
	*/
	public Condition getConditionById(int id);
	
	/**
	 * This function delete a specific condition by condition id (primary key)
	 * @param id
	*/
    public void removeCondition(int id);
    
    /**
	 * This function delete a all condition of the rules by key string
	 * @param objCondition
	 * @return Condition
	*/
    public Condition getConditionByFields(Condition objCondition);
}
