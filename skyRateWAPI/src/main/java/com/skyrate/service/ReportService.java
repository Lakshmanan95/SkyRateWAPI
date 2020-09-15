package com.skyrate.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrate.dao.ContactSubjectRepository;
import com.skyrate.dao.EventLogRepository;
import com.skyrate.dao.ReportQuery;
import com.skyrate.model.dbentity.ContactSubject;
import com.skyrate.model.dbentity.EventLog;
import com.skyrate.model.report.PopularCategory;
import com.skyrate.model.report.PopularSearch;
import com.skyrate.model.report.ReportPerBusiness;
import com.skyrate.model.report.TotalHit;
import com.skyrate.model.report.UserReport;
import com.skyrate.model.report.VendorDetails;
import com.skyrate.model.usermgmt.UserRating;

@Service
@Transactional
public class ReportService {

	@Autowired
	EventLogRepository eventLogRepository;
	
	@Autowired
	ReportQuery reportQuery;
	
	@Autowired
	ContactSubjectRepository contactSubjectRepository;
	
	public EventLog saveEvent(EventLog eventLog){
		return this.eventLogRepository.save(eventLog);
	}
	
	public EventLog getByUniqueId(String uniqueId){
		return this.eventLogRepository.findByUniqueId(uniqueId);
	}
	
	public EventLog getByEventAndBusinessId(String event, int businessId){
		return this.eventLogRepository.findByEventAndBusinessId(event, businessId);
	}
	public TotalHit getTotalHit(String date){
		return this.reportQuery.getTotalHit(date);
	}
	public List<UserReport> getUserReport(String date){
		return this.reportQuery.getUserReport(date);
	}
	public List<VendorDetails> getVendorByEvent(String value, String date){
		return this.reportQuery.getVendorByDate(value, date);
	}
	public List<PopularSearch> getTrendingBusiness(){
		return this.reportQuery.getTrendingBusiness();
	}
	public List<PopularSearch> getPopularSearch(String category){
		return this.reportQuery.getPopularSearch(category);
	}
	public List<UserRating> getUserRating(String date){
		return this.reportQuery.getUserRating(date);
	}
	public List<ReportPerBusiness> getReportPerBusienss(int userId){
		return this.reportQuery.getReportPerBusienss(userId);
	}
	public List<PopularCategory> getCategory(){
		return this.reportQuery.getCategory();
	}
	public List<PopularSearch> getMostTrendingByValue(String value){
		return this.reportQuery.getMostTrendingByValue(value);
	}
	public List<ContactSubject> getSubjects(){
		return this.contactSubjectRepository.findAll();
	}
	public ContactSubject getBySubjectId(int id){
		return this.contactSubjectRepository.findById(id);
	}
	public List<ContactSubject> getBySubjectType(int type){
		return this.contactSubjectRepository.findByType(type);
	}
}
