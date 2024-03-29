package com.op.be.usercard.model;

import java.io.Serializable;
import java.util.Objects;

public class CardLimitId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long cardId;
	private String format;
	
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	public CardLimitId() {
		super();
	}
	
	public CardLimitId(Long cardId, String format) {
		super();
		this.cardId = cardId;
		this.format = format;
	}
	
	@Override
	public String toString() {
		return "CardLimitId [cardId=" + cardId + ", format=" + format + "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardLimitId other = (CardLimitId) obj;
		return cardId == other.cardId && Objects.equals(format, other.format);
	}
	
	
	
}
