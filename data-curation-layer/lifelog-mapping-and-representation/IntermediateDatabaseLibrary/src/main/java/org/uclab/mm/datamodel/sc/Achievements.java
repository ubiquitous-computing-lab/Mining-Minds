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
public class Achievements implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long achievementID;
    private Long userID;
    private String achievementValue;
    private String achievementDescription;
    private String achievementDate;
    private String supportingLink;
    private int achievementStatusID;
    
    private String achievementStatusDescription;
    
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
        if (!(object instanceof Achievements)) {
            return false;
        }
        Achievements other = (Achievements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.Achievements[ id=" + id + " ]";
    }

    /**
     * @return the achievementID
     */
    public Long getAchievementID() {
        return achievementID;
    }

    /**
     * @param achievementID the achievementID to set
     */
    public void setAchievementID(Long achievementID) {
        this.achievementID = achievementID;
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
     * @return the achievementValue
     */
    public String getAchievementValue() {
        return achievementValue;
    }

    /**
     * @param achievementValue the achievementValue to set
     */
    public void setAchievementValue(String achievementValue) {
        this.achievementValue = achievementValue;
    }

    /**
     * @return the achievementDescription
     */
    public String getAchievementDescription() {
        return achievementDescription;
    }

    /**
     * @param achievementDescription the achievementDescription to set
     */
    public void setAchievementDescription(String achievementDescription) {
        this.achievementDescription = achievementDescription;
    }

    /**
     * @return the achievementDate
     */
    public String getAchievementDate() {
        return achievementDate;
    }

    /**
     * @param achievementDate the achievementDate to set
     */
    public void setAchievementDate(String achievementDate) {
        this.achievementDate = achievementDate;
    }

    /**
     * @return the supportingLink
     */
    public String getSupportingLink() {
        return supportingLink;
    }

    /**
     * @param supportingLink the supportingLink to set
     */
    public void setSupportingLink(String supportingLink) {
        this.supportingLink = supportingLink;
    }

    /**
     * @return the achievementStatusID
     */
    public int getAchievementStatusID() {
        return achievementStatusID;
    }

    /**
     * @param achievementStatusID the achievementStatusID to set
     */
    public void setAchievementStatusID(int achievementStatusID) {
        this.achievementStatusID = achievementStatusID;
    }

    /**
     * @return the achievementStatusDescription
     */
    public String getAchievementStatusDescription() {
        return achievementStatusDescription;
    }

    /**
     * @param achievementStatusDescription the achievementStatusDescription to set
     */
    public void setAchievementStatusDescription(String achievementStatusDescription) {
        this.achievementStatusDescription = achievementStatusDescription;
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
    
}
