package com.example.demo.util;
import java.security.*;
import java.util.regex.Pattern;


public class SecurityUtil {
	
	public static final int CHECK_CODE_ID = 101;
	public static final int CHECK_CODE_PASSWORD = 102;
	public static final int CHECK_CODE_EMAIL = 103;
	
	  

	
	// 암호화 메소드 
	public String encryptSHA256(String str) { // SHA256 
		String sha = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		}catch(NoSuchAlgorithmException e) {
			//e.printStackTrace();
			System.out.println("Encrypt Error - no such algorithmException");
			sha = "null";
		}
		return sha;
	}
	
	
	// Xss 스트링 치환 메소드 
	public String replaceXssString(String str) {
		boolean BUTTON = true;
		if(BUTTON) {
		str = str.replaceAll("&#", "");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("\'", "&#x27;");
		str = str.replaceAll("/", "&#x2F;");
		str = str.replaceAll("eval\\((.*)\\)", "");
		str = str.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
		str = str.replaceAll("[\\\"\\'][\\s]*vbscript:(.*)[\\\"\\']", "\"\"");
		str = str.replaceAll("document.cookie", "document.cookie");
		str = str.replaceAll("script>", "script>");
		str = str.replaceAll("onload", "no_onload");
		str = str.replaceAll("expression", "no_expression");
		str = str.replaceAll("onmouseover", "no_onmouseover");
		str = str.replaceAll("onmouseout", "no_onmouseout");
		str = str.replaceAll("onclick", "no_onclick"); 
		}
		return str;
	}
	
	public boolean isImageExtension(String extension) {
		boolean result = false;
		if(extension.contentEquals("JPG") || extension.contentEquals("jpg") || 
				extension.contentEquals("png") || extension.contentEquals("PNG")) {
			result = true;
		}
		return result;
	}
	
	
	// 아이디 비밀번호 이메일 유효성 검사 메소드 
	public boolean isGoodToUse(String str, final int CHECK_CODE) {
		boolean isGood = true;
		if(CHECK_CODE == CHECK_CODE_ID) {
			// id 검사 
			isGood = Pattern.matches("^[0-9a-zA-Z]*$", str) && !("".contentEquals(str));
		}else if(CHECK_CODE == CHECK_CODE_PASSWORD) {
			// password 검사 
			isGood = !("".contentEquals(str));
		}else if(CHECK_CODE == CHECK_CODE_EMAIL) {
			// email 검사 
			isGood = Pattern.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+$", str);
		}

		return isGood;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
