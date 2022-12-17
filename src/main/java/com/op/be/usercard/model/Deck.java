package com.op.be.usercard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column
	int userId;
	@Column
	int leader;
	@Column
	String color;
	@Column
	String cardList;
	@Column
	String deck;
	@Column
	String desc;
	@Column
	String counter;
	@Column
	String side;
	@Column
	String note;
	@Column
	String format;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLeader() {
		return leader;
	}
	public void setLeader(int leader) {
		this.leader = leader;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCardList() {
		return cardList;
	}
	public void setCardList(String cardList) {
		this.cardList = cardList;
	}
	public String getDeck() {
		return deck;
	}
	public void setDeck(String deck) {
		this.deck = deck;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Deck() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Deck(int id, int userId, int leader, String color, String cardList, String deck, String desc, String counter,
			String side, String note) {
		super();
		this.id = id;
		this.userId = userId;
		this.leader = leader;
		this.color = color;
		this.cardList = cardList;
		this.deck = deck;
		this.desc = desc;
		this.counter = counter;
		this.side = side;
		this.note = note;
	}
	@Override
	public String toString() {
		return "Deck [id=" + id + ", userId=" + userId + ", leader=" + leader + ", color=" + color + ", cardList="
				+ cardList + ", deck=" + deck + ", desc=" + desc + ", counter=" + counter + ", side=" + side + ", note="
				+ note + "]";
	}
	
	
	
}
