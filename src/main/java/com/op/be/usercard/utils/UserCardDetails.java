package com.op.be.usercard.utils;

public class UserCardDetails {

	Long ucId;

	String language;

	String condition;

	int qty;

	public Long getUcId() {
		return ucId;
	}

	public void setUcId(Long ucId) {
		this.ucId = ucId;
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

	public UserCardDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCardDetails(Long ucId, String language, String condition, int qty) {
		super();
		this.ucId = ucId;
		this.language = language;
		this.condition = condition;
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "UserCardDetails [ucId=" + ucId + ", language=" + language + ", condition=" + condition + ", qty=" + qty
				+ "]";
	}

}
