package com.skyrate.model.report;

import java.util.List;

import com.skyrate.clib.model.SuccessIDResponse;
import com.skyrate.model.dbentity.ContactSubject;

public class SubjectListResponse extends SuccessIDResponse{

	List<ContactSubject> contactSubject;

	public List<ContactSubject> getContactSubject() {
		return contactSubject;
	}

	public void setContactSubject(List<ContactSubject> contactSubject) {
		this.contactSubject = contactSubject;
	}
	
	
}
