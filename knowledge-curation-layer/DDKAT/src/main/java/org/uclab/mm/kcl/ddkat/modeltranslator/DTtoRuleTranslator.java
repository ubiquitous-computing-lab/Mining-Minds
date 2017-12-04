/**
 * Copyright [2017] [Maqbool Ali, Maqbool Hussain]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uclab.mm.kcl.ddkat.modeltranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.uclab.mm.kcl.ddkat.productionmodel.Conclusion;
import org.uclab.mm.kcl.ddkat.productionmodel.Condition;
import org.uclab.mm.kcl.ddkat.productionmodel.Rule;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

//TODO: Auto-generated Javadoc
/**
* This class translates the decision tree into rules.
*/
public class DTtoRuleTranslator {
	
	/** base directory to store resource data files */
	private static final String BASE_DIR = System.getProperty("user.home") + "/DDKAT/resources/";
	
    public List<Rule> translateModel() throws Exception {
 	
       BufferedReader brTest = new BufferedReader(new FileReader(BASE_DIR + "selectedalgorithmFile.txt"));
       brTest.readLine();
       brTest.close();
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document source = builder.parse(new File(BASE_DIR + "treemodelFile.xml"));
       Node rootNode = source.getDocumentElement();
       Node currentNode=rootNode.getFirstChild();
            
       List<Rule> ruleList = new ArrayList<Rule>();
       int ruleID = 0;
         
       while (currentNode!=null) {
         // print node information
           if(currentNode.getNodeName().equals("Test")){
        	// print node information  
           }

         if ( currentNode.hasChildNodes()) {
           currentNode = currentNode.getFirstChild();
         }
         else {
             // leaf
             if(currentNode.getNodeName().equals("Output")){
            	 
            	 ruleID++;
            	 Rule rule = new Rule();
            	 rule.setRuleID(ruleID);
            	 
            	 List<Condition> conditionList = new ArrayList<Condition>();
            	 List<Conclusion> conclusionList = new ArrayList<Conclusion>();
            	 
                 int conclusionID = 1;
                 String decisionattribute = "TestResult";
                 String conclusionValue = currentNode.getAttributes().getNamedItem("decision").getNodeValue();
                 
                 Conclusion conclusion = new Conclusion();
                 conclusion.setConclusionID(conclusionID);
                 conclusion.setConclusionKey(decisionattribute);
                 conclusion.setConclusionOperator("=");
                 conclusion.setConclusionValue(conclusionValue);
                 conclusionList.add(conclusion);
                 rule.setConclusionList(conclusionList);
                 
                 int pathnodes_counter = 0;
                 String conditionAttribute = " ";
                 String conditionOperator = " ";
                 String conditionValue = " ";
                 Node pathNode = currentNode;

                 while(pathNode.getParentNode().getNodeName().equals("Test")){
                     pathnodes_counter++;
                     pathNode = pathNode.getParentNode();
                     }

                 pathNode = currentNode;
                 String ruleConditionsArr[][] = new String[pathnodes_counter][3];
                 int arrIndex = pathnodes_counter-1;

                 while(pathNode.getParentNode().getNodeName().equals("Test")){
                     conditionAttribute = pathNode.getParentNode().getAttributes().getNamedItem("attribute").getNodeValue();
                     conditionOperator = pathNode.getParentNode().getAttributes().getNamedItem("operator").getNodeValue();
                     conditionValue = pathNode.getParentNode().getAttributes().getNamedItem("value").getNodeValue();
                     ruleConditionsArr[arrIndex][0] = conditionAttribute;
                     ruleConditionsArr[arrIndex][1] = conditionOperator;
                     ruleConditionsArr[arrIndex][2] = conditionValue;
                     arrIndex--;
                     pathNode = pathNode.getParentNode();
                     }

                 for(int conditionID=0; conditionID<pathnodes_counter; conditionID++){
                     Condition condition = new Condition();
                     condition.setConditionID(conditionID+1);
                     condition.setConditionKey(ruleConditionsArr[conditionID][0]);
                     condition.setConditionValueOperator(ruleConditionsArr[conditionID][1]);
                     condition.setConditionValue(ruleConditionsArr[conditionID][2]);
                     conditionList.add(condition);
                     }
                 
                 rule.setConditionList(conditionList);
                 ruleList.add(rule);
             } 
             
             // find the parent level
             while (currentNode.getNextSibling()==null && currentNode != rootNode){
            	 // use child-parent link to get to the parent level
            	 currentNode=currentNode.getParentNode();
             	 }

             currentNode = currentNode.getNextSibling();
         }
       }  
       
       return ruleList;
   }
    
}

