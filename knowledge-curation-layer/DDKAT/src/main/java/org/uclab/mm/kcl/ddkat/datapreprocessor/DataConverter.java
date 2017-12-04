/**
 * Copyright [2017] [Maqbool Ali]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uclab.mm.kcl.ddkat.datapreprocessor;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.OrderedJSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * This class converts the data into CSV format and then stores it into local machine at specific location.
 */
public class DataConverter {
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(OutlierHandler.class);
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "\\DDKAT\\resources\\";
		
	/**
	 * Constructor to instantiate a new DataConverter object.
	 * @param dataUnorderedObject the json data object
	 * @param filePath the file path
	 * @throws Exception the exception
	 */
	public DataConverter(String dataUnorderedObject) throws Exception {
		convertData(dataUnorderedObject);
	}
	
	/**
	 * Method to convert the data.
	 *
	 * @param filePath the file path
	 * @throws Exception the exception
	 */
	public void convertData(String dataUnorderedObject) throws Exception {
		log.info("JSON object is successfully loaded : " + dataUnorderedObject);
        OrderedJSONObject jsonObject = new OrderedJSONObject(dataUnorderedObject);
        JSONArray jsontokenArray = jsonObject.getJSONArray("unprocessed_data");
        String csvString="";
        String str;
        for(int i=0;i<jsontokenArray.length();i++){
        	str= jsontokenArray.get(i).toString();
            str = str.substring(1, str.length()-1);
            csvString += str +"\n";
            }
        
        try{
        	

        String filePath = BASE_DIR + "OriginalDataSet.csv";
        File file=new File(filePath);
        // if file does not exists, then create it
        if (!file.exists()) {
        	file.createNewFile();
            }
        FileUtils.writeStringToFile(file,csvString);
        log.info("Data is successfully converted into CSV format and is stored at specific location into your machine");
        
        }catch(Exception e){System.out.println(e);}
	
	}
		
}
