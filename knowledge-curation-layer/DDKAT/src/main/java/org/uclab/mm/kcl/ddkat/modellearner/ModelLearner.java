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
package org.uclab.mm.kcl.ddkat.modellearner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.trees.BFTree;
import weka.classifiers.trees.FT;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.J48graft;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomTree;
import weka.classifiers.trees.SimpleCart;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

//TODO: Auto-generated Javadoc
/**
* This class learns the data based on algorithm and computes the classification accuracies of original as well as processed data.
*/
public class ModelLearner {
	
	/** The accuracies list. */
	private ArrayList<String> modelAccuracies;
	
	/** The decision attribute. */
	private String decisionAttribute;
	
	/** The decision tree. */
	private String modelString;
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "/DDKAT/resources/";
	
	/**
	 * Constructor to instantiate a new ModelLearner object.
	 * @param algo the algorithm name
	 * @throws Exception the exception
	 */
 	public ModelLearner(String algo) throws Exception {
    	
    	this.modelAccuracies = new ArrayList<String>();
    	this.decisionAttribute = new String();
    	this.modelString = new String();
    	   	 		
		String[][] accuracyArr = new String[2][4];
			
		String originaldataFile = BASE_DIR + "OriginalDataSet.csv";
		DataSource originaldata_source = new DataSource(originaldataFile);
        Instances original_data = originaldata_source.getDataSet();
        
        String processeddataFile = BASE_DIR + "ProcessedDataSet.arff";
        DataSource processeddata_source = new DataSource(processeddataFile);
        Instances processed_data = processeddata_source.getDataSet();
		
    		       		
		accuracyArr[0] = modelAccuracy(algo, original_data,"Original Data");     
        accuracyArr[1] = modelAccuracy(algo, processed_data,"Processed Data");
        
        this.modelAccuracies.add(accuracyArr[0][3]);
        this.modelAccuracies.add(accuracyArr[1][3]);
		this.decisionAttribute = accuracyArr[0][1];
		this.modelString = accuracyArr[0][2];
		
		System.out.println("\n\n Generation of Classification Model : \n\n"+accuracyArr[0][2]);
	
 	}
 	
 	
 	/**
	 * Method to compute the classification accuracy.
	 *
	 * @param algo the algorithm name
	 * @param data the data instances
	 * @param datanature the dataset nature (i.e. original or processed data)
	 * @throws Exception the exception
	 */   
	protected String[] modelAccuracy(String algo, Instances data, String datanature) throws Exception {
	
		String modelResultSet[] = new String[4];
		String modelStr ="";
		Classifier classifier = null;
		
	    // setting class attribute if the data format does not provide this information  			
	    if (data.classIndex() == -1)
	         data.setClassIndex(data.numAttributes() - 1);
	    
	    String decisionAttribute = data.attribute(data.numAttributes() - 1).toString();
	    String res[] = decisionAttribute.split("\\s+");
	    decisionAttribute = res[1];
	   
	    
	    if(algo.equals("BFTree")){
	    	
	    	// Use BFTree classifiers
            BFTree BFTreeclassifier = new BFTree(); 
            BFTreeclassifier.buildClassifier(data);
            modelStr = BFTreeclassifier.toString();
            classifier = BFTreeclassifier;
            
	    } else if(algo.equals("FT")){
	    	  
		    // Use FT classifiers
            FT FTclassifier = new FT();
            FTclassifier.buildClassifier(data);
            modelStr = FTclassifier.toString();
            classifier = FTclassifier;
	    	
	    } else if(algo.equals("J48")){
	    	  
		    // Use J48 classifiers
            J48 J48classifier = new J48();
            J48classifier.buildClassifier(data);
            modelStr = J48classifier.toString();
            classifier = J48classifier;
            System.out.println("Model String: "+modelStr);

	    } else if(algo.equals("J48graft")){
	    	
	    	// Use J48graft classifiers
            J48graft J48graftclassifier = new J48graft();
            J48graftclassifier.buildClassifier(data);
            modelStr = J48graftclassifier.toString();
            classifier = J48graftclassifier;
	    	
	    } else if(algo.equals("RandomTree")){
	    	
	    	// Use RandomTree classifiers
            RandomTree RandomTreeclassifier = new RandomTree(); 
            RandomTreeclassifier.buildClassifier(data);
            modelStr = RandomTreeclassifier.toString();
            classifier = RandomTreeclassifier;
	    	
	    } else if(algo.equals("REPTree")){
	    	
	    	// Use REPTree classifiers
            REPTree REPTreeclassifier = new REPTree(); 
            REPTreeclassifier.buildClassifier(data);
            modelStr = REPTreeclassifier.toString();
            classifier = REPTreeclassifier;

	    } else if(algo.equals("SimpleCart")){
	    	
	    	// Use SimpleCart classifiers
            SimpleCart SimpleCartclassifier = new SimpleCart(); 
            SimpleCartclassifier.buildClassifier(data);
            modelStr = SimpleCartclassifier.toString();
            classifier = SimpleCartclassifier;
	    	
	    }
	    
	    modelResultSet[0] = algo;
	    modelResultSet[1] = decisionAttribute;
	    modelResultSet[2] = modelStr;
	    
	    // Collect every group of predictions for J48 model in a FastVector
	    FastVector predictions = new FastVector();
    
	    Evaluation evaluation=new Evaluation(data);
	    int folds = 10;  // cross fold validation = 10
	    evaluation.crossValidateModel(classifier,data,folds,new Random(1));  
	    // System.out.println("Evaluatuion"+evaluation.toSummaryString());
	    System.out.println("\n\n"+datanature + " Evaluatuion "+evaluation.toMatrixString());
	
	    // ArrayList<Prediction> predictions = evaluation.predictions();
	    predictions.appendElements(evaluation.predictions());

	    System.out.println("\n\n 11111");
	    // Calculate overall accuracy of current classifier on all splits
        double correct = 0;
	
	    for (int i = 0; i < predictions.size(); i++) {
	  	NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
	        if (np.predicted() == np.actual()) {
	                correct++;
	                        }
	                }
	
	    System.out.println("\n\n 22222");
	    double accuracy = 100 * correct / predictions.size();
	    String accString = String.format("%.2f%%", accuracy);
	    modelResultSet[3] = accString;
	    System.out.println(datanature + " Accuracy "+ accString);
	    
	    String modelFileName = algo+"-DDKA.model";
	    
	    System.out.println("\n\n 33333");
 
	    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\DDKAResources\\"+modelFileName)); 
		oos.writeObject(classifier);
		oos.flush();
		oos.close();

		return modelResultSet;

 		}


public ArrayList<String> getModelAccuracies() {
	return modelAccuracies;
}

public void setModelAccuracies(ArrayList<String> modelAccuracies) {
	this.modelAccuracies = modelAccuracies;
}

public String getDecisionAttribute() {
	return decisionAttribute;
}

public void setDecisionAttribute(String decisionAttribute) {
	this.decisionAttribute = decisionAttribute;
}

public String getModelString() {
	return modelString;
}

public void setModelString(String modelString) {
	this.modelString = modelString;
}



}
