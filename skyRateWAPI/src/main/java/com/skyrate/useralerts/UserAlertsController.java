package com.skyrate.useralerts;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyrate.clib.model.SuccessIDResponse;
import com.skyrate.clib.util.DateTimeUtil;
import com.skyrate.model.business.PopularSearchRequest;
import com.skyrate.model.report.TopSearchRequest;
import com.skyrate.useralerts.entity.UserAlerts;
import com.skyrate.useralerts.entity.UserAlertsResponse;
import com.skyrate.useralerts.model.UpdateAlertResponseRequest;
import com.skyrate.useralerts.model.UserAlertsRestResponse;

@RestController
@RequestMapping("/userAlerts")
@CrossOrigin(maxAge=3600)
public class UserAlertsController {

	private static final Logger logger =  LoggerFactory.getLogger(UserAlertsController.class);
	
	@Autowired
	UserAlertsService userAlertsService;
	
	@RequestMapping(method = RequestMethod.GET, value="/getMyAlerts/{userId}")
	public UserAlertsRestResponse getMyAlerts(@PathVariable int userId){
		UserAlertsRestResponse response = new UserAlertsRestResponse();
		try
		{
			List<UserAlerts> userAlerts = userAlertsService.getMyAlerts(userId);
			response.setUserAlerts(userAlerts);
			logger.info("User alert respnse"+ response);
		}
		catch(Exception e){
			response.setSuccess(false);
			logger.error("User alert respnse",e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/updateUserAlertResponse")
	public SuccessIDResponse getUserAlerts(@RequestBody UpdateAlertResponseRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try
		{
			UserAlertsResponse userAlertsResponse= new UserAlertsResponse();
			userAlertsResponse.setUserId(request.getUserId());
			userAlertsResponse.setAlertId(request.getAlertId());
			userAlertsResponse.setUpdatedDate(new Date());
			if(request.getUserResponse() ==1 ) {
			userAlertsResponse.setResponseYes(1);
			}else {
				userAlertsResponse.setResponseNo(1);
			}
			userAlertsService.getUserAlertsResponseRepository().save(userAlertsResponse);
			
			logger.info("User alert respnse"+ response);
		}
		catch(Exception e){
			response.setSuccess(false);
			logger.error("User alert respnse",e);
		}
		return response;
	}
}
