package com.op.be.usercard.model.dto;

public class DetailsDTO {

	int cod_condition;
	String language;
	String condition;
	int qty;
	int userCardId;

	public DetailsDTO(int cod_condition, String language, String condition, int qty, int userCardId) {
		super();
		this.cod_condition = cod_condition;
		this.language = language;
		this.condition = condition;
		this.qty = qty;
		this.userCardId = userCardId;
	}

	public int getCodCondition() {
		return cod_condition;
	}

	public void setCodCondition(int cod_condition) {
		this.cod_condition = cod_condition;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(int userCardId) {
		this.userCardId = userCardId;
	}

	@Override
	public String toString() {
		return "DetailsDTO [cod_condition=" + cod_condition + ", language=" + language + ", condition=" + condition
				+ ", qty=" + qty + ", userCardId=" + userCardId + "]";
	}

}
