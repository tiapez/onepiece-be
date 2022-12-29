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
	String color1;
	@Column
	String color2;
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
	@Column
	String language;
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	public String getColor2() {
		return color2;
	}
	public void setColor2(String color2) {
		this.color2 = color2;
	}
	public String getColor1() {
		return color1;
	}
	public void setColor1(String color1) {
		this.color1 = color1;
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
	}
	
	public Deck(int id, int userId, int leader, String color1, String color2, String desc, String counter, String side,
			String note, String format, String name, int cond) {
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
	}
	
	public Deck(int id, int userId, int leader, String color1, String color2, String desc, String counter, String side,
			String note, String format, String name, int cond, String language) {
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
	
	@Override
	public String toString() {
		return "Deck [id=" + id + ", userId=" + userId + ", leader=" + leader + ", color1=" + color1 + ", color2="
				+ color2 + ", desc=" + desc + ", counter=" + counter + ", side=" + side + ", note=" + note + ", format="
				+ format + ", name=" + name + ", cond=" + cond + ", language=" + language + "]";
	}

	




	
	
	
}
