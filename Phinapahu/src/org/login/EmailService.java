package org.login;

import java.util.*;

import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;

public class EmailService {
	public EmailService() {
		
	}
	public String sendInvitationMail(String [] recipients, String sender, String emailContent, String password) {
		Address[] addresses = new Address[recipients.length];
		String result =null;
		String from = sender;
		
		String registrationInformation = String.format("<strong>From your Phinapahu Team:</strong><br> Go to the Phinapahu website and choose \"Join Household\" during Sign-Up.<br>" + 
										"Enter the Household Password <strong> %s </strong> when asked, and enjoy your time with Phinapahu!", password);
		
		emailContent = String.format("<p>%s</p> <p>%s</p>", emailContent, registrationInformation);
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		
		Session mailSession = Session.getDefaultInstance(properties, null);
		mailSession.setDebug(true);
		
		
		try {
			MimeMessage message = new MimeMessage(mailSession);
			System.out.println(from);
			System.out.println(recipients.length);
			for (int i = 0; i < recipients.length; i++) {
				System.out.println(recipients[i]);
				addresses[i] = new InternetAddress(recipients[i]); 
				
				
			}
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setFrom(new InternetAddress(from));			
			message.setSubject("Join my Household!");
			//message.setRecipient(Message.RecipientType.TO, new InternetAddress(from));
			//message.setText(emailContent);
			message.setContent(emailContent, "text/html; charset=utf-8");
			Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", "noreply.phinapahu", "ph11n13p17h19!");
			transport.sendMessage(message, message.getAllRecipients());
			result = "Message successfully sent";
			
		}catch(MessagingException mex) {
			mex.printStackTrace();
			result = "Errors while sending message";
		}
		return result;
	}
}
