package com.skyrate.model.business;

import com.skyrate.model.dbentity.Business;

public class DeleteCapabilityRequest {

	int id;
	int userId;
	int businessId;
	String[] parts;
	Business business;
	String[] capabilities;
	
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

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public String[] getParts() {
		return parts;
	}

	public void setParts(String[] parts) {
		this.parts = parts;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public String[] getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String[] capabilities) {
		this.capabilities = capabilities;
	}

	
	
}
