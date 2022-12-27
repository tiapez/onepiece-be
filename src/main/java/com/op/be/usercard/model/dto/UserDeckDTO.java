package com.op.be.usercard.model.dto;

import java.util.ArrayList;
import java.util.Arrays;

import com.op.be.usercard.model.Deck;

public class UserDeckDTO {
	Deck deck;
	ArrayList<DeckCardDTO> cardList;
	DeckCardDTO leader;
	
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
	
	public DeckCardDTO getLeader() {
		return leader;
	}
	public void setLeader(DeckCardDTO leader) {
		this.leader = leader;
	}
	public UserDeckDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDeckDTO(Deck deck) {
		super();
		this.deck = deck;
		this.cardList = new ArrayList<DeckCardDTO>();
	}
	
	public UserDeckDTO(Deck deck, ArrayList<DeckCardDTO> cardList) {
		super();
		this.deck = deck;
		this.cardList = cardList;
	}
	
	public UserDeckDTO(Deck deck, DeckCardDTO[] cardList) {
		super();
		this.deck = deck;
		this.cardList = new ArrayList<DeckCardDTO>(Arrays.asList(cardList));
	}
	
	public UserDeckDTO(Deck deck, ArrayList<DeckCardDTO> cardList, DeckCardDTO leader) {
		super();
		this.deck = deck;
		this.cardList = cardList;
		this.leader = leader;
	}
	public UserDeckDTO(Deck deck, DeckCardDTO card) {
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
