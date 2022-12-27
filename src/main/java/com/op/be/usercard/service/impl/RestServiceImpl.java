package com.op.be.usercard.service.impl;

import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.op.be.usercard.model.CardDetails;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DetailsDTO;
import com.op.be.usercard.model.dto.UserCardDTO;
import com.op.be.usercard.service.RestService;

@Service
public class RestServiceImpl implements RestService {

	@Override
	public String decodeuser(String username) {
		try {
			String keyString = "6543210987654321";
			AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
			SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			byte[] decodeBase64 = Base64.decodeBase64(username);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			return new String(cipher.doFinal(decodeBase64), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String decodepass(String password){
		try {
			String keyString = "1234567890123456";
			AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
			SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			byte[] decodeBase64 = Base64.decodeBase64(password);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			return new String(cipher.doFinal(decodeBase64), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String decodenick(String nick){
		try {
			String keyString = "a54321098765432z";
			AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
			SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			byte[] decodeBase64 = Base64.decodeBase64(nick);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			return new String(cipher.doFinal(decodeBase64), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String codeuser(String user){
		try {
			String keyString = "6543210987654321";
			AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
			SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			// byte[] codeBase64 = Base64.encodeBase64(user.getBytes());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encValue = cipher.doFinal(user.getBytes());
			byte[] encryptedByteValue = new Base64().encode(encValue);
			return new String(encryptedByteValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String codepass(String pass){
		try {
			String keyString = "1234567890123456";
			AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
			SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encValue = cipher.doFinal(pass.getBytes());
			byte[] encryptedByteValue = new Base64().encode(encValue);
			return new String(encryptedByteValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String codenick(String nick){
		try {
			String keyString = "a54321098765432z";
			AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
			SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encValue = cipher.doFinal(nick.getBytes());
			byte[] encryptedByteValue = new Base64().encode(encValue);
			return new String(encryptedByteValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User getuser(String user){
		user = this.decodepass(user);
		user = this.decodeuser(user);

		return new Gson().fromJson(user, User.class);
	}

	@Override
	public String codepasstodb(String pass){
		try {

			String keyString = "1nuovapassworddb";
			AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
			SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encValue = cipher.doFinal(pass.getBytes());
			byte[] encryptedByteValue = new Base64().encode(encValue);
			return new String(encryptedByteValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<CardDetailsDTO> forgeCardDetails(ArrayList<UserCardDTO> userCardDTOList){
		ArrayList<CardDetailsDTO> cardList = new ArrayList<CardDetailsDTO>();
		CardDetailsDTO CardDetailsDTO = null;
		UserCardDTO userCardDTO = null;
		userCardDTO = userCardDTOList.get(0);
		CardDetailsDTO = new CardDetailsDTO(userCardDTO.getCard(),
				getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
		for (int i = 1; i < userCardDTOList.size(); i++) {
			userCardDTO = userCardDTOList.get(i);
			if (CardDetailsDTO.getCard().getId() == userCardDTO.getCard().getId()) {
				CardDetailsDTO.addDetails(getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
				CardDetailsDTO.setQtyMax(userCardDTO.getQtyMax());
			} else {
				cardList.add(CardDetailsDTO);
				CardDetailsDTO = new CardDetailsDTO(userCardDTO.getCard(),
						getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
			}
		}

		if (CardDetailsDTO.getCard().getId() == userCardDTO.getCard().getId()) {
			cardList.add(CardDetailsDTO);
		}
		return cardList;
	}

	private DetailsDTO getDetailsDTO(UserCard userCard, CardDetails cardDetails) {
		if (userCard == null)
			return new DetailsDTO(cardDetails.getCodCondition(), cardDetails.getLanguage(), cardDetails.getCondition(),
					0, 1);
		else
			return new DetailsDTO(cardDetails.getCodCondition(), cardDetails.getLanguage(), cardDetails.getCondition(),
					userCard.getQty(), userCard.getUserId());
	}

}
