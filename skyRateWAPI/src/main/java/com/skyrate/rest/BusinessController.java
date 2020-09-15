package com.skyrate.rest;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyrate.clib.model.SuccessIDResponse;
import com.skyrate.clib.service.email.EmailService;
import com.skyrate.clib.service.email.EmailTemplateService;
import com.skyrate.clib.util.DateTimeUtil;
import com.skyrate.clib.util.JSONUtil;
import com.skyrate.clib.util.SKYRATEUtil;
import com.skyrate.model.business.ActiveRequest;
import com.skyrate.model.business.AdDetails;
import com.skyrate.model.business.AdListResponse;
import com.skyrate.model.business.AddBusinessRequest;
import com.skyrate.model.business.ApprovalRequest;
import com.skyrate.model.business.BookmarkDetails;
import com.skyrate.model.business.BusinessCategory;
import com.skyrate.model.business.BusinessClaimed;
import com.skyrate.model.business.BusinessCount;
import com.skyrate.model.business.BusinessDetailRequest;
import com.skyrate.model.business.BusinessDetails;
import com.skyrate.model.business.BusinessDetailsResponse;
import com.skyrate.model.business.BusinessExistsRequest;
import com.skyrate.model.business.BusinessExistsResponse;
import com.skyrate.model.business.BusinessReplyRequest;
import com.skyrate.model.business.CapabilityResponse;
import com.skyrate.model.business.CategoryList;
import com.skyrate.model.business.CategoryResponse;
import com.skyrate.model.business.ClaimBusinessRequest;
import com.skyrate.model.business.ClaimBusinessResponse;
import com.skyrate.model.business.ClaimedBusinessResponse;
import com.skyrate.model.business.ContactRequest;
import com.skyrate.model.business.DeleteCapabilityRequest;
import com.skyrate.model.business.FavoriteResponse;
import com.skyrate.model.business.MyBusiness;
import com.skyrate.model.business.MyBusinessResponse;
import com.skyrate.model.business.PaginationRequest;
import com.skyrate.model.business.PopularSearchRequest;
import com.skyrate.model.business.PopularSearchResponse;
import com.skyrate.model.business.SearchBusinessRequest;
import com.skyrate.model.business.SearchBusinessResponse;
import com.skyrate.model.dbentity.Business;
import com.skyrate.model.dbentity.BusinessClaimMapping;
import com.skyrate.model.dbentity.BusinessRatingSummary;
import com.skyrate.model.dbentity.BusinessUserReply;
import com.skyrate.model.dbentity.Capabilities;
import com.skyrate.model.dbentity.ContactSubject;
import com.skyrate.model.dbentity.FeedbackForm;
import com.skyrate.model.dbentity.Parts;
import com.skyrate.model.dbentity.RejectedBusiness;
import com.skyrate.model.dbentity.Reviews;
import com.skyrate.model.dbentity.User;
import com.skyrate.model.email.EmailMessage;
import com.skyrate.model.report.PopularSearch;
import com.skyrate.model.report.TopSearchRequest;
import com.skyrate.service.BusinessService;
import com.skyrate.service.RatingAndReviewService;
import com.skyrate.service.ReportService;
import com.skyrate.service.UserMgmtService;
import com.skyrate.useralerts.UserAlertsService;
import com.skyrate.useralerts.entity.UserAlerts;
import com.skyrate.useralerts.entity.UserAlertsMapping;


@RestController
@RequestMapping("/business")
@CrossOrigin(maxAge=3600)
public class BusinessController {

	private static final Logger logger =  LoggerFactory.getLogger(BusinessController.class);
	private static Properties properties = new Properties();
	
	String adminConfigLocation = SKYRATEUtil.getConfigDIR() + "/adminConfig.properties";
	
	@Autowired
	BusinessService businessService;
	
	@Autowired
	RatingAndReviewService ratingReviewService;
	
	@Autowired
	UserMgmtService userMgmtService;
	
	@Autowired
	EmailService emailService;
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@Autowired
	ReportService reportService;
	
	@Autowired
	UserAlertsService userAlertsService;
	
	@RequestMapping(method = RequestMethod.POST, value="/searchBusiness")
	public SearchBusinessResponse searchBusiness(@RequestBody SearchBusinessRequest request){
		SearchBusinessResponse response = new SearchBusinessResponse();
		try{
			
			if(request.getName() != null ){
				List<BusinessDetails> business = null;
				business = businessService.searchBusiness(request.getCountry(),request.getCategory(),request.getName(),request.getRoleId(),request.getCreatedId());
				
/*					business = businessService.searchForAll(request.getCountry(),request.getName(),request.getRoleId());			
 * 
 */
			
				BusinessCount count = businessService.businessCount(request.getCountry(),request.getCategory(), request.getName(), request.getRoleId());
			
				response.setBusiness(business);
				response.setCount(count.getCount());
			}
			
		}
		catch(Exception e){
			logger.error("search failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/getBusinessById")
	public BusinessDetailsResponse getBusiness(@RequestBody BusinessDetailRequest request){
		BusinessDetailsResponse response = new BusinessDetailsResponse();
		try{
			BusinessDetails business = businessService.getBusinessById(request.getId());
			BusinessRatingSummary ratingSummary = ratingReviewService.getRatingSummaryByBusinessId(request.getId());
			if(ratingSummary != null){
				response.setBusinessRating(ratingSummary);
			}
			response.setBusiness(business);
			List<BusinessCategory> mapping = businessService.getBusinessCategory(request.getId());
			response.setBusinessCategory(mapping);
			List<CategoryList> category = businessService.getCategoryByBusiness(request.getId());
			BusinessClaimMapping businessClaim = businessService.getClaimMappingById(request.getId());
			if(businessClaim != null)
				response.setBusinessClaim(businessClaim);
			
			response.setCategory(category);
			
			
		}
		catch(Exception e){
			logger.error("Business by Id Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/getBusinessByPagination")
	public SearchBusinessResponse getBusinessByPagination(@RequestBody PaginationRequest request){
		SearchBusinessResponse response = new SearchBusinessResponse();
		try{
			 int from=1;
				int to=10;
				for(int i=1;i<=request.getValue();i++){
					if(i==1){
						from=0;
						to=10;
					}
					else{
						from+=10;
						to+=10;
					}
				}
			List<BusinessDetails> business = businessService.getBusinessByPagination(from, to,request.getCategory(),request.getSearch(),request.getRoleId());
			response.setBusiness(business);
			
		}
		catch(Exception e){
			logger.error("business pagination failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/popularSearches")
	public PopularSearchResponse popularSearch(@RequestBody PopularSearchRequest request){
		PopularSearchResponse response = new PopularSearchResponse();
		try{
			List<PopularSearch> search = null;
			if(request.getEnablePopular() == 1){
				search = reportService.getPopularSearch(request.getCategory());
			}
			else{
				search = reportService.getTrendingBusiness();
			}
			response.setPopularSearch(search);
			
		}
		catch(Exception e){
			logger.error("popular failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addUpdateBusiness")
	public SuccessIDResponse addUpdateBusiness(@RequestBody AddBusinessRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			Business business = new Business();
			if(request.getBusiness().getId() != 0){
			  business = businessService.getBusiness(request.getBusiness().getId());
			  if(request.getCapabilities().length != 0){
				  for(String value : request.getCapabilities() ){
					 
					  if(value != null){
						  Capabilities capability = new Capabilities();
						  if(capability.getId() == 0){
						  capability.setBusinessId(request.getBusiness().getId());
						  capability.setCapabilitiesLocation(value);
						  String[] bits = value.split("/");
						  String lastOne = bits[bits.length-1];
						  capability.setFileName(lastOne);
						  businessService.saveCapability(capability);
						  }
					  }
				  }
				  if(request.getBusiness().getBusinessType()==1){
				  deleteUserAlertMapping(request.getUserId(),3,business.getId());
				  }
			  }else if(request.getBusiness().getBusinessType()==1){
				  addUserAlertMapping(request.getUserId(),3,business.getId());
			  }
			  if(request.getParts().length != 0){
				
				  for(String value : request.getParts() ){
					
					  if(value != null){
						  Parts parts = new Parts();
						  if(parts.getId() == 0){
							  parts.setBusinessId(request.getBusiness().getId());
							  parts.setPartsLocation(value);
						  String[] bits = value.split("/");
						  String lastOne = bits[bits.length-1];
						  parts.setFileName(lastOne);
						  businessService.saveParts(parts);
						 
						  }
					  }
				  }
				  if(request.getBusiness().getBusinessType()==2){
					  deleteUserAlertMapping(request.getUserId(),2,business.getId());
//					  if(request.getParts().length == 0) {
//						  addUserAlertMapping(request.getUserId(),2,business.getId());
//					  }
				  }
			  }else if(request.getBusiness().getBusinessType()==2){
				  addUserAlertMapping(request.getUserId(),2,business.getId());
			  }
			}else{
				business.setActive(1);
				business.setCreatedBy(request.getBusiness().getCreatedBy());
				business.setCreatedId(request.getUserId());
				business.setCreatedDate(new Date());
				business.setExistingBusiness(1);
								
			}

			//business.setActive(0);
			business.setImageUrl(request.getBusiness().getImageUrl());
			business.setAddress(request.getBusiness().getAddress());
			business.setCountry(request.getBusiness().getCountry());
			business.setCity(request.getBusiness().getCity());
			business.setState(request.getBusiness().getState());
			business.setZip(request.getBusiness().getZip());
			business.setName(request.getBusiness().getName());
			business.setCapabilities(request.getBusiness().getCapabilities());
			business.setOverview(request.getBusiness().getOverview());
			business.setCertificateHoldingOffice(request.getBusiness().getCertificateHoldingOffice());
			business.setCertificateNumber(request.getBusiness().getCertificateNumber());
			business.setWebsite(request.getBusiness().getWebsite());
			business.setPhoneNumber(request.getBusiness().getPhoneNumber());
			business.setDesignatorCode(request.getBusiness().getDesignatorCode());
			business.setAdImageUrl(request.getBusiness().getAdImageUrl());
			business.setAdImageWebsite(request.getBusiness().getAdImageWebsite());
			business.setBusinessType(request.getBusiness().getBusinessType());
//			BeanUtils.copyProperties(request.getBusiness(), business);
			businessService.saveBusiness(business);
			if(request.getBusiness().getId() == 0){
				/*for(CategoryList mapping : request.getCategory()){
						BusinessCategoryMapping category = new BusinessCategoryMapping();
						category.setCategoryId(mapping.getId());
						category.setBusinessId(business.getId());
						businessService.saveCategoryMpping(category);
				}*/
				
				
				if(request.getBusiness().getCreatedBy().equalsIgnoreCase("Owner")){
					
					if(request.getCapabilities().length != 0){
						  for(String value : request.getCapabilities() ){
							
							  if(value != null){
								  Capabilities capability = new Capabilities();
								  if(capability.getId() == 0){
								  capability.setBusinessId(business.getId());
								  capability.setCapabilitiesLocation(value);
								  String[] bits = value.split("/");
								  String lastOne = bits[bits.length-1];
								  capability.setFileName(lastOne);
								  businessService.saveCapability(capability);
								  }
							  }
						  } if(request.getBusiness().getBusinessType()==1){
							  deleteUserAlertMapping(request.getUserId(),3,business.getId());
						  }
					  }else if(request.getBusiness().getBusinessType()==1){
						  addUserAlertMapping(request.getUserId(),3,business.getId());
					  }
					  if(request.getParts().length != 0){
						  for(String value : request.getParts() ){
							  
							  if(value != null){
								  Parts parts = new Parts();
								  if(parts.getId() == 0){
									  parts.setBusinessId(business.getId());
									  parts.setPartsLocation(value);
								  String[] bits = value.split("/");
								  String lastOne = bits[bits.length-1];
								  parts.setFileName(lastOne);
								  businessService.saveParts(parts);
								  }
							  }
						  }
						  if(request.getBusiness().getBusinessType()==2){
							  deleteUserAlertMapping(request.getUserId(),2,business.getId());
//							  if(request.getParts().length == 0) {
//								  addUserAlertMapping(request.getUserId(),2,business.getId());
//							  }
						  }
					  }else if(request.getBusiness().getBusinessType()==2){
						  addUserAlertMapping(request.getUserId(),2,business.getId());
					  }
					
						if(request.getBusiness().getCreatedBy().equalsIgnoreCase("Customer")) {
							if(request.getBusiness().getId() == 0) {
								if(request.getMyReviews().getRatingId()!=0.0 || request.getMyReviews().getReview()!=null || request.getMyReviews().getPricing()!=0||request.getMyReviews().getDeliverySpeed()!=0) {
								Reviews myReviews = new Reviews();
								myReviews.setBusinessId(business.getId());
								myReviews.setRatingId(request.getMyReviews().getRatingId());
								myReviews.setReview(request.getMyReviews().getReview());
								myReviews.setUserId(request.getUserId());
								myReviews.setRecommended(request.getMyReviews().isRecommended());
//								myReviews.setApproved(request.getMyReviews().isApproved());
								myReviews.setPricing(request.getMyReviews().getPricing());
								myReviews.setDeliverySpeed(request.getMyReviews().getDeliverySpeed());
								myReviews.setCreatedDate(DateTimeUtil.getTodayString());
								myReviews.setLastModifiedDate(DateTimeUtil.getTodayString());
								double overall = myReviews.getPricing() + myReviews.getDeliverySpeed() + myReviews.getRatingId();
								myReviews.setOverallRate(overall/3);
								
								ratingReviewService.saveReviews(myReviews);
								}
				}
			}
					  if(request.getRoleId() > 3){
						BusinessClaimMapping businessClaimMapping = new BusinessClaimMapping();
						businessClaimMapping.setBusinessId(business.getId());
						//if(request.getBusiness().getCreatedBy().equalsIgnoreCase("Owner"))
							businessClaimMapping.setUserId(request.getUserId());
						//else
					//		businessClaimMapping.setUserId(0);
						businessClaimMapping.setCreatedDate(new Date());
						businessClaimMapping.setApproval(request.getBusiness().getActive());
						businessService.saveClaimMapping(businessClaimMapping);
						
					  }
				}
			}
			else{
				/*List<CategoryList> categoryList = businessService.getCategoryByBusiness(request.getBusiness().getId());
					for(CategoryList newCategory : request.getCategory()){
							BusinessCategoryMapping categoryMapping = businessService.getByCategoryAndBusiness(newCategory.getId(), request.getBusiness().getId());
							if(categoryMapping == null){
								categoryMapping = new BusinessCategoryMapping();
								categoryMapping.setCategoryId(newCategory.getId());
								categoryMapping.setBusinessId(business.getId());
								businessService.saveCategoryMpping(categoryMapping);
							}
						}
	
				for(CategoryList deleteCategory : request.getDeleteCategory()){
							BusinessCategoryMapping categoryMapping =  businessService.getByCategoryAndBusiness(deleteCategory.getId(), request.getBusiness().getId());
							if(categoryMapping != null)
							businessService.deleteCategoryMapping(categoryMapping.getId());
				}*/
				BusinessClaimMapping businessClaimMapping = businessService.getClaimMappingById(request.getBusiness().getId());
				if(businessClaimMapping != null){
				/*	businessClaimMapping.setUserId(request.getUserId());
					businessClaimMapping.setLastUpdatedDate(new Date());
					businessClaimMapping.setApproval(0);
					businessService.saveClaimMapping(businessClaimMapping);*/
				}else{
					if(request.getRoleId() > 3 ){
					BusinessClaimMapping bcm = new BusinessClaimMapping();
					bcm.setBusinessId(business.getId());
					//if(request.getBusiness().getCreatedBy().equalsIgnoreCase("Owner"))
						bcm.setUserId(request.getUserId());
					//else
					//	bcm.setUserId(0);
					bcm.setCreatedDate(new Date());
					bcm.setApproval(request.getBusiness().getActive());
					businessService.saveClaimMapping(bcm);
					}
				}
			}
			response.setId(business.getId());

		}
		catch(Exception e){
			logger.error("failed business",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	private void addUserAlertMapping(int userId,int alertId,int businessId) {
	
		UserAlertsMapping userAlertsMapping = new UserAlertsMapping();
		userAlertsMapping = userAlertsService.getByUserIdAndAlertId(userId,alertId,businessId);
		if(userAlertsMapping == null) {
			userAlertsMapping = new UserAlertsMapping();
			userAlertsMapping.setAlertId(alertId);
			userAlertsMapping.setUserId(userId);
			userAlertsMapping.setBusinessId(businessId);
			userAlertsService.getUserAlertsMappingRepository().save(userAlertsMapping);
		}
		
	}
	private void deleteUserAlertMapping(int userId,int alertId,int businessId) {
		
		UserAlertsMapping userAlertsMapping = new UserAlertsMapping();
		userAlertsMapping = userAlertsService.getByUserIdAndAlertId(userId,alertId,businessId);
		if(userAlertsMapping != null) {
			
			userAlertsService.getUserAlertsMappingRepository().delete(userAlertsMapping);
		}
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/myBusiness/{userId}")
	public MyBusinessResponse myBusiness(@PathVariable int userId){
		MyBusinessResponse response = new MyBusinessResponse();
		try{
			List<MyBusiness> myBusiness = businessService.myBusiness(userId);
			response.setMyBusiness(myBusiness);
			
		}
		catch(Exception e){
			logger.error("failed Mybusiness ",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getFavorite/{userId}")
	public FavoriteResponse getFavorite(@PathVariable int userId){
		FavoriteResponse response = new FavoriteResponse();
		try{
			List<BookmarkDetails> bookmark = businessService.getBookmarkDetails(userId);
			response.setBookmark(bookmark);
			
		}
		catch(Exception e){
			logger.error("failed bookmark ",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getCategory")
	public CategoryResponse getCategory(){
		CategoryResponse response = new CategoryResponse();
		try{
			List<CategoryList> category = businessService.getCategory();
			response.setCategory(category);
			
		}
		catch(Exception e){
			logger.error("failed category ",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/claimBusiness")
	public ClaimBusinessResponse claimBusiness(@RequestBody ClaimBusinessRequest request){
		ClaimBusinessResponse response = new ClaimBusinessResponse();
		try{
			User user = userMgmtService.getUserById(request.getUserId());
			BusinessClaimMapping businessClaim =  new BusinessClaimMapping();
			businessClaim.setBusinessId(request.getBusinessId());
			businessClaim.setUserId(request.getUserId());
			businessClaim.setCreatedDate(new Date());
			businessService.saveClaimMapping(businessClaim);
			response.setClaimBusiness(businessClaim);
			Business business = businessService.getBusiness(request.getBusinessId());
//			UserRoleMapping userRole = userMgmtService.getByRoleId(1);
			 
			Properties properties = new Properties();
			String adminProperties;
			String adminMail= "";
			try{
				adminProperties = SKYRATEUtil.getConfigDIR() + "/adminConfig.properties";
				properties.load(new FileReader(adminProperties));
				adminMail = properties.getProperty("AdminEmail");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			
//			User adminUser = userMgmtService.getUserById(userRole.getUserId());
			properties.load(new FileReader(adminConfigLocation));
			String url = properties.getProperty("WEBSITE_URL");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("business", business.getName() );
			map.put("user", user.getFirstName());
			map.put("url", url);
			map.put("application", properties.getProperty("Application"));
			String subject= properties.getProperty("ClaimBusinessSubject");
			String emailBody = emailTemplateService.getEmailTemplate("claimBusiness.vm",map);
			EmailMessage emailMessage = new EmailMessage();
//			emailMessage.setToEmail(adminUser.getEmail());
			emailMessage.setSubject(subject);
			emailMessage.setEmailBody(emailBody);
			emailService.sendEmail(adminMail, subject, emailBody, null);
			
		}
		catch(Exception e){
			logger.error("Claimed Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/businessNameExists")
	public BusinessExistsResponse businessNameExists(@RequestBody BusinessExistsRequest request){
		BusinessExistsResponse response = new BusinessExistsResponse();
		try{
			Business business = businessService.businessExists(request.getBusiness());
			if(business != null){
				response.setBusinessNameExists(true);
			}
			else{
				response.setBusinessNameExists(false);
			}
			
		}
		catch(Exception e){
			logger.error("Exists Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getClaimed")
	public ClaimedBusinessResponse getBusinessClaimed(@RequestBody TopSearchRequest request){
		ClaimedBusinessResponse response = new ClaimedBusinessResponse();
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
			List<BusinessClaimed> newBusiness = businessService.getNewBusiness(date,0,0);
			List<BusinessClaimed> businessClaimed = businessService.getBusinessClaimed(date,0,0);
			List allBusiness = new ArrayList<BusinessClaimed>();
			if(newBusiness.size()>0){
			  allBusiness.addAll(newBusiness);
			}
			if(businessClaimed.size()>0){
			   allBusiness.addAll(businessClaimed);
			}
		
		  //	BusinessCount businessCount = businessService.getClaimedCount(date);
			BusinessCount bc= new BusinessCount();
			bc.setCount(allBusiness.size());
			response.setBusinessClaimed(allBusiness);
			response.setCount(allBusiness.size());
			
		}
		catch(Exception e){
			logger.error("Claimed Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getClaimedPagination")
	public ClaimedBusinessResponse getBusinessClaimedPagination(@RequestBody TopSearchRequest request){
		ClaimedBusinessResponse response = new ClaimedBusinessResponse();
		try{
			int from=1;
			int to=10;
			for(int i=1;i<=request.getClaimedValue();i++){
				if(i==1){
					from=0;
					to=10;
				}
				else{
					from+=10;
					to+=10;
				}
			}
			String date = DateTimeUtil.getTodayString();
			if(!request.getFeedbackDate().isEmpty() && !request.getFeedbackDate().equals("Today") && !request.getFeedbackDate().equals("Last 30 days"))
			 date = DateTimeUtil.changeStringFormat(request.getFeedbackDate());
			if(request.getFeedbackDate().equals("Last 30 days")){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				Date result = cal.getTime();

				date =DateTimeUtil.changeDatetoString(result);
			}
			List<BusinessClaimed> businessClaimed = businessService.getBusinessClaimed(date,from,to);
			response.setBusinessClaimed(businessClaimed);
			
		}
		catch(Exception e){
			logger.error("Claimed Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateApproval")
	public SuccessIDResponse updateApproval(@RequestBody ApprovalRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			String approved = "Rejected";
			
			User user = userMgmtService.getUserById(request.getUserId());
			Business business = businessService.getBusiness(request.getBusinessId());
			

			
			if(request.getApproval() == 0){
				if(request.getClaimed()==0){
					//business.setActive(1);
					//businessService.saveBusiness(business);	
					
					businessService.deleteBusiness(request.getBusinessId());
				}else{
				
					BusinessClaimMapping businessClaim = businessService.getClaimMappingById(request.getBusinessId());
					RejectedBusiness rejected = new RejectedBusiness();
					rejected.setBusinessId(businessClaim.getBusinessId());
					rejected.setUserId(businessClaim.getUserId());
					rejected.setCreatedDate(new Date());
					businessService.saveReject(rejected);
					if(business.getExistingBusiness() == 0){
						business.setUserId(0);
						businessService.saveBusiness(business);
					}
					else
						businessService.deleteBusiness(request.getBusinessId());	
					businessService.deleteClaimed(businessClaim.getId());
				}
			}
			else{
				if(request.getClaimed()==0){
					business.setActive(0);
					businessService.saveBusiness(business);	
					approved = "Approved";
				}else{
				
					BusinessClaimMapping businessClaim = businessService.getClaimMappingById(request.getBusinessId());
					businessClaim.setApproval(1);
					business.setEmail(user.getEmail());
					businessClaim.setLastUpdatedDate(new Date());
					//businessClaim.setClaimed(1);
					businessService.saveClaimMapping(businessClaim);
					approved="Approved";
					business.setActive(0);
					
					business.setUserId(businessClaim.getUserId());
					businessService.saveBusiness(business);
				}
			}
			String subject="Aviation rating";
//			User user = userMgmtService.getUserById(businessClaim.getUserId());
			properties.load(new FileReader(adminConfigLocation));
			String url = properties.getProperty("WEBSITE_URL");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("firstName", user.getFirstName());
			map.put("lastName", user.getLastName());
			map.put("business", business.getName() );
			map.put("approved", approved);
			map.put("url", url);
			map.put("application", properties.getProperty("Application"));
			if(request.getClaimed()==0){
			   subject= properties.getProperty("NewBusinessSubject"); 
			}else{
				 subject= properties.getProperty("BusinessClaimSubject");
			}
			String emailBody = emailTemplateService.getEmailTemplate("approval.vm",map);
			EmailMessage emailMessage = new EmailMessage();
			emailMessage.setToEmail(user.getEmail());
			emailMessage.setSubject(subject);
			emailMessage.setEmailBody(emailBody);
			emailService.sendEmail(user.getEmail(), subject, emailBody, null);	
			logger.info("get approved");
		}
		catch(Exception e){
			logger.error("APproval Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/setBusinessActive")
	public SuccessIDResponse setBusinessActive(@RequestBody ActiveRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			Business business = businessService.getBusiness(request.getBusinessId());
			business.setActive(request.getActive());
			businessService.saveBusiness(business);
			
		}
		catch(Exception e){
			logger.error("Active Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAd")
	public AdListResponse getAd(){
		AdListResponse response = new AdListResponse();
		try{
			List<AdDetails> adDetails = businessService.getAd();
			response.setAdDetails(adDetails);
			
		}
		catch(Exception e){
			logger.error("ad Failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addBusinessReply")
	public SuccessIDResponse addBusinessReply(@RequestBody BusinessReplyRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			BusinessUserReply reply = new BusinessUserReply();
			reply.setBusinessId(request.getBusinessId());
			reply.setReviewId(request.getReviewId());
			reply.setReply(request.getReply());
			reply.setCreatedDate(DateTimeUtil.getTodayString());
			reply.setLastModifiedDate(DateTimeUtil.getTodayString());
			businessService.saveReply(reply);
			
		}
		catch(Exception e){
			logger.error("error in reply",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/contactUs")
	public SuccessIDResponse contactUs(@RequestBody ContactRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			
			FeedbackForm feedback = new FeedbackForm();
			feedback.setEmail(request.getEmail());
			feedback.setMessage(request.getMessage());
			feedback.setName(request.getUserName());
			feedback.setPhoneNumber(request.getPhoneNumber());
			ContactSubject contactSubject = reportService.getBySubjectId(request.getSubjectId());
			feedback.setSubject(contactSubject.getSubject());
			feedback.setFeedbackDate(new Date());
			businessService.saveFeedback(feedback);
    		properties.load(new FileReader(adminConfigLocation));
    		String user = properties.getProperty("FeedbackInboxEmail");
    		properties.load(new FileReader(adminConfigLocation));
			String url = properties.getProperty("WEBSITE_URL");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("email", user);
			map.put("name", request.getUserName());
			map.put("message", request.getMessage());
			map.put("url", url);
			map.put("application", properties.getProperty("Application"));
			if(request.getPhoneNumber() != null){
				String content = "Phone Number: "+request.getPhoneNumber();
			map.put("phone",content);
			}
			String subject= contactSubject.getSubject();
			String type=contactSubject.getSubject();
			String emailBody = emailTemplateService.getEmailTemplate("contactForm.vm",map);
			EmailMessage emailMessage = new EmailMessage();
			emailMessage.setToEmail(user);
			emailMessage.setSubject(subject);
			emailMessage.setEmailBody(emailBody);
			emailService.sendEmail(user, subject, emailBody, null);
			
		}
		catch(Exception e){
			logger.error("mail failed ",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getCapability/{id}")
	public CapabilityResponse getCapability(@PathVariable int id){
		CapabilityResponse response = new CapabilityResponse();
		try{
			List<Capabilities> capabilities = businessService.getCapability(id);
			List<Parts> parts = businessService.getParts(id);
			response.setCapabilities(capabilities);
			response.setParts(parts);
			
		}
		catch(Exception e){
			logger.error("capability failed ",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	
	@PostMapping(path="/deleteCapabilities")
	public SuccessIDResponse deleteCapability(@RequestBody DeleteCapabilityRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			Integer delete = businessService.deleteCapabilitiesById(request.getId());
			List<Capabilities> capabilities = new ArrayList<Capabilities>();
			capabilities =  businessService.getCapability(request.getBusinessId());
		
			if(capabilities == null || capabilities.isEmpty()) {
				capabilities = new ArrayList<Capabilities>();
		
			addUserAlertMapping(request.getUserId(),3,request.getBusinessId());
			}
			if(delete == null || delete ==1)
				 response.setSuccess(true);
					}
		catch(Exception e){
			logger.error("falied deleted",e);
			response.setSuccess(true);
		}
		return response;
	}
	
	@PostMapping(path="/deleteParts")
	public SuccessIDResponse deleteParts(@RequestBody DeleteCapabilityRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
		
			Integer delete = businessService.deletePartsById(request.getId());
			List<Parts> parts = new ArrayList<Parts>();
			parts =  businessService.getParts(request.getBusinessId());
		
			if(parts == null || parts.isEmpty()) {
				parts = new ArrayList<Parts>();
			
			addUserAlertMapping(request.getUserId(),2,request.getBusinessId());
			}
		if(delete == null || delete ==1)
				 response.setSuccess(true);
			
		}
		catch(Exception e){
			logger.error("falied deleted",e);
			response.setSuccess(true);
		}
		return response;
	}
}
