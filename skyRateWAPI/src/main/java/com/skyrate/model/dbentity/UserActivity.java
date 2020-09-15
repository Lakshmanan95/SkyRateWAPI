package com.skyrate.model.dbentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserActivity {

	@Id
	@GeneratedValue
	int id;
	int userId;
	String secureToken;
	String loginTime;
	String userRefreshToken;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSecureToken() {
		return secureToken;
	}
	public void setSecureToken(String secureToken) {
		this.secureToken = secureToken;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getUserRefreshToken() {
		return userRefreshToken;
	}
	public void setUserRefreshToken(String userRefreshToken) {
		this.userRefreshToken = userRefreshToken;
	}
	
	
}
