package com.op.be.usercard.model.dto;

public class UserCardExcel {

	String number;
	String set;
	int cond;
	int qty = 0;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public int getCond() {
		return cond;
	}
	public void setCond(int cond) {
		this.cond = cond;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@Override
	public String toString() {
		return "UserCardExcel [number=" + number + ", set=" + set + ", cond=" + cond + ", qty=" + qty + "]";
	}
	
	
}
