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
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;
import com.op.be.usercard.service.DeckService;

@RestController
@RequestMapping("api/deck")
public class RestDeckController {

	@Autowired
	DeckService deckService;

	@GetMapping("/userDecks")
	public ArrayList<UserDeckDTO> getUserDeck(@RequestParam String nick) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		return deckService.getUserDeck(nick);
	}

	@PostMapping("/deckCardList")
	public ArrayList<CardDetailsDTO> getCardUser(@RequestBody Deck deck,@RequestParam String nick) throws Exception {
		ArrayList<CardDetailsDTO> cardlist = deckService.getCardDetailsDeck(deck, nick);
		return cardlist;
	}

	@PostMapping("/saveUserDeck")
	public void saveDeck(@RequestBody UserDeckDTO userDeck) {
		deckService.saveDeck(userDeck);
	}
	
	@PostMapping("/saveOnlyDeck")
	public void saveDeck(@RequestBody Deck deck, @RequestParam String nick) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		deckService.saveOnlyDeck(deck, nick);
	}
	
	
	

}
