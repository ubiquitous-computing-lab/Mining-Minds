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
public class RecommendationException implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long recommendationExceptionID;
    private Long recommendationID;
    private String exception;
    private String customRule;
    private String exceptionReason;
    
    
    

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
        if (!(object instanceof RecommendationException)) {
            return false;
        }
        RecommendationException other = (RecommendationException) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.RecommendationException[ id=" + id + " ]";
    }

    /**
     * @return the recommendationExceptionID
     */
    public Long getRecommendationExceptionID() {
        return recommendationExceptionID;
    }

    /**
     * @param recommendationExceptionID the recommendationExceptionID to set
     */
    public void setRecommendationExceptionID(Long recommendationExceptionID) {
        this.recommendationExceptionID = recommendationExceptionID;
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
     * @return the exception
     */
    public String getException() {
        return exception;
    }

    /**
     * @param exception the exception to set
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    /**
     * @return the customRule
     */
    public String getCustomRule() {
        return customRule;
    }

    /**
     * @param customRule the customRule to set
     */
    public void setCustomRule(String customRule) {
        this.customRule = customRule;
    }

    /**
     * @return the exceptionReason
     */
    public String getExceptionReason() {
        return exceptionReason;
    }

    /**
     * @param exceptionReason the exceptionReason to set
     */
    public void setExceptionReason(String exceptionReason) {
        this.exceptionReason = exceptionReason;
    }
    
}
