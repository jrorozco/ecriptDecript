package mx.com.ejemplos;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;



public class AESEjemplo {
	private static final String ALAGORITMO="AES";
	private byte[] keyValue;
	
	public AESEjemplo(String key) {
		keyValue = key.getBytes();
	}
	
	public String encrypt( String Data) throws Exception{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALAGORITMO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new Decoder.BASE64Encoder().encode(encVal);
		return encryptedValue;
	}
	
	public String decrypt(String encryptedData)throws Exception{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALAGORITMO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decoredValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decoredValue);
		String decryptedValue = new String(decValue);
		return  decryptedValue;
		
	}
	
	public Key generateKey() throws Exception{
		Key key = new SecretKeySpec(keyValue,ALAGORITMO);
		return key;
	}
	
	public static void main(String[] args) {
		try {
			AESEjemplo aes = new AESEjemplo("llaveDePruebaDeb");
			String encdata = aes.encrypt("ReneOrozco");
			System.out.println("Encrypted Data - " +encdata );
			String decdata = aes.decrypt(encdata);
			System.out.println("DecriptedData - " + decdata);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
