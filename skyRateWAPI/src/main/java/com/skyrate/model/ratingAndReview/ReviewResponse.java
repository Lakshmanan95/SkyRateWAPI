package com.skyrate.model.ratingAndReview;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Reviews;

public class ReviewResponse extends BaseResponse{

	Reviews reviews;

	public Reviews getReviews() {
		return reviews;
	}

	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
	}
	
	
}
