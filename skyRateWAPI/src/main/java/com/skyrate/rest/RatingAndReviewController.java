package com.skyrate.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyrate.clib.model.SuccessIDResponse;
import com.skyrate.clib.service.email.EmailService;
import com.skyrate.clib.service.email.EmailTemplateService;
import com.skyrate.clib.util.DateTimeUtil;
import com.skyrate.clib.util.JSONUtil;
import com.skyrate.model.business.DeleteCapabilityRequest;
import com.skyrate.model.dbentity.Bookmark;
import com.skyrate.model.dbentity.Business;
import com.skyrate.model.dbentity.BusinessRatingSummary;
import com.skyrate.model.dbentity.BusinessUserReply;
import com.skyrate.model.dbentity.ConversationMapping;
import com.skyrate.model.dbentity.Messenger;
import com.skyrate.model.dbentity.Rating;
import com.skyrate.model.dbentity.Reviews;
import com.skyrate.model.dbentity.User;
import com.skyrate.model.email.EmailMessage;
import com.skyrate.model.ratingAndReview.AddRatingRequest;
import com.skyrate.model.ratingAndReview.AddRatingResponse;
import com.skyrate.model.ratingAndReview.AddReviewRequest;
import com.skyrate.model.ratingAndReview.BookmarkRequest;
import com.skyrate.model.ratingAndReview.BookmarkResponse;
import com.skyrate.model.ratingAndReview.EditReplyRequest;
import com.skyrate.model.ratingAndReview.EditReviewRequest;
import com.skyrate.model.ratingAndReview.MailReviewerRequest;
import com.skyrate.model.ratingAndReview.RatingCount;
import com.skyrate.model.ratingAndReview.ReviewCount;
import com.skyrate.model.ratingAndReview.ReviewList;
import com.skyrate.model.ratingAndReview.ReviewListResponse;
import com.skyrate.model.ratingAndReview.ReviewPaginationRequest;
import com.skyrate.model.ratingAndReview.ReviewResponse;
import com.skyrate.service.BusinessService;
import com.skyrate.service.MessageService;
import com.skyrate.service.RatingAndReviewService;
import com.skyrate.service.UserMgmtService;

@RestController
@RequestMapping("/rating")
@CrossOrigin( maxAge = 3600)
public class RatingAndReviewController {

	private static final Logger logger =  LoggerFactory.getLogger(RatingAndReviewController.class);
	
	@Autowired
	RatingAndReviewService ratingReviewService;
	@Autowired
	UserMgmtService userMgmtService;
	@Autowired
	BusinessService businessService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailTemplateService emailTemplateService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(method = RequestMethod.POST, value="/addRating")
	public SuccessIDResponse addRating(@RequestBody AddRatingRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{		
			User user = userMgmtService.getUserById(request.getReview().getUserId());
			String createdDate = DateTimeUtil.changeDatetoString(request.getDate());
			Reviews reviewPerUser = ratingReviewService.reviewUserPerDay(request.getReview().getBusinessId(),request.getReview().getUserId(), createdDate);
			if(reviewPerUser == null){
			Reviews review = ratingReviewService.getReviewsById(request.getReview().getId());
			if(review == null){
				review = new Reviews();
				review.setCreatedDate(DateTimeUtil.getTodayString());
				review.setEmail(user.getEmail());
			}
			review.setBusinessId(request.getReview().getBusinessId());
			review.setRatingId(request.getReview().getRatingId());
			review.setUserId(request.getReview().getUserId());
			review.setRecommended(request.getReview().isRecommended());
			review.setApproved(request.getReview().isApproved());
			review.setReview(request.getReview().getReview());
			review.setPricing(request.getReview().getPricing());
			review.setDeliverySpeed(request.getReview().getDeliverySpeed());
			review.setLastModifiedDate(DateTimeUtil.getTodayString());
			double overall = review.getPricing() + review.getDeliverySpeed() + review.getRatingId();
			review.setOverallRate(overall/3);
			ratingReviewService.saveReviews(review);
			ratingReviewService.getBusinessRating(review.getBusinessId());
			
			}
			else{
				response.setSuccess(false);
			}
//			BusinessRatingSummary ratingSummary = ratingReviewService.getRatingSummaryByBusinessId(rating.getBusinessId());			
			
		}
		catch(Exception e){
			logger.error("Rating failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/addReview")
	public SuccessIDResponse addAndUpdateReview(@RequestBody AddReviewRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			User user = userMgmtService.getUserById(request.getReview().getUserId());
			Reviews review = ratingReviewService.getReviewsById(request.getReview().getId());
			if(review == null){
				review = new Reviews();
				review.setCreatedDate(DateTimeUtil.getTodayString());
				review.setEmail(user.getEmail());
			}
			review.setBusinessId(request.getReview().getBusinessId());
			review.setRatingId(request.getReview().getRatingId());
			review.setUserId(request.getReview().getUserId());
			review.setRecommended(request.getReview().isRecommended());
			review.setApproved(request.getReview().isApproved());
			review.setReview(request.getReview().getReview());
			review.setPricing(request.getReview().getPricing());
			review.setDeliverySpeed(request.getReview().getDeliverySpeed());
			review.setLastModifiedDate(DateTimeUtil.getTodayString());
			double overall = review.getPricing() + review.getDeliverySpeed() + review.getRatingId();
			review.setOverallRate(overall/3);
			ratingReviewService.saveReviews(review);
			ratingReviewService.getBusinessRating(review.getBusinessId());
		
		}
		catch(Exception e){
			logger.error("Review failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getReviewByBusiness/{businessId}")
	public ReviewListResponse getReviewByBusiness(@PathVariable int businessId){
		ReviewListResponse response = new ReviewListResponse();
		try{
			List<ReviewList> reviewList = ratingReviewService.getReviewListByBusiness(businessId);
			ReviewCount count = ratingReviewService.getReviewsCount(businessId);
			response.setReviewList(reviewList);
			response.setCount(count.getCount());
			
		}
		catch(Exception e){
			logger.error("Review List Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/getReviewByPagination")
	public  ReviewListResponse getReviewByPagination(@RequestBody ReviewPaginationRequest request){
		ReviewListResponse response = new ReviewListResponse();
		try{
			int from = request.getValue();
			int to=5+from;

			List<ReviewList> reviewList = ratingReviewService.getReviewPagination(request.getBusinessId(),request.getPageOrder(),from,to);
			ReviewCount count = ratingReviewService.getReviewsCount(request.getBusinessId());
			response.setReviewList(reviewList);
			response.setCount(count.getCount());
			
		}
		catch(Exception e){
			logger.error("Review List Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	@RequestMapping(method = RequestMethod.POST, value="/delateBookmark")
	public BookmarkResponse deleteBookmark(@RequestBody BookmarkRequest request){
		BookmarkResponse response = new BookmarkResponse();
		 Integer bookmarkId = ratingReviewService.deleteBookmark(request.getUserId(), request.getBusinessId());
		 if(bookmarkId == null || bookmarkId ==1)
		 response.setDeleted("deleted");
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/updateBookmark")
	public BookmarkResponse addBookmark(@RequestBody BookmarkRequest request){
		BookmarkResponse response = new BookmarkResponse();
		try{
			
			if(request.getDeleteAll()==1){
				 Integer bookmarkId = ratingReviewService.deleteAllBookmarks(request.getUserId());
				 if(bookmarkId == null || bookmarkId ==1)
				 response.setDeleted("deleted");
				 return response;
			}
			if (request.getDeleteOne()==1){
				 Integer bookmarkId = ratingReviewService.deleteBookmark(request.getUserId(), request.getBusinessId());
				 if(bookmarkId == null || bookmarkId ==1)
				 response.setDeleted("deleted");
				 return response;
			}
			long cnt = ratingReviewService.countBookMark(request.getUserId());
			if(cnt==4){
				response.setSuccess(false);
				response.setUserErrorMsg("Maximum 4 comparison allowed.");
				return response;
			}
			Bookmark bookmark = ratingReviewService.getByBoomarkId(request.getUserId(), request.getBusinessId());;
			if(bookmark == null){
				bookmark = new Bookmark();
				bookmark.setUserId(request.getUserId());
				bookmark.setBusinessId(request.getBusinessId());
				bookmark.setMarked(true);
				ratingReviewService.saveBookmark(bookmark);
				response.setBookmark(bookmark);
			}else{
				response.setSuccess(false);
				response.setUserErrorMsg("Business already in the comparison list.");
				return response;
			}
			
			logger.info("add bookmark");
		}
		catch(Exception e){
			logger.error("Bookmark failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/getBookmark")
	public BookmarkResponse getBookmark(@RequestBody BookmarkRequest request){
		BookmarkResponse response = new BookmarkResponse();
		try{
			Bookmark bookmark = ratingReviewService.getByBoomarkId(request.getUserId(), request.getBusinessId());
			response.setBookmark(bookmark);
			
		}
		catch(Exception e){
			logger.error("Bookmark failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/editReview")
	public SuccessIDResponse editReview(@RequestBody EditReviewRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
//			Reviews review = ratingReviewService.getReviewsById(request.getReview().getId());
//			review.setReview(request.getComment());
//			ratingReviewService.saveReviews(review);
			
		}
		catch(Exception e){
			logger.error("failed editing");
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteReview")
	public SuccessIDResponse deleteReview(@RequestBody EditReviewRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			ratingReviewService.deleteBusinessRating(request.getBusinessId(),request.getId());
			ratingReviewService.deleteById(request.getId());
			
		}
		catch(Exception e){
			logger.error("failed delete review ",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getReviewById/{id}")
	public ReviewResponse getReviewById(@PathVariable int id){
		ReviewResponse response = new ReviewResponse();
		try{
			Reviews reviews = ratingReviewService.getReviewsById(id);
			response.setReviews(reviews);
			
		}
		catch(Exception e){
			logger.error("Review failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/mailToReviewer")
	public SuccessIDResponse mailToReviewer(@RequestBody MailReviewerRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			System.out.println("review ask a quwstion"+JSONUtil.toJson(request));
			Reviews review = ratingReviewService.getReviewsById(request.getReviewId());
			System.out.println("business id "+review.getBusinessId() );
			Business business = businessService.getBusiness(review.getBusinessId());
			ConversationMapping conversationMapping = messageService.conversationMapping(request.getFromId(), review.getUserId());
			if(conversationMapping == null){
				conversationMapping = new ConversationMapping();
				conversationMapping.setMessenger1(request.getFromId());
				conversationMapping.setMessenger2(review.getUserId());
				messageService.saveConversationMapping(conversationMapping);
				
			}
//			Messenger message = new Messenger();
//			message.setConversationId(conversationMapping.getId());
//			message.setDateTime(new Date());
//			message.setFromId(request.getFromId());
//			message.setToId(review.getUserId());
//			message.setReviewId(review.getId());
//			message.setDefaultMessage(1);
//			message.setReadFlag(1);
//			String starter = "Regarding your review for "+business.getName();
//			String defaultMessage = starter+ "-"+review.getReview();
//			message.setMessage(defaultMessage);
//			messageService.saveMessenger(message);
		
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("firstName", user.getFirstName());
//			map.put("lastName", user.getLastName());
//			map.put("from", questionedUser.getEmail());
//			map.put("body", request.getEmailSubject());
//			String subject= "Regarding your Skyrate review for '"+business.getName()+"'";
//			String emailBody = emailTemplateService.getEmailTemplate("askQuestion.vm",map);
//			EmailMessage emailMessage = new EmailMessage();
//			emailMessage.setToEmail(user.getEmail());
//			emailMessage.setSubject(subject);
//			emailMessage.setEmailBody(emailBody);
//			emailService.sendEmail(user.getEmail(), subject, emailBody, null);
		}
		catch(Exception e){
			logger.error("EMail Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkForReview")
	public SuccessIDResponse checkForReview(@RequestBody AddRatingRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			String createdDate = DateTimeUtil.changeDatetoString(request.getDate());
			Reviews reviewPerUser = ratingReviewService.reviewUserPerDay(request.getReview().getBusinessId(),request.getReview().getUserId(), createdDate);
			if(reviewPerUser == null){
				response.setSuccess(true);
				
			}
			else{
				response.setSuccess(false);
			
			}
		}
		catch(Exception e){
			logger.error("check Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/editReply")
	public SuccessIDResponse editReply(@RequestBody EditReplyRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			BusinessUserReply businessUserReply = ratingReviewService.getBusinessUserReply(request.getId());
			businessUserReply.setReply(request.getReply());
			businessUserReply.setLastModifiedDate(DateTimeUtil.getTodayString());
			ratingReviewService.saveReply(businessUserReply);
			
		}
		catch(Exception e){
			logger.error("edit Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteReply")
	public SuccessIDResponse deleteReply(@RequestBody DeleteCapabilityRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			ratingReviewService.deleteBusinessUserReply(request.getId());
			
		}
		catch(Exception e){
			logger.error("delete Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
}
