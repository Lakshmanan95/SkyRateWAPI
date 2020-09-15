package com.skyrate.model.message;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class ReadCountResponse extends BaseResponse{

	List<ReadCount> readCount;

	public List<ReadCount> getReadCount() {
		return readCount;
	}

	public void setReadCount(List<ReadCount> readCount) {
		this.readCount = readCount;
	}
	
	
}
