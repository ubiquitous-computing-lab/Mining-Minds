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
package org.uclab.mm.kcl.ddkat.dataselector;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.OrderedJSONObject;

import weka.attributeSelection.ChiSquaredAttributeEval;
import weka.attributeSelection.GainRatioAttributeEval;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.attributeSelection.SignificanceAttributeEval;
import weka.attributeSelection.SymmetricalUncertAttributeEval;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

//TODO: Auto-generated Javadoc
/**
* This class computes the features' scores.
*/
public class FeatureEvaluator {
	
	/** The features titles list */
	private ArrayList<String> featureTitles;
	
	/** The features scores list */
	private ArrayList<Double> featureScores;
	
	/** The features weights list */
	private ArrayList<Double> featureWeights;
	
	/** The features priorities list */
	private ArrayList<Double> featurePriorities;
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "/DDKAT/resources/";

	/**
	 * Constructor to instantiate a new FeatureEvaluator object.
	 *
	 * @param json the data string
	 * @param data the data set
	 * @throws Exception the exception
	 */
	
	public FeatureEvaluator(String json, Instances data) throws Exception {
	//	public FeatureEvaluator(String json, Instances data, String filePath) throws Exception {
		
		this.featureTitles = new ArrayList<String>();
		this.featureScores = new ArrayList<Double>();
		this.featureWeights = new ArrayList<Double>();
		this.featurePriorities = new ArrayList<Double>();
		
		OrderedJSONObject jsonObject = new OrderedJSONObject(json.toString());
        JSONArray jsontokenArray = jsonObject.getJSONArray("unprocessed_data");
        String csvString="";
        String str;
        for(int i=0;i<jsontokenArray.length();i++){
        	str= jsontokenArray.get(i).toString();
            str = str.substring(1, str.length()-1);
            csvString += str +"\n";
            }
        
        String filePath = BASE_DIR + "FeaturesEvaluationDataSet.csv";
        File file=new File(filePath);
        // if file does not exists, then create it
        if (!file.exists()) 	
        	file.createNewFile();
                        
        FileUtils.writeStringToFile(file,csvString);
        
        CSVLoader loader=new CSVLoader();
        loader.setSource(new File(filePath));
        data=loader.getDataSet();
        
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1); 
           
        int numUnlabeledAttributes = data.numAttributes()-1;
        double[] minmaxValues = new double[2];
        double min, max;
           
        String[] options = new String[1];
        options[0] = "-T -1.7976931348623157E308 -N -1"; // confidenceFactor = 0.25, minNumObject = 2
        Ranker atrank = new Ranker();
        atrank.setOptions(options);   

        weka.attributeSelection.AttributeSelection atsel = new weka.attributeSelection.AttributeSelection();        

        //  Information Gain Attribute Evaluator
        InfoGainAttributeEval infoGainAttrEval = new InfoGainAttributeEval();
        atsel.setEvaluator(infoGainAttrEval);
        atsel.setSearch(atrank);
        atsel.SelectAttributes(data);
        double[] infoGainRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	infoGainRanks[i] = Math.round(10000 * infoGainAttrEval.evaluateAttribute(i)) / 10000d;
         	}
        minmaxValues = computerMinMaxValues(infoGainRanks);
        min = minmaxValues[0];
        max = minmaxValues[1];
        double[] scaledInfoGainRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	scaledInfoGainRanks[i] = Math.round(10000 * ((infoGainRanks[i]-min)/(max-min))) / 10000d;
        	}

        //  Gain Ratio Attribute Evaluator
        GainRatioAttributeEval gainRatioAttrEval = new GainRatioAttributeEval();
        atsel.setEvaluator(gainRatioAttrEval);
        atsel.setSearch(atrank);
        atsel.SelectAttributes(data);
        double[] gainRatioRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	gainRatioRanks[i] = Math.round(10000 * gainRatioAttrEval.evaluateAttribute(i)) / 10000d;
        	}
        minmaxValues = computerMinMaxValues(gainRatioRanks);
        min = minmaxValues[0];
        max = minmaxValues[1];
        double[] scaledGainRatioRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	scaledGainRatioRanks[i] = Math.round(10000 * ((gainRatioRanks[i]-min)/(max-min))) / 10000d;
        	}

        //  Chi Squared Attribute Evaluator
        ChiSquaredAttributeEval chiSquaredAttrEval = new ChiSquaredAttributeEval();
        atsel.setEvaluator(chiSquaredAttrEval);
        atsel.setSearch(atrank);
        atsel.SelectAttributes(data);
        double[] chiSquaredRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	chiSquaredRanks[i] = Math.round(10000 * chiSquaredAttrEval.evaluateAttribute(i)) / 10000d;
        	}
        minmaxValues = computerMinMaxValues(chiSquaredRanks);
        min = minmaxValues[0];
        max = minmaxValues[1];
        double[] scaledChiSquaredRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	scaledChiSquaredRanks[i] = Math.round(10000 * ((chiSquaredRanks[i]-min)/(max-min))) / 10000d;
        	}

        //  Symmetrical Uncert Attribute Evaluator
        SymmetricalUncertAttributeEval symmetricalUncertAttrEval = new SymmetricalUncertAttributeEval();
        atsel.setEvaluator(symmetricalUncertAttrEval);
        atsel.setSearch(atrank);
        atsel.SelectAttributes(data);
        double[] symmetricalUncertRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	symmetricalUncertRanks[i] = Math.round(10000 * symmetricalUncertAttrEval.evaluateAttribute(i)) / 10000d;
        	}
        minmaxValues = computerMinMaxValues(symmetricalUncertRanks);
        min = minmaxValues[0];
        max = minmaxValues[1];
        double[] scaledSymmetricalUncertRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	scaledSymmetricalUncertRanks[i] = Math.round(10000 * ((symmetricalUncertRanks[i]-min)/(max-min))) / 10000d;
        	}

        //  Significance Attribute Evaluator
        SignificanceAttributeEval significanceAttrEval = new SignificanceAttributeEval();
        atsel.setEvaluator(significanceAttrEval);
        atsel.setSearch(atrank);
        atsel.SelectAttributes(data);
        double[] significanceRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	significanceRanks[i] = Math.round(10000 * significanceAttrEval.evaluateAttribute(i)) / 10000d;
        	}
        minmaxValues = computerMinMaxValues(significanceRanks);
        min = minmaxValues[0];
        max = minmaxValues[1];
        double[] scaledSignificanceRanks = new double[numUnlabeledAttributes];
        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	scaledSignificanceRanks[i] = Math.round(10000 * ((significanceRanks[i]-min)/(max-min))) / 10000d;
        	}

        double attributeSum;

        double[] combinedRanks = new double[numUnlabeledAttributes]; 
        double combinedranksSum = 0;

        for (int i = 0; i < numUnlabeledAttributes; i++) {
        	attributeSum = scaledInfoGainRanks[i] + scaledGainRatioRanks[i] + scaledChiSquaredRanks[i] + scaledSymmetricalUncertRanks[i] + scaledSignificanceRanks[i]; 
        	combinedRanks[i] = Math.round(10000 * attributeSum) / 10000d;
        	combinedranksSum = combinedranksSum + combinedRanks[i];
        	}

        double[][] tempArray = new double[numUnlabeledAttributes][2];
        String[] attributesTitles = new String[numUnlabeledAttributes];
        double[] attributesScores = new double[numUnlabeledAttributes];
        double[] attributesWeights = new double[numUnlabeledAttributes];
        double[] attributesPriorities = new double[numUnlabeledAttributes];

        for (int j = 0; j < numUnlabeledAttributes; j++) {         
        	tempArray[j][0] = j;
        	tempArray[j][1] = combinedRanks[j];
        	}

        double temp;
        for(int i=0; i < numUnlabeledAttributes; i++){
        	for(int j=1; j < (numUnlabeledAttributes-i); j++){          
        		if(combinedRanks[j-1] < combinedRanks[j]){
        			//swap the elements!
        			temp = combinedRanks[j-1];
        			combinedRanks[j-1] = combinedRanks[j];
        			combinedRanks[j] = temp;
        			}
        		}
        	}         

        for (int j = 0; j < numUnlabeledAttributes; j++) { 
        	for (int k = 0; k < numUnlabeledAttributes; k++) { 
        		if (combinedRanks[j] == tempArray[k][1]){
        			attributesTitles[j] = data.attribute((int)tempArray[k][0]).toString();
        			String res[] = attributesTitles[j].split("\\s+");
                    attributesTitles[j] = res[1];
        
        			this.featureTitles.add(attributesTitles[j]);
        			break;
        			}
        		}
        	attributesScores[j] = Math.round(10000 * (combinedRanks[j]/9)) / 100d;
        	attributesWeights[j] = Math.round(10000 * (combinedRanks[j]/combinedranksSum)) / 100d;
        	attributesPriorities[j] = Math.round(attributesScores[j] * attributesWeights[j]) / 100d;
        	this.featureScores.add(attributesScores[j]);
        	this.featureWeights.add(attributesWeights[j]);
    		this.featurePriorities.add(attributesPriorities[j]);
          	
        	System.out.println(attributesTitles[j]+" is "+attributesScores[j]+" % Important");
       		}
		
	}
	
	public ArrayList<String> getFeatureTitles() {
		return featureTitles;
	}

	public void setFeatureTitles(ArrayList<String> featureTitles) {
		this.featureTitles = featureTitles;
	}

	public ArrayList<Double> getFeatureScores() {
		return featureScores;
	}

	public void setFeatureScores(ArrayList<Double> featureScores) {
		this.featureScores = featureScores;
	}
	
	public ArrayList<Double> getFeatureWeights() {
		return featureWeights;
	}

	public void setFeatureWeights(ArrayList<Double> featureWeights) {
		this.featureWeights = featureWeights;
	}
	
	public ArrayList<Double> getFeaturePriorities() {
		return featurePriorities;
	}

	public void setFeaturePriorities(ArrayList<Double> featurePriorities) {
		this.featurePriorities = featurePriorities;
	}
	

	protected double[] computerMinMaxValues(double dataArr[]) throws Exception {
        //assign first element of an array to largest and smallest
        double smallest = dataArr[0];
        double largetst = dataArr[0];

        for(int i=1; i< dataArr.length; i++){
            if(dataArr[i] > largetst)
                largetst = dataArr[i];
            else if(dataArr[i] < smallest)
                smallest = dataArr[i];
            }

        double minmaxArr[] = new double[2];
        minmaxArr[0] = smallest;
        minmaxArr[1] = largetst;

        return minmaxArr;
        }
	

}
