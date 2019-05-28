package se315;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encryption {

	public static byte[] encryptKey(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(password.getBytes());
	}
}
