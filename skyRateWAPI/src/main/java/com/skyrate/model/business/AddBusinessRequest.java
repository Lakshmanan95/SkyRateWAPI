package com.skyrate.model.business;

import java.util.List;

import com.skyrate.model.dbentity.Business;
import com.skyrate.model.dbentity.BusinessCategoryMapping;
import com.skyrate.model.dbentity.Reviews;

public class AddBusinessRequest {

	Business business;
	List<CategoryList> category;
	List<CategoryList> deleteCategory;
	String[] capabilities;
	String[] parts;
	int userId;
	int roleId;
	Reviews myReviews;
	
	
	

	public Reviews getMyReviews() {
		return myReviews;
	}

	public void setMyReviews(Reviews myReviews) {
		this.myReviews = myReviews;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public List<CategoryList> getCategory() {
		return category;
	}

	public void setCategory(List<CategoryList> category) {
		this.category = category;
	}

	public List<CategoryList> getDeleteCategory() {
		return deleteCategory;
	}

	public void setDeleteCategory(List<CategoryList> deleteCategory) {
		this.deleteCategory = deleteCategory;
	}

	public String[] getParts() {
		return parts;
	}

	public void setParts(String[] parts) {
		this.parts = parts;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String[] getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String[] capabilities) {
		this.capabilities = capabilities;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
