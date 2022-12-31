package com.op.be.usercard.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserCardDTO;
import com.op.be.usercard.repository.CardRepository;
import com.op.be.usercard.repository.custom.CardUserCustomRepository;
import com.op.be.usercard.repository.custom.DeckCustomRepository;
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
	
	@Autowired
	DeckCustomRepository deckCustomRepository;
	
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
		ArrayList<Card> cards = (ArrayList<Card>) cardRepository.findAllCard();
		ArrayList<CardDetailsDTO> cardList = new ArrayList<>();
		for (Card card : cards) {
			cardList.add(new CardDetailsDTO(card));
		}
		return cardList;
	}
	
	@Override
	public ArrayList<CardDetailsDTO> getCardDetailsDeck(DeckDTO deckDTO, String nickCr){
		String nick = restService.decodenick(nickCr);
		ArrayList<UserCardDTO> userCardDTOList = (ArrayList<UserCardDTO>) 
				deckCustomRepository.findUserCardDetailsByDeck(deckDTO.getFormat(), deckDTO.getColor1(), deckDTO.getColor2(),deckDTO.getCond(), nick);
		return restService.forgeCardDetails(userCardDTOList);
	}
	
	
	@Override
	public int getDetailsId(String l, int c) {
		if (l.equals("JAP")) {
			c = c + 6;
		}

		return c;
	}

}
