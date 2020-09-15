package com.skyrate.useralerts.model;

public class UpdateAlertResponseRequest {
	int id;
	int userId;
	int alertId;
	int userResponse;
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
	public int getAlertId() {
		return alertId;
	}
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}
	public int getUserResponse() {
		return userResponse;
	}
	public void setUserResponse(int userResponse) {
		this.userResponse = userResponse;
	}
	

}
