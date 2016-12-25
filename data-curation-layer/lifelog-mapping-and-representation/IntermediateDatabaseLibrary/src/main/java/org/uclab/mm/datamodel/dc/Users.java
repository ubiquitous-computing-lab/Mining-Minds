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
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private Long userID;
    private String firstName;
    private String lastName;
    private String middleName;
    private int genderId;
    //private GregorianCalendar dateOfBirth; updated
    private String dateOfBirth;
    private String contactNumber;
    private String emailAddress;
    private int martialStatusId;
    private int activityLevelId;
    private String activityLevelDescription;
    private int occupationId;
    private String password;
    private String requestType;
    
    private int userTypeID ;
    private String userTypeDescription;
    private String genderDescription;
    private String maritalStatusDescription;
    private String occupationDescription;
    

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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.dc.Users[ id=" + id + " ]";
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the genderId
     */
    public int getGenderId() {
        return genderId;
    }

    /**
     * @param genderId the genderId to set
     */
    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    /**
     * @return the dateOfBirth updated
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the martialStatusId
     */
    public int getMartialStatusId() {
        return martialStatusId;
    }

    /**
     * @param martialStatusId the martialStatusId to set
     */
    public void setMartialStatusId(int martialStatusId) {
        this.martialStatusId = martialStatusId;
    }

    /**
     * @return the activityLevelId
     */
    public int getActivityLevelId() {
        return activityLevelId;
    }

    /**
     * @param activityLevelId the activityLevelId to set
     */
    public void setActivityLevelId(int activityLevelId) {
        this.activityLevelId = activityLevelId;
    }

    /**
     * @return the occupationId
     */
    public int getOccupationId() {
        return occupationId;
    }

    /**
     * @param occupationId the occupationId to set
     */
    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the activityLevelDescription
     */
    public String getActivityLevelDescription() {
        return activityLevelDescription;
    }

    /**
     * @param activityLevelDescription the activityLevelDescription to set
     */
    public void setActivityLevelDescription(String activityLevelDescription) {
        this.activityLevelDescription = activityLevelDescription;
    }

    /**
     * @return the userTypeID
     */
    public int getUserTypeID() {
        return userTypeID;
    }

    /**
     * @param userTypeID the userTypeID to set
     */
    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }

    /**
     * @return the userTypeDescription
     */
    public String getUserTypeDescription() {
        return userTypeDescription;
    }

    /**
     * @param userTypeDescription the userTypeDescription to set
     */
    public void setUserTypeDescription(String userTypeDescription) {
        this.userTypeDescription = userTypeDescription;
    }

    /**
     * @return the genderDescription
     */
    public String getGenderDescription() {
        return genderDescription;
    }

    /**
     * @param genderDescription the genderDescription to set
     */
    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    /**
     * @return the maritalStatusDescription
     */
    public String getMaritalStatusDescription() {
        return maritalStatusDescription;
    }

    /**
     * @param maritalStatusDescription the maritalStatusDescription to set
     */
    public void setMaritalStatusDescription(String maritalStatusDescription) {
        this.maritalStatusDescription = maritalStatusDescription;
    }

    /**
     * @return the occupationDescription
     */
    public String getOccupationDescription() {
        return occupationDescription;
    }

    /**
     * @param occupationDescription the occupationDescription to set
     */
    public void setOccupationDescription(String occupationDescription) {
        this.occupationDescription = occupationDescription;
    }

    
}
