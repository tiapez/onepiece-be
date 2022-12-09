package com.op.be.usercard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "user_id")
	int userId;

	@Column(name = "card_id")
	int cardId;

	@Column
	int detailsId;

	@Column
	int qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public UserCard(int id, int userId, int cardId, int detailsId, int qty) {
		super();
		this.id = id;
		this.userId = userId;
		this.cardId = cardId;
		this.detailsId = detailsId;
		this.qty = qty;
	}

	public UserCard(int userId, int cardId, int detailsId, int qty) {
		super();
		this.userId = userId;
		this.cardId = cardId;
		this.detailsId = detailsId;
		this.qty = qty;
	}

	public UserCard() {
		super();
	}

	@Override
	public String toString() {
		return "UserCard [id=" + id + ", userId=" + userId + ", cardId=" + cardId + ", detailsId=" + detailsId
				+ ", qty=" + qty + "]";
	}

}
