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

import org.uclab.mm.kcl.edkat.datamodel.Situations;

/**
* This is interface for the situation this interface provides 
* create, update, retrieve of situations of some specific rule
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */
public interface SituationDAO {

	/**
	 * This interface function is for add new situation
	 * @param objSituation
	 * @return object of Situations
	*/
	public Situations addSituation(Situations objSituation);
	
	/**
	 *  This interface function is for update the existing situation
	 * @param objSituation
	 * @return object of Situations
	*/
	public Situations updateSituation(Situations objSituation);
	
	/**
	 * This function get all situations of the rules
	 * @return List of Situations
	*/
	public List<Situations> listSituations();
	
	/**
	 * This function get a specific situation by situation id (primary key)
	 * @param id
	 * @return Object of Situations
	*/
	public Situations getSituationById(int id);
	
	/**
	 * This function delete a specific situation by situation id (primary key)
	 * @param id
	*/
    public void removeSituation(int id);
    
    /**
	 * This function check an existing specific situation by whole object of situation
	 * @param objSituation
	 * @return object of Situations
	*/
    public Situations getExistingSituation(Situations objSituation);
}
