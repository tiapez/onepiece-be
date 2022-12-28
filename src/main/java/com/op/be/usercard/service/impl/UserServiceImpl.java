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
	public UserDTO getUserCryptedByNick(String nick){

		Optional<User> user = userRepository.findByNick(restService.decodenick(nick));
		UserDTO u = null;
		if(user.isPresent()) {
			u = modelMapper.map(user.get(),UserDTO.class);
			u.setUsername(restService.codeuser(u.getUsername()));
			u.setPassword(restService.codepass(u.getPassword()));
			u.setNick(restService.codenick(u.getNick()));
		}

		return u;
	}

	@Override
	public boolean saveUser(UserDTO userDTO){
		userDTO.setPassword(restService.codepasstodb(restService.decodepass(userDTO.getPassword())));
		userDTO.setNick(restService.decodenick(userDTO.getNick()));
		userDTO.setUsername(restService.decodeuser(userDTO.getUsername()));
		userDTO.setNavbar("Light");
		userDTO.setCondition(2);
		userDTO.setLanguage("ENG");
		User user = modelMapper.map(userDTO, User.class);
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
