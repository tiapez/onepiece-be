package com.op.be.usercard.service;

import javax.servlet.http.HttpServletResponse;

import com.op.be.usercard.model.User;

public interface UserService {

	boolean saveUser(User user);

	boolean userValidation(String username);

	boolean nickValidation(String nick);

	boolean mailValidation(String mail);

	String saveUserConfig(User user, String nick);

	User getUserCryptedByNick(String nick);

	String loginValidation(String username, String password, HttpServletResponse response);

}
