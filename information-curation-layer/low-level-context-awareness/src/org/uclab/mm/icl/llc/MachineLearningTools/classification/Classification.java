/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
