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
public class Situation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private long userID;
    private String situationCategoryDescription;
    private String situationDescription;
    private String situationDate;
    private Long situationID;
    private int situationCategoryID;
    private String requestType;
    private String startDate;
    private String endDate;
    private int timeInterval;
    

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
        if (!(object instanceof Situation)) {
            return false;
        }
        Situation other = (Situation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.Situation[ id=" + id + " ]";
    }

    /**
     * @return the userID
     */
    public long getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(long userID) {
        this.userID = userID;
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
     * @return the situationDescription
     */
    public String getSituationDescription() {
        return situationDescription;
    }

    /**
     * @param situationDescription the situationDescription to set
     */
    public void setSituationDescription(String situationDescription) {
        this.situationDescription = situationDescription;
    }

    /**
     * @return the situationDate
     */
    public String getSituationDate() {
        return situationDate;
    }

    /**
     * @param situationDate the situationDate to set
     */
    public void setSituationDate(String situationDate) {
        this.situationDate = situationDate;
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
     * @return the timeInterval
     */
    public int getTimeInterval() {
        return timeInterval;
    }

    /**
     * @param timeInterval the timeInterval to set
     */
    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }
    
}
