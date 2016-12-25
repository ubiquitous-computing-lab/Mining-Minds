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
package org.uclab.mm.datamodel.sc;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Taqdir Ali
 */
@Entity
public class Recommendation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long recommendationID;
    private String recommendationIdentifier;
    private Long situationID;
    private String recommendationDescription;
    private int recommendationTypeID;
    private String conditionValue;
    private int recommendationLevelID;
    private int recommendationStatusID;
    private String recommendationDate;
    
    private String recommendationTypeDescription;
    private String recommendationLevelDescription;
    private String recommendationStatusDescription;
    
    private int situationCategoryID;
    private String situationCategoryDescription;
    private String situationCategoryIDs;
    
    private Long userID;
    private String requestType;
    private String startDate;
    private String endDate;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recommendation)) {
            return false;
        }
        Recommendation other = (Recommendation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.Recommendation[ id=" + id + " ]";
    }

    /**
     * @return the recommendationID
     */
    public Long getRecommendationID() {
        return recommendationID;
    }

    /**
     * @param recommendationID the recommendationID to set
     */
    public void setRecommendationID(Long recommendationID) {
        this.recommendationID = recommendationID;
    }

    /**
     * @return the recommendationIdentifier
     */
    public String getRecommendationIdentifier() {
        return recommendationIdentifier;
    }

    /**
     * @param recommendationIdentifier the recommendationIdentifier to set
     */
    public void setRecommendationIdentifier(String recommendationIdentifier) {
        this.recommendationIdentifier = recommendationIdentifier;
    }

    /**
     * @return the situationID
     */
    public Long getSituationID() {
        return situationID;
    }

    /**
     * @param situationID the situationID to set
     */
    public void setSituationID(Long situationID) {
        this.situationID = situationID;
    }

    /**
     * @return the recommendationDescription
     */
    public String getRecommendationDescription() {
        return recommendationDescription;
    }

    /**
     * @param recommendationDescription the recommendationDescription to set
     */
    public void setRecommendationDescription(String recommendationDescription) {
        this.recommendationDescription = recommendationDescription;
    }

    /**
     * @return the recommendationTypeID
     */
    public int getRecommendationTypeID() {
        return recommendationTypeID;
    }

    /**
     * @param recommendationTypeID the recommendationTypeID to set
     */
    public void setRecommendationTypeID(int recommendationTypeID) {
        this.recommendationTypeID = recommendationTypeID;
    }

    /**
     * @return the conditionValue
     */
    public String getConditionValue() {
        return conditionValue;
    }

    /**
     * @param conditionValue the conditionValue to set
     */
    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    /**
     * @return the recommendationLevelID
     */
    public int getRecommendationLevelID() {
        return recommendationLevelID;
    }

    /**
     * @param recommendationLevelID the recommendationLevelID to set
     */
    public void setRecommendationLevelID(int recommendationLevelID) {
        this.recommendationLevelID = recommendationLevelID;
    }

    /**
     * @return the recommendationStatusID
     */
    public int getRecommendationStatusID() {
        return recommendationStatusID;
    }

    /**
     * @param recommendationStatusID the recommendationStatusID to set
     */
    public void setRecommendationStatusID(int recommendationStatusID) {
        this.recommendationStatusID = recommendationStatusID;
    }

    /**
     * @return the recommendationDate
     */
    public String getRecommendationDate() {
        return recommendationDate;
    }

    /**
     * @param recommendationDate the recommendationDate to set
     */
    public void setRecommendationDate(String recommendationDate) {
        this.recommendationDate = recommendationDate;
    }

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the userID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * @return the recommendationTypeDescription
     */
    public String getRecommendationTypeDescription() {
        return recommendationTypeDescription;
    }

    /**
     * @param recommendationTypeDescription the recommendationTypeDescription to set
     */
    public void setRecommendationTypeDescription(String recommendationTypeDescription) {
        this.recommendationTypeDescription = recommendationTypeDescription;
    }

    /**
     * @return the recommendationLevelDescription
     */
    public String getRecommendationLevelDescription() {
        return recommendationLevelDescription;
    }

    /**
     * @param recommendationLevelDescription the recommendationLevelDescription to set
     */
    public void setRecommendationLevelDescription(String recommendationLevelDescription) {
        this.recommendationLevelDescription = recommendationLevelDescription;
    }

    /**
     * @return the recommendationStatusDescription
     */
    public String getRecommendationStatusDescription() {
        return recommendationStatusDescription;
    }

    /**
     * @param recommendationStatusDescription the recommendationStatusDescription to set
     */
    public void setRecommendationStatusDescription(String recommendationStatusDescription) {
        this.recommendationStatusDescription = recommendationStatusDescription;
    }

    /**
     * @return the situationCategoryID
     */
    public int getSituationCategoryID() {
        return situationCategoryID;
    }

    /**
     * @param situationCategoryID the situationCategoryID to set
     */
    public void setSituationCategoryID(int situationCategoryID) {
        this.situationCategoryID = situationCategoryID;
    }

    /**
     * @return the situationCategoryDescription
     */
    public String getSituationCategoryDescription() {
        return situationCategoryDescription;
    }

    /**
     * @param situationCategoryDescription the situationCategoryDescription to set
     */
    public void setSituationCategoryDescription(String situationCategoryDescription) {
        this.situationCategoryDescription = situationCategoryDescription;
    }

    /**
     * @return the situationCategoryIDs
     */
    public String getSituationCategoryIDs() {
        return situationCategoryIDs;
    }

    /**
     * @param situationCategoryIDs the situationCategoryIDs to set
     */
    public void setSituationCategoryIDs(String situationCategoryIDs) {
        this.situationCategoryIDs = situationCategoryIDs;
    }
    
}
