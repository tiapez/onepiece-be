package com.op.be.usercard.utils;

import java.util.ArrayList;

import com.op.be.usercard.model.Deck;

public class UserDeck {
	Deck deck;
	ArrayList<DeckCard> cardList;
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public ArrayList<DeckCard> getCardList() {
		return cardList;
	}
	public void setCardList(ArrayList<DeckCard> cardList) {
		this.cardList = cardList;
	}
	public UserDeck() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDeck(Deck deck, ArrayList<DeckCard> cardList) {
		super();
		this.deck = deck;
		this.cardList = cardList;
	}
	
	public UserDeck(Deck deck, DeckCard cardList) {
		super();
		this.deck = deck;
		this.cardList = new ArrayList<>();
		this.cardList.add(cardList);
	}
	
	public void addCard(DeckCard cardList) {
		this.cardList.add(cardList);
	}
	@Override
	public String toString() {
		return "UserDeck [deck=" + deck + ", cardList=" + cardList + "]";
	}
	
	
}
