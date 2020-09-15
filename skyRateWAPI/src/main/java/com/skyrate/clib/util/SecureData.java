package com.skyrate.clib.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Purpose: Helper class for symmectric key encryption <br/>
 * Description: <br/>
 * <p>
 *  
 * </p>
 * @author axkq
 * @date  May 8, 2007
 */
public class SecureData {

	private final String ENCRYPTION_KEY = "0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0x0a,0x0b,0x0c,0x0d,0x0e,0x0f,0x10,0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08";
	private static final String ENCODING        = "UTF-8";                           

	private byte[] byteKey;

	SecretKey secretKey;


	private Cipher encryptCipher;
	private Cipher decryptCipher;


	//Main driver to allow the administrator to encrypt from a prompt
	//Note that decrypt is not exposed.
	public static void main(String[] args) throws Exception{
	/*	String[] userInputs = getUserInputs();

		if (userInputs == null ) {
			return;
		}

		SecureData en = null;
		String encrypted = null;
		try {
			en = new SecureData();
			String pwd = en.decrypt("83xPmMMFbvPoJX/56ckYKQ==");
			System.out.println("Admin password DEV: "+pwd);
			String pwd1 = en.decrypt("xgMRfiKvoPkGEBk3fGv9ig==");
			System.out.println("Admin password QA: "+pwd1);                                                        
			String pwd2 = en.decrypt("G/mOP9sczHoGEBk3fGv9ig==");
			System.out.println("Admin password INT: "+pwd2);
			encrypted = en.encrypt(userInputs[0]);
		} catch (Exception e) {                                                    
			e.printStackTrace();
			return;
		} 
		System.out.println("Encrypted value for (" + userInputs[0] + ") is =[" + encrypted + "]");
		*/
		
		System.out.println("Encryped value:");
		System.out.println("Decryped value:");
		 
	}

	public static String[] getUserInputs(){     
		BufferedReader br = null;
		String[] entries  = new String[1];
		try{

			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter Entry to Encrypt: ");
			entries[0] = br.readLine();
			if (entries[0] == null || entries[0].trim().length() == 0) {
				System.out.println("Error!! You need to supply data to encrypt. Execute Program again.");
				return null;
			} else {
				entries[0] = entries[0].trim() ;                                                                                    
			}
		} catch (Exception e){
			e.printStackTrace();
			return null;
		} finally {
			if (br != null){
				try{
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				br = null;
			}
		}
		return entries;
	}                              

	public SecureData() throws Exception {
		//Security.addProvider(new com.sun.crypto.provider.SunJCE());
		//Security.addProvider(new com.ibm.crypto.provider.IBMJCE());


		byteKey = this.string2Byte(ENCRYPTION_KEY);

		if(byteKey.length != 24) throw new Exception("Improper key length");

		secretKey = new SecretKeySpec(byteKey, "DESede");

		//encryptCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding", "SunJCE");
		encryptCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		encryptCipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey);

		decryptCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		decryptCipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey);

	}


	synchronized public String encrypt(String input) throws IOException, IllegalBlockSizeException, BadPaddingException {
		byte[] passwordBytes = input.getBytes(SecureData.ENCODING);
		byte[] encryptedPasswordBytes = this.encryptCipher.doFinal(passwordBytes);
		
		String encodedEncryptedPassword = new String(Base64.encodeBase64(encryptedPasswordBytes));
		return encodedEncryptedPassword;
	}

	synchronized public String decrypt(String input) throws IOException, IllegalBlockSizeException, BadPaddingException  {
		byte[] encryptedPasswordBytes = Base64.decodeBase64(input.getBytes());
		byte[] passwordBytes = this.decryptCipher.doFinal(encryptedPasswordBytes);
		String recoveredPassword = new String(passwordBytes, SecureData.ENCODING);
		return recoveredPassword;
	}

	private byte[] string2Byte(String input){
		if(input == null) return null;
		String [] tokens = input.split(",");
		byte [] bytes = new byte[tokens.length];
		for(int index = 0; index < tokens.length; index++){
			bytes[index] = Byte.decode(tokens[index]).byteValue();
		}
		return bytes;
	}                              


}
