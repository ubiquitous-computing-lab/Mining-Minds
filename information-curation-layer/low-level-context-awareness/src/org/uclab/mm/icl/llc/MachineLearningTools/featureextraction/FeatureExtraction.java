/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.llc.MachineLearningTools.featureextraction;

/**
 * This class extracts features from the data
 * @author Nailbrainz
 * @param <InputType> Input data to extract feature
 * @param <OutputType> Extracted feature output
 */
public abstract class FeatureExtraction<InputType, OutputType> {
    
    /**
     * 
     * @param input Input data to extract features
     * @return extracted features
     */
    abstract public OutputType extractFeature(InputType input);
}
