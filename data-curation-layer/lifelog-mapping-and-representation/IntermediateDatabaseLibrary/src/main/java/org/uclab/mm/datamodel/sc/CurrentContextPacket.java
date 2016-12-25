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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.uclab.mm.datamodel.ic.FoodLog;
import org.uclab.mm.datamodel.ic.UserDetectedLocation;
import org.uclab.mm.datamodel.ic.UserRecognizedActivity;
import org.uclab.mm.datamodel.ic.UserRecognizedEmotion;
import org.uclab.mm.datamodel.ic.UserRecognizedHLC;

/**
 *
 * @author Taqdir Ali
 */
@Entity
public class CurrentContextPacket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long userID;
    private String startTime;
    private String endTime;
    
    private List<UserRecognizedActivity> objListUserRecognizedActivity = new ArrayList<UserRecognizedActivity>();
    private List<UserDetectedLocation> objListUserDetectedLocation = new ArrayList<UserDetectedLocation>();
    private List<UserRecognizedEmotion> objListUserRecognizedEmotion = new ArrayList<UserRecognizedEmotion>();
    private List<UserRecognizedHLC> objListUserRecognizedHLC = new ArrayList<UserRecognizedHLC>();
    private List<FoodLog>  objListFoodLog = new ArrayList<FoodLog>();
    

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
        if (!(object instanceof CurrentContextPacket)) {
            return false;
        }
        CurrentContextPacket other = (CurrentContextPacket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.CurrentContextPacket[ id=" + id + " ]";
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
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the objListUserRecognizedActivity
     */
    public List<UserRecognizedActivity> getObjListUserRecognizedActivity() {
        return objListUserRecognizedActivity;
    }

    /**
     * @param objListUserRecognizedActivity the objListUserRecognizedActivity to set
     */
    public void setObjListUserRecognizedActivity(List<UserRecognizedActivity> objListUserRecognizedActivity) {
        this.objListUserRecognizedActivity = objListUserRecognizedActivity;
    }

    /**
     * @return the objListUserDetectedLocation
     */
    public List<UserDetectedLocation> getObjListUserDetectedLocation() {
        return objListUserDetectedLocation;
    }

    /**
     * @param objListUserDetectedLocation the objListUserDetectedLocation to set
     */
    public void setObjListUserDetectedLocation(List<UserDetectedLocation> objListUserDetectedLocation) {
        this.objListUserDetectedLocation = objListUserDetectedLocation;
    }

    /**
     * @return the objListUserRecognizedEmotion
     */
    public List<UserRecognizedEmotion> getObjListUserRecognizedEmotion() {
        return objListUserRecognizedEmotion;
    }

    /**
     * @param objListUserRecognizedEmotion the objListUserRecognizedEmotion to set
     */
    public void setObjListUserRecognizedEmotion(List<UserRecognizedEmotion> objListUserRecognizedEmotion) {
        this.objListUserRecognizedEmotion = objListUserRecognizedEmotion;
    }

    /**
     * @return the objListUserRecognizedHLC
     */
    public List<UserRecognizedHLC> getObjListUserRecognizedHLC() {
        return objListUserRecognizedHLC;
    }

    /**
     * @param objListUserRecognizedHLC the objListUserRecognizedHLC to set
     */
    public void setObjListUserRecognizedHLC(List<UserRecognizedHLC> objListUserRecognizedHLC) {
        this.objListUserRecognizedHLC = objListUserRecognizedHLC;
    }

    /**
     * @return the objListFoodLog
     */
    public List<FoodLog> getObjListFoodLog() {
        return objListFoodLog;
    }

    /**
     * @param objListFoodLog the objListFoodLog to set
     */
    public void setObjListFoodLog(List<FoodLog> objListFoodLog) {
        this.objListFoodLog = objListFoodLog;
    }
    
}
