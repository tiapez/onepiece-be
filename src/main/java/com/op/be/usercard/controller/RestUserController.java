package com.op.be.usercard.controller;

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

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.dto.UserDTO;
import com.op.be.usercard.service.UserService;

@RestController
@RequestMapping("api/user")
public class RestUserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/getUser")
	public UserDTO getUser(@RequestParam("nick") String nick) throws CryptException{
		return userService.getUserCryptedByNick(nick);
	}

	@PostMapping("/saveUser")
	public boolean saveUser(@RequestBody UserDTO user) throws CryptException{
		return userService.saveUser(user);
	}

	@PostMapping("/userValidation")
	public boolean userValidation(@RequestParam("username") String username, HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		return userService.userValidation(username);
	}

	@PostMapping("/nickValidation")
	public boolean nickValidation(@RequestParam("nick") String nick, HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		return userService.nickValidation(nick);
	}
	
	@PostMapping( "/mailValidation")
	public boolean mailValidation(@RequestParam("mail") String mail, HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		return userService.mailValidation(mail);
	}

	@GetMapping("/loginValidation")
	public String loginValidation(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response) throws CryptException {
		return userService.loginValidation(username, password, response);
	}

	@PutMapping("/saveUserConfig")
	public String saveUserConfig(@RequestBody UserDTO user, @RequestParam("nick") String nick) throws CryptException {
		return userService.saveUserConfig(user, nick);

	}

}
