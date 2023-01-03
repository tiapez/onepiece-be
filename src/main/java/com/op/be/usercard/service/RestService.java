package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.UserCardDTO;

public interface RestService {

	public String decodeuser(String username) throws CryptException;
	
	public String codeuser(String user) throws CryptException;
	
	public User getuser(String user) throws CryptException;

	public String decodenick(String nick) throws CryptException;
	
	public String codenick(String nick) throws CryptException;
	
	public String decodepass(String pass) throws CryptException;
	
	public String codepass(String pass) throws CryptException;
	
	public String codepasstodb(String pass) throws CryptException;

	ArrayList<CardDetailsDTO> forgeCardDetails(ArrayList<UserCardDTO> userCardDTOList);



}
