package com.op.be.usercard.model.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.op.be.usercard.model.Deck;

public class UserDeckDTO {
	
	private Deck deck;
	private ArrayList<DeckCardDTO> cardList;
	private DeckCardDTO leader;
	
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public List<DeckCardDTO> getCardList() {
		return cardList;
	}
	public void setCardList(List<DeckCardDTO> cardList) {
		this.cardList = (ArrayList<DeckCardDTO>) cardList;
	}
	
	public DeckCardDTO getLeader() {
		return leader;
	}
	public void setLeader(DeckCardDTO leader) {
		this.leader = leader;
	}
	public UserDeckDTO() {
		super();
	}
	
	public UserDeckDTO(Deck deck) {
		super();
		this.deck = deck;
		this.cardList = new ArrayList<>();
	}
	
	public UserDeckDTO(Deck deck, List<DeckCardDTO> cardList) {
		super();
		this.deck = deck;
		this.cardList = (ArrayList<DeckCardDTO>) cardList;
	}
	
	public UserDeckDTO(Deck deck, DeckCardDTO[] cardList) {
		super();
		this.deck = deck;
		this.cardList = new ArrayList<>(Arrays.asList(cardList));
	}
	
	public UserDeckDTO(Deck deck, List<DeckCardDTO> cardList, DeckCardDTO leader) {
		super();
		this.deck = deck;
		this.cardList = (ArrayList<DeckCardDTO>) cardList;
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
