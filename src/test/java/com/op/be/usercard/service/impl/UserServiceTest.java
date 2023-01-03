package com.op.be.usercard.service.impl;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.UserDTO;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.service.RestService;

public class UserServiceTest {

	@Spy
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private RestService restService;
	
	@Mock
	private UserRepository userRepository;


	
	@Before
    public void setUp() {
    	MockitoAnnotations.openMocks(this);

    }
	
	public List<String> getList(){
		List<String> list = new ArrayList<>();
    	list.add("stringa1");
    	list.add("stringa2");
    	list.add("stringa3");
    	
    	return list;
	}
    
	@Test
	public void mailValidationTrueTest() {
		when(userRepository.getAllMail()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.mailValidation("stringa1"));
	}
	
	@Test
	public void mailValidationFalseTest() {
		when(userRepository.getAllMail()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.mailValidation("stringa1"));
	}
	
	@Test
	public void userValidationTrueTest() {
		when(userRepository.getAllUsername()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.userValidation("stringa1"));
	}
	
	@Test
	public void userValidationFalseTest() {
		when(userRepository.getAllUsername()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.userValidation("stringa1"));
	}
	
	@Test
	public void nickValidationTrueTest() {
		when(userRepository.getAllNick()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.nickValidation("stringa1"));
	}
	
	@Test
	public void nickValidationFalseTest() {
		when(userRepository.getAllNick()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.nickValidation("stringa1"));
	}
	
	@Test
	public void saveUserConfig() throws CryptException {
		UserDTO user = new UserDTO();
		Optional<User> ou = Optional.empty();

		when(restService.decodenick(anyString())).thenReturn("");
		when(userRepository.findByNick(anyString())).thenReturn(ou);

		userServiceImpl.saveUserConfig(user, "");
		verifyNoInteractions(restService);

		
	}
	
	
	
	
}
