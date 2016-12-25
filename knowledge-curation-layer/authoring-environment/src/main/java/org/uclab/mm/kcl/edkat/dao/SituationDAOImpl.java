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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.uclab.mm.kcl.edkat.datamodel.Condition;
import org.uclab.mm.kcl.edkat.datamodel.Rule;
import org.uclab.mm.kcl.edkat.datamodel.Situations;


/**
* This is interface for the situation this interface provides 
* create, update, retrieve of situations of some specific rule
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */
@Repository
public class SituationDAOImpl implements SituationDAO {

	/**
	 * This creation of static object of logger for Situation 
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
	 * This function is the implementation for add new situation
	 * @param objSituation
	 * @return object of Situations
	*/
	public Situations addSituation(Situations objSituation) {
		try
		{
			Situations objNewSituation = new Situations();
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        objNewSituation = (Situations) session.merge(objSituation);
	        tx.commit();
	        session.close();
	        logger.info("Situation saved successfully, Situation Details="+objSituation);
	        return objNewSituation;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in saving new situation, Error Details="+ex.getMessage());
			return objSituation;
		}
		
	}

	/**
	 * This function is the implementation for update existing situation
	 * @param objSituation
	 * @return object of Situations
	*/
	public Situations updateSituation(Situations objSituation) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        session.update(objSituation); // Replaced with updated rule
	        tx.commit();
	        session.close();
	        logger.info("Situation updated successfully, Situation Details="+objSituation);
	        return objSituation;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in updating existing situation, Error Details="+ex.getMessage());
			return objSituation;
		}
		
	}

	/**
	 * This function is the implementation for retrieving all situations in form of list
	 * @return List of Situations
	*/
	@SuppressWarnings("unchecked")
	public List<Situations> listSituations() {
		try
		{
			Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
	        List<Situations> situationsList = session.createQuery("from Situations").list();
	        for(Situations objSituations : situationsList){
	            logger.info("Situations List::"+objSituations);
	        }
	        session.close();
	        return situationsList;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retrieving list of situation, Error Details="+ex.getMessage());
			return null;
		}
		
	}

	/**
	 * This function is the implementation for retrieving a single situation by id
	 * @param id
	 * @return Object of Situations
	*/
	public Situations getSituationById(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();      
			Situations objSituations = (Situations) session.load(Situations.class, new Integer(id));
	        logger.info("Situations loaded successfully, Situations details="+objSituations);
	        session.close();
	        return objSituations;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retrieving single situation, Error Details="+ex.getMessage());
			return null;
		}
	}

	/**
	 * This function is the implementation for deleting existing selected situation
	 * @param id
	*/
	public void removeSituation(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();
			Situations objSituations = (Situations) session.load(Situations.class, new Integer(id));
		        if(null != objSituations){
		            session.delete(objSituations);
		        }
		        logger.info("Situations deleted successfully, Situations details="+objSituations);
		        session.close();
		}
		catch(Exception ex)
		{
			logger.info("Error occured in deleting single situation, Error Details="+ex.getMessage());
		}
	
	}

	/**
	 * This function is the implementation for retrieving  situation to its check existence 
	 * @param objSituation
	 * @return object of Situations
	*/
	public Situations getExistingSituation(Situations objSituation) {
		try
		{
			Session session = this.sessionFactory.openSession();  
			Situations objSendingSituation = objSituation;
			List<Situations> objExistingSituations = session.createQuery("from Situations s left join fetch s.situationConditionList sc where sc.conditionKey='" + objSituation.getSituationConditionList().get(0).getConditionKey() + "' and sc.conditionValue='" + objSituation.getSituationConditionList().get(0).getConditionValue() + "' and sc.conditionValueOperator='" + objSituation.getSituationConditionList().get(0).getConditionValueOperator() + "'").list();
			if(objExistingSituations.size() > 0)
			{
				
				for(Situations objDBSituation : objExistingSituations)
				{
					Session session2 = this.sessionFactory.openSession();
					Situations objFullDBSituation = (Situations) session2.createQuery("from Situations where SituationID = " + objDBSituation.getSituationID()).uniqueResult();
					objDBSituation = objFullDBSituation;
					if(objSituation.getSituationConditionList().size() == objDBSituation.getSituationConditionList().size())
					{
						int countEqual = 0;
						for(Condition objCommingCondition : objSituation.getSituationConditionList())
						{
							for(Condition objDBCondition : objDBSituation.getSituationConditionList())
							{
								if(objCommingCondition.getConditionKey().equals(objDBCondition.getConditionKey()) && objCommingCondition.getConditionValue().equals(objDBCondition.getConditionValue()) && objCommingCondition.getConditionValueOperator().equals(objDBCondition.getConditionValueOperator()))
								{
									countEqual = countEqual + 1;
								}
							}
						}
						
				        if(objSituation.getSituationConditionList().size() == countEqual)
				        {
				        	objSendingSituation = objDBSituation;
				        }
					}
					session2.close();
				}
			}
			session.close();

	        return objSendingSituation;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in deleting single situation, Error Details="+ex.getMessage());
			return objSituation;
		}
		
		
	}

}
