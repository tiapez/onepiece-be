package com.op.be.usercard.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.UserDTO;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.service.RestService;
import com.op.be.usercard.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RestService restService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String saveUserConfig(UserDTO userDTO, String nick) throws CryptException {
		String gson = new Gson().toJson(userDTO.getNavbar());
		userRepository.findByNick(restService.decodenick(nick)).ifPresent(u -> {
			u.setCondition(userDTO.getCondition());
			u.setLanguage(userDTO.getLanguage());
			u.setNavbar(userDTO.getNavbar());
			u.setImage(userDTO.getImage());
			userRepository.save(u);
		});

		return gson;

	}

	@Override
	public UserDTO getUserCryptedByNick(String nick) throws CryptException {

		Optional<User> user = userRepository.findByNick(restService.decodenick(nick));
		UserDTO u = null;
		if (user.isPresent()) {
			u = modelMapper.map(user.get(), UserDTO.class);
			u.setUsername(restService.codeuser(u.getUsername()));
			u.setPassword(restService.codepass(u.getPassword()));
			u.setNick(restService.codenick(u.getNick()));
		}

		return u;
	}

	@Override
	public boolean saveUser(UserDTO userDTO) throws CryptException {
		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(restService.codepasstodb(restService.decodepass(userDTO.getPassword())));
		user.setNick(restService.decodenick(userDTO.getNick()));
		user.setUsername(restService.decodeuser(userDTO.getUsername()));
		user.setNavbar("Light");
		user.setCondition(2);
		user.setLanguage("Eng");
		
		User u = userRepository.save(user);
		return u != null;
	}

	@Override
	public boolean userValidation(String username) {
		List<String> usersname = userRepository.getAllUsername();
		return !usersname.contains(username.toLowerCase());
	}

	@Override
	public boolean nickValidation(String nick) {
		List<String> nicks = userRepository.getAllNick();
		return !nicks.contains(nick.toLowerCase());
	}

	@Override
	public boolean mailValidation(String mail) {
		List<String> mails = userRepository.getAllMail();
		return !mails.contains(mail.toLowerCase());

	}

	@Override
	public String loginValidation(String username, String password, HttpServletResponse response)
			throws CryptException {
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
