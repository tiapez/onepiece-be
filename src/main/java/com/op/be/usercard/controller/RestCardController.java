package com.op.be.usercard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.model.dto.CardWDetailsDTO;
import com.op.be.usercard.repository.CardRepository;
import com.op.be.usercard.service.CardService;

@RestController
@RequestMapping("API/Card")
public class RestCardController {

	@Autowired
	CardRepository cardRepository;

	@Autowired
	CardService cs;

	@GetMapping(value = "/getAllDetails")
	public ArrayList<CardWDetailsDTO> getAllDetails(@RequestParam("nick") String nick, @RequestParam("set") int set)
			throws Exception {
		System.out.println(nick);
		if(nick.equals(""))
			return null;
		ArrayList<CardWDetailsDTO> cardlist = cs.getCardDetails(nick, set);

		return cardlist;
	}

	@GetMapping(value = "/getAllClassic")
	public ArrayList<CardWDetailsDTO> getAllClassic(@RequestParam("nick") String nick, @RequestParam("set") int set)
			throws Exception {
		ArrayList<CardWDetailsDTO> cardlist = cs.getCardClassic(nick, set);

		return cardlist;
	}

	@GetMapping(value = "/getAll")
	public ArrayList<CardWDetailsDTO> getAll() throws Exception {
		ArrayList<CardWDetailsDTO> cardlist = cs.getAll();
		return cardlist;
	}

}
