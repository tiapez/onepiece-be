package com.op.be.usercard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "`user`")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column
	String username;
	@Column(name = "`password`")
	String password;
	@Column
	String nick;
	@Column
	String mail;
	@Column(name = "`language`")
	String language;
	@Column(name = "`condition`")
	int condition;
	@Column
	String navbar;
	@Column(name = "image")
	byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public void setNavbar(String navbar) {
		this.navbar = navbar;
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getNavbar() {
		return navbar;
	}


	public User() {
		super();
	}
	
	
	public User(String username, String password, String nick, String mail) {
		super();
		this.username = username;
		this.password = password;
		this.nick = nick;
		this.mail = mail;
	}

	public User(int id, String username, String password, String nick, String mail, String language, int condition,
			String navbar, byte[] image) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nick = nick;
		this.mail = mail;
		this.language = language;
		this.condition = condition;
		this.navbar = navbar;
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nick=" + nick + ", mail="
				+ mail + ", language=" + language + ", condition=" + condition + ", navbar=" + navbar + ", image="
				+ image + "]";
	}


}
