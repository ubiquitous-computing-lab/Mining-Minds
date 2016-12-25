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
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Taqdir
 */
@Entity
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private Long deviceId;
    private String deviceName;
    private int deviceTypeId;
    private String deviceModel;
    //private GregorianCalendar registrationDate; // updated
    private String registrationDate;
    
    

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
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.ic.Device[ id=" + id + " ]";
    }

    /**
     * @return the deviceId
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * @return the deviceTypeId
     */
    public int getDeviceTypeId() {
        return deviceTypeId;
    }

    /**
     * @param deviceTypeId the deviceTypeId to set
     */
    public void setDeviceTypeId(int deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    /**
     * @return the deviceModel
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * @param deviceModel the deviceModel to set
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    /**
     * @return the registrationDate
     * updated to string
     */
    public String getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate the registrationDate to set
     * updated to string
     */
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}
