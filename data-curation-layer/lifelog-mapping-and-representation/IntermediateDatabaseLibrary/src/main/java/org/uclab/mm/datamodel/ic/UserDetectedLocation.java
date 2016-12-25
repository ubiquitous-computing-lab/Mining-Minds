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
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import net.sourceforge.jtds.jdbc.DateTime;


/**
 *
 * @author Taqdir
 */
@Entity
public class UserDetectedLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
     private Long userDetectedLocationId;
    private Long userId;
    private String locationLabel;
   // private GregorianCalendar startTime;
   // private GregorianCalendar endTime;
     private String startTime;
    private String endTime;
    private Long duration;
      
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
        if (!(object instanceof UserDetectedLocation)) {
            return false;
        }
        UserDetectedLocation other = (UserDetectedLocation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.ic.UserDetectedLocation[ id=" + id + " ]";
    }

    /**
     * @return the userDetectedLocationId
     */
    public Long getUserDetectedLocationId() {
        return userDetectedLocationId;
    }

    /**
     * @param userDetectedLocationId the userDetectedLocationId to set
     */
    public void setUserDetectedLocationId(Long userDetectedLocationId) {
        this.userDetectedLocationId = userDetectedLocationId;
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
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the duration
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Long duration) {
        this.duration = duration;
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
    
}
