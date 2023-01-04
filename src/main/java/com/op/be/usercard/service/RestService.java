package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.CardDetailsDTO;

public interface RestService {

	 String decodeuser(String username) throws CryptException;
	
	 String codeuser(String user) throws CryptException;
	
	 User getuser(String user) throws CryptException;

	 String decodenick(String nick) throws CryptException;
	
	 String codenick(String nick) throws CryptException;
	
	 String decodepass(String pass) throws CryptException;
	
	 String codepass(String pass) throws CryptException;
	
	 String codepasstodb(String pass) throws CryptException;

	ArrayList<CardDetailsDTO> forgeCardDetails(ArrayList<Object[]> userCardDTOList);



}
