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
	@Column(name = "`desc`")
	String desc;
	@Column
	String counter;
	@Column
	String side;
	@Column
	String note;
	@Column
	String format;
	@Column
	String name;
	@Column
	int cond;
	
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public int getCond() {
		return cond;
	}
	public void setCond(int cond) {
		this.cond = cond;
	}
	
	public Deck() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Deck(int id, int userId, int leader, String color, String cardList, String deck, String desc, String counter,
			String side, String note, String format, String name, int cond) {
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
		this.format = format;
		this.name = name;
		this.cond = cond;
	}
	public Deck(int id, int userId, int leader, String color, String cardList, String deck, String desc, String counter,
			String side, String note, String format, String name) {
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
		this.format = format;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Deck [id=" + id + ", userId=" + userId + ", leader=" + leader + ", color=" + color + ", cardList="
				+ cardList + ", deck=" + deck + ", desc=" + desc + ", counter=" + counter + ", side=" + side + ", note="
				+ note + ", format=" + format + ", name=" + name + "]";
	}


	
	
	
}
