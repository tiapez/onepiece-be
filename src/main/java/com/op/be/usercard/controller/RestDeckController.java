package com.op.be.usercard.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.model.Deck;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.CardWDetailsDTO;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.repository.DeckCardRepository;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.repository.custom.DeckRepository;
import com.op.be.usercard.service.CardService;
import com.op.be.usercard.service.RestService;
import com.op.be.usercard.utils.UserDeck;

@RestController
@RequestMapping("API/Deck")
public class RestDeckController {

	@Autowired
	DeckRepository dr;
	@Autowired
	CardService cs;
	@Autowired
	DeckCardRepository dcr;
	@Autowired
	RestService restService;
	@Autowired
	UserRepository ur;

	@GetMapping("/test")
	public ArrayList<UserDeck> getUserDeck() {

		ArrayList<DeckDTO> deck = (ArrayList<DeckDTO>) dr.findDeckUser("gambero");
		ArrayList<UserDeck> deckList = new ArrayList<>();
		if (deck.size() > 0) {
			UserDeck ud = new UserDeck(deck.get(0).getDeck());

			for (int i = 0; i < deck.size(); i++) {
				DeckDTO deckDTO = deck.get(i);
				if ((ud.getDeck().getId() != deckDTO.getDeck().getId())) {
					deckList.add(ud);
					ud = new UserDeck(deck.get(i).getDeck());
				}
				if (deckDTO.getCard().getCard().getCardType().equals("Leader")) {
					int own = 0;
					if (deckDTO.getCard().getQtyOwned() > 0) {
						own = 1;
					}
					deckDTO.getCard().setQtyOwned(own);
					deckDTO.getCard().setQtyRequired(1);
					ud.setLeader(deckDTO.getCard());
				} else {
					ud.addCard(deckDTO.getCard());
				}

			}

			deckList.add(ud);
		}
		return deckList;

	}

	@GetMapping("/test2")
	public ArrayList<CardWDetailsDTO> getCardUser(@RequestParam String nick) throws Exception {
		ArrayList<CardWDetailsDTO> cardlist = cs.getCardDetailsDeck("Eng", "Red","Red", 6, nick);
		return cardlist;
	}

	@PostMapping("/test3")
	public void saveDeck(@RequestBody UserDeck userDeck) {
		cs.saveDeck(userDeck);
	}
	
	@PostMapping("/test4")
	public void saveDeck(@RequestBody Deck deck, @RequestParam String nick) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		User user = ur.findByNick(restService.decodenick(nick)).get();
		deck.setUserId(user.getId());
		dr.save(deck);
	}
	
	
	

}
