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
 * @author Taqdir
 */
@Entity
public class ActivityPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long activityPlanId;
    private Long userGoalId;
    private String planDescription;
    private String explanation; 

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
        if (!(object instanceof ActivityPlan)) {
            return false;
        }
        ActivityPlan other = (ActivityPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.ActivityPlan[ id=" + id + " ]";
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
     * @return the userGoalId
     */
    public Long getUserGoalId() {
        return userGoalId;
    }

    /**
     * @param userGoalId the userGoalId to set
     */
    public void setUserGoalId(Long userGoalId) {
        this.userGoalId = userGoalId;
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
     * @return the explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * @param explanation the explanation to set
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    
}
