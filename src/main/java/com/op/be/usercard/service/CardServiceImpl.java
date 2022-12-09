package com.op.be.usercard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.CardDetails;
import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.model.dto.CardWDetailsDTO;
import com.op.be.usercard.model.dto.DetailsDTO;
import com.op.be.usercard.model.dto.UserCardDTO;
import com.op.be.usercard.repository.CardRepository;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.repository.custom.CardUserCustomRepository;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	CardUserCustomRepository cucr;

	@Autowired
	CardRepository cr;

	@Autowired
	UserRepository ur;

	@Autowired
	RestService rs;

	@Override
	public ArrayList<CardWDetailsDTO> getCardDetails(String nickCr, int set) throws Exception {
		
		ArrayList<UserCardDTO> userCardDTOList = (ArrayList<UserCardDTO>) cucr.findUserCardDetailsBySet(set,
				rs.decodenick(nickCr));
		ArrayList<CardWDetailsDTO> cardList = forgeCardWDetails(userCardDTOList);
		return cardList;
	}

	@Override
	public ArrayList<CardWDetailsDTO> getCardClassic(String nickCr, int set) throws Exception {
		System.out.println(set);
		ArrayList<UserCardDTO> userCardDTOList = (ArrayList<UserCardDTO>) cucr.findUserCardClassicBySet(set,
				rs.decodenick(nickCr));

		System.out.println(userCardDTOList);
		System.out.println(set);
		ArrayList<CardWDetailsDTO> cardList = forgeCardWDetails(userCardDTOList);
		return cardList;
	}

	@Override
	public ArrayList<CardWDetailsDTO> getAll() {
		ArrayList<Card> cards = (ArrayList<Card>) cr.findAll();
		ArrayList<CardWDetailsDTO> cardList = new ArrayList<CardWDetailsDTO>();
		for (Card card : cards) {
			cardList.add(new CardWDetailsDTO(card));
		}
		return cardList;
	}

	private ArrayList<CardWDetailsDTO> forgeCardWDetails(ArrayList<UserCardDTO> userCardDTOList) throws Exception {
		ArrayList<CardWDetailsDTO> cardList = new ArrayList<CardWDetailsDTO>();
		CardWDetailsDTO cardWDetailsDTO = null;
		UserCardDTO userCardDTO = null;
		userCardDTO = userCardDTOList.get(0);
		cardWDetailsDTO = new CardWDetailsDTO(userCardDTO.getCard(),
				getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
		for (int i = 1; i < userCardDTOList.size(); i++) {
			userCardDTO = userCardDTOList.get(i);
			if (cardWDetailsDTO.getCard().getId() == userCardDTO.getCard().getId()) {
				cardWDetailsDTO.addDetails(getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
			} else {
				cardList.add(cardWDetailsDTO);
				cardWDetailsDTO = new CardWDetailsDTO(userCardDTO.getCard(),
						getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
			}
		}

		if (cardWDetailsDTO.getCard().getId() == userCardDTO.getCard().getId()) {
			cardList.add(cardWDetailsDTO);
		}
		return cardList;
	}

	private DetailsDTO getDetailsDTO(UserCard userCard, CardDetails cardDetails) {
		if (userCard == null)
			return new DetailsDTO(cardDetails.getCod_condition(), cardDetails.getLanguage(), cardDetails.getCondition(),
					0, 1);
		else
			return new DetailsDTO(cardDetails.getCod_condition(), cardDetails.getLanguage(), cardDetails.getCondition(),
					userCard.getQty(), userCard.getUserId());
	}

	@Override
	public int getDetailsId(String l, int c) {

		if (l.equals("ENG")) {
		}

		if (l.equals("JAP")) {
			c = c + 6;
		}

		return c;
	}

}
