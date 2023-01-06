package com.op.be.usercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.Set;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.SetCard;
import com.op.be.usercard.repository.SetRepository;
import com.op.be.usercard.service.CardService;

@RestController
@RequestMapping("api/card")
public class RestCardController
{

	@Autowired
	CardService cardService;

	@Autowired
	SetRepository setRepository;

	@GetMapping("/allDetails")
	public List<CardDetailsDTO> getAllDetails(@RequestParam("nick") String nick, @RequestParam("set") String set)
			throws CryptException
	{
		return cardService.getCardDetails(nick, set);
	}

	@GetMapping("/allClassic")
	public List<CardDetailsDTO> getAllClassic(@RequestParam("nick") String nick, @RequestParam("set") String set)
			throws CryptException
	{
		return cardService.getCardClassic(nick, set);
	}

	@PostMapping("/deckCardList")
	public List<CardDetailsDTO> getCardForDeck(@RequestBody DeckDTO deck, @RequestParam String nick) throws CryptException
	{
		return cardService.getCardForDeck(deck, nick);
	}

	@GetMapping("/set")
	public List<Set> getSetList()
	{
		return setRepository.findSetList();
	}
	
	@GetMapping("/deckSet")
	public List<Set> getDeckSetList(@RequestParam("format") String format)
	{
		return setRepository.findDeckSetList(format);
	}
	
	@GetMapping("/all")
	public List<SetCard> getAll()
	{
		return cardService.getSetCardList();
	}

}
