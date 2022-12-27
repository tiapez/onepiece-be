package com.op.be.usercard.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.op.be.usercard.model.User;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.service.RestService;
import com.op.be.usercard.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;

	@Autowired
	RestService restService;
	
	@Override
	public String saveUserConfig(User user,String nick){
		User u = userRepository.findByNick(restService.decodenick(nick)).get();
		u.setCondition(user.getCondition());
		u.setLanguage(user.getLanguage());
		u.setNavbar(user.getNavbar());
		u.setImage(user.getImage());

		userRepository.save(u);
		return new Gson().toJson(user.getNavbar());

	}
	@Override
	public User getUserCryptedByNick(String nick){
		User u = userRepository.findByNick(nick).get();
			u.setUsername(restService.codeuser(u.getUsername()));
			u.setPassword(restService.codepass(u.getPassword()));
			u.setNick(restService.codenick(u.getNick()));
		return u;
	}

	@Override
	public boolean saveUser(User user){
		user.setPassword(restService.codepasstodb(restService.decodepass(user.getPassword())));
		user.setNick(restService.decodenick(user.getNick()));
		user.setUsername(restService.decodeuser(user.getUsername()));
		user.setNavbar("Light");
		user.setCondition(2);
		user.setLanguage("ENG");
		
		User u = userRepository.save(user);
		if (u != null)
			return true;
		return false;
	}

	@Override
	public boolean userValidation(String username){
		List<String> usersname = userRepository.getAllUsername();
		if (usersname.contains(username.toLowerCase()))
			return false;
		else
			return true;
	}

	@Override
	public boolean nickValidation(String nick){
		List<String> nicks = userRepository.getAllNick();
		if (nicks.contains(nick.toLowerCase()))
			return false;
		else
			return true;
	}
	@Override
	public boolean mailValidation(@RequestParam("mail") String mail){
		List<String> mails = userRepository.getAllMail();
		if (mails.contains(mail.toLowerCase()))
			return false;
		else
			return true;
	}
	
	@Override
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

		Optional<User> u = userRepository.findByUserPass(username, password);
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
}
