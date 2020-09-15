package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseRequest;

public class EmailVerificationRequest extends BaseRequest{
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}




	
	

}
