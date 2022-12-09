package com.op.be.usercard.service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.op.be.usercard.model.User;

@Service
public class RestServiceImpl implements RestService {

	@Override
	public String decodeuser(String username) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String keyString = "6543210987654321";
		AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
		SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
		byte[] decodeBase64 = Base64.decodeBase64(username);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);

		return new String(cipher.doFinal(decodeBase64), "UTF-8");
	}

	@Override
	public String decodepass(String password) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String keyString = "1234567890123456";
		AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
		SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
		byte[] decodeBase64 = Base64.decodeBase64(password);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);

		return new String(cipher.doFinal(decodeBase64), "UTF-8");
	}

	@Override
	public String decodenick(String nick) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String keyString = "a54321098765432z";
		AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
		SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
		byte[] decodeBase64 = Base64.decodeBase64(nick);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);

		return new String(cipher.doFinal(decodeBase64), "UTF-8");
	}

	@Override
	public String codeuser(String user) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String keyString = "6543210987654321";
		AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
		SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
		// byte[] codeBase64 = Base64.encodeBase64(user.getBytes());
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] encValue = cipher.doFinal(user.getBytes());
		byte[] encryptedByteValue = new Base64().encode(encValue);
		return new String(encryptedByteValue);
	}

	@Override
	public String codepass(String pass) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String keyString = "1234567890123456";
		AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
		SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] encValue = cipher.doFinal(pass.getBytes());
		byte[] encryptedByteValue = new Base64().encode(encValue);
		return new String(encryptedByteValue);
	}
	
	@Override
	public String codenick(String nick) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String keyString = "a54321098765432z";
		AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
		SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] encValue = cipher.doFinal(nick.getBytes());
		byte[] encryptedByteValue = new Base64().encode(encValue);
		return new String(encryptedByteValue);
	}

	@Override
	public User getuser(String user) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		user = this.decodepass(user);
		user = this.decodeuser(user);

		return new Gson().fromJson(user, User.class);
	}

	@Override
	public String codepasstodb(String pass) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String keyString = "1nuovapassworddb";
		AlgorithmParameterSpec iv = new IvParameterSpec(keyString.getBytes("UTF-8"));
		SecretKey key = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] encValue = cipher.doFinal(pass.getBytes());
		byte[] encryptedByteValue = new Base64().encode(encValue);
		return new String(encryptedByteValue);
	}
	
}
