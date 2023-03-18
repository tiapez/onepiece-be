package com.op.be.usercard.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.Set;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.SetCard;
import com.op.be.usercard.repository.CardRepository;
import com.op.be.usercard.repository.SetRepository;
import com.op.be.usercard.repository.custom.CardUserCustomRepository;
import com.op.be.usercard.repository.custom.DeckCustomRepository;
import com.op.be.usercard.service.CardService;
import com.op.be.usercard.service.RestService;

@Service
public class CardServiceImpl implements CardService
{

	@Autowired
	CardUserCustomRepository cardUserCustomRepository;

	@Autowired
	CardRepository cardRepository;

	@Autowired
	RestService restService;

	@Autowired
	DeckCustomRepository deckCustomRepository;

	@Autowired
	SetRepository setRepository;

	@Override
	public ArrayList<CardDetailsDTO> getCardDetails(String nickCr, String set) throws CryptException
	{

		ArrayList<Object[]> userCardDTOList = (ArrayList<Object[]>) cardUserCustomRepository
				.findUserCardDetailsBySet(set, restService.decodenick(nickCr));
		return restService.forgeCardDetails(userCardDTOList);
	}

	@Override
	public ArrayList<CardDetailsDTO> getCardClassic(String nickCr, String set) throws CryptException
	{
		System.out.println(nickCr + set);
		ArrayList<Object[]> obs = (ArrayList<Object[]>) cardUserCustomRepository.findUserCardDetailsBySet(set,
				restService.decodenick(nickCr));

		return restService.forgeCardDetails(obs);
	}

	@Override
	public ArrayList<CardDetailsDTO> getCardForDeck(DeckDTO deckDTO, String nickCr) throws CryptException
	{
		String nick = restService.decodenick(nickCr);
		
		ArrayList<Object[]> userCardDTOList = (ArrayList<Object[]>) deckCustomRepository.findCardForDeck(
				deckDTO.getLanguage(), deckDTO.getColor1(), deckDTO.getColor2(), deckDTO.getCond(), nick, deckDTO.getFormat());
		return restService.forgeCardDetails(userCardDTOList);
	}

	@Override
	public int getDetailsId(String l, int c)
	{
		if (l.equals("Jap"))
		{
			c = c + 6;
		}

		return c;
	}

	@Override
	public ArrayList<SetCard> getSetCardList()
	{

		ArrayList<Set> setList = (ArrayList<Set>) setRepository.findSetList();
		ArrayList<Card> cardList = (ArrayList<Card>) cardRepository.findAllCard();

		ArrayList<SetCard> setCardList = new ArrayList<>();
		int i = 0;
		
		Set set = setList.get(i);
		ArrayList<Card> list = new ArrayList<>();
		for(Card card : cardList) {
			if(!set.getId().equals(card.getSetId())) {
				setCardList.add(new SetCard(set, list));
				
				i++;
				set = setList.get(i);	
				list = new ArrayList<>();
				
			}
			list.add(card);
		}
		Card card = cardList.get(cardList.size()-1);
		if(set.getId().equals(card.getSetId())) {
			setCardList.add(new SetCard(set, list));
		}
		
		return setCardList;
	}

}
