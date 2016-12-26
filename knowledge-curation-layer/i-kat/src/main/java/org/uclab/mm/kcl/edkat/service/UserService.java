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

import org.uclab.mm.kcl.edkat.datamodel.User;

/**
* This is service interface for the user it provides service interface to 
* create, update, retrieve of user
 * @author  Taqdir Ali
 * @version 1.0
 * @since   2015-08-16
 * */
public interface UserService {

	/**
	 * This interface function is for add new user
	 * @param objUser
	 * @return Object of User
	*/
	public User addUser(User objUser);
	
	/**
	 *  This interface function is for update the existing user
	 * @param objUser
	 * @return Object of User
	*/
	public User updateUser(User objUser);
	
	/**
	 * This function get all user in form of list
	 * @return List of User
	*/
	public List<User> listUser();
	
	/**
	 * This function get a specific user by user id (primary key)
	 * @param id
	 * @return Object of User
	*/
	public User getUserById(int id);
	
	/**
	 * This function delete a specific user by user id (primary key)
	 * @param id
	*/
    public void removeUser(int id);
    
    /**
	 * This function validate a specific user by user id and password
	 * @param objUser
	 * @return Object of User
	*/
    public User validateUser(User objUser);
    
}
