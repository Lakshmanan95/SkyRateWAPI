package com.skyrate.model.ratingAndReview;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skyrate.model.dbentity.Rating;
import com.skyrate.model.dbentity.Reviews;

public class AddRatingRequest {

	Rating rating;
	Reviews review;
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date date;
	
	
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
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
