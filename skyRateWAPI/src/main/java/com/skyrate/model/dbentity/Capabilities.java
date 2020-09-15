package com.skyrate.model.dbentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Capabilities {

	@Id
	@GeneratedValue
	int id;
	int businessId;
	String capabilitiesLocation;
	String fileName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getCapabilitiesLocation() {
		return capabilitiesLocation;
	}
	public void setCapabilitiesLocation(String capabilitiesLocation) {
		this.capabilitiesLocation = capabilitiesLocation;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
