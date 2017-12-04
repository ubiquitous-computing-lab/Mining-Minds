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

import weka.core.Instances;
import weka.core.Range;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * This class identifies missing values, replaces them with attribute mean values, and stores the filled data.
 */
public class MissingValueHandler {
	
	/** The confirmation message. */
	private ArrayList<String> confirmationMessage;
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(MissingValueHandler.class);
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "/DDKAT/resources/";

	/**
	 * Constructor to instantiate a new MissingValueHandler object.
	 *
	 * @throws Exception the exception
	 */
	public MissingValueHandler() throws Exception {
		
		replaceMissingValues();
	}
	
	/**
	 * Method to replace the identified missing values.
	 *
	 * @throws Exception the exception
	 */
	public void replaceMissingValues() throws Exception {
		
		this.confirmationMessage = new ArrayList<String>();
		
		Instances outputData;
		
		String inputFile = BASE_DIR + "OriginalDataSet.csv";
			
        // load CSV file
		CSVLoader fileLoader=new CSVLoader();
		fileLoader.setSource(new File(inputFile));
	    outputData = fileLoader.getDataSet();
         
        int numInstances = outputData.numInstances();
        int numAttributes = outputData.numAttributes();      
        
        final int NON_NUMERIC = -1;
        int[] m_AttributeIndices = null;
        
        Range m_Attributes = new Range("first-last");

        // attributes must be numeric
        m_Attributes.setUpper(outputData.numAttributes() - 1);
        m_AttributeIndices = m_Attributes.getSelection();
       
        for (int i = 0; i < m_AttributeIndices.length; i++) {
            // ignore class
            if (m_AttributeIndices[i] == outputData.classIndex()) {
                m_AttributeIndices[i] = NON_NUMERIC;
                continue;
                }
            // not numeric -> ignore it
            if (!outputData.attribute(m_AttributeIndices[i]).isNumeric())
                m_AttributeIndices[i] = NON_NUMERIC;
            }
        
        double sum;
        int missingCounter;
        double attributeMean;
        
        // identify the missing values               
        for (int attributeIndex = 0; attributeIndex < numAttributes; attributeIndex++) {
        	
        	// non-numeric attribute?
            if (m_AttributeIndices[attributeIndex] == NON_NUMERIC){
            	continue;
                }
                
            double tempArr[] = outputData.attributeToDoubleArray(attributeIndex);
            sum = 0;
            missingCounter = 0;
            for (int i = 0; i < tempArr.length; i++) {
            	sum = sum + tempArr[i];
            	if(tempArr[i]==0)
            		missingCounter++;                     
                }
                           
            attributeMean = sum / (numInstances-missingCounter);
                
            for (int instanceIndex = 0; instanceIndex < numInstances; instanceIndex++) {
            	
            	// replace the missing values with attribute mean values
            	if(outputData.instance(instanceIndex).value(attributeIndex)==0){
            		outputData.instance(instanceIndex).setValue(attributeIndex, attributeMean);
                	}
                }
            }
               
        outputData.deleteAttributeAt(outputData.numAttributes()-1);        
        outputData.deleteAttributeAt(outputData.numAttributes()-1);
        
        saveFilledData(inputFile, outputData); 
        
	}
	
	/**
	 * Method to store the filled data.
	 *
	 * @param filePath the file path
	 * @param outputData the output data
	 * @throws Exception the exception
	 */
	public void saveFilledData(String filePath, Instances outputData) throws Exception {
		
		// save ARFF file
		log.info("Input file : "+filePath);
        String outputFile = filePath.substring(0,filePath.lastIndexOf('/'));
        outputFile = outputFile + "/ProcessedDataSet.arff";
        log.info("Output file : "+outputFile);
  
        ArffSaver saver = new ArffSaver();
        saver.setInstances(outputData);
        saver.setFile(new File(outputFile));
        saver.writeBatch();
        
        String confirmationMessageStr = "Data is successfully cleaned and is stored at : "+outputFile;
        log.info(confirmationMessageStr);
        this.confirmationMessage.add(confirmationMessageStr);
        
	}

	/**
	 * Method to get the confirmation message of filled data.
	 *
	 * @return the confirmation message
	 */
	public ArrayList<String> getConfirmationMessage() {
		return confirmationMessage;
	}

	/**
	 * Method to set the confirmation message of filled data.
	 *
	 * @param confirmationMessage the new confirmation message
	 */
	public void setConfirmationMessage(ArrayList<String> confirmationMessage) {
		this.confirmationMessage = confirmationMessage;
	}

}

