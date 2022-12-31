package com.op.be.usercard.model.dto;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.CardDetails;
import com.op.be.usercard.model.UserCard;

public class UserCardDTO {

	private Card card;
	private UserCard userCard;
	private CardDetails cardDetails;
	private int qtyMax;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public UserCard getUserCard() {
		return userCard;
	}

	public void setUserCard(UserCard userCard) {
		this.userCard = userCard;
	}

	public CardDetails getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}
	
	public int getQtyMax() {
		return qtyMax;
	}

	public void setQtyMax(int qtyMax) {
		this.qtyMax = qtyMax;
	}

	public UserCardDTO(Card card, UserCard userCard, CardDetails cardDetails) {
		super();
		this.card = card;
		this.userCard = userCard;
		this.cardDetails = cardDetails;
		this.qtyMax = 0;
	}

	public UserCardDTO(Card card, UserCard userCard, CardDetails cardDetails, int qtyMax) {
		super();
		this.card = card;
		this.userCard = userCard;
		this.cardDetails = cardDetails;
		this.qtyMax = qtyMax;
	}

	public UserCardDTO() {
		super();
	}

	@Override
	public String toString() {
		return "UserCardDetailsDTO [card=" + card + ", userCard=" + userCard + ", cardDetails=" + cardDetails + "]";
	}

}
