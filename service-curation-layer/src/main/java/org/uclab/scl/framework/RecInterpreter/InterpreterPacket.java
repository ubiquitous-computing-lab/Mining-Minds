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

package org.uclab.scl.framework.RecInterpreter;

import java.util.List;

import org.uclab.scl.datamodel.InterpretationIngredients;
import org.uclab.scl.framework.AbstractInterpreterPacket;
import org.uclab.scl.outputModel.InterpretationModel.InterpretedRecommendations;

public class InterpreterPacket extends AbstractInterpreterPacket<InterpretationIngredients, List<InterpretedRecommendations>> {

  @Override
  public InterpretationIngredients getRecommendationInput() {
    return this.interpretationIngredients;
  }

  @Override
  public void setRecommendationInput(InterpretationIngredients recommendationInput) {
    this.interpretationIngredients = recommendationInput;
  }

  @Override
  public List<InterpretedRecommendations> getRecommendationOutput() {
    return this.InterpretedRecommendations;
  }

  @Override
  public void setRecommendationOutput(List<InterpretedRecommendations> recommendationOutput) {
    this.InterpretedRecommendations = recommendationOutput;
  }

}
