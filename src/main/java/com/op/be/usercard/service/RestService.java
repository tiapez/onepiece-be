package com.op.be.usercard.service;

import java.util.ArrayList;

import com.op.be.usercard.model.User;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.UserCardDTO;

public interface RestService {

	public String decodeuser(String username);
	
	public String codeuser(String user);
	
	public User getuser(String user);

	public String decodenick(String nick);
	
	public String codenick(String nick);
	
	public String decodepass(String pass);
	
	public String codepass(String pass);
	
	public String codepasstodb(String pass);

	ArrayList<CardDetailsDTO> forgeCardDetails(ArrayList<UserCardDTO> userCardDTOList);



}
