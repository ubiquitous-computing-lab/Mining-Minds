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

package org.uclab.mm.datamodel;

import java.util.List;

/**
 *
 * @author Taqdir
 */
public interface DataAccessInterface {
    /**
     * This is interface for accessing database with all CRUD operations
     * @param <T> T is any entity persistent object.
     * @param objEntity 
     * @return Returns list of string indicating status of the storage.
     */
	
	/**
	 * This is Save interface for insertion data
	 * @param objEntity
	 * @return List of String
	 */
    public <T> List<String> Save(T objEntity);
    
    /**
	 * This is Update interface for editing data
	 * @param objEntity
	 * @return List of String
	 */
    public <T> List<String> Update(T objEntity);
    
    /**
	 * This is RetrieveData interface for fetching data
	 * @param objEntity
	 * @return List of any entity
	 */
    public <T> List<T> RetriveData(T objEntity);
    
    /**
	 * This is Delete interface for deleting data
	 * @param objEntity
	 * @return List of any entity
	 */
    public <T> List<T> Delete(T objEntity);
    
    /**
	 * This is ConfigureAdapter interface for making connection with database
	 * @param objConf
	 */
    public <T> void ConfigureAdapter(T objConf);
    
    
}
