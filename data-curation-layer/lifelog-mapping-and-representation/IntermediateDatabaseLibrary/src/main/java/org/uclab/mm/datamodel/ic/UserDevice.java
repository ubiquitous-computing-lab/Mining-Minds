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
public class UserDevice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long userDeviceID;
    private Long userID;
    private Long deviceID;
    private int SubscriptionStatusID;
    //private GregorianCalendar registerDate; updated
    private String registerDate;

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
        if (!(object instanceof UserDevice)) {
            return false;
        }
        UserDevice other = (UserDevice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.ic.UserDevice[ id=" + id + " ]";
    }

    /**
     * @return the userDeviceID
     */
    public Long getUserDeviceID() {
        return userDeviceID;
    }

    /**
     * @param userDeviceID the userDeviceID to set
     */
    public void setUserDeviceID(Long userDeviceID) {
        this.userDeviceID = userDeviceID;
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
     * @return the deviceID
     */
    public Long getDeviceID() {
        return deviceID;
    }

    /**
     * @param deviceID the deviceID to set
     */
    public void setDeviceID(Long deviceID) {
        this.deviceID = deviceID;
    }

    /**
     * @return the SubscriptionStatusID
     */
    public int getSubscriptionStatusID() {
        return SubscriptionStatusID;
    }

    /**
     * @param SubscriptionStatusID the SubscriptionStatusID to set
     */
    public void setSubscriptionStatusID(int SubscriptionStatusID) {
        this.SubscriptionStatusID = SubscriptionStatusID;
    }

    /**
     * @return the registerDate
     */
    public String getRegisterDate() {
        return registerDate;
    }

    /**
     * @param registerDate the registerDate to set
     */
    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
    
}
