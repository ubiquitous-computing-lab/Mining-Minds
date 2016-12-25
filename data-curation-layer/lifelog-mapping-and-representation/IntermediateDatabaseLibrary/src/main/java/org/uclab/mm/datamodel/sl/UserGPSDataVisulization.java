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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author dcadmin
 */
@Entity
public class UserGPSDataVisulization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long userGPSDataId;
    private Long userDeviceId;
   
    private Float latitude;
    private Float longitude;
    private Float speed;
    private String timeStampStart;
    private String timeStampEnd;
    private String timeStamp;

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
        if (!(object instanceof UserGPSDataVisulization)) {
            return false;
        }
        UserGPSDataVisulization other = (UserGPSDataVisulization) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sl.UserGPSDataVisulization[ id=" + id + " ]";
    }

    /**
     * @return the userGPSDataId
     */
    public Long getUserGPSDataId() {
        return userGPSDataId;
    }

    /**
     * @param userGPSDataId the userGPSDataId to set
     */
    public void setUserGPSDataId(Long userGPSDataId) {
        this.userGPSDataId = userGPSDataId;
    }

    /**
     * @return the userDeviceId
     */
    public Long getUserDeviceId() {
        return userDeviceId;
    }

    /**
     * @param userDeviceId the userDeviceId to set
     */
    public void setUserDeviceId(Long userDeviceId) {
        this.userDeviceId = userDeviceId;
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
     * @return the speed
     */
    public Float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    /**
     * @return the timeStampStart
     */
    public String getTimeStampStart() {
        return timeStampStart;
    }

    /**
     * @param timeStampStart the timeStampStart to set
     */
    public void setTimeStampStart(String timeStampStart) {
        this.timeStampStart = timeStampStart;
    }

    /**
     * @return the timeStampEnd
     */
    public String getTimeStampEnd() {
        return timeStampEnd;
    }

    /**
     * @param timeStampEnd the timeStampEnd to set
     */
    public void setTimeStampEnd(String timeStampEnd) {
        this.timeStampEnd = timeStampEnd;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

   
   
    
}
