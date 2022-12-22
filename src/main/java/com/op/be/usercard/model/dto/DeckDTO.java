package com.op.be.usercard.model.dto;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.Deck;

public class DeckDTO {

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
	public DeckDTO() {
		super();
	}
	
	public DeckDTO(Card card, Deck deck, Long qtyOwned, int qtyRequired) {
		super();
		this.deck = deck;
		if(card != null)
		this.card = new DeckCardDTO(card,qtyOwned.intValue(),qtyRequired);
	}
	
	public DeckDTO(Card card, Deck deck, Long qtyOwned) {
		super();
		this.deck = deck;
		this.card = new DeckCardDTO(card,qtyOwned.intValue());
	}
	
	@Override
	public String toString() {
		return "DeckDTO [deck=" + deck + ", card=" + card + "]";
	}

	
	
	
}
