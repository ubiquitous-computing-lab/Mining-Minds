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
package org.uclab.mm.datamodel.ic;

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
public class UserPreferredLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long userPreferredLocationId;
    private Long userId;
    private Float latitude;
    private Float longitude;
    private String locationLabel;
    private String locationDescription;

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
        if (!(object instanceof UserPreferredLocation)) {
            return false;
        }
        UserPreferredLocation other = (UserPreferredLocation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.ic.UserPreferredLocation[ id=" + id + " ]";
    }

    /**
     * @return the userPreferredLocationId
     */
    public Long getUserPreferredLocationId() {
        return userPreferredLocationId;
    }

    /**
     * @param userPreferredLocationId the userPreferredLocationId to set
     */
    public void setUserPreferredLocationId(Long userPreferredLocationId) {
        this.userPreferredLocationId = userPreferredLocationId;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the latitude
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the locationLabel
     */
    public String getLocationLabel() {
        return locationLabel;
    }

    /**
     * @param locationLabel the locationLabel to set
     */
    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

    /**
     * @return the locationDescription
     */
    public String getLocationDescription() {
        return locationDescription;
    }

    /**
     * @param locationDescription the locationDescription to set
     */
    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }
    
}
