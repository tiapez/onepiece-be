package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.model.Deck;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;

public interface DeckService {

	void saveDeck(UserDeckDTO userDeck);

	ArrayList<CardDetailsDTO> getCardDetailsDeck(Deck deck, String nickCr);

	ArrayList<UserDeckDTO> getUserDeck(String nickcr);

	void saveOnlyDeck(Deck deck, String nickcr);
	

}
