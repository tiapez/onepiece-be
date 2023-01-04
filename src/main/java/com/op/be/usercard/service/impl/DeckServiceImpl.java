package com.op.be.usercard.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.Deck;
import com.op.be.usercard.model.DeckCard;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.DeckCardDTO;
import com.op.be.usercard.model.dto.DeckCardRow;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;
import com.op.be.usercard.repository.CardRepository;
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
	CardRepository cardRepository;
	
    @Autowired
    private ModelMapper modelMapper;
    
	@Override
	public void saveDeck(UserDeckDTO userDeck) {
		deckCardRepository.deleteByDeckId(userDeck.getDeck().getId());
		for (DeckCardDTO deckCard : userDeck.getCardList()) {
			deckCardRepository.save(new DeckCard(userDeck.getDeck().getId(), deckCard.getCard().getId(),deckCard.getQtyRequired()));
		}
		deckCustomRepository.save(userDeck.getDeck());
	}
	
	@Override
	public void saveOnlyDeck(DeckDTO deckDTO, String nickcr) throws CryptException {
		userRepository.findByNick(restService.decodenick(nickcr)).ifPresent((User u) -> {
			Deck deck = modelMapper.map(deckDTO, Deck.class);
			deckDTO.setUserId(u.getId());
			deckCustomRepository.save(deck);
		});
	}
	
	@Override
	public ArrayList<UserDeckDTO> getUserDeck(String nickcr) throws CryptException{
		String nick = restService.decodenick(nickcr);
		ArrayList<Object[]> deckObj = (ArrayList<Object[]>) deckCustomRepository.findDeckUser(nick);
		ArrayList<UserDeckDTO> deckList = new ArrayList<>();
		if (!deckObj.isEmpty()) {
			Object[] ob = deckObj.get(0);
			UserDeckDTO ud = new UserDeckDTO((Deck) ob[1]);

			for (int i = 0; i < deckObj.size(); i++) {
				ob = deckObj.get(i);
				DeckCardRow deckCardRow = new DeckCardRow((Card) ob[0],(Deck) ob[1],(Long) ob[2],(int) ob[3],(int) ob[4]);
				if ((ud.getDeck().getId() != deckCardRow.getDeck().getId())) {
					deckList.add(ud);
					ud = new UserDeckDTO((Deck) ob[1]);
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
	
	@Override
	public ArrayList<Card> getAllLeader(){
		return 	(ArrayList<Card>) cardRepository.findAllLeader();	
	}
}
