package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.model.dto.CardWDetailsDTO;

public interface CardService {

	ArrayList<CardWDetailsDTO> getCardDetails(String nickCr, int set) throws Exception;

	ArrayList<CardWDetailsDTO> getCardClassic(String nickCr, int set) throws Exception;

	ArrayList<CardWDetailsDTO> getAll();

	int getDetailsId(String l, int c);

}
