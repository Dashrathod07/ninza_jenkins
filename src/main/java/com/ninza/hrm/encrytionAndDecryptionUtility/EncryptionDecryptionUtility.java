package com.ninza.hrm.encrytionAndDecryptionUtility;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
//Private key=Ac03tEam@j!tu_#1--16Bit
//4234567890123456--16 bit IV
//1234567890123456
//Private key=Ac03tEam@j!tu_1234567890AbcDefgh---32bit
//12345678901234567890123456789012--32 bit IV
public class EncryptionDecryptionUtility {
	//String secretKey="Ac03tEam@j!tu_#1";
	String secretKey="Ac03tEam@j!tu_#1";
	String iv = "4234567890123456";
	
		public String encryptString(String input, String privateKey) throws Exception, NoSuchPaddingException {
			SecretKeySpec secretKeySpec=new SecretKeySpec(secretKey.getBytes(),"AES");
			IvParameterSpec ivParamSpec=new IvParameterSpec(iv.getBytes());
			Cipher cipher= Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParamSpec);
			byte[] encrypted=cipher.doFinal(input.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		}
		public String decryptString(String input, String privateKey) throws Exception, NoSuchPaddingException {
			SecretKeySpec secretKeySpec=new SecretKeySpec(secretKey.getBytes(),"AES");
			IvParameterSpec ivParamSpec=new IvParameterSpec(iv.getBytes());
			Cipher cipher= Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParamSpec);
			 byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(input));
			return new String(decrypted);	
		}
	 



}
