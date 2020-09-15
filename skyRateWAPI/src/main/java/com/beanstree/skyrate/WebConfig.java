package com.beanstree.skyrate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.skyrate.clib.service.email.EmailService;
import com.skyrate.clib.service.email.EmailTemplateService;
import com.skyrate.clib.util.MyCounterInterceptor;
import com.skyrate.clib.util.SKYRATEUtil;
import com.skyrate.model.message.MessageEmailContent;
import com.skyrate.service.BusinessService;
import com.skyrate.service.MessageService;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan({"com.skyrate.*"})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger =  LoggerFactory.getLogger(WebConfig.class);
	private static Properties properties = new Properties();
	String adminConfigLocation = SKYRATEUtil.getConfigDIR() + "/adminConfig.properties";
	
	@Autowired
	MessageService messageService;
	@Autowired
	BusinessService businessService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	
	 @Override
	    public void addInterceptors (InterceptorRegistry registry) {
		 
	        registry.addInterceptor(new MyCounterInterceptor());
	    }
	 
	
	 //0 0 17 * * ? // exactly 5pm server time
	 //0 0/5 * * * ? // every 5 min

//	 @Scheduled(cron = "0 0 9 * * ?")
	 @Scheduled(cron = "${corn.expression}")
	 public void runScheduler(){
		 System.out.println("### Running scheduler.."+new Date());
		sendMessageEmail();
		sendReviewToBusinessOwner();
	 }
	 
	 public void sendMessageEmail(){
		 		try{
					List<MessageEmailContent> messageEmailContent = messageService.getMessageEmailContent();
					properties.load(new FileReader(adminConfigLocation));
					String url = properties.getProperty("WEBSITE_URL");
					for(MessageEmailContent messageEmail : messageEmailContent){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("firstName", messageEmail.getFirstName());
						map.put("readCount", messageEmail.getReadCount());
						map.put("url", url);
						map.put("application", properties.getProperty("Application"));
						String subject= properties.getProperty("MessageSubject");
						String emailBody = emailTemplateService.getEmailTemplate("messageEmailTemplate.vm",map);
						System.out.println("### sending email 1 chat owners.."+messageEmail.getEmail() +new Date());
					//	System.out.println("##EmailBody:"+emailBody);
						emailService.sendEmail(messageEmail.getEmail(), subject, emailBody, null);
					}
				}
				catch(Exception e){
					logger.error("print "+e);
				}
	 }
	 
	 public void sendReviewToBusinessOwner(){
		 try{
				List<MessageEmailContent> messageEmailContent = businessService.getEmailContent();
				properties.load(new FileReader(adminConfigLocation));
				String url = properties.getProperty("WEBSITE_URL");
				for(MessageEmailContent messageEmail : messageEmailContent){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("firstName", messageEmail.getFirstName());
					map.put("readCount", messageEmail.getReadCount());
					map.put("url", url);
					map.put("application", properties.getProperty("Application"));
					String subject= properties.getProperty("ReviewSubject");
					String emailBody = emailTemplateService.getEmailTemplate("reviewEmailTemplate.vm",map);
					System.out.println("### sending email 2 Business owner.."+messageEmail.getEmail()+new Date());
					emailService.sendEmail(messageEmail.getEmail(), subject, emailBody, null);
				}
			}
			catch(Exception e){
				logger.error("print "+e);
			}
	 }
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		/*
		System.out.println("####CORS ADDED..");
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("PUT", "DELETE","GET","POST")
			.allowedHeaders("header1", "header2", "header3")
			.exposedHeaders("header1", "header2")
			.allowCredentials(false).maxAge(3600);
			*/
	}
	
}