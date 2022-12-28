package com.op.be.usercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;
import com.op.be.usercard.service.DeckService;

@RestController
@RequestMapping("api/deck")
public class RestDeckController {

	@Autowired
	DeckService deckService;

	@GetMapping("/userDecks")
	public List<UserDeckDTO> getUserDeck(@RequestParam String nick){
		return deckService.getUserDeck(nick);
	}

	@PostMapping("/deckCardList")
	public List<CardDetailsDTO> getCardUser(@RequestBody DeckDTO deck,@RequestParam String nick){
		return deckService.getCardDetailsDeck(deck, nick);
	}

	@PostMapping("/saveUserDeck")
	public void saveDeck(@RequestBody UserDeckDTO userDeck) {
		deckService.saveDeck(userDeck);
	}
	
	@PostMapping("/saveOnlyDeck")
	public void saveDeck(@RequestBody DeckDTO deck, @RequestParam String nick){
		deckService.saveOnlyDeck(deck, nick);
	}
	
	
	

}
