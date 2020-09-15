package com.skyrate.model.report;

import com.skyrate.clib.model.BaseResponse;

public class TotalHit extends BaseResponse{

	int count;
	String lastUpdatedDate;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	
}
