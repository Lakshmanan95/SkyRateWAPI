package com.skyrate.model.business;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.report.PopularSearch;

public class PopularSearchResponse extends BaseResponse{

	List<PopularSearch> popularSearch;

	public List<PopularSearch> getPopularSearch() {
		return popularSearch;
	}

	public void setPopularSearch(List<PopularSearch> popularSearch) {
		this.popularSearch = popularSearch;
	}
	
	
}
