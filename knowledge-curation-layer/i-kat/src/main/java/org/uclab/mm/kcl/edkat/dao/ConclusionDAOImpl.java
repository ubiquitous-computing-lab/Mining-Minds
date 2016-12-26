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

/**
* This is Data Access Object implementation class for  the conclusion of a specific rule this DAO implementation handles to
* create, update, retrieve of conclusions of a particular rule
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */


@Repository
public class ConclusionDAOImpl implements ConclusionDAO {

	/**
	 * This creation of static object of logger for Conclusion 
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
	 * This function is the implementation for add new Conclusion
	 * @param objConclusion
	 * @return object of Conclusion
	*/
	public Conclusion addConclusion(Conclusion objConclusion) {
		
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        objConclusion = (Conclusion) session.merge(objConclusion);
	        tx.commit();
	        session.close();
	        logger.info("Conclusion saved successfully, Conclusion Details="+objConclusion);
	        return objConclusion;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in adding Conclusion, Error Details="+ex.getMessage());
			return objConclusion;
		}
		
		
	}

	/**
	 * This function is the implementation for update existing Conclusion
	 * @param objConclusion
	 * @return object of Conclusion
	*/
	public Conclusion updateConclusion(Conclusion objConclusion) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        session.update(objConclusion);
	        tx.commit();
	        session.close();
	        logger.info("Conclusion updated successfully, Conclusion Details="+objConclusion);
	        return objConclusion;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in updating Conclusion, Error Details="+ex.getMessage());
			return objConclusion;
		}
	}

	/**
	 * This function is the implementation for retrieving all Conclusions of a specific rule
	 *  @return List of Conclusion
	*/
	@SuppressWarnings("unchecked")
	public List<Conclusion> listConclusion() {
		try
		{
			Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
	        List<Conclusion> conclusionList = session.createQuery("from Conclusion").list();
	        for(Conclusion objConclusion : conclusionList){
	            logger.info("Conclusion List::"+objConclusion);
	        }
	        session.close();
	        return conclusionList;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in getting list of Conclusion, Error Details="+ex.getMessage());
			return null;
		}
		
	}

	/**
	 * This function is the implementation for retrieving a single Conclusion by id
	 * @param id
	 * @return Object of Conclusion
	*/
	public Conclusion getConclusionById(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();      
			Conclusion objConclusion = (Conclusion) session.load(Conclusion.class, new Integer(id));
	        logger.info("Conclusion loaded successfully, Conclusion details="+objConclusion);
	        session.close();
	        return objConclusion;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in getting list of Conclusion, Error Details="+ex.getMessage());
			return null;
		}
		
	}

	/**
	 * This function is the implementation for deleting existing selected Conclusion
	 * @param id
	*/
	public void removeConclusion(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();
			Conclusion objConclusion = (Conclusion) session.load(Conclusion.class, new Integer(id));
		        if(null != objConclusion){
		            session.delete(objConclusion);
		        }
		        logger.info("Conclusion deleted successfully, Conclusion details="+objConclusion);
		        session.close();
		}
		catch(Exception ex)
		{
			logger.info("Error occured in deleting of Conclusion, Error Details="+ex.getMessage());
		}
	}
}
