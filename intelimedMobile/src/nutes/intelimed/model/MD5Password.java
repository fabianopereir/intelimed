package nutes.intelimed.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Password{
	public static String getPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	public static boolean testPassword(String password, String encodedActualPassword)
	    throws NoSuchAlgorithmException
    {
		   String encodedTestPassword = MD5Password.getPassword(password);
		   return (encodedTestPassword.equals(encodedActualPassword));
    }
}


