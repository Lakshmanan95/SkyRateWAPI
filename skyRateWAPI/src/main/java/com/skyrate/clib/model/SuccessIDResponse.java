package com.skyrate.clib.model;

public class SuccessIDResponse extends BaseResponse{
	long id;
	String desc;
	
	public SuccessIDResponse(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
