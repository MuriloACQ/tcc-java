package encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	private static String IV = "AAAAAAAAAAAAAAAA";
	private static String encryptionKey = "0123456789abcdef";

	public static byte[] encrypt(String plainText) throws Exception {
		plainText = addPaddingIfNecessary(plainText);
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.ENCRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(plainText.getBytes("UTF-8"));
	}

	public static String decrypt(byte[] cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.DECRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipherText), "UTF-8");
	}

	public static byte[] stringToByteArray(String string) {
		String[] stringArray = string.split(" ");
		byte[] byteArray = new byte[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			byteArray[i] = new Integer(stringArray[i]).byteValue();
		}
		return byteArray;
	}
	
	public static String byteArrayToString(byte[] byteArray){
		String string = "";
		for(byte bt : byteArray){
			string += new Integer(bt) + " ";
		}
		return string;
	}

	private static String addPaddingIfNecessary(String string) {
		int padding = ((string.length() % 16 - 16) * -1) % 16;
		for (int i = 0; i < padding; i++) {
			string += '\0';
		}
		return string;
	}
}
