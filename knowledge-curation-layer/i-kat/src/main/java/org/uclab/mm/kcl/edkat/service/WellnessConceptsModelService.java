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

import org.uclab.mm.kcl.edkat.datamodel.WellnessConceptsModel;
import org.uclab.mm.kcl.edkat.datamodel.WellnessConceptsRelationships;

/**
* This is service interface  for the wellness model, it provides service interfaces to 
* create, update, retrieve of wellness concepts
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

public interface WellnessConceptsModelService {

	/**
	 * This interface function is for add new wellness concept
	 * @param objWellnessConceptsModel
	*/
    public void addWellnessConcept(WellnessConceptsModel objWellnessConceptsModel);
    
    /**
	 *  This interface function is for update the existing wellness concept
	 * @param objWellnessConceptsModel
	*/
    public void updateWellnessConcept(WellnessConceptsModel objWellnessConceptsModel);
    
    /**
	 * This function get all wellness concept 
	 * @return List of WellnessConceptsModel
	*/
    public List<WellnessConceptsModel> listWellnessConceptsModel(String strQuery);
    
    /**
	 * This function get a specific wellness concept by wellness concept id (primary key)
	 * @param id
	 * @return Object of WellnessConceptsModel
	*/
    public WellnessConceptsModel getWellnessConceptById(int id);
    
    /**
	 * This function delete a specific wellness concept by wellness concept id (primary key)
	 * @param id
	*/
    public void removeWellnessConcept(int id);
    
    /**
	 * This function retrieve a specific wellness concept by wellness concept key string
	 * @param strSelectedKey
	 * @param strQuery
	 * @return List of WellnessConceptsRelationships
	*/
    public List<WellnessConceptsRelationships> listWellnessConceptsByKey(String strSelectedKey, String strQuery);
}
