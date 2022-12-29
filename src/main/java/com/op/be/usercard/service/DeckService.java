package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;

public interface DeckService {

	void saveDeck(UserDeckDTO userDeck);

	ArrayList<UserDeckDTO> getUserDeck(String nickcr);

	void saveOnlyDeck(DeckDTO deck, String nickcr);

	ArrayList<Card> getAllLeader();
	

}
