package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Business;
import com.skyrate.model.dbentity.BusinessClaimMapping;
import com.skyrate.model.dbentity.BusinessRatingSummary;
import com.skyrate.model.dbentity.Category;

public class BusinessDetailsResponse extends BaseResponse{

	Business business;
	
	BusinessRatingSummary businessRating;
	List<BusinessCategory> businessCategory;
	List<CategoryList> category;
	BusinessClaimMapping businessClaim;
	
	
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	
	
	public BusinessRatingSummary getBusinessRating() {
		return businessRating;
	}
	public void setBusinessRating(BusinessRatingSummary businessRating) {
		this.businessRating = businessRating;
	}
	public List<BusinessCategory> getBusinessCategory() {
		return businessCategory;
	}
	public void setBusinessCategory(List<BusinessCategory> businessCategory) {
		this.businessCategory = businessCategory;
	}
	public List<CategoryList> getCategory() {
		return category;
	}
	public void setCategory(List<CategoryList> category) {
		this.category = category;
	}
	public BusinessClaimMapping getBusinessClaim() {
		return businessClaim;
	}
	public void setBusinessClaim(BusinessClaimMapping businessClaim) {
		this.businessClaim = businessClaim;
	}
	
	
	
}
