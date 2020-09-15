package com.skyrate.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
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
import com.skyrate.clib.util.DateTimeUtil;
import com.skyrate.clib.util.JSONUtil;
import com.skyrate.model.business.PopularSearchResponse;
import com.skyrate.model.dbentity.ContactSubject;
import com.skyrate.model.dbentity.EventLog;
import com.skyrate.model.dbentity.User;
import com.skyrate.model.report.AddEventRequest;
import com.skyrate.model.report.MostTrendingResponse;
import com.skyrate.model.report.PopularCategory;
import com.skyrate.model.report.PopularCategoryResponse;
import com.skyrate.model.report.PopularSearch;
import com.skyrate.model.report.ReportPerBusiness;
import com.skyrate.model.report.ReportPerUserResponse;
import com.skyrate.model.report.ReportResponse;
import com.skyrate.model.report.SubjectListResponse;
import com.skyrate.model.report.TopSearchRequest;
import com.skyrate.model.report.TotalHit;
import com.skyrate.model.report.TotalHitRequest;
import com.skyrate.model.report.UserReport;
import com.skyrate.model.report.UserReportResponse;
import com.skyrate.model.report.VendorDetails;
import com.skyrate.model.usermgmt.UserRating;
import com.skyrate.service.ReportService;
import com.skyrate.service.UserMgmtService;

@RestController
@RequestMapping("/report")
@CrossOrigin(maxAge=3600)
public class ReportController {

	private static final Logger logger =  LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	ReportService reportService;
	@Autowired
	private UserMgmtService userMgmtService;
	
	@RequestMapping(method = RequestMethod.POST, value="/addEvent")
	public SuccessIDResponse addEvent(@RequestBody AddEventRequest request,HttpServletRequest httpRequest){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			EventLog event = new EventLog();
			 String ipAddress = httpRequest.getHeader("X-FORWARDED-FOR");  
		       if (ipAddress == null) {  
		         ipAddress = httpRequest.getRemoteAddr();  
		   }
		      
			String uuid = UUID.randomUUID().toString().replace("-", "");
//			if(event == null){
//				event = new EventLog();
				event.setUniqueId(uuid);
//			}
			event.setBusinessId(request.getBusinessId());
			event.setEvent(request.getEvent());
			event.setUserId(request.getUserId());
			event.setIpAddress(request.getIpAddress());
			event.setLastUpdatedDate(new Date());
			int value = event.getTotalHits() + 1;
			event.setTotalHits(value);
			reportService.saveEvent(event);
			
		}
		catch(Exception e){
			logger.error("Event added failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/getVendorEvent")
	public ReportResponse getVendorEvent(@RequestBody TopSearchRequest request){
		ReportResponse response = new ReportResponse();
		try{
			String date = null;
			if(request.getVendorDate().equals("Today")){
				
				date = DateTimeUtil.getTodayString();
			}
			if(!request.getVendorDate().isEmpty() && !request.getVendorDate().equals("Today") && !request.getVendorDate().equals("Last 30 days"))
			 date = DateTimeUtil.changeStringFormat(request.getVendorDate());
			if(request.getVendorDate().equals("Last 30 days")){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				Date result = cal.getTime();
				System.out.println("nw date "+result);
				date =DateTimeUtil.changeDatetoString(result);
			}
			List<VendorDetails> vendorSearch = reportService.getVendorByEvent(request.getValue(),date);
			response.setVendorSearch(vendorSearch);
			
		}
		catch(Exception e){
			logger.error("vendor detail failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	@RequestMapping(method = RequestMethod.POST, value="/getFeedbackEvent")
	public ReportResponse getUserFeedbackEvent(@RequestBody TopSearchRequest request){
		ReportResponse response = new ReportResponse();
		try{
			String date = DateTimeUtil.getTodayString();
			if(!request.getFeedbackDate().isEmpty() && !request.getFeedbackDate().equals("Today") && !request.getFeedbackDate().equals("Last 30 days"))
			 date = DateTimeUtil.changeStringFormat(request.getFeedbackDate());
			if(request.getFeedbackDate().equals("Last 30 days")){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				Date result = cal.getTime();
				
				date =DateTimeUtil.changeDatetoString(result);
			}
			List<UserRating> vendorFeedback = reportService.getUserRating(date);
			response.setVendorReview(vendorFeedback);
			
		}
		catch(Exception e){
			logger.error("vendor detail failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/getTotalHits")
	public TotalHit getTotalHits(@RequestBody TotalHitRequest request){
		TotalHit response = new TotalHit();
		try{
			String date = null;
			if(request.getDate().equals("Today"))
				date = DateTimeUtil.getTodayString();
			if(!request.getDate().isEmpty() && !request.getDate().equals("Today") && !request.getDate().equals("Last 30 days"))
			 date = DateTimeUtil.changeStringFormat(request.getDate());
			if(request.getDate().equals("Last 30 days")){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				Date result = cal.getTime();
				date =DateTimeUtil.changeDatetoString(result);
			}
			response = reportService.getTotalHit(date);
		
		}
		catch(Exception e){
			logger.error("total count failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/getUserReport")
	public UserReportResponse getUserReport(@RequestBody TopSearchRequest request){
		UserReportResponse response = new UserReportResponse();
		try{
			String date = DateTimeUtil.getTodayString();
			if(!request.getFeedbackDate().isEmpty() && !request.getFeedbackDate().equals("Today") && !request.getFeedbackDate().equals("Last 30 days"))
			 date = DateTimeUtil.changeStringFormat(request.getFeedbackDate());
			if(request.getFeedbackDate().equals("Last 30 days")){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				Date result = cal.getTime();
				
				date =DateTimeUtil.changeDatetoString(result);
			}
			List<UserReport> report = reportService.getUserReport(date);
			response.setReport(report);
					
		}
		catch(Exception e){
			logger.error("response failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/userReport/{userId}")
	public ReportPerUserResponse userReportById(@PathVariable int userId){
		ReportPerUserResponse response = new ReportPerUserResponse();
		try{
			List<ReportPerBusiness> report = reportService.getReportPerBusienss(userId);
			User user = userMgmtService.getUserById(userId);
			user.setPassword(null);
			response.setUser(user);
			response.setReport(report);
			
		}
		catch(Exception e){
			logger.error("response failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getCategory")
	public PopularCategoryResponse getCategory(){
		PopularCategoryResponse response = new PopularCategoryResponse();
		try{
			List<PopularCategory> category = reportService.getCategory();
			response.setCategory(category);
			
		}
		catch(Exception e){
			logger.error("response failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/getTrending")
	public MostTrendingResponse getMostTrending(){
		MostTrendingResponse response = new MostTrendingResponse();
		try{
			List<PopularSearch> mostViewd = reportService.getMostTrendingByValue("TOTAL_HITS");
			List<PopularSearch> mostReviewd = reportService.getMostTrendingByValue("REVIEW_COUNT");
			List<PopularSearch> mostRated = reportService.getMostTrendingByValue("OVERALL_RATING");
			response.setMostViewd(mostViewd);
			response.setMostReviewd(mostReviewd);
			response.setMostRated(mostRated);
			
			
		}
		catch(Exception e){
			logger.error("tranding failed "+e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getSubject/{subjectType}")
	public SubjectListResponse getSubject(@PathVariable int subjectType){
		SubjectListResponse response = new SubjectListResponse();
		try{
			List<ContactSubject> contactSubject = reportService.getBySubjectType(subjectType);
			response.setContactSubject(contactSubject);
		
		}
		catch(Exception e){
			logger.error("subject list failed "+e);
			response.setSuccess(false);
		}
		return response;
		
	}
}
