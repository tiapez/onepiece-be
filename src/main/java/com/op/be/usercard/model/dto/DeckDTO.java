package com.op.be.usercard.model.dto;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.Deck;
import com.op.be.usercard.utils.DeckCard;

public class DeckDTO {

	Deck deck;
	DeckCard card;

	
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public DeckCard getCard() {
		return card;
	}
	public void setCard(DeckCard card) {
		this.card = card;
	}
	public DeckDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DeckDTO(Card card, Deck deck, Long qty) {
		super();
		this.deck = deck;
		this.card = new DeckCard(card,qty);
	}
	@Override
	public String toString() {
		return "DeckDTO [deck=" + deck + ", card=" + card + "]";
	}

	
	
	
}
