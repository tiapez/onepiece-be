package com.op.be.usercard.service;

import javax.servlet.http.HttpServletResponse;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.dto.UserDTO;

public interface UserService {

	boolean userValidation(String username);

	boolean nickValidation(String nick);

	boolean mailValidation(String mail);

	UserDTO getUserCryptedByNick(String nick)  throws CryptException;

	String loginValidation(String username, String password, HttpServletResponse response)  throws CryptException;

	boolean saveUser(UserDTO userDTO) throws CryptException;

	String saveUserConfig(UserDTO userDTO, String nick)  throws CryptException;

}
