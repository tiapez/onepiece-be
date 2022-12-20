package com.op.be.usercard.model.dto;

import com.op.be.usercard.model.Card;

public class DeckCardDTO {
	
	Card card;
	int qtyOwned;
	int qtyRequired;
	
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
	public int getQtyOwned() {
		return qtyOwned;
	}
	public void setQtyOwned(int qtyOwned) {
		this.qtyOwned = qtyOwned;
	}
	
	public int getQtyRequired() {
		return qtyRequired;
	}
	public void setQtyRequired(int qtyRequired) {
		this.qtyRequired = qtyRequired;
	}
	
	public DeckCardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeckCardDTO(Card card, int qty) {
		super();
		this.card = card;
		this.qtyOwned = qty;
	}
	
	public DeckCardDTO(Card card, int qtyOwned, int qtyRequired) {
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
