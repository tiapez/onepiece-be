package com.op.be.usercard.model.dto;

public class DetailsDTO {

	private int codCondition;
	private String language;
	private String condition;
	private int qty;
	private Long userId;

	public DetailsDTO(int codCondition, String language, String condition, int qty, Long userCardId) {
		super();
		this.codCondition = codCondition;
		this.language = language;
		this.condition = condition;
		this.qty = qty;
		this.userId = userCardId;
	}

	public int getCodCondition() {
		return codCondition;
	}

	public void setCodCondition(int codCondition) {
		this.codCondition = codCondition;
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

	public Long getuserId() {
		return userId;
	}

	public void setuserId(Long userCardId) {
		this.userId = userCardId;
	}

	@Override
	public String toString() {
		return "DetailsDTO [cod_condition=" + codCondition + ", language=" + language + ", condition=" + condition
				+ ", qty=" + qty + ", userId=" + userId + "]";
	}

}
