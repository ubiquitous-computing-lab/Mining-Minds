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
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Taqdir
 */
@Entity
public class ActivityRecommendation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private Long activityRecommendationId;
    private Long activityPlanId;
    private String description;
    //private GregorianCalendar timestamp;
    private String timestamp;
    private String planDescription;
    private String requestType;
    private Long userID;
    
    private String startTimestamp;
    private String endTimestamp;
    

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
        if (!(object instanceof ActivityRecommendation)) {
            return false;
        }
        ActivityRecommendation other = (ActivityRecommendation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.ActivityRecommendation[ id=" + id + " ]";
    }

    /**
     * @return the activityRecommendationId
     */
    public Long getActivityRecommendationId() {
        return activityRecommendationId;
    }

    /**
     * @param activityRecommendationId the activityRecommendationId to set
     */
    public void setActivityRecommendationId(Long activityRecommendationId) {
        this.activityRecommendationId = activityRecommendationId;
    }

    /**
     * @return the activityPlanId
     */
    public Long getActivityPlanId() {
        return activityPlanId;
    }

    /**
     * @param activityPlanId the activityPlanId to set
     */
    public void setActivityPlanId(Long activityPlanId) {
        this.activityPlanId = activityPlanId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the planDescription
     */
    public String getPlanDescription() {
        return planDescription;
    }

    /**
     * @param planDescription the planDescription to set
     */
    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
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
     * @return the startTimestamp
     */
    public String getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * @param startTimestamp the startTimestamp to set
     */
    public void setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    /**
     * @return the endTimestamp
     */
    public String getEndTimestamp() {
        return endTimestamp;
    }

    /**
     * @param endTimestamp the endTimestamp to set
     */
    public void setEndTimestamp(String endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
    
}
