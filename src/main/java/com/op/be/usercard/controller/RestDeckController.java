package com.op.be.usercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserDeckDTO;
import com.op.be.usercard.service.DeckService;

@RestController
@RequestMapping("api/deck")
public class RestDeckController {

	@Autowired
	DeckService deckService;

	@GetMapping("/userDecks")
	public List<UserDeckDTO> getUserDeck(@RequestParam String nick) throws CryptException{
		return deckService.getUserDeck(nick);
	}

	@PostMapping("/saveUserDeck")
	public void saveDeck(@RequestBody UserDeckDTO userDeck) {
		deckService.saveDeck(userDeck);
	}
	
	@PostMapping("/saveOnlyDeck")
	public void saveDeck(@RequestBody DeckDTO deck, @RequestParam String nick) throws CryptException{
		deckService.saveOnlyDeck(deck, nick);
	}
	
	@GetMapping("/allLeader")
	public List<Card> getAllLeader(){
		return deckService.getAllLeader();
	}
	
	@DeleteMapping("/deleteDeck")
		public void deleteDeck(@RequestParam("id") Long id) {
		deckService.deleteDeck(id);
	}
}
