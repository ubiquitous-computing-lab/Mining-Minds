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
@Table(name="tblWellnessConceptsModel")
@Proxy(lazy=false)
public class WellnessConceptsModel implements Serializable{
	@Id
    @Column(name="WellnessConceptID")
    private int wellnessConceptID;
	
	@Column(name="WellnessConceptDescription")
	private String wellnessConceptDescription;
	
	@Column(name="ActiveYNID")
	private int activeYNID;
	
	public int getWellnessConceptID() {
		return wellnessConceptID;
	}

	public void setWellnessConceptID(int wellnessConceptID) {
		this.wellnessConceptID = wellnessConceptID;
	}

	public String getWellnessConceptDescription() {
		return wellnessConceptDescription;
	}

	public void setWellnessConceptDescription(String wellnessConceptDescription) {
		this.wellnessConceptDescription = wellnessConceptDescription;
	}

	public int getActiveYNID() {
		return activeYNID;
	}

	public void setActiveYNID(int activeYNID) {
		this.activeYNID = activeYNID;
	}
	
}
