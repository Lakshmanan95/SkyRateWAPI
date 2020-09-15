package com.skyrate.model.report;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.EventLog;
import com.skyrate.model.usermgmt.UserRating;

public class ReportResponse extends BaseResponse{

	List<VendorDetails> vendorSearch;
	List<UserRating> vendorReview;
	
	public List<VendorDetails> getVendorSearch() {
		return vendorSearch;
	}
	public void setVendorSearch(List<VendorDetails> vendorSearch) {
		this.vendorSearch = vendorSearch;
	}
	public List<UserRating> getVendorReview() {
		return vendorReview;
	}
	public void setVendorReview(List<UserRating> vendorReview) {
		this.vendorReview = vendorReview;
	}

	
	
	
}
