package com.skyrate.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.model.report.PopularCategory;
import com.skyrate.model.report.PopularSearch;
import com.skyrate.model.report.ReportPerBusiness;
import com.skyrate.model.report.TotalHit;
import com.skyrate.model.report.UserReport;
import com.skyrate.model.report.VendorDetails;
import com.skyrate.model.usermgmt.UserRating;

@Service
@Transactional
public class ReportQuery {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<VendorDetails> getVendorByDate(String value,String date){
		String query = "SELECT @n := @n + 1 id,event_log.event,event_log.LAST_UPDATED_DATE, event_log.BUSINESS_ID, SUM(event_log.TOTAL_HITS) as total_hits,business.name,"
						+ " CASE WHEN business_rating_summary.RATING_AVERAGE is NULL THEN 0 ELSE business_rating_summary.RATING_AVERAGE END AS RATING_AVERAGE, "
						+ " CASE WHEN business_rating_summary.REVIEW_COUNT is NULL THEN 0 ELSE business_rating_summary.REVIEW_COUNT END AS REVIEW_COUNT, "
						+ " CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING "
						+ " FROM business inner join event_log on event_log.BUSINESS_ID = business.id "
						+ " left join business_rating_summary on business_rating_summary.BUSINESS_ID = business.id, (SELECT @n := 0) m "
						+ " where event_log.event ='"+value+"' and event_log.LAST_UPDATED_DATE >= '"+date+"'"
						+ " GROUP BY event, business_id ORDER BY total_hits desc limit 10";
		List<VendorDetails> vendor = jdbcTemplate.query(query,  new BeanPropertyRowMapper(VendorDetails.class));
		return vendor;
	}
	public TotalHit getTotalHit(String date){
		String query=" select sum(total_hits)as count,last_updated_date from event_log";
		if(date != null){
			query+=" where last_updated_date >='"+date+"'";
		}
		TotalHit total = (TotalHit) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(TotalHit.class));
		return total;
	}
	
	public List<PopularSearch> getPopularSearch(String category){
		String query = "SELECT event_log.BUSINESS_ID, SUM(event_log.TOTAL_HITS) as total_hits,business.*,"
				+ " CASE WHEN business_rating_summary.REVIEW_COUNT is NULL THEN 0 ELSE business_rating_summary.REVIEW_COUNT END AS REVIEW_COUNT,"
				+ " CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING "
							+" FROM business_category_mapping " 
							+" inner join event_log on event_log.BUSINESS_ID = business_category_mapping.BUSINESS_ID "
							+" inner join business on business.id = business_category_mapping.BUSINESS_ID "
							+" inner join category on category.id = business_category_mapping.CATEGORY_ID"
							+" left join business_rating_summary on business_rating_summary.BUSINESS_ID = business_category_mapping.BUSINESS_ID where ";
				
					query+= " category.category = '"+category+"' and  business.ACTIVE = 0 and ";
					query+= " event_log.event='Search' GROUP BY event, event_log.business_id ORDER BY ";
					query+=" TOTAL_HITS  desc limit 10";
					
		List<PopularSearch> search = jdbcTemplate.query(query,  new BeanPropertyRowMapper(PopularSearch.class));
		return search;
	}
	public List<PopularSearch> getTrendingBusiness(){
		String query = "select business.*,  business_rating_summary.IS_RECOMMEND,business_rating_summary.NOT_RECOMMEND,"
						+ " CASE WHEN business_rating_summary.REVIEW_COUNT is NULL THEN 0 ELSE business_rating_summary.REVIEW_COUNT END AS REVIEW_COUNT,"
						+ " CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING from business inner join " 
						+ " business_rating_summary on business_rating_summary.BUSINESS_ID = business.id "
						+ " where business_rating_summary.OVERALL_RATING != 0 and business.ACTIVE = 0 ORDER BY OVERALL_RATING  desc limit 6";
				
		List<PopularSearch> search = jdbcTemplate.query(query,  new BeanPropertyRowMapper(PopularSearch.class));
		return search;
	}
	
	public List<UserRating> getUserRating(String date){
		
		String query = "select sum(RATING_ID) as rating,USER_ID,business.id,business.name,user.FIRST_NAME from reviews "
						+ " inner join business on business.id = reviews.BUSINESS_ID "
						+ " inner join user on user.id = reviews.USER_ID  "
						+ " where reviews.LAST_MODIFIED_DATE >= '"+date+"'"
						+ " group by reviews.BUSINESS_ID ORDER BY rating desc limit 10";
		List<UserRating> rating = jdbcTemplate.query(query,  new BeanPropertyRowMapper(UserRating.class));
		return rating;
	}
	
	public List<UserReport> getUserReport(String date){
		String query = "select sum(overall_rate) as overall,user.first_name, user.id as user_id from reviews "
						+" inner join user on user.id = reviews.USER_ID  "
						+ " where reviews.LAST_MODIFIED_DATE >= '"+date+"' group by reviews.USER_ID order by overall desc limit 10 ";
		
		List<UserReport> userReport = jdbcTemplate.query(query,  new BeanPropertyRowMapper(UserReport.class));
		return userReport;		
	}
	
	public List<ReportPerBusiness> getReportPerBusienss(int userId){
		String query = "select business.id,business.name as business, reviews.review, reviews.overall_rate,user.first_name,user.last_name,user.email,user.phone_number, user.id as user_id, reviews.created_date as date from reviews"
						+" inner join business on business.id = reviews.business_id "
						+" inner join user on user.id = reviews.user_id where reviews.user_id="+userId+" order by date desc";
		List<ReportPerBusiness> report = jdbcTemplate.query(query,  new BeanPropertyRowMapper(ReportPerBusiness.class));
		return report;		
					
	}
	public List<PopularCategory> getCategory(){
		String query = "select count(*) as count,category.category,category.id,category.image_url from event_log inner join business_category_mapping on business_category_mapping.BUSINESS_ID = event_log.BUSINESS_ID "
						+ " inner join business on business.id = event_log.BUSINESS_ID inner join category on category.ID = business_category_mapping.CATEGORY_ID "
						+ " group by category.CATEGORY HAVING COUNT(category.CATEGORY) > 1 order by count desc limit 3";
		List<PopularCategory> category = jdbcTemplate.query(query,  new BeanPropertyRowMapper(PopularCategory.class));
		return category;	
	}
	
	public List<PopularSearch> getMostTrendingByValue(String value){
		String query = "SELECT event_log.BUSINESS_ID, SUM(event_log.TOTAL_HITS) as total_hits,business.*,"
						+"CASE WHEN business_rating_summary.REVIEW_COUNT is NULL THEN 0 ELSE business_rating_summary.REVIEW_COUNT END AS REVIEW_COUNT,"
						+"CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING, " 
						+" CASE WHEN business_rating_summary.IS_RECOMMEND is NULL THEN 0 ELSE business_rating_summary.IS_RECOMMEND END AS IS_RECOMMEND "
						+" FROM business  inner join event_log on event_log.BUSINESS_ID = business.ID "
						+"left join business_rating_summary on business_rating_summary.BUSINESS_ID = business.ID where "
						+"business.ACTIVE = 0 GROUP BY event_log.business_id ORDER BY "+value
						+" desc limit 3";
		List<PopularSearch> mostViewed = jdbcTemplate.query(query,  new BeanPropertyRowMapper(PopularSearch.class));
		return mostViewed;
	}
	
	
}
