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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.uclab.mm.kcl.edkat.datamodel.Condition;
import org.uclab.mm.kcl.edkat.datamodel.Situations;


/**
* This is Data Access Object implementation class for the condition this DAO provides implements to 
* create, update, retrieve of conditions of a specific rule
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

@Repository
public class ConditionDAOImpl implements ConditionDAO {

	/**
	 * This creation of static object of logger for Condition 
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
	 * This function is the implementation for add new Condition
	 * @param objCondition
	 * @return object of Condition
	*/
	public Condition addCondition(Condition objCondition) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        objCondition = (Condition) session.merge(objCondition);
	        tx.commit();
	        session.close();
	        logger.info("Condition saved successfully, Condition Details="+objCondition);
	        return objCondition;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in saving new condition, Error Details="+ex.getMessage());
			return objCondition;
		}
		
	}

	/**
	 * This function is the implementation for update existing condition
	 * @param objCondition
	 * @return object of Condition
	*/
	public Condition updateCondition(Condition objCondition) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        session.update(objCondition);
	        tx.commit();
	        session.close();
	        logger.info("Condition updated successfully, Condition Details="+objCondition);
	        return objCondition;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in updating existing condition, Error Details="+ex.getMessage());
			return objCondition;
		}
		
	}

	/**
	 * This function is the implementation for retrieving all condition of a specific rule
	 * @return List of Condition
	*/
	@SuppressWarnings("unchecked")
	public List<Condition> listCondition() {
		try
		{
			Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
	        List<Condition> conditionList = session.createQuery("from Condition").list();
	        for(Condition objCondition : conditionList){
	            logger.info("Condition List::"+objCondition);
	        }
	        session.close();
	        return conditionList;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retrieving condition list, Error Details="+ex.getMessage());
			return null;
		}
		
	}
	
	/**
	 * This function retrieve a condition of the rules by key string
	 * @param objCondition
	 * @return Condition
	*/
	@SuppressWarnings("unchecked")
	public Condition getConditionByFields(Condition objCondition) {
		try
		{
			Condition exCondition = new Condition();
			Session session = this.sessionFactory.openSession();
				
			exCondition = (Condition) session.createQuery("from Condition c where c.conditionKey='" + objCondition.getConditionKey() + "' and c.conditionValue='" + objCondition.getConditionValue() + "' and c.conditionValueOperator='" + objCondition.getConditionValueOperator() + "'").setMaxResults(1).uniqueResult();
			if(exCondition == null)
			{ 
				exCondition = objCondition;
				exCondition.setConditionID(0);
			}
			session.close();
			logger.info("Condition List::"+objCondition);
			return exCondition;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retrieving condition, Error Details="+ex.getMessage());
			return null;
		}
		
	}

	/**
	 * This function is the implementation for retrieving a single condition by id
	 * @param id
	 * @return Condition
	*/
	public Condition getConditionById(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();      
			Condition objCondition = (Condition) session.load(Condition.class, new Integer(id));
	        logger.info("Condition loaded successfully, Condition details="+objCondition);
	        session.close();
	        return objCondition;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retrieving condition, Error Details="+ex.getMessage());
			return null;
		}
	}

	/**
	 * This function is the implementation for deleting existing selected condition
	 * @param id
	*/
	public void removeCondition(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();
			Condition objCondition = (Condition) session.load(Condition.class, new Integer(id));
		        if(null != objCondition){
		            session.delete(objCondition);
		        }
		        logger.info("Condition deleted successfully, Condition details="+objCondition);
		        session.close();
		}
		catch(Exception ex)
		{
			logger.info("Error occured in deleting condition, Error Details="+ex.getMessage());
		}
		
	}

}
