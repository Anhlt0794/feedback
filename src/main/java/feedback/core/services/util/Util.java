package feedback.core.services.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	public static final String EMAIL_STUDENT_EX="@st.hcmuaf.edu.vn";
	public static final String EMAIL_TEACHER_EX="@hcmuaf.edu.vn";
	
      public static String encryptMD5(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
      public static void main(String[] args) {
		
    	  System.out.println(Util.encryptMD5("123456"));
	}

      
}
