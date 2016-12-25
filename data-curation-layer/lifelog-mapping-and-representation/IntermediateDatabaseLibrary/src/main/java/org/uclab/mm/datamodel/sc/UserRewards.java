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
 * @author dcadmin
 */
@Entity
public class UserRewards implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long userRewardID;
    private Long userID;
    private int rewardPoints;
    private String rewardDescription;
    private String rewardDate;
    private int rewardTypeID;
    private String rewardTypeDescription;
    
    

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
        if (!(object instanceof UserRewards)) {
            return false;
        }
        UserRewards other = (UserRewards) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.UserRewards[ id=" + id + " ]";
    }

    /**
     * @return the userRewardID
     */
    public Long getUserRewardID() {
        return userRewardID;
    }

    /**
     * @param userRewardID the userRewardID to set
     */
    public void setUserRewardID(Long userRewardID) {
        this.userRewardID = userRewardID;
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
     * @return the rewardPoints
     */
    public int getRewardPoints() {
        return rewardPoints;
    }

    /**
     * @param rewardPoints the rewardPoints to set
     */
    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    /**
     * @return the rewardDescription
     */
    public String getRewardDescription() {
        return rewardDescription;
    }

    /**
     * @param rewardDescription the rewardDescription to set
     */
    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }

    /**
     * @return the rewardDate
     */
    public String getRewardDate() {
        return rewardDate;
    }

    /**
     * @param rewardDate the rewardDate to set
     */
    public void setRewardDate(String rewardDate) {
        this.rewardDate = rewardDate;
    }

    /**
     * @return the rewardTypeID
     */
    public int getRewardTypeID() {
        return rewardTypeID;
    }

    /**
     * @param rewardTypeID the rewardTypeID to set
     */
    public void setRewardTypeID(int rewardTypeID) {
        this.rewardTypeID = rewardTypeID;
    }

    /**
     * @return the rewardTypeDescription
     */
    public String getRewardTypeDescription() {
        return rewardTypeDescription;
    }

    /**
     * @param rewardTypeDescription the rewardTypeDescription to set
     */
    public void setRewardTypeDescription(String rewardTypeDescription) {
        this.rewardTypeDescription = rewardTypeDescription;
    }
    
}
