package com.skyrate.model.report;

import com.skyrate.model.dbentity.Business;

public class PopularSearch extends Business{

	int totalHits;
	String ratingAverage;
	double overallRating;
	int reviewCount;
	int isRecommend;
	int notRecommend;
	
	public int getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(int totalHits) {
		this.totalHits = totalHits;
	}
	public String getRatingAverage() {
		return ratingAverage;
	}
	public void setRatingAverage(String ratingAverage) {
		this.ratingAverage = ratingAverage;
	}
	public double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	public int getNotRecommend() {
		return notRecommend;
	}
	public void setNotRecommend(int notRecommend) {
		this.notRecommend = notRecommend;
	}
	
	
	
}
