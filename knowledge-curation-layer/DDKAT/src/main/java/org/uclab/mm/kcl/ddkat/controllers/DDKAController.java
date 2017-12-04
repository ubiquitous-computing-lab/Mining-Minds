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
package org.uclab.mm.kcl.ddkat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.uclab.mm.kcl.ddkat.algoselector.AutoAlgorithmSelector;
import org.uclab.mm.kcl.ddkat.datapreprocessor.*;
import org.uclab.mm.kcl.ddkat.dataselector.FeatureEvaluator;
import org.uclab.mm.kcl.ddkat.modellearner.ModelLearner;
import org.uclab.mm.kcl.ddkat.modeltranslator.ModelTranslator;

import weka.core.Instances;

//TODO: Auto-generated Javadoc
/**
* This class controls the tasks of data selection, data preprocessing, algorithm selection, model learning, and model translation.
*/
@Controller
public class DDKAController {
	
	/** The data instances */	
	private Instances data;
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "\\DDKAT\\resources\\";
	
	/**
	 * Method to communicate the request/response of Web interface to open the data selection view.
	 *
	 * @return the model view
	 */
	// Open the data selection interface
	@RequestMapping("/dataselectionview")	
	public ModelAndView showMessage() {
		ModelAndView mv = new ModelAndView("dataselector");
		return mv;
		}
	
	
	/**
	 * Method to communicate the request/response of Web interface for data conversion as well as its storage.
	 *
	 * @param json the data string
	 * @return the data converter class object
	 * @throws Exception the exception
	 */
	// Convert the data into CSV format and then store it into local machine.
	@RequestMapping(value="/dataconverter", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String convertData(@RequestBody String json) throws Exception {
		String confirmationStr="Data is successfully converted into CSV format and is stored at : "+ BASE_DIR +"OriginalDataSet.arff";
		new DataConverter(json.toString());
		return confirmationStr;
    	}

	
	/**
	 * Method to communicate the request/response of Web interface with FeatureEvaluator class.
	 *
	 * @param json the data string
	 * @return the feature evaluator class object
	 * @throws Exception the exception
	 */	
	// Compute the EFRS methodology
	@RequestMapping(value="/featuresvisualizer", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody FeatureEvaluator evaluateFeatures(@RequestBody String json) throws Exception {
		FeatureEvaluator fe = new FeatureEvaluator(json, data);
		return fe;
	}
	
	/**
	 * Method to communicate the request/response of Web interface to open the data preprocessing view.
	 */
	// Open the data preprocessing interface
	@RequestMapping(value ="/preprocessor", method = RequestMethod.POST)
    public String preProcess(@ModelAttribute("loadingdataQuery")String query, BindingResult preprocessor, Model model) {
       
        if(preprocessor.hasErrors()){
            return "dataselector";
        }else{
        	
        	 System.out.println("\n\nQuery for Required Data : " + query);
        	 model.addAttribute("loadingdataQueryId",query);
        	 return "preprocessor";
           }
    	}
	
	
   /**
    * Method to communicate the request/response of Web interface with MissingValueHandler class.
    *
    * @return the missing value handler
    * @throws Exception the exception
    */
   // Cleaning up the data
	@RequestMapping(value = "/missingvaluehandler", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody MissingValueHandler missingValueHandler() throws Exception {
		MissingValueHandler mvh = new MissingValueHandler();
		return mvh;
		}
	
	
	/**
	 * Method to communicate the request/response of Web interface with OutlierHandler class.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	// Detecting and Removing Outliers and Extreme Values
	@RequestMapping(value="/outlierhandler", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String outlierHandler() throws Exception {
		String confirmationStr="Outliers have been successfully removed and is stored at : "+ BASE_DIR +"ProcessedDataSet.arff";
		new OutlierHandler();
		return confirmationStr;
	}
	
	
	/**
	 * Method to communicate the request/response of Web interface with DataDiscretizer class.
	 *
	 * @return the data discretizer
	 * @throws Exception the exception
	 */
	// Discretize the data
	@RequestMapping(value = "/datadiscretizer", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody DataDiscretizer dataDiscretization() throws Exception {
	    DataDiscretizer dd = new DataDiscretizer();
		return dd;
		}
	
	
	/**
	 * Method to communicate the request/response of Web interface with FeaturesSelector class.
	 *
	 * @return the features selector
	 * @throws Exception the exception
	 */
	// Filter the data
	@RequestMapping(value = "/featuresselector", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody FeaturesSelector featuresSelection() throws Exception {
	    FeaturesSelector fs = new FeaturesSelector();
		return fs;
		}
	
	
	/**
	 * Method to communicate the request/response of Web interface to open the model learning interface.
	 *
	 * @param algo the decision tree algorithm string
	 * @return the model learner class object
	 * @throws Exception the exception
	 */	
	// Open the model learning interface
	@RequestMapping(value ="/modellearner", method = RequestMethod.POST)
    public String modelLearner(@ModelAttribute("communicationVariable")String communicationVariable, BindingResult modellearner, Model model) throws Exception {
       
        if(modellearner.hasErrors()){
            return "preprocessor";
        }else{
        	 model.addAttribute("communicationVariableId",communicationVariable);
        	 return "modellearner";
           }
    	}
	
	
	/**
	 * Method to communicate the request/response of Web interface with Auto Algorithm Selector class.
	 *
	 * @return the auto algorithm selector class object
	 * @throws Exception the exception
	 */	
	// Select the appropriate decision tree algorithm
	@RequestMapping(value="/autoalgoselection", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody AutoAlgorithmSelector autoAlgoSelection() throws Exception {
		AutoAlgorithmSelector aas = new AutoAlgorithmSelector();
		return aas;
		}
	
	
	/**
	 * Method to communicate the request/response of Web interface with ModelTranslator class.
	 *
	 * @param algo the decision tree algorithm string
	 * @return the model learner class object
	 * @throws Exception the exception
	 */	
	// Learn the decision tree classification model
	@RequestMapping(value="/learnmodel", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ModelLearner learnModel(@RequestBody String algo) throws Exception {
		ModelLearner ml = new ModelLearner(algo);
		return ml;
		}
	
	
	/**
	 * Method to communicate the request/response of Web interface with ModelTranslator class.
	 *
	 * @param algo_modelstr the decision tree model string
	 * @return the model translator class object
	 * @throws Exception the exception
	 */
	// translate the model
	@RequestMapping(value="/modeltranslator", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ModelTranslator translateModel(@RequestBody String algo_modelstr) throws Exception {
		ModelTranslator mt = new ModelTranslator(algo_modelstr);
		return mt;
	}

	
}

