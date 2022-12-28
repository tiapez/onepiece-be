package com.op.be.usercard.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.be.usercard.model.Deck;
import com.op.be.usercard.model.DeckCard;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DeckCardDTO;
import com.op.be.usercard.model.dto.DeckCardRow;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserCardDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;
import com.op.be.usercard.repository.DeckCardRepository;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.repository.custom.DeckCustomRepository;
import com.op.be.usercard.service.DeckService;
import com.op.be.usercard.service.RestService;

@Service
public class DeckServiceImpl implements DeckService{
		
	@Autowired
	DeckCustomRepository deckCustomRepository;

	@Autowired
	DeckCardRepository deckCardRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RestService restService;
	
    @Autowired
    private ModelMapper modelMapper;
    
	@Override
	public ArrayList<CardDetailsDTO> getCardDetailsDeck(DeckDTO deckDTO, String nickCr){
		String nick = restService.decodenick(nickCr);
		ArrayList<UserCardDTO> userCardDTOList = (ArrayList<UserCardDTO>) 
				deckCustomRepository.findUserCardDetailsByDeck(deckDTO.getFormat(), deckDTO.getColor1(), deckDTO.getColor2(),deckDTO.getCond(), nick);
		return restService.forgeCardDetails(userCardDTOList);
	}
	
	@Override
	public void saveDeck(UserDeckDTO userDeck) {
		deckCardRepository.deleteByDeckId(userDeck.getDeck().getId());
		for (DeckCardDTO deckCard : userDeck.getCardList()) {
			deckCardRepository.save(new DeckCard(userDeck.getDeck().getId(), deckCard.getCard().getId(),deckCard.getQtyRequired()));
		}
		deckCustomRepository.save(userDeck.getDeck());
	}
	
	@Override
	public void saveOnlyDeck(DeckDTO deckDTO, String nickcr) {
		userRepository.findByNick(restService.decodenick(nickcr)).ifPresent((User u) -> {
			deckDTO.setUserId(u.getId());
			Deck deck = modelMapper.map(deckDTO, Deck.class);
			deckCustomRepository.save(deck);
		});
	}
	
	@Override
	public ArrayList<UserDeckDTO> getUserDeck(String nickcr){
		String nick = restService.decodenick(nickcr);
		ArrayList<DeckCardRow> deck = (ArrayList<DeckCardRow>) deckCustomRepository.findDeckUser(nick);
		ArrayList<UserDeckDTO> deckList = new ArrayList<>();
		if (!deck.isEmpty()) {
			UserDeckDTO ud = new UserDeckDTO(deck.get(0).getDeck());

			for (int i = 0; i < deck.size(); i++) {
				DeckCardRow deckCardRow = deck.get(i);
				if ((ud.getDeck().getId() != deckCardRow.getDeck().getId())) {
					deckList.add(ud);
					ud = new UserDeckDTO(deck.get(i).getDeck());
				}
				if (deckCardRow.getCard().getCard().getCardType().equals("Leader")) {
					int own = 0;
					if (deckCardRow.getCard().getQtyOwned() > 0) {
						own = 1;
					}
					deckCardRow.getCard().setQtyOwned(own);
					deckCardRow.getCard().setQtyRequired(1);
					ud.setLeader(deckCardRow.getCard());
				} else {
					ud.addCard(deckCardRow.getCard());
				}

			}

			deckList.add(ud);
		}
		return deckList;
	}
	
}
