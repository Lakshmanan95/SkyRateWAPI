package com.skyrate.model.dbentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Parts {

	@Id
	@GeneratedValue
	int id;
	int businessId;
	String partsLocation;
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
	public String getPartsLocation() {
		return partsLocation;
	}
	public void setPartsLocation(String partsLocation) {
		this.partsLocation = partsLocation;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
