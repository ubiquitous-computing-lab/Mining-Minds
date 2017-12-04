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
