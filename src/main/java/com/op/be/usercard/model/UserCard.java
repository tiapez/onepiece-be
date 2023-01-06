package com.op.be.usercard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(UserCardId.class)
public class UserCard	
{

	@Id
	@Column
	private Long userId;

	@Id
	@Column
	private Long cardId;

	@Id
	@Column
	private Long detailsId;

	@Column
	private int qty;

	public UserCard()
	{
		super();
	}

	public UserCard(Long userId, Long cardId, Long detailsId, int qty)
	{
		super();
		this.userId = userId;
		this.cardId = cardId;
		this.detailsId = detailsId;
		this.qty = qty;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getCardId()
	{
		return cardId;
	}

	public void setCardId(Long cardId)
	{
		this.cardId = cardId;
	}

	public Long getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Long detailsId)
	{
		this.detailsId = detailsId;
	}

	public int getQty()
	{
		return qty;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

	@Override
	public String toString()
	{
		return "UserCard [userId=" + userId + ", cardId=" + cardId + ", detailsId=" + detailsId + ", qty=" + qty + "]";
	}

}
