/*
Copyright [2016] [Jae Hun, Bang]

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
package org.uclab.mm.icl.llc.MachineLearningTools.classification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.uclab.mm.icl.llc.LLCManager.ContextLabel;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Nailbrainz
 * @param <InputType> Datatype which will be used for classification 
 */
public abstract class Classification<InputType> {
    
    /**
     * 
     * @param data data to classify
     * @return classified result
     */
    abstract public String Classify(InputType data);
    
   
}
