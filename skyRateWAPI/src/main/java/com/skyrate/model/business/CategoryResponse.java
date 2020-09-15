package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Category;

public class CategoryResponse extends BaseResponse{

	List<CategoryList> category;

	public List<CategoryList> getCategory() {
		return category;
	}

	public void setCategory(List<CategoryList> category) {
		this.category = category;
	}
	
	
	
}
