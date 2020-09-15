package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.User;

public class UserSignupResponse extends BaseResponse{
	
	User user;
	String userRefresh;
	int roleId;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserRefresh() {
		return userRefresh;
	}
	public void setUserRefresh(String userRefresh) {
		this.userRefresh = userRefresh;
	}
	
	
	
	
}
