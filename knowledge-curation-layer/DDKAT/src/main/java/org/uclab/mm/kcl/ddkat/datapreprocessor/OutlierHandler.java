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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.filters.Filter;
import weka.filters.unsupervised.attribute.InterquartileRange;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Range;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

// TODO: Auto-generated Javadoc
/**
 * This class detects the outlier values using Interquartile approach, replaces them with attribute mean values, and stores the consistent data.
 */
public class OutlierHandler extends InterquartileRange {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(OutlierHandler.class);
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "/DDKAT/resources/";
		
	/**
	 * Constructor to instantiate a new OutlierHandler object.
	 *
	 * @throws Exception the exception
	 */
	public OutlierHandler() throws Exception {
		
		replaceOutliers();
	}
	
	/**
	 * Method to replace the detected outlier values.
	 *
	 * @throws Exception the exception
	 */
	public void replaceOutliers() throws Exception {
		
		Instances inputData, outputData;
		
		String inputFile = BASE_DIR + "OriginalDataSet.csv";
			
        // load CSV file
		CSVLoader fileLoader=new CSVLoader();
		fileLoader.setSource(new File(inputFile));
	    inputData = fileLoader.getDataSet();   
	    this.setInputFormat(inputData);
	    outputData = Filter.useFilter(inputData, this);
   
        int numInstances = outputData.numInstances();
        int numAttributes = outputData.numAttributes();

        final int NON_NUMERIC = -1;
        double[] outlier_AttributeValues = null;
        double[] extreme_AttributeValues = null;
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

        for (int instanceIndex = 0; instanceIndex < numInstances; instanceIndex++) {
            // access instance 
            Instance tempInstance = outputData.instance(instanceIndex);                     

            for (int attributeIndex = 0; attributeIndex < numAttributes; attributeIndex++) {
                // non-numeric attribute?
                if (m_AttributeIndices[attributeIndex] == NON_NUMERIC){
                    continue;
                    }
                
                // detect the outlier values using Interquartile approach
                if (this.isOutlier(tempInstance, m_AttributeIndices[attributeIndex])){

                    double outlierValue = tempInstance.value(attributeIndex);
                    int outlierColumnIndex = attributeIndex;
                    double sum = 0.0;

                    outlier_AttributeValues = outputData.attributeToDoubleArray(outlierColumnIndex);

                    for (int i = 0; i < outlier_AttributeValues.length; i++){
                        sum = sum + outlier_AttributeValues[i]; 
                        }

                    sum = sum - outlierValue;
                    double replacedValue = sum/(outlier_AttributeValues.length-1);
                    replacedValue = Math.round(replacedValue * 100D) / 100D;
                    
                    // replace the outliers with attribute mean values
                    outputData.instance(instanceIndex).setValue(outlierColumnIndex, replacedValue);
                    }

                // extreme value?
                if (this.isExtremeValue(tempInstance, m_AttributeIndices[attributeIndex])){

                    double extremeValue = tempInstance.value(attributeIndex);
                    int extremeColumnIndex = attributeIndex;
                    double sum = 0.0;

                    extreme_AttributeValues = outputData.attributeToDoubleArray(extremeColumnIndex);

                    for (int i = 0; i < extreme_AttributeValues.length; i++){
                        sum = sum + extreme_AttributeValues[i]; 
                        }

                    sum = sum - extremeValue;
                    double replacedValue = sum/(extreme_AttributeValues.length-1);
                    replacedValue = Math.round(replacedValue * 100D) / 100D;
                    outputData.instance(instanceIndex).setValue(extremeColumnIndex, replacedValue);
                    }

                } 
            }
        outputData.deleteAttributeAt(outputData.numAttributes()-1); 
        outputData.deleteAttributeAt(outputData.numAttributes()-1);
        
        saveConsistentData(inputFile, outputData);
                           
 	}
	
	/**
	 * Method to store the consistent data.
	 *
	 * @param filePath the file path
	 * @param outputData the output data
	 * @throws Exception the exception
	 */
	public void saveConsistentData(String filePath, Instances outputData) throws Exception {
		
		// save ARFF file
        String outputFile = filePath.substring(0,filePath.lastIndexOf('/'));
        outputFile = outputFile + "/ProcessedDataSet.arff";
        log.info("Output file : "+outputFile);
        log.info("Outliers are successfully removed");
               
        ArffSaver saver = new ArffSaver();
        saver.setInstances(outputData);
        saver.setFile(new File(outputFile));
        saver.writeBatch();
        
	}
	
}

