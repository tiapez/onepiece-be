package com.op.be.usercard.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardDetails{

	@Id
	private int id;
	@Column
	private int codCondition;
	@Column(name = "`language`")
	private String language;
	@Column(name = "`condition`")
	private String condition;
	@Column
	private String condDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodCondition() {
		return codCondition;
	}

	public void setCodCondition(int codCondition) {
		this.codCondition = codCondition;
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
		this.codCondition = id;
		this.condition = condition;
		this.language = language;
		this.condDesc = condDesc;
	}

	public CardDetails() {
		super();
	}

	@Override
	public String toString()
	{
		return "CardDetails [id=" + id + ", codCondition=" + codCondition + ", language=" + language + ", condition="
				+ condition + ", condDesc=" + condDesc + "]";
	}

}
