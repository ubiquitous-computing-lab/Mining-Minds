/*
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */
package org.uclab.mm.datamodel.llm;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maqbool
 */
@XmlRootElement()
public class SituationConditions implements Serializable{

    private String conditionKey;
    private String ConditionValueOperator;
    private String ConditionValue;
    private String ConditionType;

    public SituationConditions()
    {
        
    }
    /**
     * @return the conditionKey
     */
    public String getConditionKey() {
        return conditionKey;
    }

    /**
     * @param conditionKey the conditionKey to set
     */
    public void setConditionKey(String conditionKey) {
        this.conditionKey = conditionKey;
    }

    /**
     * @return the ConditionValueOperator
     */
    public String getConditionValueOperator() {
        return ConditionValueOperator;
    }

    /**
     * @param ConditionValueOperator the ConditionValueOperator to set
     */
    public void setConditionValueOperator(String ConditionValueOperator) {
        this.ConditionValueOperator = ConditionValueOperator;
    }

    /**
     * @return the ConditionValue
     */
    public String getConditionValue() {
        return ConditionValue;
    }

    /**
     * @param ConditionValue the ConditionValue to set
     */
    public void setConditionValue(String ConditionValue) {
        this.ConditionValue = ConditionValue;
    }

    /**
     * @return the ConditionType
     */
    public String getConditionType() {
        return ConditionType;
    }

    /**
     * @param ConditionType the ConditionType to set
     */
    public void setConditionType(String ConditionType) {
        this.ConditionType = ConditionType;
    }


}
