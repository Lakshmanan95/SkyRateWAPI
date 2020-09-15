package com.skyrate.rest;

import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyrate.clib.model.SuccessIDResponse;
import com.skyrate.clib.service.email.EmailService;
import com.skyrate.clib.service.email.EmailTemplateService;
import com.skyrate.clib.util.SKYRATEUtil;
import com.skyrate.model.business.ContactRequest;
import com.skyrate.model.citystate.ContactInfoResponse;
import com.skyrate.model.citystate.CountryList;
import com.skyrate.model.citystate.CountryListResponse;
import com.skyrate.model.citystate.CountryResponse;
import com.skyrate.model.citystate.StateResponse;
import com.skyrate.model.citystate.UploadCountry;
import com.skyrate.model.dbentity.AddressState;
import com.skyrate.model.dbentity.ContactInfo;
import com.skyrate.model.dbentity.Country;
import com.skyrate.model.dbentity.Feedback;
import com.skyrate.model.dbentity.FeedbackForm;
import com.skyrate.model.dbentity.User;
import com.skyrate.model.dbentity.UserRoleMapping;
import com.skyrate.model.email.EmailMessage;
import com.skyrate.service.CityStateService;
import com.skyrate.service.UserMgmtService;
import com.skyrate.useralerts.UserAlertsService;
import com.skyrate.useralerts.entity.UserAlertsMapping;
import com.skyrate.useralerts.entity.UserAlertsResponse;

@RestController
@RequestMapping("/address")
@CrossOrigin(maxAge=3600)
public class CityStateController {

private static final Logger logger = LoggerFactory.getLogger(CityStateController.class);
private static Properties properties = new Properties();

String adminConfigLocation = SKYRATEUtil.getConfigDIR() + "/adminConfig.properties";
	
	@Autowired
	private CityStateService cityStateService;
	@Autowired
	EmailService emailService;
	@Autowired
	private EmailTemplateService emailTemplateService;
	@Autowired
	UserAlertsService userAlertsService;
	@Autowired
	UserMgmtService userMgmtService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getStates")
	public StateResponse getStates(){
		StateResponse response = new StateResponse();
		try{
			List<AddressState> getStates = cityStateService.getState();
			response.setStates(getStates);
		}
		catch(Exception e){
			logger.error("Getting States failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getCountryList")
	public CountryListResponse getCountryList(){
		CountryListResponse response = new CountryListResponse();
		try{
			List<CountryList> country = cityStateService.getCountryList();
			response.setCountry(country);
			
		}
		catch(Exception e){
			logger.error("failes",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getCountry")
	public CountryResponse getCountry(){
		CountryResponse response = new CountryResponse();
		try{
			List<Country> country = cityStateService.getCountry();
			response.setCountry(country);
			
		}
		catch(Exception e){
			logger.error("failes",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/uploadCountry")
	public SuccessIDResponse uploadCountry(@RequestBody List<Country> request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			for(Country countryGet : request){
				Country country = new Country();
				country.setName(countryGet.getName());
				country.setCode(countryGet.getCode());
				cityStateService.saveCountry(country);
			}
		}
		catch(Exception e){
			logger.error("error country",e);
		}
		return response;
	}
	
	@GetMapping("/contactInfo")
	public ContactInfoResponse getContactInfo(){
		ContactInfoResponse response = new ContactInfoResponse();
		try{
			List<ContactInfo> contactInfo = cityStateService.getContactInfo();
			response.setContactInfo(contactInfo);
		
		}
		catch(Exception e){
			logger.error("get contact info failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/feedback")
	public SuccessIDResponse contactUs(@RequestBody ContactRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			System.out.println("request "+request.getMessage());
			
			Feedback feedback = new Feedback();
			feedback.setEmail(request.getEmail());
			feedback.setMessage(request.getMessage());
			feedback.setName(request.getUserName());
			feedback.setPhoneNumber(request.getPhoneNumber());
			feedback.setSubject(request.getSubject());
			feedback.setFeedbackDate(new Date());
			feedback.setUserId(request.getUserId());
			cityStateService.saveFeedback(feedback);
			System.out.println("kiran id"+request.getUserId());
			addUserAlertResponse(request.getUserId(),1);
    		properties.load(new FileReader(adminConfigLocation));
    		String user = properties.getProperty("FeedbackEmail");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("email", user);
			map.put("name", request.getUserName());
			map.put("message", request.getMessage());
			if(request.getPhoneNumber() != null){
				String content = "Phone Number: "+request.getPhoneNumber();
			map.put("phone",content);
			}
			String subject= request.getSubject();
			String emailBody = emailTemplateService.getEmailTemplate("feedbackForm.vm",map);
			EmailMessage emailMessage = new EmailMessage();
			emailMessage.setToEmail(user);
			emailMessage.setSubject(subject);
			emailMessage.setEmailBody(emailBody);
			emailService.sendEmail(user, subject, emailBody, null);
			
		}
		catch(Exception e){
			logger.error("mail failed ",e);
			response.setSuccess(false);
		}
		return response;
	}
	private void addUserAlertResponse(int userId,int alertId) {
		UserAlertsResponse userAlertsResponse= new UserAlertsResponse();
		userAlertsResponse.setUserId(userId);
		userAlertsResponse.setAlertId(alertId);
		userAlertsResponse.setUpdatedDate(new Date());
		userAlertsResponse.setResponseYes(1);
		userAlertsService.getUserAlertsResponseRepository().save(userAlertsResponse);
	}
}
