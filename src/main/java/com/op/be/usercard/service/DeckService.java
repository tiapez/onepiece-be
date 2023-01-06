package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;

public interface DeckService {

	void saveDeck(UserDeckDTO userDeck);

	ArrayList<UserDeckDTO> getUserDeck(String nickcr) throws CryptException;

	void saveOnlyDeck(DeckDTO deck, String nickcr) throws CryptException;

	ArrayList<Card> getAllLeader();

	void deleteDeck(Long id);
	

}
