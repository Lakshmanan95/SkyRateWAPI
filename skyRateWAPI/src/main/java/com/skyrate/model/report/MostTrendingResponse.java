package com.skyrate.model.report;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class MostTrendingResponse extends BaseResponse {

	List<PopularSearch> mostViewd;
	List<PopularSearch> mostReviewd;
	List<PopularSearch> mostRated;
	public List<PopularSearch> getMostViewd() {
		return mostViewd;
	}
	public void setMostViewd(List<PopularSearch> mostViewd) {
		this.mostViewd = mostViewd;
	}
	public List<PopularSearch> getMostReviewd() {
		return mostReviewd;
	}
	public void setMostReviewd(List<PopularSearch> mostReviewd) {
		this.mostReviewd = mostReviewd;
	}
	public List<PopularSearch> getMostRated() {
		return mostRated;
	}
	public void setMostRated(List<PopularSearch> mostRated) {
		this.mostRated = mostRated;
	}
	
	
	
	
}
