package com.op.be.usercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.repository.custom.DeckRepository;

@RestController
@RequestMapping("API/Deck")
public class RestDeckController {
	
	@Autowired
	DeckRepository dr;
	
	
	@GetMapping("/test")
	public List<Object> getDeck() {

		return dr.findDeckUser();
	}

}
