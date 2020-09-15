package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.User;

public class UserNameExistResponse extends BaseResponse{
	
	boolean userNameExists;

	public boolean isUserNameExists() {
		return userNameExists;
	}

	public void setUserNameExists(boolean userNameExists) {
		this.userNameExists = userNameExists;
	}
	
	
}
