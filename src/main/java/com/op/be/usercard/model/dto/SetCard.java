package com.op.be.usercard.model.dto;

import java.util.List;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.Set;

public class SetCard
{
	Set set;
	List<Card> cardList;
	
	public Set getSet()
	{
		return set;
	}
	public void setSet(Set set)
	{
		this.set = set;
	}
	public List<Card> getCardList()
	{
		return cardList;
	}	
	public void setCardList(List<Card> cardList)
	{
		this.cardList = cardList;
	}
	
	public SetCard(Set set, List<Card> cardList)
	{
		super();
		this.set = set;
		this.cardList = cardList;
	}
	@Override
	public String toString()
	{
		return "SetCard [set=" + set + ", cardList=" + cardList + "]";
	}
	
	

}
