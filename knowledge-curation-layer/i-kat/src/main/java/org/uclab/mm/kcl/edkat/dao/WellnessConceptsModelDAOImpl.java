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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.uclab.mm.kcl.edkat.datamodel.WellnessConceptsModel;
import org.uclab.mm.kcl.edkat.datamodel.WellnessConceptsRelationships;

/**
* This is Data Access Object implementation class for the rule this DAO implements to 
* create, update, retrieve of rule with conditions, conclusions, and situations
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

@Repository
public class WellnessConceptsModelDAOImpl implements WellnessConceptsModelDAO {

	/**
	 * This creation of static object of logger for Wellness Concept 
	*/
	private static final Logger logger = LoggerFactory.getLogger(RuleDAOImpl.class);
	
	/**
	 * This creation of Session for factory  
	*/
	 private SessionFactory sessionFactory;
    
   public void setSessionFactory(SessionFactory sf){
       this.sessionFactory = sf;
   }
   
   /**
  	 * This function is the implementation for add new Wellness Concept
	 * @param objWellnessConceptsModel
	*/
	public void addWellnessConcept(WellnessConceptsModel objWellnessConceptsModel) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        session.persist(objWellnessConceptsModel);
	        tx.commit();
	        session.close();
	        logger.info("Wellness Concepts Model saved successfully, Wellness Concepts Model Details="+objWellnessConceptsModel);
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in wellness concept saving"+ ex.getMessage());
		}

	}

	/**
	 * This function is the implementation for update existing Wellness Concept
	 * @param objWellnessConceptsModel
	*/
	public void updateWellnessConcept(WellnessConceptsModel objWellnessConceptsModel) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        session.update(objWellnessConceptsModel);
	        tx.commit();
	        session.close();
	        logger.info("Wellness Concepts Model updated successfully, Wellness Concepts Model Details="+objWellnessConceptsModel);
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in wellness concept updating"+ ex.getMessage());
		}
		
	}

	/**
	 * This function is the implementation for retrieving all Wellness Concepts in form of list
	 * @return List of WellnessConceptsModel
	*/
	@SuppressWarnings("unchecked")
	public List<WellnessConceptsModel> listWellnessConceptsModel(String strQuery) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
	        List<WellnessConceptsModel> wellnessConceptsModelList = session.createQuery("from WellnessConceptsModel w Where w.wellnessConceptDescription like '%" + strQuery + "%'").list();
	        for(WellnessConceptsModel objWellnessConceptsModel : wellnessConceptsModelList){
	            logger.info("WellnessConceptsModel List::"+objWellnessConceptsModel);
	        }
	        session.close();
	        return wellnessConceptsModelList;
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in wellness concept retrieving list"+ ex.getMessage());
			return null;
		}
		
	}
	
	/**
	 * This function is the implementation for retrieving all Wellness Concept in form of list by searching key
	 * @param strKey
	 * @param strQuery
	 * @return List of WellnessConceptsRelationships
	*/
	@SuppressWarnings("unchecked")
	public List<WellnessConceptsRelationships> listWellnessConceptsByKey(String strKey, String strQuery) {
		try
		{
			Session session = this.sessionFactory.openSession();

			Query query = session.createSQLQuery("{CALL Usp_Get_WellnessConceptsByKey(:key, :query)}").addEntity(WellnessConceptsRelationships.class).setParameter("key", strKey).setParameter("query", strQuery);
					
			List<WellnessConceptsRelationships> wellnessConceptsRelationshipslList = query.list();
	        
	        for(WellnessConceptsRelationships objWellnessConceptsRelationships : wellnessConceptsRelationshipslList){
	            logger.info("WellnessConceptsRelationships List::"+objWellnessConceptsRelationships);
	        }
	        session.close();
	        return wellnessConceptsRelationshipslList;
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in wellness concept retrieving list"+ ex.getMessage());
			return null;
		}
		
	}
	
	/**
	 * This function is the implementation for retrieving a single Wellness Concept by id
	 * @param id
	 * @return Object of WellnessConceptsModel
	*/
	public WellnessConceptsModel getWellnessConceptById(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();      
			WellnessConceptsModel objWellnessConceptsModel = (WellnessConceptsModel) session.load(WellnessConceptsModel.class, new Integer(id));
	        logger.info("Wellness Concepts Model loaded successfully, WellnessConceptsModel details="+objWellnessConceptsModel);
	        session.close();
	        return objWellnessConceptsModel;
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in wellness concept retrieving"+ ex.getMessage());
			return null;
		}
		
	}

	/**
	 * This function is the implementation for deleting Wellness Concept 
	 * @param id
	*/
	public void removeWellnessConcept(int id) {
		try
		{
			 Session session = this.sessionFactory.openSession();
			 WellnessConceptsModel objWellnessConceptsModel = (WellnessConceptsModel) session.load(WellnessConceptsModel.class, new Integer(id));
		        if(null != objWellnessConceptsModel){
		            session.delete(objWellnessConceptsModel);
		        }
		        logger.info("WellnessConceptsModel deleted successfully, Rule details="+objWellnessConceptsModel);
		        session.close();
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in wellness concept deleting"+ ex.getMessage());
		}
		
	}

}
