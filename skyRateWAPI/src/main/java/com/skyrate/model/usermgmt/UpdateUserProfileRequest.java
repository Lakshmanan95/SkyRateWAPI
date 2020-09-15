package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseRequest;
import com.skyrate.model.dbentity.User;

public class UpdateUserProfileRequest extends BaseRequest {
	
	User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	


	
	/*int userId;
	String firstName;
	String lastName;
	
	String securityQuestion;
	String securityQuestionAnswer;
	String phoneNumber;
	String email;
	*/
	
	
}
