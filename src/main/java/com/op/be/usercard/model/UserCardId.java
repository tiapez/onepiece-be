package com.op.be.usercard.model;

import java.io.Serializable;

public class UserCardId implements Serializable {
	
	private static final long serialVersionUID = 8316835624730943346L;

	private int userId;

	private int cardId;

	private int detailsId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(int detailsId) {
		this.detailsId = detailsId;
	}
	
	
	
}
