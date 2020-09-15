package com.skyrate.useralerts.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class UserAlertsResponse {
	@Id
	@GeneratedValue
	int id;
	Date updatedDate;
	Integer userId;
	Integer responseYes;
	Integer responseNo;
	int alertId;
	public int getAlertId() {
		return alertId;
	}
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getResponseYes() {
		return responseYes;
	}
	public void setResponseYes(Integer responseYes) {
		this.responseYes = responseYes;
	}
	public Integer getResponseNo() {
		return responseNo;
	}
	public void setResponseNo(Integer responseNo) {
		this.responseNo = responseNo;
	}
	
	
	
}
