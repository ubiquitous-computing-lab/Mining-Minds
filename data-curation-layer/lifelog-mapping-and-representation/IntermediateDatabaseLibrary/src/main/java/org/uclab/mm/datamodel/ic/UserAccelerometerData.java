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
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Taqdir
 */
@Entity
public class UserAccelerometerData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userAccelerometerDataId;
    private Long userDeviceId;
    private List<Float> xCoordinate = new ArrayList<Float>();
    private List<Float> yCoordinate = new ArrayList<Float>();
    private List<Float> zCoordinate = new ArrayList<Float>();
    //private GregorianCalendar timeStamp; updated
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
        if (!(object instanceof UserAccelerometerData)) {
            return false;
        }
        UserAccelerometerData other = (UserAccelerometerData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.ic.UserAccelerometerData[ id=" + id + " ]";
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
     * @return the timeStamp
     */ 
    // Updated one.
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
  /*  public void setTimeStamp(GregorianCalendar timeStamp) {  // updated
        this.timeStamp = timeStamp;
    }*/
     public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the xCoordinate
     */
    public List<Float> getxCoordinate() {
        return xCoordinate;
    }

    /**
     * @param xCoordinate the xCoordinate to set
     */
    public void setxCoordinate(List<Float> xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * @return the yCoordinate
     */
    public List<Float> getyCoordinate() {
        return yCoordinate;
    }

    /**
     * @param yCoordinate the yCoordinate to set
     */
    public void setyCoordinate(List<Float> yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * @return the zCoordinate
     */
    public List<Float> getzCoordinate() {
        return zCoordinate;
    }

    /**
     * @param zCoordinate the zCoordinate to set
     */
    public void setzCoordinate(List<Float> zCoordinate) {
        this.zCoordinate = zCoordinate;
    }
    
}
