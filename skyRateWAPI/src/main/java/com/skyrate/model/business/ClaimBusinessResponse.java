package com.skyrate.model.business;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.BusinessClaimMapping;

public class ClaimBusinessResponse extends BaseResponse{

	BusinessClaimMapping claimBusiness;

	public BusinessClaimMapping getClaimBusiness() {
		return claimBusiness;
	}

	public void setClaimBusiness(BusinessClaimMapping claimBusiness) {
		this.claimBusiness = claimBusiness;
	}
	
	
	
}
