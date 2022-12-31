package com.op.be.usercard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CardLimit.class)
public class CardLimit implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private int cardId;

	@Column
	private int qtyMax;

	@Id
	@Column
	private String format;

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getQtyMax() {
		return qtyMax;
	}

	public void setQtyMax(int qtyMax) {
		this.qtyMax = qtyMax;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public CardLimit() {
		super();
	}

	public CardLimit(int cardId, int qtyMax, String format) {
		super();
		this.cardId = cardId;
		this.qtyMax = qtyMax;
		this.format = format;
	}

	@Override
	public String toString() {
		return "CardLimit [cardId=" + cardId + ", qtyMax=" + qtyMax + ", format=" + format + "]";
	}

}
