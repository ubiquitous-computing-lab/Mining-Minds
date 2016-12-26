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
package org.uclab.mm.kcl.edkat.datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;


/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Taqdir Ali
 *
 */

@Entity
@Table(name="tblWellnessConceptsRelationships")
@Proxy(lazy=false)
public class WellnessConceptsRelationships {

	@Id
    @Column(name="WellnessConceptsRelationshipID")
    private int wellnessConceptsRelationshipID;
	
	@Column(name="WellnessConceptID1")
    private int wellnessConceptID1;
	
	@Column(name="RelationshipType")
    private int relationshipType;
	
	@Column(name="WellnessConceptID2")
    private int wellnessConceptID2;
	

	private String wellnessConcept1Description;
	

	private String wellnessConcept2Description;

	public int getWellnessConceptsRelationshipID() {
		return wellnessConceptsRelationshipID;
	}

	public void setWellnessConceptsRelationshipID(int wellnessConceptsRelationshipID) {
		this.wellnessConceptsRelationshipID = wellnessConceptsRelationshipID;
	}

	public int getWellnessConceptID1() {
		return wellnessConceptID1;
	}

	public void setWellnessConceptID1(int wellnessConceptID1) {
		this.wellnessConceptID1 = wellnessConceptID1;
	}

	public int getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(int relationshipType) {
		this.relationshipType = relationshipType;
	}

	public int getWellnessConceptID2() {
		return wellnessConceptID2;
	}

	public void setWellnessConceptID2(int wellnessConceptID2) {
		this.wellnessConceptID2 = wellnessConceptID2;
	}

	public String getWellnessConcept1Description() {
		return wellnessConcept1Description;
	}

	public void setWellnessConcept1Description(String wellnessConcept1Description) {
		this.wellnessConcept1Description = wellnessConcept1Description;
	}

	public String getWellnessConcept2Description() {
		return wellnessConcept2Description;
	}

	public void setWellnessConcept2Description(String wellnessConcept2Description) {
		this.wellnessConcept2Description = wellnessConcept2Description;
	}

	
	
}
