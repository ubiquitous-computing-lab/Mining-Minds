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
import org.uclab.mm.kcl.edkat.datamodel.Conclusion;
import org.uclab.mm.kcl.edkat.datamodel.Rule;

/**
* This is Data Access Object implementation class for the rule this DAO implements to 
* create, update, retrieve of rule with conditions, conclusions, and situations
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */

@Repository
public class RuleDAOImpl implements RuleDAO {

	/**
	 * This creation of static object of logger for Rule 
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
	 * This function is the implementation for add new rule
	 * @param objRule
	 * @return Object of Rule
	*/
	public Rule addRule(Rule objRule) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        objRule = (Rule) session.merge(objRule);
	        tx.commit();
	        session.close();
	        logger.info("Rule saved successfully, Rule Details="+objRule);
	        return objRule;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in saving new rule, Error Details="+ex.getMessage());
			return objRule;
		}
		
	}

	/**
	 * This function is the implementation for update existing rule
	 * @param objRule
	 * @return Object of Rule
	*/
	public Rule updateRule(Rule objRule) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        session.update(objRule); // Replaced with updated rule
	        tx.commit();
	        session.close();
	        logger.info("Rule updated successfully, Rule Details="+objRule);
	        return objRule;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in updating rule, Error Details="+ex.getMessage());
			return objRule;
		}
		
	}
	
	/**
	 * This function is the implementation for deleting conclusion before deleting rules
	 * @param objRule
	 * @param objConclusion
	*/
	public void removeConclusion(Rule objRule, Conclusion objConclusion)
	{
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        objRule.getConclusionList().remove(objConclusion);
	        session.delete(objConclusion);
	        tx.commit();
	        logger.info("Conclusion deleted successfully, Rule Details="+objRule);
	        session.close();
		}
		catch(Exception ex)
		{
			logger.info("Error occured in deleting conclusion, Error Details="+ex.getMessage());
		}
		
	}

	/**
	 * This function is the implementation for retrieving all rules in form of list
	 * @return List of Rules
	*/
	@SuppressWarnings("unchecked")
	public List<Rule> listRule() {
		try
		{
			Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
	        List<Rule> rulesList = session.createQuery("from Rule").list();
	        for(Rule objRule : rulesList){
	            logger.info("Rule List::"+objRule);
	        }
	        session.close();
	        return rulesList;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retieving rules list, Error Details="+ex.getMessage());
			return null;
		}
		
	}

	/**
	 * This function is the implementation for retrieving a single rule by id
	 * @param id
	 * @return Object of Rule
	*/
	public Rule getRuleById(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();      
	        Rule objRule = (Rule) session.createQuery("from Rule r left outer join fetch r.conclusionList where r.RuleID=" + id).uniqueResult();
	        logger.info("Rule loaded successfully, Rule details="+objRule);
	        session.close();
	        return objRule;
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in rule loading "+ ex.getMessage());
			return null;
		}
	}

	/**
	 * This function is the implementation for deleting existing selected rule
	 * @param id
	*/
	public void removeRule(int id) {
		try
		{
			 Session session = this.sessionFactory.openSession();
			 Rule objRule = (Rule) session.load(Rule.class, new Integer(id));
		        if(null != objRule){
		            session.delete(objRule);
		        }
		        session.close();
		        logger.info("Rule deleted successfully, Rule details="+objRule);
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in rule deleting "+ ex.getMessage());
		}
		
	}

}
