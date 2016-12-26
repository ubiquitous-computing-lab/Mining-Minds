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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.uclab.mm.kcl.edkat.dao.ConclusionDAO;
import org.uclab.mm.kcl.edkat.dao.RuleDAOImpl;
import org.uclab.mm.kcl.edkat.datamodel.Conclusion;

/**
* This is service implementation for the Conclusion it provides implementation to 
* create, update, retrieve of Conclusion of a specific rule
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */
public class ConclusionServiceImpl implements ConclusionService {

	private ConclusionDAO conclusionDAO;
	/**
	 * This creation of static object of logger for Condition 
	*/
	private static final Logger logger = LoggerFactory.getLogger(RuleDAOImpl.class);
	
	
	/**
	 * getter of ConclusionDAO
	*/
	public ConclusionDAO getConclusionDAO() {
		return conclusionDAO;
	}

	/**
	 * setter of ConclusionDAO
	*/
	public void setConclusionDAO(ConclusionDAO conclusionDAO) {
		this.conclusionDAO = conclusionDAO;
	}
	
	/**
	 * This function is for add new Conclusion
	 * @param objConclusion
	 * @return object of Conclusion
	*/
	@Transactional
	public Conclusion addConclusion(Conclusion objConclusion) {
		try
		{
			Conclusion objInnerConclusion = new Conclusion();
			objInnerConclusion = this.conclusionDAO.addConclusion(objConclusion);
	        logger.info("Condition saved successfully, Condition Details="+objInnerConclusion);
	        return objInnerConclusion;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in saving new condition, Error Details="+ex.getMessage());
			return objConclusion;
		}
		
	}

	/**
	 *  This function is for update the existing conclusion
	 * @param objConclusion
	 * @return object of Conclusion
	*/
	@Transactional
	public Conclusion updateConclusion(Conclusion objConclusion) {
		try
		{
			Conclusion objInnerConclusion = new Conclusion();
			objInnerConclusion = this.conclusionDAO.updateConclusion(objConclusion);
	        logger.info("Condition updated successfully, Condition Details="+objInnerConclusion);
	        return objInnerConclusion;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in Updating new condition, Error Details="+ex.getMessage());
			return objConclusion;
		}
	}
	
	/**
	 * This function get all Conclusion of the rules
	 * @return List of Conclusion
	*/
	public List<Conclusion> listConclusion() {
		try
		{
	        List<Conclusion> conclusionList = this.conclusionDAO.listConclusion();
	        return conclusionList;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retrieving condition list, Error Details="+ex.getMessage());
			return null;
		}
	}

	/**
	 * This function get a specific Conclusion by Conclusion id (primary key)
	 * @param id
	 * @return Conclusion
	*/
	public Conclusion getConclusionById(int id) {
		return this.conclusionDAO.getConclusionById(id);
	}

	/**
	 * This function delete a specific Conclusion by Conclusion id (primary key)
	 * @param id
	*/
	public void removeConclusion(int id) {
		this.conclusionDAO.removeConclusion(id);
		
	}

}
