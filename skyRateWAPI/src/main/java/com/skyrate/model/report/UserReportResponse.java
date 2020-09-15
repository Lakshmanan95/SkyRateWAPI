package com.skyrate.model.report;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class UserReportResponse extends BaseResponse{

	List<UserReport> report;

	public List<UserReport> getReport() {
		return report;
	}

	public void setReport(List<UserReport> report) {
		this.report = report;
	}
	
	
}
