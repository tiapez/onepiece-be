package com.op.be.usercard.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.repository.UserCardRepository;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.service.CardService;
import com.op.be.usercard.service.RestService;

@RestController
@RequestMapping("API/Action")
public class RestActionController {

	@Autowired
	UserCardRepository cur;

	@Autowired
	UserRepository ur;

	@Autowired
	CardService cs;

	@Autowired
	RestService rs;

	@RequestMapping(value = "/addDetails", method = RequestMethod.POST)
	public void addDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "cardId") int cardId, @RequestParam(value = "nick") String nick,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "condition", required = false, defaultValue = "0") int condition)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		Optional<UserCard> cu = null;
		UserCard userCard = null;
		if (language == null && condition == 0) {
			cu = cur.findCardUserClassic(cardId, rs.decodenick(nick));
			if (cu.isPresent()) {
				userCard = cu.get();
				userCard.setQty(userCard.getQty() + 1);
			} else {
				cur.insertUserCardClassic(cardId, rs.decodenick(nick), 1);
				return;
			}
		} else {
			int id = cs.getDetailsId(language, condition);
			cu = cur.findCardUserDetails(cardId, rs.decodenick(nick), id);
			if (cu.isPresent()) {
				userCard = cu.get();
				userCard.setQty(userCard.getQty() + 1);
			} else {
				cur.insertUserCardDetails(cardId, rs.decodenick(nick), id, 1);
				return;
			}
		}

		cur.save(userCard);

	}

	@RequestMapping(value = "/removeDetails", method = RequestMethod.POST)
	public void minusDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "cardId") int cardId, @RequestParam(value = "nick") String nick,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "condition", required = false, defaultValue = "0") int condition)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Optional<UserCard> cu = null;
		if (language == null && condition == 0) {
			cu = cur.findCardUserClassic(cardId, rs.decodenick(nick));
		} else {
			int id = cs.getDetailsId(language, condition);
			cu = cur.findCardUserDetails(cardId, rs.decodenick(nick), id);
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
			cur.save(userCard);
		} else {
			cur.delete(userCard);
		}
	}

}
