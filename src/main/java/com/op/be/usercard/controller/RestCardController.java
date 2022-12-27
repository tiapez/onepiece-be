package com.op.be.usercard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.service.CardService;

@RestController
@RequestMapping("api/card")
public class RestCardController {

	@Autowired
	CardService cardService;

	@GetMapping("/getAllDetails")
	public ArrayList<CardDetailsDTO> getAllDetails(@RequestParam("nick") String nick, @RequestParam("set") String set){
		return cardService.getCardDetails(nick, set);
	}

	@GetMapping("/getAllClassic")
	public ArrayList<CardDetailsDTO> getAllClassic(@RequestParam("nick") String nick, @RequestParam("set") String set){
		return cardService.getCardClassic(nick, set);
	}

	@GetMapping("/getAll")
	public ArrayList<CardDetailsDTO> getAll() throws Exception {
		return cardService.getAll();
	}
	
	@GetMapping("/getAllLeader")
	public ArrayList<Card> getAllLeader() throws Exception {
		return cardService.getAllLeader();
	}

}
