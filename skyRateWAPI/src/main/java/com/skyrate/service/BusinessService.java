package com.skyrate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.clib.util.DateTimeUtil;
import com.skyrate.dao.BusinessCategoryMappingRepository;
import com.skyrate.dao.BusinessClaimMappingRepository;
import com.skyrate.dao.BusinessQuery;
import com.skyrate.dao.BusinessRepository;
import com.skyrate.dao.BusinessUserReplyRepository;
import com.skyrate.dao.CapabilityRepository;
import com.skyrate.dao.CategoryRepository;
import com.skyrate.dao.ContactRepository;
import com.skyrate.dao.RejectedBusinessRepository;
import com.skyrate.model.business.AdDetails;
import com.skyrate.model.business.BookmarkDetails;
import com.skyrate.model.business.BusinessCategory;
import com.skyrate.model.business.BusinessClaimed;
import com.skyrate.model.business.BusinessCount;
import com.skyrate.model.business.BusinessDetails;
import com.skyrate.model.business.BusinessSummaryDetails;
import com.skyrate.model.business.CategoryList;
import com.skyrate.model.business.MyBusiness;
import com.skyrate.model.business.PartsRepository;
import com.skyrate.model.dbentity.Business;
import com.skyrate.model.dbentity.BusinessCategoryMapping;
import com.skyrate.model.dbentity.BusinessClaimMapping;
import com.skyrate.model.dbentity.BusinessUserReply;
import com.skyrate.model.dbentity.Capabilities;
import com.skyrate.model.dbentity.FeedbackForm;
import com.skyrate.model.dbentity.Parts;
import com.skyrate.model.dbentity.RejectedBusiness;
import com.skyrate.model.message.MessageEmailContent;

@Service
@Transactional
public class BusinessService {
	
	@Autowired
	BusinessQuery businessQuery;
	
	@Autowired
	BusinessRepository businessRepository;
	
	@Autowired
	BusinessClaimMappingRepository businessClaimMappingRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BusinessCategoryMappingRepository businessCategoryMappingRepository;
	
	@Autowired
	ContactRepository feedbackRepository;
	
	
	@Autowired
	RejectedBusinessRepository rejectedBusinessRepository;
	
	@Autowired
	BusinessUserReplyRepository businessUserReplyRepository;
	
	@Autowired
	CapabilityRepository capabilityRepository;
	
	@Autowired
	PartsRepository partsRepository;
	
	public List<BusinessDetails> searchBusiness(String country,int category,String name, int roleId, int createdId){
		return this.businessQuery.searchBusiness(country,category,name,roleId,createdId);
	}
	public List<BusinessDetails> searchForAll(String country, String name, int roleId){
		return this.businessQuery.searchBusinessForAll(country, name, roleId);
	}
	public List<BusinessDetails> searchPageBusiness(String category,String name, int roleId){
		return this.businessQuery.searchPageBusiness(category,name,roleId);
	}
	public BusinessDetails getBusinessById(int id){
		return this.businessQuery.getBusinessById(id);
	}
	public Business getBusiness(int id){
		return this.businessRepository.findById(id);
	}
	public List<BusinessCategory> getBusinessCategory(int businessId){
		return this.businessQuery.getBusinessCategory(businessId);
	}
	public Business saveBusiness(Business business){
		return this.businessRepository.save(business);
	}
	public BusinessCount businessCount(String country, int category, String search, int roleId){
		return this.businessQuery.getBusinessCount(country, category, search, roleId);
	}
	public List<BusinessDetails> getBusinessByPagination(int from, int to,int category,String search,int roleId){
		return this.businessQuery.getBusinessPagination(from, to,category, search,roleId);
	}
	public BusinessSummaryDetails businessSummary(int businessId){
		return this.businessQuery.getBusinessSummary(businessId);
	}
	public List<BookmarkDetails> getBookmarkDetails(int userId){
		return this.businessQuery.getBookmarkDetails(userId);
	}
	public List<MyBusiness> myBusiness(int userId){
		return this.businessQuery.myBusiness(userId);
	}
	public List<CategoryList> getCategory(){
		return this.businessQuery.getCategoryList();
	}
	public List<CategoryList> getCategoryByBusiness(int id){
		return this.businessQuery.getCategoryByBusiness(id);
	}
	public BusinessCategoryMapping saveCategoryMpping(BusinessCategoryMapping categoryMapping){
		return this.businessCategoryMappingRepository.save(categoryMapping);
	}
	public BusinessCategoryMapping getByCategoryAndBusiness(int categoryId, int businessId){
		return this.businessCategoryMappingRepository.findByCategoryIdAndBusinessId(categoryId, businessId);
	}
	public void deleteCategoryMapping(int id){
		this.businessQuery.deleteMapping(id);
	}
	
	public BusinessClaimMapping saveClaimMapping(BusinessClaimMapping businessClaim){
		return this.businessClaimMappingRepository.save(businessClaim);
	}
	public BusinessClaimMapping getClaimMappingById(int id){
		return this.businessClaimMappingRepository.findByBusinessId(id);
	}
	public Business businessExists(String name){
		return this.businessRepository.findByName(name);
	}
	public List<BusinessClaimed> getBusinessClaimed(String date, int from, int to){
		return this.businessQuery.getBusinessClaimed(date, from, to);
	}
	public List<BusinessClaimed> getNewBusiness(String date, int from, int to){
		return this.businessQuery.getNewBusiness(date, from, to);
	}
	public BusinessCount getClaimedCount(String date){
		return this.businessQuery.getBusinessClaimedCount(date);
	}
	public RejectedBusiness saveReject(RejectedBusiness reject){
		return this.rejectedBusinessRepository.save(reject);
	}
	public void deleteClaimed(int id){
		System.out.println("rejected id "+id);
		 this.businessQuery.deleteClaimed(id);
	}
	public List<AdDetails> getAd(){
		return this.businessQuery.adDetails();
	}
	public BusinessUserReply saveReply(BusinessUserReply userReply){
		return this.businessUserReplyRepository.save(userReply);
	}
	public Capabilities saveCapability(Capabilities capabilities){
		return this.capabilityRepository.save(capabilities);
	}
	public List<Capabilities> getCapability(int businessId){
		return this.businessQuery.getCapability(businessId);
	}
	public Capabilities findById(int id){
		return this.capabilityRepository.findById(id);
	}
	public Integer deleteCapabilitiesById(int id){
		return this.capabilityRepository.deleteById(id);
	}
	public Parts saveParts(Parts parts){
		return this.partsRepository.save(parts);
	}
	public List<Parts> getParts(int businessId){
		return this.businessQuery.getParts(businessId);
	}
	public Parts findPartsById(int id){
		return this.partsRepository.findById(id);
	}
	public Integer deletePartsById(int id){
		return this.partsRepository.deleteById(id);
	}
	public FeedbackForm saveFeedback(FeedbackForm feedback){
		return this.feedbackRepository.save(feedback);
	}
	public List<MessageEmailContent> getEmailContent(){
		String date = DateTimeUtil.getTodayString();
		return this.businessQuery.getBusinessReviewEmail(date);
	}
	public void deleteBusiness(int businessId){
		this.businessRepository.deleteById(businessId);
	}
}
