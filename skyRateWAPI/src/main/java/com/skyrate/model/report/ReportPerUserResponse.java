package com.skyrate.model.report;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.User;

public class ReportPerUserResponse extends BaseResponse{

	List<ReportPerBusiness> report;
	User user;

	public List<ReportPerBusiness> getReport() {
		return report;
	}

	public void setReport(List<ReportPerBusiness> report) {
		this.report = report;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	
}
