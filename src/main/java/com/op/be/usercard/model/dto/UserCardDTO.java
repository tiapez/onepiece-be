package com.op.be.usercard.model.dto;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.CardDetails;
import com.op.be.usercard.model.UserCard;

public class UserCardDTO {

	Card card;
	UserCard userCard;
	CardDetails cardDetails;

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

	public UserCardDTO(Card card, UserCard userCard, CardDetails cardDetails) {
		super();
		this.card = card;
		this.userCard = userCard;
		this.cardDetails = cardDetails;
	}

	public UserCardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserCardDetailsDTO [card=" + card + ", userCard=" + userCard + ", cardDetails=" + cardDetails + "]";
	}

}
