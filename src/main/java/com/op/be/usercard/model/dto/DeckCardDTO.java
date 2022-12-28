package com.op.be.usercard.model.dto;

import com.op.be.usercard.model.Card;

public class DeckCardDTO {
	
	Card card;
	int qtyOwned;
	int qtyRequired;
	int qtyMax;
	
	
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
	
	public int getQtyMax() {
		return qtyMax;
	}
	
	public void setQtyMax(int qtyMax) {
		this.qtyMax = qtyMax;
	}
	
	public DeckCardDTO() {
		super();
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
	
	public DeckCardDTO(Card card, int qtyOwned, int qtyRequired, int qtyMax) {
		super();
		this.card = card;
		this.qtyOwned = qtyOwned;
		this.qtyRequired = qtyRequired;
		this.qtyMax = qtyMax;
	}
	
	@Override
	public String toString() {
		return "DeckCard [card=" + card + ", qtyOwned=" + qtyOwned + ", qtyRequired=" + qtyRequired + "]";
	}
	

}
