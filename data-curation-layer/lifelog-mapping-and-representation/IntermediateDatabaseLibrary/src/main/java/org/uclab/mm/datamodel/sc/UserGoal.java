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
public class UserGoal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private Long userGoalId;
    private Long userId;
    private int weightStatusId;
    private Long dailyCaloriesInTake;
    private float idealWeight;
    private String goalDescription;
    private Long totalCaloriesToBurn;
    private int burnedCalories;
    //private GregorianCalendar date;
    private String date;
    private int dailyBurnedCal;
    private int weklyBurnedCal;
    private int monthlyBurnedCal;
    private int quarterlyBurnedCal;
    private float bMI;
    
    private String requestType;
    private String startDate;
    private String endDate;
    
    
        

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
        if (!(object instanceof UserGoal)) {
            return false;
        }
        UserGoal other = (UserGoal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.UserGoal[ id=" + id + " ]";
    }

    /**
     * @return the userGoalId
     */
    public Long getUserGoalId() {
        return userGoalId;
    }

    /**
     * @param userGoalId the userGoalId to set
     */
    public void setUserGoalId(Long userGoalId) {
        this.userGoalId = userGoalId;
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
     * @return the weightStatusId
     */
    public int getWeightStatusId() {
        return weightStatusId;
    }

    /**
     * @param weightStatusId the weightStatusId to set
     */
    public void setWeightStatusId(int weightStatusId) {
        this.weightStatusId = weightStatusId;
    }

    /**
     * @return the dailyCaloriesInTake
     */
    public Long getDailyCaloriesInTake() {
        return dailyCaloriesInTake;
    }

    /**
     * @param dailyCaloriesInTake the dailyCaloriesInTake to set
     */
    public void setDailyCaloriesInTake(Long dailyCaloriesInTake) {
        this.dailyCaloriesInTake = dailyCaloriesInTake;
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
     * @return the goalDescription
     */
    public String getGoalDescription() {
        return goalDescription;
    }

    /**
     * @param goalDescription the goalDescription to set
     */
    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    /**
     * @return the totalCaloriesToBurn
     */
    public Long getTotalCaloriesToBurn() {
        return totalCaloriesToBurn;
    }

    /**
     * @param totalCaloriesToBurn the totalCaloriesToBurn to set
     */
    public void setTotalCaloriesToBurn(Long totalCaloriesToBurn) {
        this.totalCaloriesToBurn = totalCaloriesToBurn;
    }

    /**
     * @return the date updated
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the dailyBurnedCal
     */
    public int getDailyBurnedCal() {
        return dailyBurnedCal;
    }

    /**
     * @param dailyBurnedCal the dailyBurnedCal to set
     */
    public void setDailyBurnedCal(int dailyBurnedCal) {
        this.dailyBurnedCal = dailyBurnedCal;
    }

    /**
     * @return the weklyBurnedCal
     */
    public int getWeklyBurnedCal() {
        return weklyBurnedCal;
    }

    /**
     * @param weklyBurnedCal the weklyBurnedCal to set
     */
    public void setWeklyBurnedCal(int weklyBurnedCal) {
        this.weklyBurnedCal = weklyBurnedCal;
    }

    /**
     * @return the monthlyBurnedCal
     */
    public int getMonthlyBurnedCal() {
        return monthlyBurnedCal;
    }

    /**
     * @param monthlyBurnedCal the monthlyBurnedCal to set
     */
    public void setMonthlyBurnedCal(int monthlyBurnedCal) {
        this.monthlyBurnedCal = monthlyBurnedCal;
    }

    /**
     * @return the quarterlyBurnedCal
     */
    public int getQuarterlyBurnedCal() {
        return quarterlyBurnedCal;
    }

    /**
     * @param quarterlyBurnedCal the quarterlyBurnedCal to set
     */
    public void setQuarterlyBurnedCal(int quarterlyBurnedCal) {
        this.quarterlyBurnedCal = quarterlyBurnedCal;
    }

    /**
     * @return the burnedCalories
     */
    public int getBurnedCalories() {
        return burnedCalories;
    }

    /**
     * @param burnedCalories the burnedCalories to set
     */
    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    /**
     * @return the bMI
     */
    public float getbMI() {
        return bMI;
    }

    /**
     * @param bMI the bMI to set
     */
    public void setbMI(float bMI) {
        this.bMI = bMI;
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

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
}
