package com.skyrate.model.usermgmt;


import com.skyrate.model.dbentity.User;


public class UserProfile extends User{
	
	long roleId;
	

	public UserProfile(User user){
		this.setId(user.getId());
		this.setUserName(user.getUserName());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
		this.setPhoneNumber(user.getPhoneNumber());
		this.setId(user.getId());
		this.setProfileImageUrl(user.getProfileImageUrl());

	}
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	

	
}
