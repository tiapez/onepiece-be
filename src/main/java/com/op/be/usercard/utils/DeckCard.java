package com.op.be.usercard.utils;

import com.op.be.usercard.model.Card;

public class DeckCard {
	
	Card card;
	Long qtyOwned;
	Long qtyRequired;
	
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
	public Long getQtyOwned() {
		return qtyOwned;
	}
	public void setQtyOwned(Long qtyOwned) {
		this.qtyOwned = qtyOwned;
	}
	
	public Long getQtyRequired() {
		return qtyRequired;
	}
	public void setQtyRequired(Long qtyRequired) {
		this.qtyRequired = qtyRequired;
	}
	
	public DeckCard(Card card, Long qty) {
		super();
		this.card = card;
		this.qtyOwned = qty;
	}
	
	public DeckCard(Card card, Long qtyOwned, Long qtyRequired) {
		super();
		this.card = card;
		this.qtyOwned = qtyOwned;
		this.qtyRequired = qtyRequired;
	}
	@Override
	public String toString() {
		return "DeckCard [card=" + card + ", qtyOwned=" + qtyOwned + ", qtyRequired=" + qtyRequired + "]";
	}
	

}
