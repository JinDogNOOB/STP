package com.example.demo.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailServiceImpl{
	private static String strMailServerPort="25";
	private static String strMailFrom="3thWebServerTeam@gmail.com"; // 보내는 사람 메일 주소 
	
	
	public boolean sendSimpleMessage(String dstMailAddress, String subject, String content)throws Exception {
		Boolean result = true;
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "127.0.0.1");
			props.put("mail.smtp.port", strMailServerPort);
			props.put("mail.smtp.auth", "false");
			Session msgSession = Session.getDefaultInstance(props,null);
			MimeMessage msg = new MimeMessage(msgSession);
			InternetAddress from = new InternetAddress(strMailFrom, "3thWebServerTeam", "UTF-8");
			
			msg.setFrom(from);   
			InternetAddress to = new InternetAddress(dstMailAddress);    // 받는사람 메일주소 
			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setSubject(subject);
			msg.setContent(content, "text/html; charset=UTF-8");
			
			Transport.send(msg);
		}catch(MessagingException e) {
			result = false;
			System.out.println(e);
		}
		return result;
			
		}
	
	
	
	
}
