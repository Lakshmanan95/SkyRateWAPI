package com.skyrate.clib.service.email;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.skyrate.clib.util.SendEmail;

public class EmailServiceImpl {

	private static final long serialVersionUID = 1L;
	
	

	public static final String DEFAULT_SMTP_HOST = "smtp.gmail.com";
	public static final int DEFAULT_SMTP_PORT = 587;
	private static final String EMAIL_SETTING_HOST = "email.host";
	private static final String EMAIL_SETTING_PORT = "email.port";
	private JavaMailSender fmailServer;
	
	public JavaMailSender getMailServer() {
		return fmailServer;
	}

	public void setMailServer(JavaMailSender mailServer) {
		this.fmailServer = mailServer;
	}
	public EmailServiceImpl(){
		//System.out.println("Email service impl constructor");
	}
	
	public void sendEmail(String to, String from, String subject, String message) throws Exception {
		try {
			//InternetAddress toAddr = new InternetAddress(to);
			
			InternetAddress fromAddr = new InternetAddress("noreply@aviationrating.com");
			MimeMessage emailMsg = getMailServer().createMimeMessage();
			
			emailMsg.setFrom(fromAddr);
			emailMsg.addRecipients(Message.RecipientType.TO, "luxdlakshmanan@gmail.com");
			emailMsg.setSubject(subject);
		//	emailMsg.setText(message);
			emailMsg.setContent(message, "text/html; charset=utf-8");
			
			((JavaMailSenderImpl) getMailServer()).setHost(DEFAULT_SMTP_HOST);
			((JavaMailSenderImpl) getMailServer()).setPort(DEFAULT_SMTP_PORT);
			
			getMailServer().send(emailMsg);
			
		}
		catch (Exception e) {
			throw new Exception("Could not send email message.", e);
		}
	}
	
	public static void main(String[] args){
		EmailServiceImpl email = new EmailServiceImpl();
		try {
			email.sendEmail("to", "from", "Subjec", "Check message");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
