package com.op.be.usercard.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.op.be.usercard.model.Card;

public class CardDetailsDTO {

	private Card card;
	private List<DetailsDTO> details;
	private int qtyMax = 1;

	public CardDetailsDTO(Card card, DetailsDTO details) {
		this.card = card;
		this.details = new ArrayList<>();
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

	public int getQtyMax() {
		return qtyMax;
	}

	public void setQtyMax(int qtyMax) {
		this.qtyMax = qtyMax;
	}

	public List<DetailsDTO> getDetailsDTO() {
		return details;
	}

	public void setDetailsDTO(List<DetailsDTO> detailsDTO) {
		this.details = detailsDTO;
	}

	@Override
	public String toString()
	{
		return "CardDetailsDTO [card=" + card + ", details=" + details + ", qtyMax=" + qtyMax + "]";
	}

	public CardDetailsDTO(Card card) {
		this.card = card;
		this.details = new ArrayList<>();
	}

}
