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
public class ProfileData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
     private Long userID;
    // private GregorianCalendar dateOfBirth;
      private String dateOfBirth;
     private int genderId;
     private String genderDescription;
     private int activityLevelId;
     private String activityLevelDescription;
    private float weight;
    private float height;

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
        if (!(object instanceof ProfileData)) {
            return false;
        }
        ProfileData other = (ProfileData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.ProfileData[ id=" + id + " ]";
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
     * @return the dateOfBirth updated
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the genderId
     */
    public int getGenderId() {
        return genderId;
    }

    /**
     * @param genderId the genderId to set
     */
    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    /**
     * @return the genderDescription
     */
    public String getGenderDescription() {
        return genderDescription;
    }

    /**
     * @param genderDescription the genderDescription to set
     */
    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    /**
     * @return the activityLevelId
     */
    public int getActivityLevelId() {
        return activityLevelId;
    }

    /**
     * @param activityLevelId the activityLevelId to set
     */
    public void setActivityLevelId(int activityLevelId) {
        this.activityLevelId = activityLevelId;
    }

    /**
     * @return the activityLevelDescription
     */
    public String getActivityLevelDescription() {
        return activityLevelDescription;
    }

    /**
     * @param activityLevelDescription the activityLevelDescription to set
     */
    public void setActivityLevelDescription(String activityLevelDescription) {
        this.activityLevelDescription = activityLevelDescription;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }
    
}
