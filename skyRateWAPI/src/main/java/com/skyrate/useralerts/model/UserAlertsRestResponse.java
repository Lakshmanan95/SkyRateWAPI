package com.skyrate.useralerts.model;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.useralerts.entity.UserAlerts;
import com.skyrate.useralerts.entity.UserAlertsResponse;

public class UserAlertsRestResponse extends BaseResponse{

	List<UserAlerts> userAlerts;
	List<UserAlertsResponse> userAlertsResponse;

	public List<UserAlertsResponse> getUserAlertsResponse() {
		return userAlertsResponse;
	}

	public void setUserAlertsResponse(List<UserAlertsResponse> userAlertsResponse) {
		this.userAlertsResponse = userAlertsResponse;
	}

	public List<UserAlerts> getUserAlerts() {
		return userAlerts;
	}

	public void setUserAlerts(List<UserAlerts> userAlerts) {
		this.userAlerts = userAlerts;
	}

	
	
}
