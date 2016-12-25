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
import java.util.Date;
import java.util.GregorianCalendar;
/**
 *
 * @author Taqdir
 */
@Entity
public class PhysiologicalFactors implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private Long physiologicalFactorId;
    private Long userId;
    private float weight;
    private float height;
    //private GregorianCalendar date; //updated
    private String date;
    private float idealWeight;
    private float targetWeight;
    
    
    
    

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
        if (!(object instanceof PhysiologicalFactors)) {
            return false;
        }
        PhysiologicalFactors other = (PhysiologicalFactors) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.dc.PhysiologicalFactors[ id=" + id + " ]";
    }

    /**
     * @return the physiologicalFactorId
     */
    public Long getPhysiologicalFactorId() {
        return physiologicalFactorId;
    }

    /**
     * @param physiologicalFactorId the physiologicalFactorId to set
     */
    public void setPhysiologicalFactorId(Long physiologicalFactorId) {
        this.physiologicalFactorId = physiologicalFactorId;
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
     * @return the phyFactorDate updated
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the phyFactorDate to set
     */
    public void setDate(String date) {
        this.date = date;
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

    /**
     * @return the idealWeight
     */
    public float getIdealWeight() {
        return idealWeight;
    }

    /**
     * @param idealWeight the idealWeight to set
     */
    public void setIdealWeight(float idealWeight) {
        this.idealWeight = idealWeight;
    }

    /**
     * @return the targetWeight
     */
    public float getTargetWeight() {
        return targetWeight;
    }

    /**
     * @param targetWeight the targetWeight to set
     */
    public void setTargetWeight(float targetWeight) {
        this.targetWeight = targetWeight;
    }
    
}
