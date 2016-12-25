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


package org.uclab.mm.datamodel;

import java.util.List;
import org.uclab.mm.datamodel.dc.PhysiologicalFactors;
import org.uclab.mm.datamodel.dc.UserDisabilities;
import org.uclab.mm.datamodel.dc.UserFacilities;
import org.uclab.mm.datamodel.dc.UserRiskFactors;
import org.uclab.mm.datamodel.dc.UserSchedule;
import org.uclab.mm.datamodel.dc.Users;
import org.uclab.mm.datamodel.ic.Device;
import org.uclab.mm.datamodel.ic.FoodLog;
import org.uclab.mm.datamodel.ic.UserAccelerometerData;
import org.uclab.mm.datamodel.ic.UserDetectedLocation;
import org.uclab.mm.datamodel.ic.UserDevice;
import org.uclab.mm.datamodel.ic.UserGPSData;
import org.uclab.mm.datamodel.ic.UserPreferredLocation;
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;
import org.uclab.mm.datamodel.ic.UserRecognizedActivityAccumulate;
import org.uclab.mm.datamodel.ic.UserRecognizedActivityLog;
import org.uclab.mm.datamodel.ic.UserRecognizedEmotion;
import org.uclab.mm.datamodel.ic.UserRecognizedHLC;
import org.uclab.mm.datamodel.llm.LifelogMonitorActivity;
import org.uclab.mm.datamodel.sc.Achievements;
import org.uclab.mm.datamodel.sc.ActivityPlan;
import org.uclab.mm.datamodel.sc.ActivityRecommendation;
import org.uclab.mm.datamodel.sc.ActivityRecommendationLog;
import org.uclab.mm.datamodel.sc.Facts;
import org.uclab.mm.datamodel.sc.ProfileData;
import org.uclab.mm.datamodel.sc.Recommendation;
import org.uclab.mm.datamodel.sc.RecommendationException;
import org.uclab.mm.datamodel.sc.RecommendationExplanation;
import org.uclab.mm.datamodel.sc.Situation;
import org.uclab.mm.datamodel.sc.UserGoal;
import org.uclab.mm.datamodel.sc.UserPreferredActivities;
import org.uclab.mm.datamodel.sc.UserRewards;
import org.uclab.mm.datamodel.sl.ActiveSession;
import org.uclab.mm.datamodel.sl.ActivityFeedback;
import org.uclab.mm.datamodel.sl.ExpertReview;
import org.uclab.mm.datamodel.sl.FactsFeedback;
import org.uclab.mm.datamodel.sl.RecommendationFeedback;
import org.uclab.mm.datamodel.sl.UserAccelerometerDataVisulization;
import org.uclab.mm.datamodel.sl.UserGPSDataVisulization;


/**
 * This abstract class is bridge between for the CRUD operations between Database and all corresponding implementation classes.
 * @author Taqdir Ali
 */
public abstract class AbstractDataBridge {
    
    protected DataAccessInterface objDAInterface;
    public AbstractDataBridge(DataAccessInterface objDAInterface)
    {
        this.objDAInterface = objDAInterface;
    }
    
    /**
     * This constructor of the abstract class
     */
    public AbstractDataBridge()
    {
        
    }
    
    /**
     * This is abstract function for saving User.
     * @param objUser
     * @return List of String 
     */
    public abstract List<String> SaveUser(Users objUser);
    /**
     * This is abstract function for updating User.
     * @param objUser
     * @return List of string
     *  
     */
    public abstract List<String> UpdateUser(Users objUser);
    /**
     * This is abstract function for retrieving User. 
     * @param objUser
     * @return List of Users
     */
    public abstract List<Users> RetriveUser(Users objUser);
    /**
     * This is abstract function for deleting User.
     * @param objUser
     * @return List of String 
     */
    public abstract List<String> DeleteUser(Users objUser);
    
    /**
     * This is abstract function for saving Device. 
     * @param objDevice
     * @return List of String
     */
    public abstract List<String> SaveDevice(Device objDevice);
    /**
     * This is abstract function for updating Device. 
     * @param objDevice
     * @return List of String
     */
    public abstract List<String> UpdateDevice(Device objDevice);
    /**
     * This is abstract function for retrieving Device. 
     * @param objDevice
     * @return List of String
     */
    public abstract List<Device> RetriveDevice(Device objDevice);
    
    /**
     * This is abstract function for saving UserDevice. 
     * @param objUserDevice
     * @return List of String
     */
    public abstract List<String> SaveUserDevice(UserDevice objUserDevice);
    /**
     * This is abstract function for updating UserDevice. 
     * @param objUserDevice
     * @return List of String
     */
    public abstract List<String> UpdateUserDevice(UserDevice objUserDevice);
    /**
     * This is abstract function for retrieving UserDevice. 
     * @param objUserDevice
     * @return List of UserDevice
     */
    
    public abstract List<UserDevice> RetriveUserDevice(UserDevice objUserDevice);
    
    /**
     * This is abstract function for saving UserRiskFactors. 
     * @param objUserRiskFactors
     * @return List of String
     */
    public abstract List<String> SaveUserRiskFactors(UserRiskFactors objUserRiskFactors);
    /**
     * This is abstract function for updating UserRiskFactors. 
     * @param objUserRiskFactors
     * @return List of String
     */
    public abstract List<String> UpdateUserRiskFactors(UserRiskFactors objUserRiskFactors);
    /**
     * This is abstract function for retrieving UserRiskFactors.
     * @param objUserRiskFactors
     * @return List of UserRiskFactors 
     */
    public abstract List<UserRiskFactors> RetriveUserRiskFactors(UserRiskFactors objUserRiskFactors);
    
    /**
     * This is abstract function for saving PhysiologicalFactors. 
     * @param objPhysiologicalFactors
     * @return List of String
     */
    public abstract List<String> SavePhysiologicalFactors(PhysiologicalFactors objPhysiologicalFactors);
    /**
     * This is abstract function for updating PhysiologicalFactors. 
     * @param objPhysiologicalFactors
     * @return List of String
     */
    public abstract List<String> UpdatePhysiologicalFactors(PhysiologicalFactors objPhysiologicalFactors);
    /**
     * This is abstract function for retrieving PhysiologicalFactors.
     * @param objPhysiologicalFactors
     * @return List of PhysiologicalFactors 
     */
    public abstract List<PhysiologicalFactors> RetrivePhysiologicalFactors(PhysiologicalFactors objPhysiologicalFactors);
    
    /**
     * This is abstract function for saving UserFacilities. 
     * @param objUserFacilities
     * @return List of String
     */
    public abstract List<String> SaveUserFacilities(UserFacilities objUserFacilities);
    /**
     * This is abstract function for updating UserFacilities. 
     * @param objUserFacilities
     * @return List of String
     */
    public abstract List<String> UpdateUserFacilities(UserFacilities objUserFacilities);
    /**
     * This is abstract function for retrieving UserFacilities.
     * @param objUserFacilities
     * @return List of UserFacilities
     */
    public abstract List<UserFacilities> RetriveUserFacilities(UserFacilities objUserFacilities);
    
    /**
     * This is abstract function for saving UserPreferredActivities.
     * @param objUserPreferredActivities
     * @return List of String
     */
    public abstract List<String> SaveUserPreferredActivities(UserPreferredActivities objUserPreferredActivities);
    /**
     * This is abstract function for updating User. 
     * @param objUserPreferredActivities
     * @return List of String
     */
    public abstract List<String> UpdateUserPreferredActivities(UserPreferredActivities objUserPreferredActivities);
    /**
     * This is abstract function for retrieving UserPreferredActivities. 
     * @param objUserPreferredActivities
     * @return List of UserPreferredActivities
     */
    public abstract List<UserPreferredActivities> RetriveUserPreferredActivities(UserPreferredActivities objUserPreferredActivities);
    
    /**
     * This is abstract function for saving UserGPSData. 
     * @param objUserGPSData
     * @return List of String
     */
    public abstract List<String> SaveUserGPSData(UserGPSData objUserGPSData);
    /**
     * This is abstract function for retrieving UserGPSData. 
     * @param objUserGPSData
     * @return List of UserGPSData
     */
    public abstract List<UserGPSData> RetriveUserGPSData(UserGPSData objUserGPSData);
    
    /**
     * This is abstract function for saving UserAccelerometerData. 
     * @param objUserAccelerometerData
     * @return List of String
     */
    public abstract List<String> SaveUserAccelerometerData(UserAccelerometerData objUserAccelerometerData);
    /**
     * This is abstract function for retrieving UserAccelerometerData. 
     * @param objUserAccelerometerData
     * @return List of UserAccelerometerData
     */
    public abstract List<UserAccelerometerData> RetriveUserAccelerometerData(UserAccelerometerData objUserAccelerometerData);
    
    /**
     * This is abstract function for saving UserRecognizedActivity.
     * @param objUserRecognizedActivity
     * @return List of String 
     */
    public abstract List<String> SaveUserRecognizedActivity(UserRecognizedActivity objUserRecognizedActivity);
    /**
     * This is abstract function for retrieving UserRecognizedActivity.
     * @param objUserRecognizedActivity
     * @return List of UserRecognizedActivity 
     */
    public abstract List<UserRecognizedActivity> RetriveUserRecognizedActivity(UserRecognizedActivity objUserRecognizedActivity);
    
    /**
     * This is abstract function for saving UserDetectedLocation. 
     * @param objUserDetectedLocation
     * @return List of String
     */
    public abstract List<String> SaveUserDetectedLocation(UserDetectedLocation objUserDetectedLocation);
    /**
     * This is abstract function for retrieving UserDetectedLocation.
     * @param objUserDetectedLocation
     * @return List of UserDetectedLocation 
     */
    public abstract List<UserDetectedLocation> RetriveUserDetectedLocation(UserDetectedLocation objUserDetectedLocation);
    
    /**
     * This is abstract function for saving UserGoal. 
     * @param objUserGoal
     * @return List of String
     */
    public abstract List<String> SaveUserGoal(UserGoal objUserGoal);
    /**
     * This is abstract function for retrieving UserGoal.
     * @param objUserGoal
     * @return List of UserGoal 
     */
    public abstract List<UserGoal> RetriveUserGoal(UserGoal objUserGoal);
    
    /**
     * This is abstract function for saving ActivityPlan. 
     * @param objActivityPlan
     * @return List of String
     */
    public abstract List<String> SaveActivityPlan(ActivityPlan objActivityPlan);
    /**
     * This is abstract function for retrieving ActivityPlan. 
     * @param objActivityPlan
     * @return List of ActivityPlan
     * 
     */
   public abstract List<ActivityPlan> RetriveActivityPlan(ActivityPlan objActivityPlan);
    
    /**
     * This is abstract function for saving ActivityRecommendation. 
     * @param objActivityRecommendation
     * @return List of String
     */
    public abstract List<String> SaveActivityRecommendation(ActivityRecommendation objActivityRecommendation);
    /**
     * This is abstract function for retrieving ActivityRecommendation. 
     * @param objActivityRecommendation
     * @return List of ActivityRecommendation
     */
    public abstract List<ActivityRecommendation> RetriveActivityRecommendation(ActivityRecommendation objActivityRecommendation);
    
    /**
     * This is abstract function for saving ActivityRecommendationLog. 
     * @param objActivityRecommendationLog
     * @return List of String
     */
    public abstract List<String> SaveActivityRecommendationLog(ActivityRecommendationLog objActivityRecommendationLog);
    /**
     * This is abstract function for retrieving ActivityRecommendationLog. 
     * @param objActivityRecommendationLog
     * @return List of ActivityRecommendationLog
     */
    public abstract List<ActivityRecommendationLog> RetriveActivityRecommendationLog(ActivityRecommendationLog objActivityRecommendationLog);
    
    
    /**
     * This is abstract function for validating Users. 
     * @param objUsers
     * @return List of String
     */
    public abstract List<String> IsUserValid(Users objUsers);
    
    /**
     * This is abstract function for saving ActiveSession. 
     * @param objActiveSession
     * @return List of String
     */
    public abstract List<String> SaveActiveSession(ActiveSession objActiveSession);
    
    /**
     * This is abstract function setter of DataAccessInterface. 
     * @param DAInterface
     *
     */
    public abstract void setDataAdapter(DataAccessInterface DAInterface);
    
    /**
     * This is abstract function for retrieving ProfileData. 
     * @param objProfileData
     * @return List of ProfileData
     */
     public abstract List<ProfileData> RetriveProfileData(ProfileData objProfileData);
     
     /**
      * This is abstract function for saving UserGPSDataVisulization. 
      * @param objUserGPSData
     * @return List of UserGPSDataVisulization
      */
     public abstract List<UserGPSDataVisulization> RetriveUserGPSDataVisulization(UserGPSDataVisulization objUserGPSData);
     /**
      * This is abstract function for retrieving UserAccelerometerDataVisulization. 
      * @param objUserAccelerometerData
     * @return List of UserAccelerometerDataVisulization
      */
     public abstract List<UserAccelerometerDataVisulization> RetriveUserAccelerometerDataVisulization(UserAccelerometerDataVisulization objUserAccelerometerData);
     
     /**
      * This is abstract function for retrieving UserRewards. 
      * @param objUserRewards
     * @return List of UserRewards
      */
      public abstract List<UserRewards> RetriveUserRewards(UserRewards objUserRewards);
      /**
       * This is abstract function for saving UserRewards. 
       * @param objUserRewards
     * @return List of String
       */
      public abstract List<String> SaveUserRewards(UserRewards objUserRewards);
      /**
       * This is abstract function for updating UserRewards.
       * @param objUserRewards
     * @return List of String 
       */
      public abstract List<String> UpdateUserRewards(UserRewards objUserRewards);
      
      /**
       * This is abstract function for saving UserRecognizedActivityLog. 
       * @param objUserRecognizedActivityLog
     * @return List of String
       */
    public abstract List<String> SaveUserRecognizedActivityLog(UserRecognizedActivityLog objUserRecognizedActivityLog);
    /**
     * This is abstract function for retrieving UserRecognizedActivityLog. 
     * @param objUserRecognizedActivityLog
     * @return List of UserRecognizedActivityLog
     */
    public abstract List<UserRecognizedActivityLog> RetriveUserRecognizedActivityLog(UserRecognizedActivityLog objUserRecognizedActivityLog);
    
    /**
     * This is abstract function for saving Situation. 
     * @param objSituation
     * @return List of String
     */
    public abstract List<String> SaveSituation(Situation objSituation);
    /**
     * This is abstract function for retrieving Situation.
     * @param objSituation
     * @return List of Situation 
     */
    public abstract List<Situation> RetriveSituation(Situation objSituation);
    /**
     * This is abstract function for updating Situation. 
     * @param objSituation
     * @return List of String
     */
    public abstract List<String> UpdateSituation(Situation objSituation);
    
    /**
     * This is abstract function for saving Recommendation. 
     * @param objRecommendation
     * @return List of String
     */
    public abstract List<String> SaveRecommendation(Recommendation objRecommendation);
    /**
     * This is abstract function for retrieving Recommendation. 
     * @param objRecommendation
     * @return List of Recommendation
     */
    public abstract List<Recommendation> RetriveRecommendation(Recommendation objRecommendation);
    /**
     * This is abstract function for updating Recommendation. 
     * @param objRecommendation
     * @return List of String
     */
    public abstract List<String> UpdateRecommendation(Recommendation objRecommendation);
    
    /**
     * This is abstract function for saving RecommendationException.
     * @param objRecommendationException
     * @return List of String 
     */
    public abstract List<String> SaveRecommendationException(RecommendationException objRecommendationException);
    /**
     * This is abstract function for retrieving RecommendationException.
     * @param objRecommendationException
     * @return List of RecommendationException 
     */
    public abstract List<RecommendationException> RetriveRecommendationException(RecommendationException objRecommendationException);
    /**
     * This is abstract function for updating RecommendationException.
     * @param objRecommendationException
     * @return List of RecommendationException 
     */
    public abstract List<String> UpdateRecommendationException(RecommendationException objRecommendationException);
    
    /**
     * This is abstract function for saving RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of String
     */
    public abstract List<String> SaveRecommendationExplanation(RecommendationExplanation objRecommendationExplanation);
    /**
     * This is abstract function for retrieving RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of RecommendationExplanation
     */
    public abstract List<RecommendationExplanation> RetriveRecommendationExplanation(RecommendationExplanation objRecommendationExplanation);
    /**
     * This is abstract function for updating RecommendationExplanation. 
     * @param objRecommendationExplanation
     * @return List of String
     */
    public abstract List<String> UpdateRecommendationExplanation(RecommendationExplanation objRecommendationExplanation);
      
    /**
     * This is abstract function for saving RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of String
     */
    public abstract List<String> SaveRecommendationFeedback(RecommendationFeedback objRecommendationFeedback);
    /**
     * This is abstract function for retrieving RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of RecommendationFeedback
     */
    public abstract List<RecommendationFeedback> RetriveRecommendationFeedback(RecommendationFeedback objRecommendationFeedback);
    /**
     * This is abstract function for updating RecommendationFeedback. 
     * @param objRecommendationFeedback
     * @return List of String
     */
    public abstract List<String> UpdateRecommendationFeedback(RecommendationFeedback objRecommendationFeedback);
     
    /**
     * This is abstract function for saving ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of String
     */
    public abstract List<String> SaveActivityFeedback(ActivityFeedback objActivityFeedback);
    /**
     * This is abstract function for retrieving ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of ActivityFeedback
     */
    public abstract List<ActivityFeedback> RetriveActivityFeedback(ActivityFeedback objActivityFeedback);
    /**
     * This is abstract function for updating ActivityFeedback. 
     * @param objActivityFeedback
     * @return List of String
     */
    public abstract List<String> UpdateActivityFeedback(ActivityFeedback objActivityFeedback);
     
    /**
     * This is abstract function for saving ExpertReview. 
     * @param objExpertReview
     * @return List of String
     */
    public abstract List<String> SaveExpertReview(ExpertReview objExpertReview);
    /**
     * This is abstract function for retrieving ExpertReview. 
     * @param objExpertReview
     * @return List of ExpertReview
     */
    public abstract List<ExpertReview> RetriveExpertReview(ExpertReview objExpertReview);
    /**
     * This is abstract function for updating ExpertReview. 
     * @param objExpertReview
     * @return List of String
     */
    public abstract List<String> UpdateExpertReview(ExpertReview objExpertReview);
     
    /**
     * This is abstract function for saving UserDisabilities. 
     * @param objUserDisabilities
     * @return List of String
     */
    public abstract List<String> SaveUserDisabilities(UserDisabilities objUserDisabilities);
    /**
     * This is abstract function for updating UserDisabilities. 
     * @param objUserDisabilities
     * @return List of String
     */
    public abstract List<String> UpdateUserDisabilities(UserDisabilities objUserDisabilities);
    /**
     * This is abstract function for retrieving UserDisabilities. 
     * @param objUserDisabilities
     * @return List of UserDisabilities
     */
    public abstract List<UserDisabilities> RetriveUserDisabilities(UserDisabilities objUserDisabilities);
    
    /**
     * This is abstract function for saving UserSchedule. 
     * @param objUserSchedule
     * @return List of String
     */
    public abstract List<String> SaveUserSchedule(UserSchedule objUserSchedule);
    /**
     * This is abstract function for updating UserSchedule. 
     * @param objUserSchedule
     * @return List of String
     */
    public abstract List<String> UpdateUserSchedule(UserSchedule objUserSchedule);
    /**
     * This is abstract function for retrieving UserSchedule.
     * @param objUserSchedule
     * @return List of UserSchedule 
     */
    public abstract List<UserSchedule> RetriveUserSchedule(UserSchedule objUserSchedule);

    /**
     * This is abstract function for saving LifelogMonitorActivity. 
     * @param objLifelogMonitorActivity
     * @return List of LifelogMonitorActivity
     */   
    public abstract List<LifelogMonitorActivity> RetriveLifelogMonitorActivity(LifelogMonitorActivity objLifelogMonitorActivity);
     
    /**
     * This is abstract function for saving Facts. 
     * @param objFacts
     * @return List of String
     */
    public abstract List<String> SaveFacts(Facts objFacts);
    /**
     * This is abstract function for retrieving Facts. 
     * @param objFacts
     * @return List of Facts
     */
    public abstract List<Facts> RetriveFacts(Facts objFacts);
    /**
     * This is abstract function for updating Facts. 
     * @param objFacts
     * @return List of String
     */
    public abstract List<String> UpdateFacts(Facts objFacts);
    
    /**
     * This is abstract function for saving FactsFeedback. 
     * @param objFactsFeedback
     * @return List of String
     */
    public abstract List<String> SaveFactsFeedback(FactsFeedback objFactsFeedback);
    /**
     * This is abstract function for retrieving FactsFeedback.
     * @param objFactsFeedback
     * @return List of FactsFeedback 
     */
    public abstract List<FactsFeedback> RetriveFactsFeedback(FactsFeedback objFactsFeedback);
    /**
     * This is abstract function for updating FactsFeedback. 
     * @param objFactsFeedback
     * @return List of String
     */
    public abstract List<String> UpdateFactsFeedback(FactsFeedback objFactsFeedback);
    
    /**
     * This is abstract function for saving UserRecognizedActivityAccumulate. 
     * @param objUserRecognizedActivityAccumulate
     * @return List of String
     */
    public abstract List<String> SaveUserRecognizedActivityAccumulate(UserRecognizedActivityAccumulate objUserRecognizedActivityAccumulate);
    /**
     * This is abstract function for retrieving UserRecognizedActivityAccumulate. 
     * @param objUserRecognizedActivityAccumulate
     * @return List of RetriveUserRecognizedActivityAccumulate
     */
    public abstract List<UserRecognizedActivityAccumulate> RetriveUserRecognizedActivityAccumulate(UserRecognizedActivityAccumulate objUserRecognizedActivityAccumulate);
    /**
     * This is abstract function for updating UserRecognizedActivityAccumulate.
     * @param objUserRecognizedActivityAccumulate
     * @return List of String 
     */
    public abstract List<String> UpdateUserRecognizedActivityAccumulate(UserRecognizedActivityAccumulate objUserRecognizedActivityAccumulate);
    
    /**
     * This is abstract function for saving Achievements. 
     * @param objAchievements
     * @return List of String
     */
    public abstract List<String> SaveAchievements(Achievements objAchievements);
    /**
     * This is abstract function for retrieving Achievements. 
     * @param objAchievements
     * @return List of Achievements
     */
    public abstract List<Achievements> RetriveAchievements(Achievements objAchievements);
    /**
     * This is abstract function for updating Achievements. 
     * @param objAchievements
     * @return List of String
     */
    public abstract List<String> UpdateAchievements(Achievements objAchievements);
    
    /**
     * This is abstract function for saving UserRecognizedEmotion. 
     * @param objUserRecognizedEmotion
     * @return List of String
     */
    public abstract List<String> SaveUserRecognizedEmotion(UserRecognizedEmotion objUserRecognizedEmotion);
    /**
     * This is abstract function for retrieving UserRecognizedEmotion.
     * @param objUserRecognizedEmotion
     * @return List of RetriveUserRecognizedEmotion 
     */
    public abstract List<UserRecognizedEmotion> RetriveUserRecognizedEmotion(UserRecognizedEmotion objUserRecognizedEmotion);
    
    /**
     * This is abstract function for saving UserRecognizedHLC. 
     * @param objUserRecognizedHLC
     * @return List of String
     */
    public abstract List<String> SaveUserRecognizedHLC(UserRecognizedHLC objUserRecognizedHLC);
    /**
     * This is abstract function for retrieving UserRecognizedHLC.
     * @param objUserRecognizedHLC
     * @return List of UserRecognizedHLC 
     */
    public abstract List<UserRecognizedHLC> RetriveUserRecognizedHLC(UserRecognizedHLC objUserRecognizedHLC);
    
    /**
     * This is abstract function for saving UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of String
     */
    public abstract List<String> SaveUserPreferredLocation(UserPreferredLocation objUserPreferredLocation);
    /**
     * This is abstract function for retrieving UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of UserPreferredLocation
     */
    public abstract List<UserPreferredLocation> RetriveUserPreferredLocation(UserPreferredLocation objUserPreferredLocation);
    /**
     * This is abstract function for updating UserPreferredLocation. 
     * @param objUserPreferredLocation
     * @return List of String
     */
    public abstract List<String> UpdateUserPreferredLocation(UserPreferredLocation objUserPreferredLocation);
    
    /**
     * This is abstract function for saving FoodLog. 
     * @param objFoodLog
     * @return List of String
     */
    public abstract List<String> SaveFoodLog(FoodLog objFoodLog);
    /**
     * This is abstract function for retrieving FoodLog.
     * @param objFoodLog
     * @return List of FoodLog 
     */
    public abstract List<FoodLog> RetriveFoodLog(FoodLog objFoodLog);
}
