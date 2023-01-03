package com.op.be.usercard.model.dto;

import java.util.Arrays;

public class UserDTO {

	private int id = 0;
	private String username;
	private String password;
	private String nick;
	private String mail;
	private String language;
	private int condition = 0;
	private String navbar;
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
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", nick=" + nick + ", mail="
				+ mail + ", language=" + language + ", condition=" + condition + ", navbar=" + navbar + ", image="
				+ Arrays.toString(image) + "]";
	}
	
	public boolean isEmpty() {
		return id == 0 || username == null || password == null || nick == null || mail == null || language == null ||  condition == 0 || navbar == null;
	}
	
	
	
}
