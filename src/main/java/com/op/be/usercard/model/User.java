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
	private int id;
	@Column
	private String username;
	@Column(name = "`password`")
	private String password;
	@Column
	private String nick;
	@Column
	private String mail;
	@Column(name = "`language`")
	private String language;
	@Column(name = "`condition`")
	private int condition;
	@Column
	private String navbar;
	@Column(name = "image")
	private byte[] image;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public int getCondition() {
		return condition;
	}


	public void setCondition(int condition) {
		this.condition = condition;
	}


	public String getNavbar() {
		return navbar;
	}


	public void setNavbar(String navbar) {
		this.navbar = navbar;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
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
