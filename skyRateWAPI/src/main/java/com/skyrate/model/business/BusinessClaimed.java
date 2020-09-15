package com.skyrate.model.business;

import com.skyrate.model.dbentity.BusinessClaimMapping;

public class BusinessClaimed extends BusinessClaimMapping {

	String name;
	String firstName;
	String email;
	String phoneNumber;
	int active;
	int claimed;
	int userId;
	int businessId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getClaimed() {
		return claimed;
	}
	public void setClaimed(int claimed) {
		this.claimed = claimed;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String toString(){
		return "Claimed:"+claimed;
	}
	
	
	
}
