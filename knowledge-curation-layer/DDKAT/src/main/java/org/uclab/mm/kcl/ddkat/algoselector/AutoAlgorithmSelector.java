/**
* Copyright [2017] [Maqbool Ali, Maqbool Hussain, Rahman Ali]
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
package org.uclab.mm.kcl.ddkat.algoselector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcolibri.cbrcore.CBRCase;
import jcolibri.method.retrieve.RetrievalResult;

import org.uclab.mm.kcl.edket.algoselector.AutomaticAlgorithmSelector;
import org.uclab.mm.kcl.edket.algoselector.QueryManager;
import org.uclab.mm.kcl.edket.algoselector.SimilarityManager;
import org.uclab.mm.kcl.edket.algoselector.mfe.InputCaseBuilder;
import org.uclab.mm.kcl.edket.algoselector.mfe.MetaFeature;
import org.uclab.mm.kcl.edket.algoselector.mfe.MetaFeatureExtractor;


//TODO: Auto-generated Javadoc
/**
* This class recommends appropriate decision tree algorithm.
*/
public class AutoAlgorithmSelector {
	
	private ArrayList<String> caseIDs;
	private ArrayList<String> autoSelectedAlgos;
	private ArrayList<String> similarityScores;
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "/DDKAT/resources/";
	
	/**
	 * Constructor to instantiate a new AutoAlgorithmSelector object.
	 * @param dataUnorderedObject the json data object
	 * @throws Exception the exception
	 */
//	public AutoAlgorithmSelector(String inputfilePath) throws Exception {
	public AutoAlgorithmSelector() throws Exception {
    	
    	this.caseIDs = new ArrayList<String>();
    	this.autoSelectedAlgos = new ArrayList<String>();
    	this.similarityScores = new ArrayList<String>();
        /**** build inputCase file if not exists ****/	
    	String resolvedCasesFile = BASE_DIR + "AlgorithmsSelectionCaseBase.csv";
    	String outputFile = resolvedCasesFile.substring(0,resolvedCasesFile.lastIndexOf('/'));
     	InputCaseBuilder icb = new InputCaseBuilder();
    	icb.setMetaFeatureFile(resolvedCasesFile);
    	icb.buildCasesIfNotExists();
    	
    	String inputFile = BASE_DIR + "ProcessedDataSet.arff";
        Map<MetaFeature, Integer> simIntervals = InputCaseBuilder.detectSimIntervals(resolvedCasesFile);
               
        /****instantiate AutomaticAlgorithmSelector****/
        AutomaticAlgorithmSelector autoAlgoSelector = new AutomaticAlgorithmSelector(new QueryManager(), new SimilarityManager(simIntervals));
        autoAlgoSelector.configure();
        
        /**** set options and extract meta features ****/ 
        Map<String, String> options = new HashMap<String, String>();
        options.put("dataset_dir", "");
        options.put("dataset_file", inputFile);
        options.put("mode", "single");
        options.put("output_dir", outputFile + "/output/");
 
        MetaFeatureExtractor mfe = new MetaFeatureExtractor( options );
        List<Map<String, Double>> xFeaturesList = mfe.extractMetaFeatures();

        
        /**** build queryCase from the extracted features ****/
        Map<MetaFeature, Object> queryCase = buildQueryCase(xFeaturesList.get(0));
              
        /********* select cases ***********/
        Collection<CBRCase> selectedCases = autoAlgoSelector.evaluateSimilarity(queryCase).retrieveTopKResults(3);
        Collection<RetrievalResult> evaluation = autoAlgoSelector.getEvaluationList();
        
        /**** string parsing ****/
        for(RetrievalResult rr : evaluation){
        	
            if(selectedCases.contains(rr.get_case())){
            	
            	String originalDescriptionStr = rr.get_case().getDescription().toString(); 
            	String originalSolutionStr = rr.get_case().getSolution().toString();
            	    
            	String[] splitDescriptionArr = originalDescriptionStr.split("CaseID=");
                String subDescriptionStr = splitDescriptionArr[1];  
                String[] subDescriptionSplitArr = subDescriptionStr.split(", ",2);
                String caseIDStr = subDescriptionSplitArr[0];
                System.out.println("CASE ID : "+caseIDStr);
                this.caseIDs.add(caseIDStr);
                                 
                originalSolutionStr = originalSolutionStr.substring(1, originalSolutionStr.length()-1);
                String[] algorithmSplitArr = originalSolutionStr.split(": ",3);
                String tempAlgoStr;
                for(int i=0;i<3;i++){
                    tempAlgoStr = algorithmSplitArr[i];
                    tempAlgoStr = tempAlgoStr.substring(6, tempAlgoStr.length()-3);
                    System.out.println("Position:"+i+" "+tempAlgoStr);
                    if(!tempAlgoStr.equals("RandomForest") && !tempAlgoStr.equals("LADTree")){
                        System.out.println("Algo 1:"+tempAlgoStr);
                        this.autoSelectedAlgos.add(tempAlgoStr);
                        break;
                        }
                    }

                double similarityScore = rr.getEval();
                long factor = (long) Math.pow(10, 6);
                similarityScore = similarityScore * factor;
                long tmp = Math.round(similarityScore);
                double roundedSimilarityScore = (double) tmp / factor;
               	String similarityStr = String.valueOf(roundedSimilarityScore);
            	this.similarityScores.add(similarityStr);
            	
            	}
        	}
              
            
        /******* reuse *********/
        Collection<CBRCase> reuseCases = autoAlgoSelector.combineQueryAndCaseMethod(selectedCases);
        
        /******** revise *******/ 
        CBRCase bestCase = autoAlgoSelector.getBestCase(reuseCases);
        System.out.println("Best Algorithm: " + bestCase.getSolution().toString());

        /********* retain ********/
        //. autoAlgoSelector.retainCase(bestCase);
        
    }
    

	/**
     * builds queryCase from the extracted meta features.
     * Note: extracted features contains extra features that we don't need for our application, so here we are filtering only required features.
     * 
     * @param xFeatures
     * @return Map<MetaFeature, Object> queryCase
     */
    private Map<MetaFeature, Object> buildQueryCase(Map<String, Double> xFeatures){
        
        Map<MetaFeature, Object> queryCase = new HashMap<MetaFeature, Object>();
        for(String key : xFeatures.keySet()){
            
            //. filter out only required metaFeatures
            try{ 
                MetaFeature columnName = MetaFeature.valueOf(key);
                Object columnValue = xFeatures.get(key);
                queryCase.put(columnName, columnValue);
            } catch (IllegalArgumentException e){
                //. we do not need this feature
            }
        }
        return queryCase;
    }  
    
    
    public ArrayList<String> getCaseIDs() {
 		return caseIDs;
 	}

 	public void setCaseIDs(ArrayList<String> caseIDs) {
 		this.caseIDs = caseIDs;
 	}
    
    public ArrayList<String> getAutoSelectedAlgos() {
		return autoSelectedAlgos;
	}

	public void setAutoSelectedAlgos(ArrayList<String> autoSelectedAlgos) {
		this.autoSelectedAlgos = autoSelectedAlgos;
	}
	
	public ArrayList<String> getSimilarityScores() {
		return similarityScores;
	}

	public void setSimilarityScores(ArrayList<String> similarityScores) {
		this.similarityScores = similarityScores;
	}


}
