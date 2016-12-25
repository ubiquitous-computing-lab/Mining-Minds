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
public class FactsFeedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long factsFeedbackID;
    private Long factID;
    private Long userID;
    private int rate;
    private String reason;
    private String feedbackDate;
    
    private String requestType;
    private int situationCategoryID;
    private String situationCategoryDescription;
    
    

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
        if (!(object instanceof FactsFeedback)) {
            return false;
        }
        FactsFeedback other = (FactsFeedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sl.FactsFeedback[ id=" + id + " ]";
    }

    /**
     * @return the factsFeedbackID
     */
    public Long getFactsFeedbackID() {
        return factsFeedbackID;
    }

    /**
     * @param factsFeedbackID the factsFeedbackID to set
     */
    public void setFactsFeedbackID(Long factsFeedbackID) {
        this.factsFeedbackID = factsFeedbackID;
    }

    /**
     * @return the factID
     */
    public Long getFactID() {
        return factID;
    }

    /**
     * @param factID the factID to set
     */
    public void setFactID(Long factID) {
        this.factID = factID;
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
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the feedbackDate
     */
    public String getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * @param feedbackDate the feedbackDate to set
     */
    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
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
    
}
