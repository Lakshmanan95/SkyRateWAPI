package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class AdListResponse extends BaseResponse{

	List<AdDetails> adDetails;

	public List<AdDetails> getAdDetails() {
		return adDetails;
	}

	public void setAdDetails(List<AdDetails> adDetails) {
		this.adDetails = adDetails;
	}
	
	
	
}
