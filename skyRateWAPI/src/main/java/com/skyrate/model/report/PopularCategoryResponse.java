package com.skyrate.model.report;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class PopularCategoryResponse extends BaseResponse{

	List<PopularCategory> category;

	public List<PopularCategory> getCategory() {
		return category;
	}

	public void setCategory(List<PopularCategory> category) {
		this.category = category;
	}
	
	
}
