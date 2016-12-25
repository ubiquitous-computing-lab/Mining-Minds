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
public class Facts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long factID;
    private Long situationID;
    private String factDescription;
    private String supportingLinks;
    private String factDate;
    private int factStatusID;
    
    private String factStatusDescription;
    
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
        if (!(object instanceof Facts)) {
            return false;
        }
        Facts other = (Facts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.Facts[ id=" + id + " ]";
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
     * @return the factDescription
     */
    public String getFactDescription() {
        return factDescription;
    }

    /**
     * @param factDescription the factDescription to set
     */
    public void setFactDescription(String factDescription) {
        this.factDescription = factDescription;
    }

    /**
     * @return the supportingLinks
     */
    public String getSupportingLinks() {
        return supportingLinks;
    }

    /**
     * @param supportingLinks the supportingLinks to set
     */
    public void setSupportingLinks(String supportingLinks) {
        this.supportingLinks = supportingLinks;
    }

    /**
     * @return the factDate
     */
    public String getFactDate() {
        return factDate;
    }

    /**
     * @param factDate the factDate to set
     */
    public void setFactDate(String factDate) {
        this.factDate = factDate;
    }

    /**
     * @return the factStatusID
     */
    public int getFactStatusID() {
        return factStatusID;
    }

    /**
     * @param factStatusID the factStatusID to set
     */
    public void setFactStatusID(int factStatusID) {
        this.factStatusID = factStatusID;
    }

    /**
     * @return the factStatusDescription
     */
    public String getFactStatusDescription() {
        return factStatusDescription;
    }

    /**
     * @param factStatusDescription the factStatusDescription to set
     */
    public void setFactStatusDescription(String factStatusDescription) {
        this.factStatusDescription = factStatusDescription;
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
