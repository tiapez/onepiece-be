package com.op.be.usercard.model.dto;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.Deck;

public class DeckCardRow {

	Deck deck;
	DeckCardDTO card;

	
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public DeckCardDTO getCard() {
		return card;
	}
	public void setCard(DeckCardDTO card) {
		this.card = card;
	}
	public DeckCardRow() {
		super();
	}
	
	public DeckCardRow(Card card, Deck deck, Long qtyOwned, int qtyRequired, int qtyMax) {
		super();
		this.deck = deck;
		if(card != null) {
			this.card = new DeckCardDTO(card,qtyOwned.intValue(),qtyRequired, qtyMax);
		}

	}
	
	public DeckCardRow(Card card, Deck deck, Long qtyOwned) {
		super();
		this.deck = deck;
		this.card = new DeckCardDTO(card,qtyOwned.intValue());
	}
	
	@Override
	public String toString() {
		return "DeckDTO [deck=" + deck + ", card=" + card + "]";
	}

	
	
	
}
