package com.skyrate.model.dbentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RatingDescription {

	@Id
	@GeneratedValue
	int id;
	int ratingNo;
	String ratingDesription;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRatingNo() {
		return ratingNo;
	}
	public void setRatingNo(int ratingNo) {
		this.ratingNo = ratingNo;
	}
	public String getRatingDesription() {
		return ratingDesription;
	}
	public void setRatingDesription(String ratingDesription) {
		this.ratingDesription = ratingDesription;
	}
	
	

}
