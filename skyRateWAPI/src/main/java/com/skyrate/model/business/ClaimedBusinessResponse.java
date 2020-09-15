package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class ClaimedBusinessResponse extends BaseResponse{

	List<BusinessClaimed> businessClaimed;
	int count;

	public List<BusinessClaimed> getBusinessClaimed() {
		return businessClaimed;
	}

	public void setBusinessClaimed(List<BusinessClaimed> businessClaimed) {
		this.businessClaimed = businessClaimed;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
