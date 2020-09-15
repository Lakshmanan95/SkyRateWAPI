package com.skyrate.model.generic;

import com.skyrate.clib.model.BaseResponse;

public class CountResponse extends BaseResponse {
	long count;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	

}
