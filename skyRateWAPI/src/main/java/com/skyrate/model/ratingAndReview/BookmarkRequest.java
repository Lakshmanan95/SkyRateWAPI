package com.skyrate.model.ratingAndReview;

public class BookmarkRequest {

	int userId;
	int businessId;
	int deleteAll;
	int deleteOne;
	
	public int getDeleteAll() {
		return deleteAll;
	}
	public void setDeleteAll(int deleteAll) {
		this.deleteAll = deleteAll;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getDeleteOne() {
		return deleteOne;
	}
	public void setDeleteOne(int deleteOne) {
		this.deleteOne = deleteOne;
	}
	
	
	
	
}
