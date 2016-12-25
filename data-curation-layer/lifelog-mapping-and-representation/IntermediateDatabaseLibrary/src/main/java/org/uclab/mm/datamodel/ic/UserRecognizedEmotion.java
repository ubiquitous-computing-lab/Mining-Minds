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
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import net.sourceforge.jtds.jdbc.DateTime;
/**
 *
 * @author Taqdir
 */
public class UserRecognizedEmotion implements Serializable {
    
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long userRecognizedEmotionId;
    private Long userId;
    private String emotionLabel;
    private String startTime;
    private String endTime;
    private Long duration;
    
    private String requestType;

    /**
     * @return the userRecognizedEmotionId
     */
    public Long getUserRecognizedEmotionId() {
        return userRecognizedEmotionId;
    }

    /**
     * @param userRecognizedEmotionId the userRecognizedEmotionId to set
     */
    public void setUserRecognizedEmotionId(Long userRecognizedEmotionId) {
        this.userRecognizedEmotionId = userRecognizedEmotionId;
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
     * @return the duration
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * @return the emotionLabel
     */
    public String getEmotionLabel() {
        return emotionLabel;
    }

    /**
     * @param emotionLabel the emotionLabel to set
     */
    public void setEmotionLabel(String emotionLabel) {
        this.emotionLabel = emotionLabel;
    }

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    
}
