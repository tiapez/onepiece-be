package com.op.be.usercard.model;

import java.io.Serializable;
import java.util.Objects;


public class DeckCardId implements Serializable {

	private static final long serialVersionUID = -1440873333707203566L;

	private int deckId;

	private int cardId;
	
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
	
	public DeckCardId() {
		super();
	}
	
	public DeckCardId(int deckId, int cardId) {
		super();
		this.deckId = deckId;
		this.cardId = cardId;
	}
	
	public DeckCardId(int deckId) {
		super();
		this.deckId = deckId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash( cardId, deckId );
	}
	
	@Override
	public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        DeckCardId pk = (DeckCardId) o;
        return Objects.equals( cardId, pk.cardId ) &&
                Objects.equals( deckId, pk.deckId );
	}
	
	
}
