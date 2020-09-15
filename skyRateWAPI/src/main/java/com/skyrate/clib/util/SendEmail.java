package com.skyrate.clib.util;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	private class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("gomu.jeyarajan@gmail.com", "ottapidaram");
		}
	}

	public void createAndSendEmailMessage(ArrayList<?> messageContents) throws MessagingException {
		Email email = new Email();
		email.setRecipient(messageContents.get(0) + "");
		email.setSender("gomu.jeyarajan@gmail.com");
		email.setSubject(messageContents.get(1) + "");
		email.setMessageContent(messageContents.get(2) + "");
		sendEmailMessage(email);
	}

	public void sendEmailMessage(Email email) throws MessagingException {

		// Get system properties
		Properties props = System.getProperties();
		props = new Properties();
		props.put("mail.smtp.user", "gomu.jeyarajan@gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		SMTPAuthenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props, auth);
		session.setDebug(false);

		MimeMessage msg = new MimeMessage(session);
		msg.setText(email.getMessageContent());
		msg.setSubject(email.getSubject());
		msg.setFrom(new InternetAddress(email.getSender()));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));

		Transport transport = session.getTransport("smtps");
		transport.connect("smtp.gmail.com", 465, "gomu.jeyarajan", "ottapidaram");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();

	}

	public static void main(String s[]) {
		ArrayList<String> emailInfo = new ArrayList<String>();
		emailInfo.add("rajazekar@gmail.com");
		emailInfo.add("An account has been created for you!");
		emailInfo.add("Here is a message");
		SendEmail newEmail = new SendEmail();
		try {
			newEmail.createAndSendEmailMessage(emailInfo);
			System.out.println("Email sent");
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}