package com.skyrate.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrate.clib.util.JSONUtil;
import com.skyrate.dao.BookmarkRepository;
import com.skyrate.dao.BusinessRatingSummaryRepository;
import com.skyrate.dao.BusinessUserReplyRepository;
import com.skyrate.dao.RatingQuery;
import com.skyrate.dao.RatingRepository;
import com.skyrate.dao.ReviewsRepository;
import com.skyrate.model.dbentity.Bookmark;
import com.skyrate.model.dbentity.BusinessRatingSummary;
import com.skyrate.model.dbentity.BusinessUserReply;
import com.skyrate.model.dbentity.Rating;
import com.skyrate.model.dbentity.Reviews;
import com.skyrate.model.ratingAndReview.RatingCount;
import com.skyrate.model.ratingAndReview.ReviewCount;
import com.skyrate.model.ratingAndReview.ReviewList;

@Service
@Transactional
public class RatingAndReviewService {

	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	RatingQuery ratingQuery;

	@Autowired
	ReviewsRepository reviewsRepository;
	
	@Autowired
	BusinessRatingSummaryRepository ratingSummaryRepository;
	
	@Autowired
	BookmarkRepository bookmarkRepository;
	
	@Autowired
	BusinessUserReplyRepository businessUserReplyRepository;
	
	/* Rating and RatingSummary */	
	public Rating saveRating(Rating rating){
		return this.ratingRepository.save(rating);
	}
	
	public Rating getById(int id){
		return this.ratingRepository.findById(id);
	}
	
	public BusinessRatingSummary getRatingSummaryByBusinessId(int id){
		return this.ratingSummaryRepository.findByBusinessId(id);
	}
	
	public BusinessRatingSummary saveRatingSummary(BusinessRatingSummary ratingSummary){
		return this.ratingSummaryRepository.save(ratingSummary);
	}
	public long getRatingCount(int businessId){
		return this.ratingRepository.countByBusinessId(businessId);
	}
	public RatingCount getRatingTotal(int businessId){
		return this.ratingQuery.getRatingTotalByBusinessId(businessId);
	}
	public void getBusinessRating(int businessId){
		BusinessRatingSummary ratingSummary = getRatingSummaryByBusinessId(businessId);
		if(ratingSummary == null){
			ratingSummary = new BusinessRatingSummary();
			ratingSummary.setBusinessId(businessId);
		}
		int count = (int) getRatingCount(businessId);
		RatingCount totalRating = getRatingTotal(businessId);
		ReviewCount reviewCount = getReviewsCount(businessId);
		ReviewCount pricingCount = getCount(businessId,"pricing");
		RatingCount pricingAvg = getAverage(businessId,"pricing");
		ReviewCount speedCount = getCount(businessId,"delivery_speed");
		RatingCount speedAvg = getAverage(businessId,"delivery_speed");
		int totalRate = this.reviewsRepository.countByBusinessId(businessId);
		double average = totalRating.getCount()/totalRate;
		double pricingAverage = 0;
		if(pricingCount.getCount() != 0){
			pricingAverage = pricingAvg.getCount()/pricingCount.getCount();
		}
		double speedAverage = 0;
		if(speedCount.getCount() != 0){
			speedAverage = speedAvg.getCount()/speedCount.getCount();
		}
		int isRecommend = this.reviewsRepository.countByRecommendedAndBusinessId(true, businessId);
		int notRecommend = this.reviewsRepository.countByRecommendedAndBusinessId(false, businessId);
		double overallRatingAdded= average + speedAverage + pricingAverage;
		double overallRating = overallRatingAdded/3;
		DecimalFormat df = new DecimalFormat("#.00");
	    String angleFormated = df.format(overallRating);
		ratingSummary.setRatingCount(totalRate);
		ratingSummary.setRatingAverage(average);
		ratingSummary.setReviewCount((int) reviewCount.getCount());
		ratingSummary.setIsRecommend(isRecommend);
		ratingSummary.setNotRecommend(notRecommend);
		ratingSummary.setPricingAverage(pricingAverage);
		ratingSummary.setDeliverySpeed(speedAverage);
		ratingSummary.setOverallRating(Double.parseDouble(angleFormated));
		saveRatingSummary(ratingSummary);

	}
	/* Reviews*/	
	public Reviews saveReviews(Reviews reviews){
		return this.reviewsRepository.save(reviews);
	}
	public Reviews getReviewsById(int id){
		return this.reviewsRepository.findById(id);
	}
	public Reviews reviewUserPerDay(int businessId,int userId,String date){
		return this.reviewsRepository.findByBusinessIdAndUserIdAndCreatedDate(businessId,userId, date);
	}
	public ReviewCount getReviewsCount(int businessId){
		return this.ratingQuery.getReviewCoutByBusinessId(businessId);
	}
	public List<ReviewList> getReviewListByBusiness(int businessId){
		return this.ratingQuery.getReviewByBusinessId(businessId);
	}
	public List<ReviewList> getReviewPagination(int businessId,String pageOrder,int from, int to){
		return this.ratingQuery.getReviewByPagination(businessId, pageOrder, from, to);
	}
	public RatingCount getAverage(int businessId,String column){
		return this.ratingQuery.getAverage(businessId, column);
	}
	public ReviewCount getCount(int businessId, String column){
		return this.ratingQuery.getCount(businessId, column);
	}
	
	public Bookmark saveBookmark(Bookmark bookmark){
		return this.bookmarkRepository.save(bookmark);
	}
	public Bookmark getByBoomarkId(int userId,int businessId){
		return this.bookmarkRepository.findByUserIdAndBusinessId(userId, businessId);
	}
	public long countBookMark(int userId){
		return this.bookmarkRepository.countByUserId(userId);
	}
	public Integer deleteAllBookmarks(int userId){
		return this.bookmarkRepository.deleteByUserId(userId);
	}
	public Integer deleteBookmark(int userId,int businessId){
		return this.bookmarkRepository.deleteByUserIdAndBusinessId(userId, businessId);
	}
	public void deleteById(int id){
		 this.reviewsRepository.deleteById(id);
	}
	public void deleteBusinessRating(int businessId,int reviewId){
		BusinessRatingSummary ratingSummary = getRatingSummaryByBusinessId(businessId);
		Reviews review = getReviewsById(reviewId);
		RatingCount totalRating = getRatingTotal(businessId);
		ReviewCount pricingCount = getCount(businessId,"pricing");
		RatingCount pricingAvg = getAverage(businessId,"pricing");
		ReviewCount speedCount = getCount(businessId,"delivery_speed");
		RatingCount speedAvg = getAverage(businessId,"delivery_speed");
		int reviewCount = ratingSummary.getReviewCount() - 1;
		double ratingCount = ratingSummary.getRatingCount() - 1;
		int totalRate = this.reviewsRepository.countByBusinessId(businessId) - 1;
		double average = 0;
		if(totalRate != 0){
			average =  (totalRating.getCount() - review.getRatingId())/totalRate;
		}
		double pricingAverage = 0;
		if(pricingCount.getCount() != 0 && pricingCount.getCount() != 1){
			int totalPriceCount = pricingCount.getCount() - 1;
			double avgCount = pricingAvg.getCount()-review.getPricing();
			pricingAverage = avgCount/totalPriceCount;
		}
		double speedAverage = 0;
		if(speedCount.getCount() != 0 && speedCount.getCount() != 1){
			int totalSpeedCount = speedCount.getCount() - 1;
			double avgCount = speedAvg.getCount()-review.getDeliverySpeed();
			speedAverage = avgCount/totalSpeedCount;
		}
		int isRecommend = this.reviewsRepository.countByRecommendedAndBusinessId(true, businessId);
		if(review.isRecommended()){
			if(isRecommend == 1)
				isRecommend = 0;
			else
				isRecommend-=1;
		}
		int notRecommend = this.reviewsRepository.countByRecommendedAndBusinessId(false, businessId);
		if(!review.isRecommended()){
			if(notRecommend == 1)
				notRecommend = 0;
			else
				notRecommend-=1;
		}
		String angleFormated = "0";
		if(average != 0.0){
		double overallRatingAdded= average + speedAverage +  pricingAverage;
		double overallRating = overallRatingAdded/3;
		DecimalFormat df = new DecimalFormat("#.00");
	    angleFormated = df.format(overallRating);
		}
		ratingSummary.setRatingAverage(average);
		ratingSummary.setRatingCount(ratingCount);
		ratingSummary.setReviewCount(reviewCount);
		ratingSummary.setPricingAverage(pricingAverage);
		ratingSummary.setDeliverySpeed(speedAverage);
		ratingSummary.setOverallRating(Double.parseDouble(angleFormated));
		ratingSummary.setIsRecommend(isRecommend);
		ratingSummary.setNotRecommend(notRecommend);
		saveRatingSummary(ratingSummary);
	}
	
	public BusinessUserReply getBusinessUserReply(int id){
		return businessUserReplyRepository.findById(id);
	}
	public BusinessUserReply saveReply(BusinessUserReply userReply){
		return businessUserReplyRepository.save(userReply);
	}
	public void deleteBusinessUserReply(int id){
		businessUserReplyRepository.deleteById(id);
	}
}
