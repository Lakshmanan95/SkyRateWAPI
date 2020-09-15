package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Business;

public class SearchBusinessResponse extends BaseResponse{

	List<BusinessDetails> business;
	long count;

	public List<BusinessDetails> getBusiness() {
		return business;
	}

	public void setBusiness(List<BusinessDetails> business) {
		this.business = business;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	
}
