/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.llc.MachineLearningTools;

/**
 * 
 * @author Nailbrainz
 * This class preprocess the data
 * @param <InputType> Data which will be preprocessed
 * @param <OutputType> preprocessed output
 */
public abstract class Preprocessing<InputType, OutputType> {
    
    /**
     * Abstract function, a structure for child preprocessing classes
     * @param input input data to preprocess
     * @return processed data
     */
    abstract public OutputType preprocessing(InputType input);
}
