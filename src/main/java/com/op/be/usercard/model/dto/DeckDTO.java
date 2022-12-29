package com.op.be.usercard.model.dto;

import com.op.be.usercard.model.Deck;

public class DeckDTO {
	int id;

	int userId;

	int leader;

	String color1;

	String color2;

	String desc;

	String counter;

	String side;

	String note;

	String format;

	String name;

	int cond;

	String language;
	
	public DeckDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeckDTO(int id, int userId, int leader, String color1, String color2, String desc, String counter,
			String side, String note, String format, String name, int cond, String language) {
		super();
		this.id = id;
		this.userId = userId;
		this.leader = leader;
		this.color1 = color1;
		this.color2 = color2;
		this.desc = desc;
		this.counter = counter;
		this.side = side;
		this.note = note;
		this.format = format;
		this.name = name;
		this.cond = cond;
		this.language = language;
	}

	public DeckDTO(Deck d) {
		this.id = d.getId();
		this.leader = d.getLeader();
		this.color1 = d.getColor1();
		this.color2 = d.getColor2();
		this.desc = d.getDesc();
		this.counter = d.getCounter();
		this.side = d.getSide();
		this.note = d.getNote();
		this.format = d.getFormat();
		this.name = d.getName();
		this.cond = d.getCond();
		this.language = d.getLanguage();
	}
	
	public Deck getDeck() {
		return new Deck(id,userId,leader,color1,color2,desc,counter,side,note,format,name,cond,language); 
	}

	@Override
	public String toString() {
		return "DeckDTO [id=" + id + ", userId=" + userId + ", leader=" + leader + ", color1=" + color1 + ", color2="
				+ color2 + ", desc=" + desc + ", counter=" + counter + ", side=" + side + ", note=" + note + ", format="
				+ format + ", name=" + name + ", cond=" + cond + ", language=" + language + "]";
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

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
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

	public int getCond() {
		return cond;
	}

	public void setCond(int cond) {
		this.cond = cond;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	

}
