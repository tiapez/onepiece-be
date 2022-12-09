package com.op.be.usercard.model.dto;

import java.util.ArrayList;

import com.op.be.usercard.model.Card;

public class CardWDetailsDTO {

	public Card card;
	public ArrayList<DetailsDTO> details;

	public CardWDetailsDTO(Card card, DetailsDTO details) {
		this.card = card;
		this.details = new ArrayList<DetailsDTO>();
		this.details.add(details);
	}

	public void addDetails(DetailsDTO details) {
		this.details.add(details);
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public ArrayList<DetailsDTO> getDetailsDTO() {
		return details;
	}

	public void setDetailsDTO(ArrayList<DetailsDTO> detailsDTO) {
		this.details = detailsDTO;
	}

	@Override
	public String toString() {
		return "CardWDetailsDTO [card=" + card + ", detailsDTO=" + details + "]";
	}

	public CardWDetailsDTO(Card card) {
		this.card = card;
		this.details = new ArrayList<DetailsDTO>();
	}

}
