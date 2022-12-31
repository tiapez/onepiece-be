package com.op.be.usercard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(DeckCardId.class)
public class DeckCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private int deckId;
	@Id
	@Column
	private int cardId;
	
	@Column
	private int qty;

	public int getDeckId() {
		return deckId;
	}

	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public DeckCard() {
		super();
	}

	public DeckCard(int deckId, int cardId, int qty) {
		super();
		this.deckId = deckId;
		this.cardId = cardId;
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "DeckCard [deckId=" + deckId + ", cardId=" + cardId + ", qty=" + qty + "]";
	}
	
	
	
}
