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
package org.uclab.mm.datamodel.sc;

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
public class RecommendationExplanation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long recommendationExplanationID;
    private Long recommendationID;
    private String factExplanation;
    private int factCategoryID;
    private String factCategoryDescription;
    

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
        if (!(object instanceof RecommendationExplanation)) {
            return false;
        }
        RecommendationExplanation other = (RecommendationExplanation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.RecommendationExplanation[ id=" + id + " ]";
    }

    /**
     * @return the recommendationExplanationID
     */
    public Long getRecommendationExplanationID() {
        return recommendationExplanationID;
    }

    /**
     * @param recommendationExplanationID the recommendationExplanationID to set
     */
    public void setRecommendationExplanationID(Long recommendationExplanationID) {
        this.recommendationExplanationID = recommendationExplanationID;
    }

    /**
     * @return the recommendationID
     */
    public Long getRecommendationID() {
        return recommendationID;
    }

    /**
     * @param recommendationID the recommendationID to set
     */
    public void setRecommendationID(Long recommendationID) {
        this.recommendationID = recommendationID;
    }

    /**
     * @return the factExplanation
     */
    public String getFactExplanation() {
        return factExplanation;
    }

    /**
     * @param factExplanation the factExplanation to set
     */
    public void setFactExplanation(String factExplanation) {
        this.factExplanation = factExplanation;
    }

    /**
     * @return the factCategoryID
     */
    public int getFactCategoryID() {
        return factCategoryID;
    }

    /**
     * @param factCategoryID the factCategoryID to set
     */
    public void setFactCategoryID(int factCategoryID) {
        this.factCategoryID = factCategoryID;
    }

    /**
     * @return the factCategoryDescription
     */
    public String getFactCategoryDescription() {
        return factCategoryDescription;
    }

    /**
     * @param factCategoryDescription the factCategoryDescription to set
     */
    public void setFactCategoryDescription(String factCategoryDescription) {
        this.factCategoryDescription = factCategoryDescription;
    }
    
}
