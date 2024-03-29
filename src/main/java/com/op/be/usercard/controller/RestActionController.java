package com.op.be.usercard.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.repository.UserCardRepository;
import com.op.be.usercard.service.CardService;
import com.op.be.usercard.service.RestService;

@RestController
@RequestMapping("api/card")
public class RestActionController {

	@Autowired
	UserCardRepository userCardRepository;

	@Autowired
	CardService cardService;

	@Autowired
	RestService restService;

	@PostMapping("/addDetails")
	public void addDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam("cardId") Long cardId, @RequestParam("nick") String nick,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "condition", required = false, defaultValue = "0") int condition) throws CryptException
			{

		Optional<UserCard> cu;
		UserCard userCard = null;
		if (language == null && condition == 0) {
			cu = userCardRepository.findCardUserClassic(cardId, restService.decodenick(nick));
			if (cu.isPresent()) {
				userCard = cu.get();
				userCard.setQty(userCard.getQty() + 1);
			} else {
				userCardRepository.insertUserCardClassic(cardId, restService.decodenick(nick), 1);
				return;
			}
		} else {
			Long id = (long) cardService.getDetailsId(language, condition);
			cu = userCardRepository.findCardUserDetails(cardId, restService.decodenick(nick), id);
			if (cu.isPresent()) {
				userCard = cu.get();
				userCard.setQty(userCard.getQty() + 1);
			} else {
				userCardRepository.insertUserCardDetails(cardId, restService.decodenick(nick), id, 1);
				return;
			}
		}

		userCardRepository.save(userCard);

	}

	@PostMapping("/removeDetails")
	public void minusDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "cardId") Long cardId, @RequestParam(value = "nick") String nick,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "condition", required = false, defaultValue = "0") int condition)
					throws CryptException{
		Optional<UserCard> cu;
		if (language == null && condition == 0) {
			cu = userCardRepository.findCardUserClassic(cardId, restService.decodenick(nick));
		} else {
			Long id = (long) cardService.getDetailsId(language, condition);
			cu = userCardRepository.findCardUserDetails(cardId, restService.decodenick(nick), id);
		}

		UserCard userCard = null;
		if (cu.isPresent()) {
			userCard = cu.get();
		} else {
			return;
		}
		if (userCard.getQty() == 0) {
			response.setStatus(201);
			return;
		}
		int qty = userCard.getQty() - 1;
		if (qty > 0) {
			userCard.setQty(qty);
			userCardRepository.save(userCard);
		} else {
			userCardRepository.delete(userCard);
		}
	}


}
