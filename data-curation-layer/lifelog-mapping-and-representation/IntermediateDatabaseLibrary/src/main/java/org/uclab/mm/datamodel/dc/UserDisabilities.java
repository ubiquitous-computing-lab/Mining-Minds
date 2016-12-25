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
package org.uclab.mm.datamodel.dc;

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
public class UserDisabilities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private  Long userDisabilityID;
    private  Long userID;
    private  int disabilityID;
    private  int  statusID;
    
    private String disabilityDescription;
    private String statusDescription;
    
    
    

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
        if (!(object instanceof UserDisabilities)) {
            return false;
        }
        UserDisabilities other = (UserDisabilities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.dc.UserDisabilities[ id=" + id + " ]";
    }

    /**
     * @return the userDisabilityID
     */
    public Long getUserDisabilityID() {
        return userDisabilityID;
    }

    /**
     * @param userDisabilityID the userDisabilityID to set
     */
    public void setUserDisabilityID(Long userDisabilityID) {
        this.userDisabilityID = userDisabilityID;
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
     * @return the disabilityID
     */
    public int getDisabilityID() {
        return disabilityID;
    }

    /**
     * @param disabilityID the disabilityID to set
     */
    public void setDisabilityID(int disabilityID) {
        this.disabilityID = disabilityID;
    }

    /**
     * @return the statusID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the disabilityDescription
     */
    public String getDisabilityDescription() {
        return disabilityDescription;
    }

    /**
     * @param disabilityDescription the disabilityDescription to set
     */
    public void setDisabilityDescription(String disabilityDescription) {
        this.disabilityDescription = disabilityDescription;
    }

    /**
     * @return the statusDescription
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * @param statusDescription the statusDescription to set
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    
}
