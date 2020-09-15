package com.skyrate.model.citystate;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.ContactInfo;

public class ContactInfoResponse extends BaseResponse{

	List<ContactInfo> contactInfo;

	public List<ContactInfo> getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(List<ContactInfo> contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	
}
