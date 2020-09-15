package com.skyrate.model.business;

public class ApprovalRequest {

	int approval;
	int businessId;
	int userId;
	int claimed;
	
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getClaimed() {
		return claimed;
	}
	public void setClaimed(int claimed) {
		this.claimed = claimed;
	}
	
	
}
