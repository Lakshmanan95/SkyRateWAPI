package com.skyrate.model.dbentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BusinessRatingSummary {

	@Id
	@GeneratedValue
	int id;
	int businessId;
	int reviewCount;
	double ratingCount;
	double ratingAverage;
	int isRecommend;
	int notRecommend;
	double pricingAverage;
	double deliverySpeedAvg;
	double overallRating;
	
	
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
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public double getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(double ratingCount) {
		this.ratingCount = ratingCount;
	}
	public double getRatingAverage() {
		return ratingAverage;
	}
	public void setRatingAverage(double ratingAverage) {
		this.ratingAverage = ratingAverage;
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
	public double getPricingAverage() {
		return pricingAverage;
	}
	public void setPricingAverage(double pricingAverage) {
		this.pricingAverage = pricingAverage;
	}
	public double getDeliverySpeedAvg() {
		return deliverySpeedAvg;
	}
	public void setDeliverySpeed(double deliverySpeedAvg) {
		this.deliverySpeedAvg = deliverySpeedAvg;
	}
	public double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	public void setDeliverySpeedAvg(double deliverySpeedAvg) {
		this.deliverySpeedAvg = deliverySpeedAvg;
	}
	
	
}
