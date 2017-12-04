/**
 * Copyright [2016] [Muhammad Afzal]
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
package org.uclab.scl.rs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.scl.Utilities.ComputationUtilityMethods;
import org.uclab.scl.communication.CommunicationManager;
import org.uclab.scl.datamodel.Conclusion;
import org.uclab.scl.datamodel.Disibilities;
import org.uclab.scl.datamodel.FiredRule;
import org.uclab.scl.datamodel.PhysiologicalFactors;
import org.uclab.scl.datamodel.RecommendationIngredients;
import org.uclab.scl.datamodel.RiskFactors;
import org.uclab.scl.datamodel.SituationConditions;
import org.uclab.scl.datamodel.SituationEvent;
import org.uclab.scl.datamodel.SituationNotification;
import org.uclab.scl.datamodel.User;
import org.uclab.scl.framework.Reasoner;
import org.uclab.scl.framework.ReasonerEnum;
import org.uclab.scl.framework.recbuilder.RecBuilder;
import org.uclab.scl.framework.recbuilder.RecBuilderPacket;
import org.uclab.scl.outputModel.SCLResponse;
import org.uclab.scl.outputModel.RecModel.PhyActivityRecommendations;

/**
 * REST Web Service
 * @version MM v2.5
 * @author Afzal
 */
@Path("SCLService")
public class SCLServiceResource {
  private static Logger LOG = LogManager.getRootLogger();
  CommunicationManager dclManager = CommunicationManager.getCommunicationManager();
  

  @Context
  private UriInfo context;

  /**
   * Constructor that creates a new instance of SCLServiceResource
   */
  public SCLServiceResource() {
  }

  
  /**
   * This service method is developed to build rule-based recommendation. 
   * It takes situation notification object as input return the recommendation results accordingly.
   * @param situationNotification
   * @return
   */

  @Path("PushPARecommendation")
  @POST
  @Produces("application/json")
  @Consumes("application/json")
  public SCLResponse buildSituationBasedPARecommendation(SituationNotification situationNotification) {

    SCLResponse response = new SCLResponse();
    List<String> result = new ArrayList<>();

    try {
      Reasoner rbr = Reasoner.getRuleBaseReasoner();
      RecBuilder recomBuilder = (RecBuilder) rbr.getMatchedRules(ReasonerEnum.SITUATION_PAREASONER);

      RecommendationIngredients recIngredients = new RecommendationIngredients();
      ComputationUtilityMethods cUtility = ComputationUtilityMethods.getComputationUtilityMethods();
      String situationValues = "";
      for (SituationConditions sc : situationNotification.getSituationEvent().getListSConditions()) {
        situationValues = situationValues + sc.getConditionValue() + " ";
      }
      LOG.debug("Situation Event Received from Lifelog Monitor (LLM): " + situationValues + "\n*************");

      User user = dclManager.getUser(Long.parseLong(situationNotification.getUserID()));
      List<RiskFactors> riskFactors = dclManager.getRiskFactors(Long.parseLong(situationNotification.getUserID()));
      List<Disibilities> disibilities = dclManager.getDisibilities(Long.parseLong(situationNotification.getUserID()));
      List<PhysiologicalFactors> physFactors = dclManager.getPhysiologicalFactors(Long.parseLong(situationNotification.getUserID()));
      
      recIngredients.setRiskFactors(riskFactors);
      recIngredients.setDisibilities(disibilities);
      recIngredients.setUser(user);
      recIngredients.setsNotification(situationNotification);
      recIngredients.setPhysFactors(physFactors);

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
      Date dob = new Date();
      dob = sdf.parse(user.getDateOfBirth());
      recIngredients.setAgeGroup(cUtility.getAgeGroup(dob));
      LOG.debug("Call for situation reasoner...");
      RecBuilderPacket paRecBuilderInputPacket = new RecBuilderPacket();
      paRecBuilderInputPacket.setRecommendationInput(recIngredients);
      recomBuilder.buildRecommendation(paRecBuilderInputPacket);
      LOG.debug("Sittuation reasoner passed!");
      List<FiredRule> rules = paRecBuilderInputPacket.getRecommendationOutput();

      List<PhyActivityRecommendations> paRecommendations = new ArrayList<PhyActivityRecommendations>();
      PhyActivityRecommendations paRecommendation = new PhyActivityRecommendations();

      String currentDate = cUtility.getCurrentDate();

      LOG.debug("Final Resolved Rule(s): ");
      for (FiredRule fr : rules) {
        result.add("\n*******************************\nRecommendation Builder\n*******************************\nRecommendation(" + fr.getRuleId() + "): " + fr.getRuleConclusion());
        LOG.debug(fr.getRuleConclusion());
        for (Conclusion c : fr.getConclusionList()) {
          result.add(c.getConclusionKey() + c.getConclusionOperator() + c.getConclusionValue());
          LOG.debug(c.getConclusionKey() + c.getConclusionOperator() + c.getConclusionValue());
        }

      }

      List<String> resultRecPersistence = null;
      if (!(paRecommendations.isEmpty())) {
        resultRecPersistence = dclManager.persistPhysicalRecommendations(paRecommendations);
      }
      String recommendationID = resultRecPersistence.get(0);
      result.add(recommendationID);
      result.add("No Error");
    }
    catch (Exception e) {
      result.add("Error:");
      result.add(e.getMessage());
    }
    response.setResult(result);
    return response;

  }
  


  /**
   * PUT method for updating or creating an instance of SCLServiceResource
   * 
   * @param content
   *          representation for the resource
   * @return an HTTP response with content of the updated or created resource.
   */
  @PUT
  @Consumes("application/json")
  public void putJson(String content) {
  }

  @Path("TestLLMJSONFormat/")
  @GET
  @Produces("application/json")
  public SituationNotification getTestSitNotification() {
    SituationNotification sNotif = new SituationNotification();
    sNotif.setUserID("1");

    SituationEvent sEvt = new SituationEvent();
    sEvt.setSituationID("112");
    Set<SituationConditions> sitCon = new HashSet<>();

    SituationConditions sitCon1 = new SituationConditions();
    sitCon1.setConditionKey("Activity Level");
    sitCon1.setConditionValueOperator("String");
    sitCon1.setConditionValue("Sedentary");
    sitCon1.setConditionValueOperator("=");
    sitCon.add(sitCon1);

    SituationConditions sitCon2 = new SituationConditions();
    sitCon2.setConditionKey("Consumed Fat");
    sitCon2.setConditionValue("85");
    sitCon2.setConditionValueOperator(">");
    sitCon2.setConditionType("String");
    sitCon.add(sitCon2);

    sEvt.setListSConditions(sitCon);

    sNotif.setSituationEvent(sEvt);

    return sNotif;
  }
}
