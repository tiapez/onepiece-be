package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;

public interface DeckService {

	void saveDeck(UserDeckDTO userDeck);

	ArrayList<CardDetailsDTO> getCardDetailsDeck(DeckDTO deck, String nickCr);

	ArrayList<UserDeckDTO> getUserDeck(String nickcr);

	void saveOnlyDeck(DeckDTO deck, String nickcr);
	

}
