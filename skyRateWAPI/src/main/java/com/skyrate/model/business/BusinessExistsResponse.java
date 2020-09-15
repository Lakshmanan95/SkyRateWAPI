package com.skyrate.model.business;

import com.skyrate.clib.model.BaseResponse;

public class BusinessExistsResponse extends BaseResponse{

	boolean businessNameExists;

	public boolean isBusinessNameExists() {
		return businessNameExists;
	}

	public void setBusinessNameExists(boolean businessNameExists) {
		this.businessNameExists = businessNameExists;
	}
	
	
	
}
