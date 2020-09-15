package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Capabilities;
import com.skyrate.model.dbentity.Parts;

public class CapabilityResponse extends BaseResponse{

	List<Capabilities> capabilities;
	List<Parts> parts;

	public List<Capabilities> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<Capabilities> capabilities) {
		this.capabilities = capabilities;
	}

	public List<Parts> getParts() {
		return parts;
	}

	public void setParts(List<Parts> parts) {
		this.parts = parts;
	}
	
	
}
