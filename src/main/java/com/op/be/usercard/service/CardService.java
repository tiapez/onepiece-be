package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.dto.CardDetailsDTO;

public interface CardService {

	ArrayList<CardDetailsDTO> getCardDetails(String nickCr, String set);

	ArrayList<CardDetailsDTO> getCardClassic(String nickCr, String set);

	ArrayList<CardDetailsDTO> getAll();

	int getDetailsId(String l, int c);

	ArrayList<Card> getAllLeader();

}
