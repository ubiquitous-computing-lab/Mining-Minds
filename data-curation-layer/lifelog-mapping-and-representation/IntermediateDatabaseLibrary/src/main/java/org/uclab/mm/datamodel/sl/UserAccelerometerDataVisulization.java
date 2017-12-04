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
 * @author dcadmin
 */
@Entity
public class UserAccelerometerDataVisulization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // updated Taqdir Ali
    private Long userAccelerometerDataId;
    private Long userDeviceId;
    private Float xCoordinate ;
    private Float yCoordinate ;
    private Float zCoordinate ;
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
        if (!(object instanceof UserAccelerometerDataVisulization)) {
            return false;
        }
        UserAccelerometerDataVisulization other = (UserAccelerometerDataVisulization) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sl.UserrAccelerometerDataVisulization[ id=" + id + " ]";
    }

    /**
     * @return the userAccelerometerDataId
     */
    public Long getUserAccelerometerDataId() {
        return userAccelerometerDataId;
    }

    /**
     * @param userAccelerometerDataId the userAccelerometerDataId to set
     */
    public void setUserAccelerometerDataId(Long userAccelerometerDataId) {
        this.userAccelerometerDataId = userAccelerometerDataId;
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
     * @return the xCoordinate
     */
    public Float getxCoordinate() {
        return xCoordinate;
    }

    /**
     * @param xCoordinate the xCoordinate to set
     */
    public void setxCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * @return the yCoordinate
     */
    public Float getyCoordinate() {
        return yCoordinate;
    }

    /**
     * @param yCoordinate the yCoordinate to set
     */
    public void setyCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * @return the zCoordinate
     */
    public Float getzCoordinate() {
        return zCoordinate;
    }

    /**
     * @param zCoordinate the zCoordinate to set
     */
    public void setzCoordinate(Float zCoordinate) {
        this.zCoordinate = zCoordinate;
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
