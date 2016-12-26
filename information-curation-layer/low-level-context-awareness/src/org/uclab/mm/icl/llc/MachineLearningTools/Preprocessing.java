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
