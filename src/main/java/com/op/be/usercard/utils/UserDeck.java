package com.op.be.usercard.utils;

import java.util.ArrayList;
import java.util.Arrays;

import com.op.be.usercard.model.Deck;
import com.op.be.usercard.model.dto.DeckCardDTO;

public class UserDeck {
	Deck deck;
	ArrayList<DeckCardDTO> cardList;
	
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public ArrayList<DeckCardDTO> getCardList() {
		return cardList;
	}
	public void setCardList(ArrayList<DeckCardDTO> cardList) {
		this.cardList = cardList;
	}
	
	public UserDeck() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDeck(Deck deck, ArrayList<DeckCardDTO> cardList) {
		super();
		this.deck = deck;
		this.cardList = cardList;
	}
	
	public UserDeck(Deck deck, DeckCardDTO[] cardList) {
		super();
		this.deck = deck;
		this.cardList = new ArrayList<DeckCardDTO>(Arrays.asList(cardList));
	}
	
	
	public UserDeck(Deck deck, DeckCardDTO card) {
		super();
		this.deck = deck;
		this.cardList = new ArrayList<>();
		this.cardList.add(card);
	}
	
	public void addCard(DeckCardDTO cardList) {
		this.cardList.add(cardList);
	}
	
	@Override
	public String toString() {
		return "UserDeck [deck=" + deck + ", cardList=" + cardList + "]";
	}
	
	
}
