/* Copyright [2016] [Syed Imran Ali]

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

package org.uclab.scl.framework.RecInterpreter.Explanation;

import org.uclab.scl.outputModel.InterpretationModel.InterpretedRecommendations;

public abstract class AbstractRecExplanation {
	 public abstract String parseAggregateVec(int[] aggregateVec) throws Exception;
	 public abstract InterpretedRecommendations handleExplanation(int[] interpRec, String duration, String context, String description) throws Exception;
	 public abstract String explanationSentenceTemplate(String rec, String duration, String sentence, String context) throws Exception;
	 public abstract String buildUrl(String recValue ) throws Exception;
}
