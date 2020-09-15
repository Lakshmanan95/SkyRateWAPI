package com.skyrate.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skyrate.clib.service.email.EmailService;
import com.skyrate.clib.service.email.EmailTemplateService;
import com.skyrate.model.email.EmailMessage;
import com.skyrate.model.message.MessageEmailContent;
import com.skyrate.service.MessageService;

@Component
public class RunScheduler {

	@Autowired
	MessageService messageService;
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	 public RunScheduler() { }
	
	public void sendMessageEmail(){
		
		try{
		List<MessageEmailContent> messageEmailContent = messageService.getMessageEmailContent();
		messageEmailContent.parallelStream().forEach(i -> 
		System.out.println(i.getFirstName()));
		}
		catch(Exception e){
			System.out.println("print "+e);
		}
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("firstName", retUser.getFirstName());
		map.put("lastName", retUser.getLastName());
		String subject= "New message from Skyrate";
		String emailBody = emailTemplateService.getEmailTemplate("registrationConfirm.vm",map);
		emailService.sendEmail(retUser.getEmail(), subject, emailBody, null);*/
	}
}
