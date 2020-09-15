package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseRequest;

public class UserNameExistRequest extends BaseRequest{
	String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
