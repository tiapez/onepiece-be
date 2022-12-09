package com.op.be.usercard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardDetails{

	@Id
	int id;
	@Column
	int cod_condition;
	@Column(name = "`language`")
	String language;
	@Column(name = "`condition`")
	String condition;
	@Column
	String condDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCod_condition() {
		return cod_condition;
	}

	public void setCod_condition(int cod_condition) {
		this.cod_condition = cod_condition;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCondDesc() {
		return condDesc;
	}

	public void setCondDesc(String condDesc) {
		this.condDesc = condDesc;
	}

	public CardDetails(int id, String condition, String language, String condDesc) {
		super();
		this.cod_condition = id;
		this.condition = condition;
		this.language = language;
		this.condDesc = condDesc;
	}

	public CardDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
