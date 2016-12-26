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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Taqdir Ali
 *
 */

@Entity
@Table(name="tblRules")
public class Rule implements Serializable {

	@Id
    @Column(name="RuleID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int RuleID;
	
	@Column(name="RuleTitle")
	private String ruleTitle;
	
	@Column(name="Institution")
	private String institution;
	
	@Column(name="RuleDescription")
	private String ruleDescription;
	
	@Column(name="RuleCondition")
	private String ruleCondition;
	
	@Column(name="RuleConclusion")
	private String ruleConclusion;
	
	@Column(name="RuleCreatedDate")
	private Date ruleCreatedDate;
	
	@Column(name="RuleCreatedBy")
	private Integer ruleCreatedBy;
	
	@Column(name="SpecialistName")
	private String specialistName;
	
	@Column(name="RuleUpdatedBy")
	private Integer ruleUpdatedBy;
	
	@Column(name="RuleLastUpdatedDate")
	private Date ruleLastUpdatedDate;
	
	@Column(name="RuleTypeID")
	private Integer ruleTypeID;
	
	@Column(name="SourceClassName")
	private String sourceClassName;
	
	@Column(name="SituationID")
	private int situationID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="RuleID")
	private List<Conclusion> conclusionList = new ArrayList<Conclusion>();
	 
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tblRulesConditions", 
             joinColumns = { @JoinColumn(name = "RuleID") }, 
             inverseJoinColumns = { @JoinColumn(name = "ConditionID") })
    private List<Condition> conditionList = new ArrayList<Condition>();
	
	public Rule(){}
	
	public int getSituationID() {
		return situationID;
	}

	public void setSituationID(int situationID) {
		this.situationID = situationID;
	}

	public int getRuleID() {
		return RuleID;
	}

	public List<Conclusion> getConclusionList() {
		return conclusionList;
	}

	public void setConclusionList(List<Conclusion> conclusionList) {
		this.conclusionList = conclusionList;
	}

    public void setRuleID(int ruleID) {
		RuleID = ruleID;
	}

	public String getRuleTitle() {
		return ruleTitle;
	}

	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getRuleCondition() {
		return ruleCondition;
	}

	public void setRuleCondition(String ruleCondition) {
		this.ruleCondition = ruleCondition;
	}

	public String getRuleConclusion() {
		return ruleConclusion;
	}

	public void setRuleConclusion(String ruleConclusion) {
		this.ruleConclusion = ruleConclusion;
	}

	public Date getRuleCreatedDate() {
		return ruleCreatedDate;
	}

	public void setRuleCreatedDate(Date ruleCreatedDate) {
		this.ruleCreatedDate = ruleCreatedDate;
	}

	public Integer getRuleCreatedBy() {
		return ruleCreatedBy;
	}

	public void setRuleCreatedBy(Integer ruleCreatedBy) {
		this.ruleCreatedBy = ruleCreatedBy;
	}

	public String getSpecialistName() {
		return specialistName;
	}

	public void setSpecialistName(String specialistName) {
		this.specialistName = specialistName;
	}

	public Integer getRuleUpdatedBy() {
		return ruleUpdatedBy;
	}

	public void setRuleUpdatedBy(Integer ruleUpdatedBy) {
		this.ruleUpdatedBy = ruleUpdatedBy;
	}

	public Date getRuleLastUpdatedDate() {
		return ruleLastUpdatedDate;
	}

	public void setRuleLastUpdatedDate(Date ruleLastUpdatedDate) {
		this.ruleLastUpdatedDate = ruleLastUpdatedDate;
	}

	public Integer getRuleTypeID() {
		return ruleTypeID;
	}

	public void setRuleTypeID(Integer ruleTypeID) {
		this.ruleTypeID = ruleTypeID;
	}

	public String getSourceClassName() {
		return sourceClassName;
	}

	public void setSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
	}
	
	public List<Condition> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<Condition> conditionList) {
		this.conditionList = conditionList;
	}

		
}
