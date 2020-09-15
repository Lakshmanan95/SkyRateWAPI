package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseRequest;

public class LoginRequest extends BaseRequest {
	String email;
	String password;
	boolean rememberMe;
	String tokenEmail;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getTokenEmail() {
		return tokenEmail;
	}
	public void setTokenEmail(String tokenEmail) {
		this.tokenEmail = tokenEmail;
	}
	
	

}
