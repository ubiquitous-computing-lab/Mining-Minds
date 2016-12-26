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
import org.springframework.transaction.annotation.Transactional;
import org.uclab.mm.kcl.edkat.dao.WellnessConceptsModelDAO;
import org.uclab.mm.kcl.edkat.datamodel.WellnessConceptsModel;
import org.uclab.mm.kcl.edkat.datamodel.WellnessConceptsRelationships;

/**
* This is service implementation  for the wellness model, it provides service implementation to 
* create, update, retrieve of wellness concepts
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */
public class WellnessConceptsModelServiceImpl implements WellnessConceptsModelService{

	private WellnessConceptsModelDAO wellnessConceptsModelDAO;
	
	
	public WellnessConceptsModelDAO getWellnessConceptsModelDAO() {
		return wellnessConceptsModelDAO;
	}

	public void setWellnessConceptsModelDAO(WellnessConceptsModelDAO wellnessConceptsModelDAO) {
		this.wellnessConceptsModelDAO = wellnessConceptsModelDAO;
	}

	/**
	 * This function is for add new wellness concept
	 * @param objWellnessConceptsModel
	*/
	@Transactional
	public void addWellnessConcept(WellnessConceptsModel objWellnessConceptsModel) {
		this.wellnessConceptsModelDAO.addWellnessConcept(objWellnessConceptsModel);
	}

	 /**
	 *  This function is for update the existing wellness concept
	 * @param objWellnessConceptsModel
	*/
	@Transactional
	public void updateWellnessConcept(WellnessConceptsModel objWellnessConceptsModel) {
		this.wellnessConceptsModelDAO.updateWellnessConcept(objWellnessConceptsModel);
	}

	 /**
	  * This function get all wellness concept 
	 * @return List of WellnessConceptsModel
	*/
	public List<WellnessConceptsModel> listWellnessConceptsModel(String strQuery) {
		return this.wellnessConceptsModelDAO.listWellnessConceptsModel(strQuery);
	}

	/**
	 * This function get a specific wellness concept by wellness concept id (primary key)
		 * @param id
	 * @return Object of WellnessConceptsModel
	*/
	public WellnessConceptsModel getWellnessConceptById(int id) {
		return this.wellnessConceptsModelDAO.getWellnessConceptById(id);
	}

	/**
	 * This function delete a specific wellness concept by wellness concept id (primary key)
	 * @param id
	*/
	public void removeWellnessConcept(int id) {
		this.wellnessConceptsModelDAO.removeWellnessConcept(id);
	}

	/**
	 * This function retrieve a specific wellness concept by wellness concept key string
	 * @param strSelectedKey
	 * @param strQuery
	 * @return List of WellnessConceptsRelationships
	*/
	public List<WellnessConceptsRelationships> listWellnessConceptsByKey(String strSelectedKey, String strQuery) {
		return this.wellnessConceptsModelDAO.listWellnessConceptsByKey(strSelectedKey, strQuery);
	}

}
