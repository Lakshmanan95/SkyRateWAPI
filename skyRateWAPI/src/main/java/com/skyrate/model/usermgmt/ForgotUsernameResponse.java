package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseResponse;

public class ForgotUsernameResponse extends BaseResponse{
	String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}