package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.User;
public class UpdateUserProfileResponse extends BaseResponse{

	User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	
	
}
