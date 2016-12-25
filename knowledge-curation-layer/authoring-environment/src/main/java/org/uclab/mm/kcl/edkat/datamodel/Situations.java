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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Taqdir Ali
 *
 */

@Entity
@Table(name="tblSituations")
public class Situations {

	@Id
    @Column(name="SituationID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int SituationID;

	@Column(name="SituationDescription")
	private String situationDescription;
	
	public int getSituationID() {
		return SituationID;
	}

	public void setSituationID(int situationID) {
		SituationID = situationID;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="SituationID")
	private List<Rule> rulesList = new ArrayList<Rule>();
		
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tblSituationConditions", 
             joinColumns = { @JoinColumn(name = "SituationID") }, 
             inverseJoinColumns = { @JoinColumn(name = "ConditionID") })
    private List<Condition> situationConditionList = new ArrayList<Condition>();
	
	public Situations(){}
	
	public List<Rule> getRulesList() {
		return rulesList;
	}

	public void setRulesList(List<Rule> rulesList) {
		this.rulesList = rulesList;
	}

	

	public String getSituationDescription() {
		return situationDescription;
	}

	public void setSituationDescription(String situationDescription) {
		this.situationDescription = situationDescription;
	}

	public List<Condition> getSituationConditionList() {
		return situationConditionList;
	}

	public void setSituationConditionList(List<Condition> situationConditionList) {
		this.situationConditionList = situationConditionList;
	}

	
	
}
