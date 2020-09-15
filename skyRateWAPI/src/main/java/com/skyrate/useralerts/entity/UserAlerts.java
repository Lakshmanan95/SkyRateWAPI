package com.skyrate.useralerts.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class UserAlerts {
	@Id
	@GeneratedValue
	int id;
	String alertMsg;
	String alertType;
	String alertFor;
	Date startDate;
	Date endDate;
	Integer totalReminderTimes;
	Integer reminderFrequency;
	Integer userGroup;
	Boolean isUserAlert;
	int active;
	String responseYesUrl;
	String header;
	String buttonYes;
	String buttonNo;
	int businessId;
	String businessName;
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	
	public String getButtonYes() {
		return buttonYes;
	}
	public void setButtonYes(String buttonYes) {
		this.buttonYes = buttonYes;
	}
	public String getButtonNo() {
		return buttonNo;
	}
	public void setButtonNo(String buttonNo) {
		this.buttonNo = buttonNo;
	}
	public String getResponseYesUrl() {
		return responseYesUrl;
	}
	public void setResponseYesUrl(String responseYesUrl) {
		this.responseYesUrl = responseYesUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getAlertFor() {
		return alertFor;
	}
	public void setAlertFor(String alertFor) {
		this.alertFor = alertFor;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getTotalReminderTimes() {
		return totalReminderTimes;
	}
	public void setTotalReminderTimes(Integer totalReminderTimes) {
		this.totalReminderTimes = totalReminderTimes;
	}
	public Integer getReminderFrequency() {
		return reminderFrequency;
	}
	public void setReminderFrequency(Integer reminderFrequency) {
		this.reminderFrequency = reminderFrequency;
	}
	public Integer getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(Integer userGroup) {
		this.userGroup = userGroup;
	}

	public Boolean getIsUserAlert() {
		return isUserAlert;
	}
	public void setIsUserAlert(Boolean isUserAlert) {
		this.isUserAlert = isUserAlert;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	
	
}
