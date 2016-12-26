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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Taqdir Ali
 *
 */

@Entity
@Table(name="tblConclusions")
public class Conclusion {

	@Id
    @Column(name="ConclusionID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int ConclusionID;
	
	@Column(name="RuleID")
	private int RuleID;
	
	public int getRuleID() {
		return RuleID;
	}

	public void setRuleID(int ruleID) {
		RuleID = ruleID;
	}

	@Column(name="ConclusionKey")
	private String conclusionKey;
	
	@Column(name="ConclusionValue")
	private String conclusionValue;
	
	@Column(name="ConclusionOperator")
	private String conclusionOperator;
	
	public Conclusion()  
	 {  
	 }  
	   
	public int getConclusionID() {
		return ConclusionID;
	}

	public void setConclusionID(int conclusionID) {
		ConclusionID = conclusionID;
	}

	public String getConclusionKey() {
		return conclusionKey;
	}

	public void setConclusionKey(String conclusionKey) {
		this.conclusionKey = conclusionKey;
	}

	public String getConclusionValue() {
		return conclusionValue;
	}

	public void setConclusionValue(String conclusionValue) {
		this.conclusionValue = conclusionValue;
	}

	public String getConclusionOperator() {
		return conclusionOperator;
	}

	public void setConclusionOperator(String conclusionOperator) {
		this.conclusionOperator = conclusionOperator;
	}
	
	
	
}
