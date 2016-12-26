/*
Copyright [2016] [Dong Uk, Kang]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.uclab.mm.icl.llc.config;

import org.uclab.mm.icl.llc.AER.vui.AudioEmotionRecognizerV;
import org.uclab.mm.icl.llc.LLCRecognizer.AudioEmotionRecognizer;
import org.uclab.mm.icl.llc.LLCRecognizer.GPSLocationRecognizer;
import org.uclab.mm.icl.llc.LLCRecognizer.InertialActivityRecognizer;
import org.uclab.mm.icl.llc.LLCRecognizer.LLCRecognizer;
import org.uclab.mm.icl.llc.LLCRecognizer.FoodRecognizer;
import org.uclab.mm.icl.llc.LLCRecognizer.VideoActivityRecognizer;
import org.uclab.mm.icl.llc.MachineLearningTools.ExtClassification;
import org.uclab.mm.icl.utils.FileUtil;
import weka.classifiers.functions.SMO;
import weka.core.SelectedTag;



/**
 * Enum for the different recognizer type for different context. Consumes the Datatype and generates ContextType data
 * @author Nailbrainz
 *
 */
public enum RecognizerType {
	SER(0), ER(1), IAR(2), VAR(3), LR(4), FR(5);
	
	
    private int value;
    
    /**
     * Unique constructor of RecognizerType.
     * @param _value
     */
    private RecognizerType(int _value){
        value = _value;
    }
  //, Food(5), EmoPic(6);

/*public static String[] actLabels = {"Stretching","Standing","Sitting","Sweeping"
			,"Walking","Running","ClimbingStairs","Eating","LyingDown","Jumping", "Cycling", "NoActivity"};*/
   
    /**
     * Returns the context which each recognizer produces
     * @return ContextType enum, context which each recognizer produces
     */
    public ContextType getContext(){
    	RecognizerType rec = this.values()[value];
        switch(rec){
	    	case SER:
	    		return ContextType.Emotion;
	    	case ER:
	    		return ContextType.Emotion;
	    	case IAR:
	    		return ContextType.Activity;
	    	case VAR:
	    		return ContextType.Activity;
	    	case LR:
	    		return ContextType.Location;
	    	case FR:
	    		return ContextType.Food;
	    }
    	return null;
    }
    
    /**
     * Returns the corresponding recognizer
     * @param rec recognizer type to return
     * @param userID user ID to set
     * @return instance of the corresponding recognizer
     */
    public LLCRecognizer getRecognizer(long userID){
    	
    	RecognizerType rec = this.values()[value];
        switch(rec){
        case SER:
        	String[] labels = { "Anger", "Happiness", "Sadness"};
        	String path = FileUtil.getRootPath()+"/training/modeldataV2.7.txt";
        	SMO svm = new SMO(); // Define Classifier with Weka
    		try {
    			svm.setOptions(weka.core.Utils
    					.splitOptions("-C 1.0 -L 0.0010 -P 1.0E-12 -N 1 -V -1 -W 1 -K \"weka.classifiers.functions.supportVector.RBFKernel -C 250007 -G 0.01\""));
    			svm.setFilterType(new SelectedTag(SMO.FILTER_STANDARDIZE, SMO.TAGS_FILTER));
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		ExtClassification classifier = new ExtClassification(path, 78*2, labels, svm);
    		AudioEmotionRecognizer aer = new AudioEmotionRecognizer(classifier, path, userID);
        	
        	return aer;
        case ER:
        	return new AudioEmotionRecognizerV(userID);
        case IAR:
        	return new InertialActivityRecognizer(userID);
        case VAR:
        	return new VideoActivityRecognizer(userID);
        case LR:
        	//get user loc coord / label with userID by restful service
        	return new GPSLocationRecognizer(userID);
        case FR:
        	return new FoodRecognizer(userID);
        }
        return null;
    }
    
    
    
   
  
    /**
     * Returns the boolean value which indicates whether the current recognizer is running or not
     * @return boolean value which indicates whether the current recognizer is running or not
     */
    public boolean isOn(){
    	return  ICLConfig.isRecogOn.get(value);
    }
    

    /**
     * Returns the string which best describes the current recognizer type
     */
    public String toString(){
    	return this.name();
    }
    
    /**
     * Returns the value for the current context, which is configed inside the enum
     * @return integer value of the current context
     */
    public int getValue(){
        return value;
    }
    
}
