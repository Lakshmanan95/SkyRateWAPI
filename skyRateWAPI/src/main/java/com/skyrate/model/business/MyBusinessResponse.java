package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class MyBusinessResponse extends BaseResponse{
	List<MyBusiness> myBusiness;

	public List<MyBusiness> getMyBusiness() {
		return myBusiness;
	}

	public void setMyBusiness(List<MyBusiness> myBusiness) {
		this.myBusiness = myBusiness;
	}
	
}

