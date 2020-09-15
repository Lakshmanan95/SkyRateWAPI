package com.skyrate.model.business;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Business;

public class BusinessResponse extends BaseResponse{

	Business business;

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
	
	
}
