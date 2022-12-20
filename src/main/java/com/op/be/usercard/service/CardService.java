package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.model.dto.CardWDetailsDTO;
import com.op.be.usercard.utils.UserDeck;

public interface CardService {

	ArrayList<CardWDetailsDTO> getCardDetails(String nickCr, String set) throws Exception;

	ArrayList<CardWDetailsDTO> getCardClassic(String nickCr, String set) throws Exception;

	ArrayList<CardWDetailsDTO> getAll();

	int getDetailsId(String l, int c);

	ArrayList<CardWDetailsDTO> getCardDetailsDeck(String lang, String color, int codCond, String nickCr)
			throws Exception;

	void saveDeck(UserDeck userDeck);


}
