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
package org.uclab.mm.datamodel.sl;

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
public class ExpertReview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long expertReviewID;
    private Long userID;
    private Long userExpertID;
    private String reviewDescription;
    private String reviewDate;
    private int reviewStatusID;
    
    private String startDate;
    private String endDate;

    private String requestType;

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
        if (!(object instanceof ExpertReview)) {
            return false;
        }
        ExpertReview other = (ExpertReview) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sl.ExpertReview[ id=" + id + " ]";
    }

    /**
     * @return the expertReviewID
     */
    public Long getExpertReviewID() {
        return expertReviewID;
    }

    /**
     * @param expertReviewID the expertReviewID to set
     */
    public void setExpertReviewID(Long expertReviewID) {
        this.expertReviewID = expertReviewID;
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
     * @return the userExpertID
     */
    public Long getUserExpertID() {
        return userExpertID;
    }

    /**
     * @param userExpertID the userExpertID to set
     */
    public void setUserExpertID(Long userExpertID) {
        this.userExpertID = userExpertID;
    }

    /**
     * @return the reviewDescription
     */
    public String getReviewDescription() {
        return reviewDescription;
    }

    /**
     * @param reviewDescription the reviewDescription to set
     */
    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    /**
     * @return the reviewDate
     */
    public String getReviewDate() {
        return reviewDate;
    }

    /**
     * @param reviewDate the reviewDate to set
     */
    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
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
     * @return the reviewStatusID
     */
    public int getReviewStatusID() {
        return reviewStatusID;
    }

    /**
     * @param reviewStatusID the reviewStatusID to set
     */
    public void setReviewStatusID(int reviewStatusID) {
        this.reviewStatusID = reviewStatusID;
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

    
}
