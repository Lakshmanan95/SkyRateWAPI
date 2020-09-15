package com.skyrate.model.ratingAndReview;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class ReviewListResponse extends BaseResponse{

	List<ReviewList> reviewList;
	int count;

	public List<ReviewList> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<ReviewList> reviewList) {
		this.reviewList = reviewList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
