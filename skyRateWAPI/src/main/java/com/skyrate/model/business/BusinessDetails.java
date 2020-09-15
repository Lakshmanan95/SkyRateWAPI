package com.skyrate.model.business;

import org.apache.commons.lang.StringUtils;

import com.skyrate.model.dbentity.Business;

public class BusinessDetails extends Business{

	int ratingCount;
	int reviewCount;
	String description;
	String overallRating;
	int businessId;
	int categoryId;

	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(String overallRating) {
		this.overallRating = overallRating;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getAddressStr(){
		try{
			StringBuffer addressStr = new StringBuffer();

			if(StringUtils.isNotBlank(getAddress())){
				addressStr.append(getAddress().trim()+",");
			}
			if(StringUtils.isNotBlank(getCity())){
				addressStr.append(getCity().trim()+",");
			}
			if(StringUtils.isNotBlank(getState())){
				addressStr.append(getState().trim()+",");
			}
			if(StringUtils.isNotBlank(getZip())){
				addressStr.append(getZip().trim());
			}
			return cutLastChar(addressStr.toString());
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}
	public String cutLastChar(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	public static void main(String s[]){
		Business b = new Business();
		b.setAddress("");
		b.setCity("Pleasanton");
		b.setState("CA");
		b.setZip("94566");
		//	System.out.println("##--address"+b.getAddressStr());
	}


}
