package com.op.be.usercard.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.UserDTO;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.service.RestService;
import com.op.be.usercard.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;

	@Autowired
	RestService restService;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public String saveUserConfig(UserDTO userDTO,String nick){
		
		userRepository.findByNick(restService.decodenick(nick)).ifPresent(u ->{
			u.setCondition(userDTO.getCondition());
			u.setLanguage(userDTO.getLanguage());
			u.setNavbar(userDTO.getNavbar());
			u.setImage(userDTO.getImage());

			userRepository.save(u);
		});

		return new Gson().toJson(userDTO.getNavbar());
	}
	@Override
	public User getUserCryptedByNick(String nick){

		Optional<User> user = userRepository.findByNick(nick);
		User u = null;
		if(user.isPresent()) {
			u = user.get();
			u.setUsername(restService.codeuser(u.getUsername()));
			u.setPassword(restService.codepass(u.getPassword()));
			u.setNick(restService.codenick(u.getNick()));
		}

		return u;
	}

	@Override
	public boolean saveUser(UserDTO userDTO){
		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(restService.codepasstodb(restService.decodepass(user.getPassword())));
		user.setNick(restService.decodenick(user.getNick()));
		user.setUsername(restService.decodeuser(user.getUsername()));
		user.setNavbar("Light");
		user.setCondition(2);
		user.setLanguage("ENG");
		
		User u = userRepository.save(user);
		return u!=null;
	}

	@Override
	public boolean userValidation(String username){
		List<String> usersname = userRepository.getAllUsername();
		return !usersname.contains(username.toLowerCase());
	}

	@Override
	public boolean nickValidation(String nick){
		List<String> nicks = userRepository.getAllNick();
		return !nicks.contains(nick.toLowerCase());
	}
	@Override
	public boolean mailValidation(@RequestParam("mail") String mail){
		List<String> mails = userRepository.getAllMail();
		return !mails.contains(mail.toLowerCase());

	}
	
	@Override
	public String loginValidation(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response) {
			username = restService.decodeuser(username);
			password = restService.decodepass(password);
			password = restService.codepasstodb(password);

		Optional<User> u = userRepository.findByUserPass(username, password);
		if (u.isPresent()) {
			return new Gson().toJson(u.get().getNick() + "/" + u.get().getNavbar());
		} else
			response.setStatus(401);
		return null;

	}
}
