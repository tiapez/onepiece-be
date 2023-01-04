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
	private int userId;

	@Id
	@Column
	private int cardId;

	@Id
	@Column
	private int detailsId;

	@Column
	private int qty;

	public UserCard()
	{
		super();
	}

	public UserCard(int userId, int cardId, int detailsId, int qty)
	{
		super();
		this.userId = userId;
		this.cardId = cardId;
		this.detailsId = detailsId;
		this.qty = qty;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getCardId()
	{
		return cardId;
	}

	public void setCardId(int cardId)
	{
		this.cardId = cardId;
	}

	public int getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(int detailsId)
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
