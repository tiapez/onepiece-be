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
import com.op.be.usercard.service.CardService;

@RestController
@RequestMapping("api/card")
public class RestCardController {

	@Autowired
	CardService cardService;

	@GetMapping("/getAllDetails")
	public List<CardDetailsDTO> getAllDetails(@RequestParam("nick") String nick, @RequestParam("set") String set){
		return cardService.getCardDetails(nick, set);
	}

	@GetMapping("/getAllClassic")
	public List<CardDetailsDTO> getAllClassic(@RequestParam("nick") String nick, @RequestParam("set") String set){
		return cardService.getCardClassic(nick, set);
	}

	@GetMapping("/getAll")
	public List<CardDetailsDTO> getAll(){
		return cardService.getAll();
	}
	
	@PostMapping("/deckCardList")
	public List<CardDetailsDTO> getCardUser(@RequestBody DeckDTO deck,@RequestParam String nick){
		return cardService.getCardDetailsDeck(deck, nick);
	}

}
