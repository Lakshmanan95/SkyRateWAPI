package com.skyrate.model.dbentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContactInfo {

	@Id
	@GeneratedValue
	int id;
	String nameOfService;
	String phoneNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameOfService() {
		return nameOfService;
	}
	public void setNameOfService(String nameOfService) {
		this.nameOfService = nameOfService;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
