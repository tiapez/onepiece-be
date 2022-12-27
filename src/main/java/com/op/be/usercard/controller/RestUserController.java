package com.op.be.usercard.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.be.usercard.model.User;
import com.op.be.usercard.service.UserService;

@RestController
@RequestMapping("api/user")
public class RestUserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/getUser")
	public User getUser(@RequestParam("nick") String nick)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		return userService.getUserCryptedByNick(nick);
	}

	@PostMapping("/saveUser")
	public boolean saveUser(@RequestBody User user) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		return userService.saveUser(user);
	}

	@PostMapping("/userValidation")
	public boolean userValidation(@RequestParam("username") String username, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		return userService.userValidation(username);
	}

	@PostMapping("/nickValidation")
	public boolean nickValidation(@RequestParam("nick") String nick, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		return userService.nickValidation(nick);
	}
	
	@PostMapping( "/mailValidation")
	public boolean mailValidation(@RequestParam("mail") String mail, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		return userService.mailValidation(mail);
	}

	@GetMapping("/loginValidation")
	public String loginValidation(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response) {
		return userService.loginValidation(username, password, response);
	}

	@PutMapping("/saveUserConfig")
	public String saveUserConfig(@RequestBody User user, @RequestParam("nick") String nick)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		return userService.saveUserConfig(user, nick);

	}

}
