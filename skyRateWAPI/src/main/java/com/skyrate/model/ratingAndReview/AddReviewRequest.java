package com.skyrate.model.ratingAndReview;

import java.util.Date;

import com.skyrate.model.dbentity.Reviews;

public class AddReviewRequest {

	Reviews review;
	Date date;

	public Reviews getReview() {
		return review;
	}

	public void setReview(Reviews review) {
		this.review = review;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
