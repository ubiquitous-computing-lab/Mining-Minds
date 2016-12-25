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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Taqdir Ali
 *
 */

@Entity
@Table(name="tblConditions" ,
uniqueConstraints=
@UniqueConstraint(columnNames={"ConditionKey", "ConditionValue", "ConditionType", "ConditionValueOperator"}))
public class Condition {

	@Id
    @Column(name="ConditionID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int ConditionID;
	
	@Column(name="ConditionKey")
	private String conditionKey;
	
	@Column(name="ConditionValue")
	private String conditionValue;
	
	@Column(name="ConditionType")
	private String conditionType;

	@Column(name="ConditionValueOperator")
	private String conditionValueOperator;
	
	@JsonIgnore
	 @ManyToMany(mappedBy="conditionList")
	 private List<Rule> ruleList = new ArrayList<Rule>();
	 
	@JsonIgnore
	 @ManyToMany(mappedBy="situationConditionList")
	 private List<Situations> situationList = new ArrayList<Situations>();
	 
	 @Transient
	 private Boolean isItSituation = false;
	 
	public Boolean getIsItSituation() {
		return isItSituation;
	}

	public void setIsItSituation(Boolean isItSituation) {
		this.isItSituation = isItSituation;
	}

	public Condition() {}
	 
	 public int getConditionID() {
			return ConditionID;
		}

		public void setConditionID(int conditionID) {
			ConditionID = conditionID;
		}

		public String getConditionKey() {
			return conditionKey;
		}

		public void setConditionKey(String conditionKey) {
			this.conditionKey = conditionKey;
		}

		public String getConditionValue() {
			return conditionValue;
		}

		public void setConditionValue(String conditionValue) {
			this.conditionValue = conditionValue;
		}

		public String getConditionType() {
			return conditionType;
		}

		public void setConditionType(String conditionType) {
			this.conditionType = conditionType;
		}

		public String getConditionValueOperator() {
			return conditionValueOperator;
		}

		public void setConditionValueOperator(String conditionValueOperator) {
			this.conditionValueOperator = conditionValueOperator;
		}
		
		public List<Rule> getRuleList() {
			return ruleList;
		}

		public void setRuleList(List<Rule> ruleList) {
			this.ruleList = ruleList;
		}
		
		 public List<Situations> getSituationList() {
				return situationList;
			}

			public void setSituationList(List<Situations> situationList) {
				this.situationList = situationList;
			}
		
		
}
