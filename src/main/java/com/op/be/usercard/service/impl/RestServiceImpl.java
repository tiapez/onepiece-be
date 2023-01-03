package com.op.be.usercard.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.CardDetails;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DetailsDTO;
import com.op.be.usercard.model.dto.UserCardDTO;
import com.op.be.usercard.service.RestService;


@Service
public class RestServiceImpl implements RestService {

	
	@Value("${UserKey}")
	private String userKey;
	@Value("${PassKey}")
	private String passKey;
	@Value("${NickKey}")
	private String nickKey;
	@Value("${PassToDBKey}")
	private String passToDBKey;
	@Value("${Chiper}")
	private String cipherString;
	@Value("${Chiper1}")
	private String keyString;
	
	private static String chiperError = "Incorrect cipher string";
	private static String keyError = "Incorrect key / algorithm";
	private static String crDrError = "Error while crypting / decrypting";
	
	private static final Logger logger = LoggerFactory.getLogger(RestServiceImpl.class);
	
	
	@Override
	public String decodeuser(String username) throws CryptException {

			AlgorithmParameterSpec iv = new IvParameterSpec(userKey.getBytes());
			SecretKey key = new SecretKeySpec(userKey.getBytes(), keyString);
			byte[] decodeBase64 = Base64.decodeBase64(username);
			Cipher cipher;
			try {
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.DECRYPT_MODE, key, iv);
				return new String(cipher.doFinal(decodeBase64), StandardCharsets.UTF_8);
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				logger.error("RestService - Decoding User");
				if(e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException  ) {
					throw new CryptException(chiperError );
				}
				if(e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException  ) {
					throw new CryptException(keyError);
				}
				if(e instanceof IllegalBlockSizeException || e instanceof BadPaddingException  ) {
					throw new CryptException(crDrError);
				}
			}

			
		

		return null;
	}

	@Override
	public String decodepass(String password) throws CryptException {

			AlgorithmParameterSpec iv = new IvParameterSpec(passKey.getBytes());
			SecretKey key = new SecretKeySpec(passKey.getBytes(), keyString);
			byte[] decodeBase64 = Base64.decodeBase64(password);
			Cipher cipher;
			try {
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.DECRYPT_MODE, key, iv);
				return new String(cipher.doFinal(decodeBase64), StandardCharsets.UTF_8);
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				logger.error("RestService - Decoding Password");
				if(e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException  ) {
					throw new CryptException(chiperError );
				}
				if(e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException  ) {
					throw new CryptException(keyError);
				}
				if(e instanceof IllegalBlockSizeException || e instanceof BadPaddingException  ) {
					throw new CryptException(crDrError);
				}
			}
		return null;
	}

	@Override
	public String decodenick(String nick) throws CryptException {

			AlgorithmParameterSpec iv = new IvParameterSpec(nickKey.getBytes());
			SecretKey key = new SecretKeySpec(nickKey.getBytes(), keyString);
			byte[] decodeBase64 = Base64.decodeBase64(nick);
			Cipher cipher;
			try {
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.DECRYPT_MODE, key, iv);
				return new String(cipher.doFinal(decodeBase64), StandardCharsets.UTF_8);
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				logger.error("RestService - Decoding Nick");
				if(e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException  ) {
					throw new CryptException(chiperError );
				}
				if(e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException  ) {
					throw new CryptException(keyError);
				}
				if(e instanceof IllegalBlockSizeException || e instanceof BadPaddingException  ) {
					throw new CryptException(crDrError);
				}
			}

		return null;
	}

	@Override
	public String codeuser(String user) throws CryptException {
		logger.info(keyString);
			AlgorithmParameterSpec iv = new IvParameterSpec(userKey.getBytes());
			SecretKey key = new SecretKeySpec(userKey.getBytes(), keyString);
			Cipher cipher;
			try {
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.ENCRYPT_MODE, key, iv);
				byte[] encValue = cipher.doFinal(user.getBytes());
				byte[] encryptedByteValue = new Base64().encode(encValue);
				return new String(encryptedByteValue);
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				logger.error("RestService - Coding User");
				if(e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException  ) {
					throw new CryptException(chiperError );
				}
				if(e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException  ) {
					throw new CryptException(keyError);
				}
				if(e instanceof IllegalBlockSizeException || e instanceof BadPaddingException  ) {
					throw new CryptException(crDrError);
				}
			}
		return null;
	}

	@Override
	public String codepass(String pass) throws CryptException {
			AlgorithmParameterSpec iv = new IvParameterSpec(passKey.getBytes());
			SecretKey key = new SecretKeySpec(passKey.getBytes(), keyString);
			Cipher cipher;
			try {
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.ENCRYPT_MODE, key, iv);
				byte[] encValue = cipher.doFinal(pass.getBytes());
				byte[] encryptedByteValue = new Base64().encode(encValue);
				return new String(encryptedByteValue);
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				logger.error("RestService - Coding Pass");
				if(e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException  ) {
					throw new CryptException(chiperError );
				}
				if(e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException  ) {
					throw new CryptException(keyError);
				}
				if(e instanceof IllegalBlockSizeException || e instanceof BadPaddingException  ) {
					throw new CryptException(crDrError);
				}
			}
		return null;
	}

	@Override
	public String codenick(String nick) throws CryptException {
			AlgorithmParameterSpec iv = new IvParameterSpec(nickKey.getBytes());
			SecretKey key = new SecretKeySpec(nickKey.getBytes(), keyString);
			Cipher cipher;
			try {
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.ENCRYPT_MODE, key, iv);
				byte[] encValue = cipher.doFinal(nick.getBytes());
				byte[] encryptedByteValue = new Base64().encode(encValue);
				return new String(encryptedByteValue);
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				logger.error("RestService - Coding Nick");
				if(e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException  ) {
					throw new CryptException(chiperError );
				}
				if(e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException  ) {
					throw new CryptException(keyError);
				}
				if(e instanceof IllegalBlockSizeException || e instanceof BadPaddingException  ) {
					throw new CryptException(crDrError);
				}
			}

		return null;
	}

	@Override
	public User getuser(String user) throws CryptException {
		user = this.decodepass(user);
		user = this.decodeuser(user);

		return new Gson().fromJson(user, User.class);
	}

	@Override
	public String codepasstodb(String pass) throws CryptException {
		
			AlgorithmParameterSpec iv = new IvParameterSpec(passToDBKey.getBytes());
			SecretKey key = new SecretKeySpec(passToDBKey.getBytes(), keyString);
			Cipher cipher;
			try {
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.ENCRYPT_MODE, key, iv);
				byte[] encValue = cipher.doFinal(pass.getBytes());
				byte[] encryptedByteValue = new Base64().encode(encValue);
				return new String(encryptedByteValue);
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				logger.error("RestService - Coding Password");
				if(e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException  ) {
					throw new CryptException(chiperError );
				}
				if(e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException  ) {
					throw new CryptException(keyError);
				}
				if(e instanceof IllegalBlockSizeException || e instanceof BadPaddingException  ) {
					throw new CryptException(crDrError);
				}
			}

		return null;
	}

	@Override
	public ArrayList<CardDetailsDTO> forgeCardDetails(ArrayList<UserCardDTO> userCardDTOList) {
		ArrayList<CardDetailsDTO> cardList = new ArrayList<>();
		CardDetailsDTO cardDetailsDTO = null;
		UserCardDTO userCardDTO = null;
		userCardDTO = userCardDTOList.get(0);
		cardDetailsDTO = new CardDetailsDTO(userCardDTO.getCard(),
				getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
		for (int i = 1; i < userCardDTOList.size(); i++) {
			userCardDTO = userCardDTOList.get(i);
			if (cardDetailsDTO.getCard().getId() == userCardDTO.getCard().getId()) {
				cardDetailsDTO.addDetails(getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
				cardDetailsDTO.setQtyMax(userCardDTO.getQtyMax());
			} else {
				cardList.add(cardDetailsDTO);
				cardDetailsDTO = new CardDetailsDTO(userCardDTO.getCard(),
						getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
			}
		}

		if (cardDetailsDTO.getCard().getId() == userCardDTO.getCard().getId()) {
			cardList.add(cardDetailsDTO);
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
