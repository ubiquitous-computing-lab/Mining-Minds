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
import org.uclab.mm.kcl.edkat.datamodel.User;

/**
* This is Data Access Object implementation class for the user this DAO implements to 
* create, update, retrieve of user information
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */


@Repository
public class UserDAOImpl implements UserDAO {

	/**
	 * This creation of static object of logger for User 
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
  	 * This function is the implementation for add user
	 * @param objUser
	 * @return Object of User
	*/
	public User addUser(User objUser) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        session.merge(objUser);
	        tx.commit();
	        session.close();
	        logger.info("User saved successfully, User Details="+objUser);
	        return objUser;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in saving new user, Error Details="+ex.getMessage());
			return objUser;
		}
		
	}

	/**
	 * This function is the implementation for update existing user
	 * @param objUser
	 * @return Object of User
	*/
	public User updateUser(User objUser) {
		try
		{
			Session session = this.sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        session.update(objUser); // Replaced with updated rule
	        tx.commit();
	        session.close();
	        logger.info("User updated successfully, User Details="+objUser);
	        return objUser;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in updating user, Error Details="+ex.getMessage());
			return objUser;
		}
	}

	/**
	 * This function is the implementation for retrieving all users in form of list
	 * @return List of User
	*/
	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		try
		{
			Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
	        List<User> userList = session.createQuery("from User").list();
	        for(User objUser : userList){
	            logger.info("User List:"+objUser);
	        }
	        session.close();
	        return userList;
		}
		catch(Exception ex)
		{
			logger.info("Error occured in retieving users list, Error Details="+ex.getMessage());
			return null;
		}
		
	}

	/**
	 * This function is the implementation for retrieving a single user by id
	 * @param id
	 * @return Object of User
	*/
	public User getUserById(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();      
			User objUser = (User) session.createQuery("from User u where u.UserID=" + id).uniqueResult();
	        logger.info("User loaded successfully, User details="+objUser);
	        session.close();
	        return objUser;
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in user loading "+ ex.getMessage());
			return null;
		}
		
	}

	/**
	 * This function is the implementation for deleting existing selected user
	 * @param id
	*/
	public void removeUser(int id) {
		try
		{
			Session session = this.sessionFactory.openSession();
			User objUser = (User) session.load(User.class, new Integer(id));
	        if(null != objUser){
	            session.delete(objUser);
	        }
	        session.close();
	        logger.info("User deleted successfully, User details="+objUser);
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in user deleting "+ ex.getMessage());
		}
		
	}

	/**
	 * This function is the implementation for validating user
	 * @param objUser
	 * @return Object of User
	*/
	public User validateUser(User objUser) {
		try
		{
			Session session = this.sessionFactory.openSession();      
			User objDBUser = (User) session.createQuery("from User u where u.loginID ='" + objUser.getLoginID() + "' and u.password = '" + objUser.getPassword() + "'").uniqueResult();
	        logger.info("User loaded successfully, User details="+objUser);
	        session.close();
	        if(objDBUser == null)
	        { objDBUser = objUser; }
	        return objDBUser;
		}
		catch(Exception  ex )
		{
			logger.info("Error occurred in user deleting "+ ex.getMessage());
			return null;
		}
		
	}

}
