package com.op.be.usercard.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.op.be.usercard.model.User;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.service.RestService;

@RestController
@RequestMapping("API/User")
public class RestUserController {

	@Autowired
	UserRepository ur;

	@Autowired
	RestService restService;

	@GetMapping(value = "/getUser")
	public User getUser2(@RequestParam("nick") String nick)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		User u = ur.findByNick(nick).get();
			u.setUsername(restService.codeuser(u.getUsername()));
			u.setPassword(restService.codepass(u.getPassword()));
			u.setNick(restService.codenick(u.getNick()));
		return u;
	}

	@PostMapping(value = "/saveUser")
	public boolean postUser(@RequestBody User user) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		System.out.println(user.toString());
		user.setPassword(restService.codepasstodb(restService.decodepass(user.getPassword())));
		user.setNick(restService.decodenick(user.getNick()));
		user.setUsername(restService.decodeuser(user.getUsername()));
		user.setNavbar("Light");
		user.setCondition(2);
		user.setLanguage("ENG");
		
		User u = ur.save(user);
		if (u != null)
			return true;
		return false;
	}

	@PostMapping(value = "/userValidation")
	public boolean userValidation(@RequestParam("username") String username, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		List<String> usersname = ur.getAllUsername();
		if (usersname.contains(username.toLowerCase()))
			return false;
		else
			return true;
	}

	@RequestMapping(value = "/nickValidation", method = RequestMethod.POST)
	public boolean nickValidation(@RequestParam("nick") String nick, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		List<String> nicks = ur.getAllNick();
		if (nicks.contains(nick.toLowerCase()))
			return false;
		else
			return true;
	}
	
	@RequestMapping(value = "/mailValidation", method = RequestMethod.POST)
	public boolean mailValidation(@RequestParam("mail") String mail, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		List<String> mails = ur.getAllMail();
		if (mails.contains(mail.toLowerCase()))
			return false;
		else
			return true;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@CookieValue(name = "navType", required = false, defaultValue = "Light") String navType) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("navType", navType);
		return mv;
	}

	@GetMapping("/loginValidation")
	public String loginValidation(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response) {
		try {
			username = restService.decodeuser(username);
			password = restService.decodepass(password);
			password = restService.codepasstodb(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Optional<User> u = ur.findByUserPass(username, password);
		if (u.isPresent()) {
//			Cookie cookie = new Cookie("navType2", "asd");
//			cookie.setMaxAge(-1);
//			cookie.setPath("/");
//			cookie.setDomain("localhost");
//			cookie.setHttpOnly(false);
//			response.addCookie(cookie);
//			Cookie cookie2 = new Cookie("asd", "asd");
//			cookie2.setMaxAge(-1);
//			cookie2.setPath("/");
//			cookie2.setDomain("localhost");
//			cookie2.setHttpOnly(false);
//			response.addCookie(cookie2);
			String nick = new Gson().toJson(u.get().getNick() + "/" + u.get().getNavbar());
			return nick;
		} else
			response.setStatus(401);
		return null;

	}

	@PutMapping(value = "/saveUserConfig")
	public String saveUserConfig(@RequestBody User user, @RequestParam("nick") String nick)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		User u = ur.findByNick(restService.decodenick(nick)).get();
		u.setCondition(user.getCondition());
		u.setLanguage(user.getLanguage());
		u.setNavbar(user.getNavbar());
		u.setImage(user.getImage());

		ur.save(u);
		return new Gson().toJson(user.getNavbar());

	}

}
