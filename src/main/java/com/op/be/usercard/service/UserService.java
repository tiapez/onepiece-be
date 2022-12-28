package com.op.be.usercard.service;

import javax.servlet.http.HttpServletResponse;

import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.UserDTO;

public interface UserService {

	boolean userValidation(String username);

	boolean nickValidation(String nick);

	boolean mailValidation(String mail);

	User getUserCryptedByNick(String nick);

	String loginValidation(String username, String password, HttpServletResponse response);

	boolean saveUser(UserDTO userDTO);

	String saveUserConfig(UserDTO userDTO, String nick);

}
