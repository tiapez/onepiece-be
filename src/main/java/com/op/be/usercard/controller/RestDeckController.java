package com.op.be.usercard.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.repository.custom.DeckRepository;
import com.op.be.usercard.utils.UserDeck;

@RestController
@RequestMapping("API/Deck")
public class RestDeckController {
	
	@Autowired
	DeckRepository dr;
	
	
	@GetMapping("/test")
	public ArrayList<UserDeck> getDeck() {
		
		ArrayList<DeckDTO> deck = (ArrayList<DeckDTO>) dr.findDeckUser();
		for (DeckDTO deckDTO : deck) {
			System.out.println(deckDTO.getDeck().getId() + " - " + deckDTO.getCard().getCard().getNumber()+ " - " + deckDTO.getCard().getCard().getSetId());
		}
		ArrayList<UserDeck> deckList = new ArrayList<>();
		
		String[] arrayString = deck.get(0).getDeck().getCardList().split("/");
		ArrayList<String> deckCardString = new ArrayList<String>(Arrays.asList(arrayString));
		Collections.sort(deckCardString);
		String cqty = deckCardString.get(0);
		deck.get(0).getCard().setQtyRequired(Long.parseLong(cqty.substring(cqty.length()-1)));
		UserDeck ud = new UserDeck(deck.get(0).getDeck(),deck.get(0).getCard());
		
		int j = 0;
		for (int i = 1; i<deck.size(); i++) {
			DeckDTO deckDTO =  deck.get(i);
			if(ud.getDeck().getId() == deckDTO.getDeck().getId()) {
				cqty = deckCardString.get(i-j);
				deckDTO.getCard().setQtyRequired(Long.parseLong(cqty.substring(cqty.length()-1)));
				ud.addCard(deckDTO.getCard());
			}else {
				deckList.add(ud);
				arrayString = deck.get(i).getDeck().getCardList().split("/");
				deckCardString = new ArrayList<String>(Arrays.asList(arrayString));
				Collections.sort(deckCardString);
				deck.get(i).getCard().setQtyRequired(Long.parseLong(cqty.substring(cqty.length()-1)));
				ud = new UserDeck(deck.get(i).getDeck(),deck.get(i).getCard());
				j=i;
			}
			System.out.println(ud.getCardList().size());
		}

		deckList.add(ud);
		
		return deckList;
	}
	

}
