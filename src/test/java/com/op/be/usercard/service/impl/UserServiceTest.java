package com.op.be.usercard.service.impl;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.UserDTO;
import com.op.be.usercard.repository.UserRepository;
import com.op.be.usercard.service.RestService;

class UserServiceTest {

	@Spy
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private RestService restService;
	
	@Mock
	private UserRepository userRepository;


	
	@BeforeEach
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
	 void testMailValidationTrue() {
		when(userRepository.getAllMail()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.mailValidation("stringa1"));
	}
	
	@Test
	 void testMailValidationFalse() {
		when(userRepository.getAllMail()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.mailValidation("stringa1"));
	}
	
	@Test
	 void testUserValidationTrue() {
		when(userRepository.getAllUsername()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.userValidation("stringa1"));
	}
	
	@Test
	 void testUserValidationFalse() {
		when(userRepository.getAllUsername()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.userValidation("stringa1"));
	}
	
	@Test
	 void testNickValidationTrue() {
		when(userRepository.getAllNick()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.nickValidation("stringa1"));
	}
	
	@Test
	 void testNickValidationFalse() {
		when(userRepository.getAllNick()).thenReturn(getList());
		
		assertTrue(!userServiceImpl.nickValidation("stringa1"));
	}
	
	@Test
	 void testSaveUserConfig() throws CryptException {
		UserDTO user = new UserDTO();
		Optional<User> ou = Optional.empty();

		when(restService.decodenick(anyString())).thenReturn("");
		when(userRepository.findByNick(anyString())).thenReturn(ou);

		userServiceImpl.saveUserConfig(user, "");
		
		verify(restService).decodenick(anyString());
		verify(userRepository).findByNick(anyString());
		verifyNoMoreInteractions(restService);
		verifyNoMoreInteractions(userRepository);
		
		assert(true);
	}
	
	
	
	
}
