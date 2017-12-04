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
import java.util.ArrayList;

import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * This class discretizes the data using equal-width binning approach and then stores the discretized data.
 */
public class DataDiscretizer {
	
	/** The confirmation message. */
	private ArrayList<String> confirmationMessage;
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(DataDiscretizer.class);
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "/DDKAT/resources/";
		
	/**
	 * Constructor to instantiate a new DataDiscretizer object.
	 *
	 * @throws Exception the exception
	 */
	public DataDiscretizer() throws Exception {
		
		discretizeData();
	}
	
	/**
	 * Method to discretize the input data using equal-width binning approach.
	 *
	 * @throws Exception the exception
	 */
	public void discretizeData() throws Exception {
				
		this.confirmationMessage = new ArrayList<String>();
		
		Instances inputData, outputData;
		String inputFile = BASE_DIR + "OriginalDataSet.csv";
				
		// load CSV file
		CSVLoader fileLoader=new CSVLoader();
		fileLoader.setSource(new File(inputFile));
	    inputData = fileLoader.getDataSet();
       		
		Discretize discrete = new Discretize();
        discrete.setInputFormat(inputData);
        outputData = Filter.useFilter(inputData, discrete);
        
        saveDiscretizedData(inputFile, outputData);
        
 	}
	
	/**
	 * Method to store the discretized data.
	 *
	 * @param filePath the file path
	 * @param outputData the output data
	 * @throws Exception the exception
	 */
	public void saveDiscretizedData(String filePath, Instances outputData) throws Exception {
 
        // save ARFF file
        String outputFile = filePath.substring(0,filePath.lastIndexOf('/'));
        outputFile = outputFile + "/ProcessedDataSet.arff";
        log.info("Output file : "+outputFile);
        
        ArffSaver saver = new ArffSaver();
        saver.setInstances(outputData);
        saver.setFile(new File(outputFile));
        saver.writeBatch();
                           
        String confirmationMessageStr = "Data is successfully discretized and is stored at : "+outputFile;
        log.info(confirmationMessageStr);
        this.confirmationMessage.add(confirmationMessageStr);                        
	}
	
	/**
	 * Method to get the confirmation message of data discretization.
	 *
	 * @return the confirmation message
	 */
	public ArrayList<String> getConfirmationMessage() {
		return confirmationMessage;
	}

	/**
	 * Method to set the confirmation message of data discretization.
	 *
	 * @param confirmationMessage the new confirmation message
	 */
	public void setConfirmationMessage(ArrayList<String> confirmationMessage) {
		this.confirmationMessage = confirmationMessage;
	}

}
