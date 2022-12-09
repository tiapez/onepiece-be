package com.op.be.usercard.utils;

import java.util.ArrayList;

import com.op.be.usercard.model.Card;

public class UserCard {

	Card card;

	ArrayList<UserCardDetails> cardDetails = new ArrayList<UserCardDetails>();

	int qty = 0;

	public int getQty() {
		return qty;
	}

	public void setQty(int qtyAll) {
		this.qty = qtyAll;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public ArrayList<UserCardDetails> getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(ArrayList<UserCardDetails> cardDetails) {
		this.cardDetails = cardDetails;
	}

	public UserCard(Card card, ArrayList<UserCardDetails> cardDetails) {
		super();
		this.card = card;
		this.cardDetails = cardDetails;
	}

	public UserCard(Card card, UserCardDetails cardDetails) {
		super();
		this.card = card;
		this.addDetails(cardDetails);
	}

	public void addDetails(UserCardDetails cd) {
		this.cardDetails.add(cd);
		this.qty = this.qty + cd.getQty();
	}

	public UserCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "asd [card=" + card + ", cardDetails=" + cardDetails + ", qty=" + qty + "]";
	}

	public String getString() {
		String s = ("\"" + card.getId() + "@" + card.getName());
		for (UserCardDetails userCardDetails : cardDetails) {
			String a = "null";
			if (userCardDetails.getUcId() != null)
				a = userCardDetails.getUcId().toString();
			s = s.concat("@" + a + "#" + userCardDetails.getLanguage() + "#" + userCardDetails.getCondition() + "#"
					+ userCardDetails.getQty());
		}
		s = s.concat("\"");
		return s;
	}

}
