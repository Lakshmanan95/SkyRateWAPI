package com.skyrate.model.dbentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reviews {

	@Id
	@GeneratedValue
	int id;
	int businessId;
	double ratingId;
	String review;
	int userId;
	boolean approved;
	boolean recommended;
	String createdDate;
	String lastModifiedDate;
	int pricing;
	int deliverySpeed;
	double overallRate;
	String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	
	public double getRatingId() {
		return ratingId;
	}
	public void setRatingId(double ratingId) {
		this.ratingId = ratingId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isRecommended() {
		return recommended;
	}
	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public int getPricing() {
		return pricing;
	}
	public void setPricing(int pricing) {
		this.pricing = pricing;
	}
	public int getDeliverySpeed() {
		return deliverySpeed;
	}
	public void setDeliverySpeed(int deliverySpeed) {
		this.deliverySpeed = deliverySpeed;
	}
	public double getOverallRate() {
		return overallRate;
	}
	public void setOverallRate(double overallRate) {
		this.overallRate = overallRate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
