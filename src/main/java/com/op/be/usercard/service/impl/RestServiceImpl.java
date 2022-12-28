package com.op.be.usercard.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.microsoft.azure.keyvault.KeyVaultClient;
import com.op.be.usercard.model.CardDetails;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DetailsDTO;
import com.op.be.usercard.model.dto.UserCardDTO;
import com.op.be.usercard.service.RestService;


@Service
public class RestServiceImpl implements RestService {

	@Autowired
	KeyVaultClient keyVaultClient;

	@Value("${azure.keyvault.uri}")
	private String uri;

	private String cipherString = "AES/CBC/PKCS5Padding";
	private String aes = "AES";

	SecureRandom random = new SecureRandom();

	private static Logger logger = Logger.getLogger("ErrorLogging");
	
	
	@Override
	public String decodeuser(String username) {
		try {
			String userKey = keyVaultClient.getSecret(uri, "UserKey").value();
			AlgorithmParameterSpec iv = new IvParameterSpec(userKey.getBytes());
			SecretKey key = new SecretKeySpec(userKey.getBytes(), aes);
			byte[] decodeBase64 = Base64.decodeBase64(username);
			Cipher cipher = Cipher.getInstance(cipherString);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			return new String(cipher.doFinal(decodeBase64), StandardCharsets.UTF_8);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public String decodepass(String password) {
		try {
			String passKey = keyVaultClient.getSecret(uri, "PassKey").value();
			AlgorithmParameterSpec iv = new IvParameterSpec(passKey.getBytes());
			SecretKey key = new SecretKeySpec(passKey.getBytes(), aes);
			byte[] decodeBase64 = Base64.decodeBase64(password);
			Cipher cipher = Cipher.getInstance(cipherString);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			return new String(cipher.doFinal(decodeBase64), StandardCharsets.UTF_8);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public String decodenick(String nick) {
		try {
		    byte[] bytesIV = new byte[16];
		    random.nextBytes(bytesIV);
			String nickKey = keyVaultClient.getSecret(uri,"NickKey").value();
			AlgorithmParameterSpec iv = new IvParameterSpec(nickKey.getBytes());
			SecretKey key = new SecretKeySpec(nickKey.getBytes(), aes);
			byte[] decodeBase64 = Base64.decodeBase64(nick);
			Cipher cipher = Cipher.getInstance(cipherString);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			return new String(cipher.doFinal(decodeBase64), StandardCharsets.UTF_8);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public String codeuser(String user) {
		try {
			String userKey = keyVaultClient.getSecret(uri, "UserKey").value();
			AlgorithmParameterSpec iv = new IvParameterSpec(userKey.getBytes());
			SecretKey key = new SecretKeySpec(userKey.getBytes(), aes);
			Cipher cipher = Cipher.getInstance(cipherString);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encValue = cipher.doFinal(user.getBytes());
			byte[] encryptedByteValue = new Base64().encode(encValue);
			return new String(encryptedByteValue);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public String codepass(String pass) {
		try {
			String passKey = keyVaultClient.getSecret(uri, "PassKey").value();
			AlgorithmParameterSpec iv = new IvParameterSpec(passKey.getBytes());
			SecretKey key = new SecretKeySpec(passKey.getBytes(), aes);
			Cipher cipher = Cipher.getInstance(cipherString);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encValue = cipher.doFinal(pass.getBytes());
			byte[] encryptedByteValue = new Base64().encode(encValue);
			return new String(encryptedByteValue);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public String codenick(String nick) {
		try {
			String nickKey = keyVaultClient.getSecret(uri, "NickKey").value();
			AlgorithmParameterSpec iv = new IvParameterSpec(nickKey.getBytes());
			SecretKey key = new SecretKeySpec(nickKey.getBytes(), aes);
			Cipher cipher = Cipher.getInstance(cipherString);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encValue = cipher.doFinal(nick.getBytes());
			byte[] encryptedByteValue = new Base64().encode(encValue);
			return new String(encryptedByteValue);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Override
	public User getuser(String user) {
		user = this.decodepass(user);
		user = this.decodeuser(user);

		return new Gson().fromJson(user, User.class);
	}

	@Override
	public String codepasstodb(String pass) {
		try {
			String passToKey = keyVaultClient.getSecret(uri, "PassToDBKey").value();
			AlgorithmParameterSpec iv = new IvParameterSpec(passToKey.getBytes());
			SecretKey key = new SecretKeySpec(passToKey.getBytes(), aes);
			Cipher cipher = Cipher.getInstance(cipherString);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encValue = cipher.doFinal(pass.getBytes());
			byte[] encryptedByteValue = new Base64().encode(encValue);
			return new String(encryptedByteValue);
		} catch (Exception e) {
			logger.info(e.getMessage());
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
