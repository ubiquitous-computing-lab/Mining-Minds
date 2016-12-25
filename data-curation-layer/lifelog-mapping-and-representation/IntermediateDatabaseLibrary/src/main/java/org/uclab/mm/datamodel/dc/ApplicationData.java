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
import org.uclab.mm.datamodel.ic.UserAccelerometerData;
import org.uclab.mm.datamodel.ic.UserGPSData;

/**
 *
 * @author Taqdir
 */
@Entity
public class ApplicationData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    UserGPSData objUserGPSData = new UserGPSData();
    UserAccelerometerData objUserAccelerometerData = new UserAccelerometerData();

    public UserGPSData getObjUserGPSData() {
        return objUserGPSData;
    }

    public void setObjUserGPSData(UserGPSData objUserGPSData) {
        this.objUserGPSData = objUserGPSData;
    }

    public UserAccelerometerData getObjUserAccelerometerData() {
        return objUserAccelerometerData;
    }

    public void setObjUserAccelerometerData(UserAccelerometerData objUserAccelerometerData) {
        this.objUserAccelerometerData = objUserAccelerometerData;
    }

     
    

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
        if (!(object instanceof ApplicationData)) {
            return false;
        }
        ApplicationData other = (ApplicationData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.dc.ApplicationData[ id=" + id + " ]";
    }
    
}
