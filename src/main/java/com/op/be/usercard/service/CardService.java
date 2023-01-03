package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DeckDTO;

public interface CardService {

	ArrayList<CardDetailsDTO> getCardDetails(String nickCr, String set)  throws CryptException;

	ArrayList<CardDetailsDTO> getCardClassic(String nickCr, String set)  throws CryptException;

	ArrayList<CardDetailsDTO> getAll();

	int getDetailsId(String l, int c);

	ArrayList<CardDetailsDTO> getCardDetailsDeck(DeckDTO deckDTO, String nickCr)  throws CryptException;

}
