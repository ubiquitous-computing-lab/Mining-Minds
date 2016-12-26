/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uclab.mm.icl.llc.MachineLearningTools;

import org.uclab.mm.icl.llc.MachineLearningTools.classification.Classification;
import org.uclab.mm.icl.llc.MachineLearningTools.featureextraction.FeatureExtraction;

import mm.icl.llc.MachineLearningTools.Preprocessing;
import mm.icl.llc.MachineLearningTools.Segmentation;

/**
 *
 * this interface defines basic structure of machine learning algorithm implementation
 */
public abstract class MachineLearning {
    protected Classification m_classification;
    protected Preprocessing m_preprocessing;
    protected Segmentation m_segmentation;
    protected FeatureExtraction m_featureExtraction;
    
}
