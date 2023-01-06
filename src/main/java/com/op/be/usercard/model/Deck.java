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
	private Long id;
	@Column
	private Long userId;
	@Column
	private Long leader;
	@Column
	private String color1;
	@Column
	private String color2;
	@Column(name = "`desc`")
	private String desc;
	@Column
	private String counter;
	@Column
	private String side;
	@Column
	private String note;
	@Column
	private String format;
	@Column
	private String name;
	@Column
	private int cond;
	@Column
	private String language;
	@Column(name = "image")
	private byte[] image;
	
	
	
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getLeader() {
		return leader;
	}
	public void setLeader(Long leader) {
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
	
	public Deck(Long id, Long userId, Long leader, String color1, String color2, String desc, String counter, String side,
			String note, String format, String name, int cond, String language, byte[] image) {
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
		this.image = image;
	}
	@Override
	public String toString() {
		return "Deck [id=" + id + ", userId=" + userId + ", leader=" + leader + ", color1=" + color1 + ", color2="
				+ color2 + ", desc=" + desc + ", counter=" + counter + ", side=" + side + ", note=" + note + ", format="
				+ format + ", name=" + name + ", cond=" + cond + ", language=" + language + "]";
	}

	




	
	
	
}
