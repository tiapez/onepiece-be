package com.op.be.usercard.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DeckCardRow;
import com.op.be.usercard.model.dto.UserCardDTO;
import com.op.be.usercard.repository.CardRepository;
import com.op.be.usercard.repository.custom.CardUserCustomRepository;
import com.op.be.usercard.service.CardService;
import com.op.be.usercard.service.RestService;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	CardUserCustomRepository cardUserCustomRepository;
	
	@Autowired
	CardRepository cardRepository;

	@Autowired
	RestService restService;
	
	@Override
	public ArrayList<CardDetailsDTO> getCardDetails(String nickCr, String set){
		
		ArrayList<UserCardDTO> userCardDTOList = (ArrayList<UserCardDTO>) cardUserCustomRepository.findUserCardDetailsBySet(set,
				restService.decodenick(nickCr));
		return restService.forgeCardDetails(userCardDTOList);
	}
	

	@Override
	public ArrayList<CardDetailsDTO> getCardClassic(String nickCr, String set){
		ArrayList<UserCardDTO> userCardDTOList = (ArrayList<UserCardDTO>) cardUserCustomRepository.findUserCardClassicBySet(set,
				restService.decodenick(nickCr));

		return restService.forgeCardDetails(userCardDTOList);
	}

	@Override
	public ArrayList<CardDetailsDTO> getAll() {
		ArrayList<Card> cards = (ArrayList<Card>) cardRepository.findAll();
		ArrayList<CardDetailsDTO> cardList = new ArrayList<>();
		for (Card card : cards) {
			cardList.add(new CardDetailsDTO(card));
		}
		return cardList;
	}
	
	
	public DeckCardRow getDeckDTO() {
		return null;
	}
	
	@Override
	public ArrayList<Card> getAllLeader(){
		return 	(ArrayList<Card>) cardRepository.findAllLeader();	
	}


	@Override
	public int getDetailsId(String l, int c) {
		if (l.equals("JAP")) {
			c = c + 6;
		}

		return c;
	}

}
