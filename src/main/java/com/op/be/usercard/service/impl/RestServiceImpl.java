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
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.CardDetails;
import com.op.be.usercard.model.User;
import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.model.dto.CardDetailsDTO;
import com.op.be.usercard.model.dto.DetailsDTO;
import com.op.be.usercard.model.dto.UserCardDTO;
import com.op.be.usercard.service.RestService;

@Service
public class RestServiceImpl implements RestService
{

	private String userKey    ="6543210987654321";
	private String passKey    ="1234567890123456";
	private String nickKey	  ="a54321098765432z";
	private String passToDBKey="a54aranzaarunzaz";
	private String cipherString="AES/CBC/PKCS5Padding";
	private String keyString="AES";

	private static String chiperError = "Incorrect cipher string: ";
	private static String keyError = "Incorrect key / algorithm: ";
	private static String crDrError = "Error while crypting / decrypting: ";

	private static final Logger logger = LoggerFactory.getLogger(RestServiceImpl.class);

	@Override
	public String decodeuser(String username) throws CryptException
	{

		AlgorithmParameterSpec iv = new IvParameterSpec(userKey.getBytes());
		SecretKey key = new SecretKeySpec(userKey.getBytes(), keyString);

		return codeDecodeString(username, iv, key, false);
	}

	@Override
	public String decodepass(String password) throws CryptException
	{

		AlgorithmParameterSpec iv = new IvParameterSpec(passKey.getBytes());
		SecretKey key = new SecretKeySpec(passKey.getBytes(), keyString);

		return codeDecodeString(password, iv, key, false);
	}

	@Override
	public String decodenick(String nick) throws CryptException
	{

		AlgorithmParameterSpec iv = new IvParameterSpec(nickKey.getBytes());
		SecretKey key = new SecretKeySpec(nickKey.getBytes(), keyString);

		return codeDecodeString(nick, iv, key, false);
	}

	@Override
	public String codeuser(String user) throws CryptException
	{
		logger.info(keyString);
		AlgorithmParameterSpec iv = new IvParameterSpec(userKey.getBytes());
		SecretKey key = new SecretKeySpec(userKey.getBytes(), keyString);

		return codeDecodeString(user, iv, key, true);
	}

	@Override
	public String codepass(String pass) throws CryptException
	{
		AlgorithmParameterSpec iv = new IvParameterSpec(passKey.getBytes());
		SecretKey key = new SecretKeySpec(passKey.getBytes(), keyString);

		return codeDecodeString(pass, iv, key, true);
	}

	@Override
	public String codenick(String nick) throws CryptException
	{
		AlgorithmParameterSpec iv = new IvParameterSpec(nickKey.getBytes());
		SecretKey key = new SecretKeySpec(nickKey.getBytes(), keyString);

		return codeDecodeString(nick, iv, key, true);
	}

	@Override
	public User getuser(String user) throws CryptException
	{
		user = this.decodepass(user);
		user = this.decodeuser(user);

		return new Gson().fromJson(user, User.class);
	}

	@Override
	public String codepasstodb(String pass) throws CryptException
	{

		AlgorithmParameterSpec iv = new IvParameterSpec(passToDBKey.getBytes());
		SecretKey key = new SecretKeySpec(passToDBKey.getBytes(), keyString);

		return codeDecodeString(pass, iv, key, true);
	}

	private String codeDecodeString(String string, AlgorithmParameterSpec iv, SecretKey key, boolean flag)
			throws CryptException
	{
		try
		{
			Cipher cipher;
			if (flag) {
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.ENCRYPT_MODE, key, iv);
				byte[] encValue = cipher.doFinal(string.getBytes());
				byte[] encryptedByteValue = new Base64().encode(encValue);
				return new String(encryptedByteValue);
			}
			else
			{
				cipher = Cipher.getInstance(cipherString);
				cipher.init(Cipher.DECRYPT_MODE, key, iv);
				byte[] decodeBase64 = Base64.decodeBase64(string);
				return new String(cipher.doFinal(decodeBase64), StandardCharsets.UTF_8);
			}

		}
		catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e)
		{

			logger.error("RestService - Coding Password");
			if (e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException)
			{
				throw new CryptException(chiperError + e.getMessage());
			}
			if (e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException)
			{
				throw new CryptException(keyError + e.getMessage());
			}
			if (e instanceof IllegalBlockSizeException || e instanceof BadPaddingException)
			{
				throw new CryptException(crDrError + e.getMessage());
			}
		}

		return null;
	}

	private DetailsDTO getDetailsDTO(UserCard userCard, CardDetails cardDetails)
	{
		if (userCard == null)
			return new DetailsDTO(cardDetails.getCodCondition(), cardDetails.getLanguage(), cardDetails.getCondition(),
					0, new Long(1));
		else
			return new DetailsDTO(cardDetails.getCodCondition(), cardDetails.getLanguage(), cardDetails.getCondition(),
					userCard.getQty(), userCard.getUserId());
	}

	@Override
	public ArrayList<CardDetailsDTO> forgeCardDetails(ArrayList<Object[]> userCardDTOList)
	{
		ArrayList<CardDetailsDTO> cardList = new ArrayList<>();
		CardDetailsDTO cardDetailsDTO = null;
		UserCardDTO userCardDTO = null;
		Object[] ob ;
		ob = userCardDTOList.get(0);
		userCardDTO = new UserCardDTO((Card) ob[0],(UserCard) ob[1],(CardDetails) ob[2]);
		cardDetailsDTO = new CardDetailsDTO(userCardDTO.getCard(),
				getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
		for (int i = 1; i < userCardDTOList.size(); i++)
		{
			ob = userCardDTOList.get(i);
			if(ob.length >3) {
				userCardDTO = new UserCardDTO((Card) ob[0],(UserCard) ob[1],(CardDetails) ob[2],(int) ob[3]);
			}else {
				userCardDTO = new UserCardDTO((Card) ob[0],(UserCard) ob[1],(CardDetails) ob[2]);
			}
			if (cardDetailsDTO.getCard().getId() == userCardDTO.getCard().getId())
			{
				cardDetailsDTO.addDetails(getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
				if(ob.length >3) {
					cardDetailsDTO.setQtyMax(userCardDTO.getQtyMax());
				}else {
					cardDetailsDTO.setQtyMax(1);
				}

			}
			else
			{
				cardList.add(cardDetailsDTO);
				cardDetailsDTO = new CardDetailsDTO(userCardDTO.getCard(),
						getDetailsDTO(userCardDTO.getUserCard(), userCardDTO.getCardDetails()));
			}
		}

		if (cardDetailsDTO.getCard().getId() == userCardDTO.getCard().getId())
		{
			cardList.add(cardDetailsDTO);
		}

		return cardList;
	}
}
