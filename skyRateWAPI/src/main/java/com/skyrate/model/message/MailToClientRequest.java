package com.skyrate.model.message;

import java.util.List;

public class MailToClientRequest {

	List<MailRecipient> mailRecipient;
	String subject;
	String body;
	int userMail;

	public List<MailRecipient> getMailRecipient() {
		return mailRecipient;
	}

	public void setMailRecipient(List<MailRecipient> mailRecipient) {
		this.mailRecipient = mailRecipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getUserMail() {
		return userMail;
	}

	public void setUserMail(int userMail) {
		this.userMail = userMail;
	}
	
	
}
