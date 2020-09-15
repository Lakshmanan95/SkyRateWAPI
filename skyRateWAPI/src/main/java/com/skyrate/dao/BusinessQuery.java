package com.skyrate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.model.business.AdDetails;
import com.skyrate.model.business.BookmarkDetails;
import com.skyrate.model.business.BusinessCategory;
import com.skyrate.model.business.BusinessClaimed;
import com.skyrate.model.business.BusinessCount;
import com.skyrate.model.business.BusinessDetails;
import com.skyrate.model.business.BusinessSummaryDetails;
import com.skyrate.model.business.CategoryList;
import com.skyrate.model.business.MyBusiness;
import com.skyrate.model.dbentity.Capabilities;
import com.skyrate.model.dbentity.Parts;
import com.skyrate.model.message.MessageEmailContent;

@Service
@Transactional
public class BusinessQuery {

	@Autowired
	JdbcTemplate jdbcTemplate;
	public BusinessDetails getBusinessById(int id){
		String query = "select business.* from business "
				+"  where business.id="+id;
		
		BusinessDetails  business = (BusinessDetails) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(BusinessDetails.class));
		return business;
	}
	
	public List<BusinessDetails> searchBusiness(String country,int category,String name, int roleId, int createdId){
		System.out.println("category "+category+" name "+name+" ");
		String query = "select business.*, "
						+ " CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING  from business "
//						+" inner join business on business.id = business_category_mapping.BUSINESS_ID "
//						+" inner join category on category.id = business_category_mapping.CATEGORY_ID "
						+" left join business_rating_summary  on business_rating_summary.BUSINESS_ID = business.ID "
						+" where ";
	/*	if(country != null && !country.isEmpty())
			query+=" business.country = '"+country+"' and";*/
		if(category != 0)
			query+=" business.business_type ="+category+" and";
//		and  category.category like '%"+category+"%'
		query+="  (business.name like '"+name+"%' or  business.search_key like '"+name+"%')";
		if(roleId != 2 && roleId != 1){
			query+="and (business.ACTIVE = 0 or business.CREATED_BY='Customer' and business.CREATED_ID="+createdId+")";
		}
				if(!name.isEmpty())	{	
					query+="  order by business.name ASC limit 10";
					System.out.println("name "+name);
				}
				else
					query+="limit 10";
				System.out.println("query "+query);
		List<BusinessDetails> business = jdbcTemplate.query(query, new BeanPropertyRowMapper(BusinessDetails.class));
		return business;
	}
	
	public List<BusinessDetails> searchBusinessForAll(String country, String name,int roleId){
		String query ="select business.*, "
					+ " CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING  from business "
					+" left join business_rating_summary  on business_rating_summary.BUSINESS_ID = business.ID "
					+" where (business.name like '"+name+"%' or business.search_key like '"+name+"%') and business.international = 0";
		if(!country.isEmpty() && country != null)
			query+=" and business.country ='"+country+"'";
		if(roleId != 2 && roleId != 1){
			query+=" and business.ACTIVE = 0 ";
		}
		query+=" limit 10";
		List<BusinessDetails> business = jdbcTemplate.query(query, new BeanPropertyRowMapper(BusinessDetails.class));
		return business;
	}
	
	public List<BusinessDetails> searchPageBusiness(String category,String name, int roleId){
		
		String query = "select business.*, "+
				"CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING from business "+
						"left join business_rating_summary  on business_rating_summary.BUSINESS_ID = business.ID  where business.businessType like '%"+category+"%' and ( business.name like'"+name+"%' or  business.search_key like '"+name+"%') ";
		if(roleId != 2 && roleId != 1)
			query+=" and business.active=0 ";
		query+="order by business.name ASC";
		List<BusinessDetails> business = jdbcTemplate.query(query, new BeanPropertyRowMapper(BusinessDetails.class));
		return business;
	}
	
	public List<BusinessDetails> getBusinessPagination(int from,int to,int category, String search, int roleId){
		String  query = null;
		System.out.println("category "+category);
			 query = "select business.*, "
					+ " CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING  from business "
//					+" inner join business on business.id = business_category_mapping.BUSINESS_ID "
//					+" inner join category on category.id = business_category_mapping.CATEGORY_ID "
					+" left join business_rating_summary  on business_rating_summary.BUSINESS_ID = business.ID "
					+" where (business.name like '"+search+"%' or  business.search_key like '"+search+"%') ";
			if(category != 0)
				query+=" and business.business_type="+category;
		if(roleId != 2 && roleId != 1){
			query+=" and business.ACTIVE = 0 ";
		}
			query+=" order by business.name ASC limit "+from+" , "+to;
		
//		else{
//			System.out.println("category else "+category+" roleId "+roleId);
//			query ="select business.*, "
//					+ " CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING  from business "
//					+" left join business_rating_summary  on business_rating_summary.BUSINESS_ID = business.ID "
//					+" where (business.name like '"+search+"%' or  business.search_key like '"+search+"%')";
//		if(roleId != 2 && roleId != 1){
//			query+=" and business.ACTIVE = 0 ";
//		}
//		query+="  limit "+from+" , "+to;
//		}
//		
		List<BusinessDetails> business = jdbcTemplate.query(query, new BeanPropertyRowMapper(BusinessDetails.class));
		return business;
	}
	
	public BusinessSummaryDetails getBusinessSummary(int businessId){
		String query = "select business_rating_summary.*,bookmark.is_marked from business_rating_summary "
						+" left join boomark on bookmark.user_id = business_rating_summary.user_id where business_rating_summary.business_id="+businessId;
		BusinessSummaryDetails summary = (BusinessSummaryDetails) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(BusinessSummaryDetails.class));
		return summary;
	}
//	public BusinessDetails getBusinessDetailsById(int businessId){
//		String query = "select business_rating_summary.review_count,business_rating_summary.rating_count"
//	}
	public  List<MyBusiness> myBusiness(int userId){
		
		String query="select b.id,b.name from business b "
					 +"inner join business_claim_mapping bcm "
					 +"on b.id=bcm.business_id "
					 +"and bcm.user_id="+userId +" and bcm.approval=1";
		List<MyBusiness> myBusiness = jdbcTemplate.query(query, new BeanPropertyRowMapper(MyBusiness.class));
		return myBusiness;
	}
	public  List<BookmarkDetails> getBookmarkDetails(int userId){
		String query = "select business.*, bookmark.*,bookmark.id as bookmark_Id, "
				+"CASE WHEN business_rating_summary.RATING_AVERAGE is NULL THEN 0 ELSE business_rating_summary.RATING_AVERAGE END AS RATING_AVERAGE, " 
						+"CASE WHEN business_rating_summary.PRICING_AVERAGE is NULL THEN 0 ELSE business_rating_summary.PRICING_AVERAGE END AS PRICING_AVERAGE, "
						+"CASE WHEN business_rating_summary.OVERALL_RATING is NULL THEN 0 ELSE business_rating_summary.OVERALL_RATING END AS OVERALL_RATING, "
                        +"CASE WHEN business_rating_summary.REVIEW_COUNT is NULL THEN 0 ELSE business_rating_summary.REVIEW_COUNT END AS REVIEW_COUNT,"
                        +"CASE WHEN business_rating_summary.IS_RECOMMEND is NULL THEN 0 ELSE business_rating_summary.IS_RECOMMEND END AS IS_RECOMMEND, "
                        +"CASE WHEN business_rating_summary.NOT_RECOMMEND is NULL THEN 0 ELSE business_rating_summary.NOT_RECOMMEND END AS NOT_RECOMMEND,"
						+"CASE WHEN business_rating_summary.DELIVERY_SPEED_AVG is NULL THEN 0 ELSE business_rating_summary.DELIVERY_SPEED_AVG END AS DELIVERY_SPEED_AVG from bookmark "
						+" inner join business on business.id = bookmark.business_id "
                        +" left join business_rating_summary on business_rating_summary.business_id = bookmark.business_id "
						+" where bookmark.user_id="+userId +" and business.active != 1";
		List<BookmarkDetails> bookmark = jdbcTemplate.query(query, new BeanPropertyRowMapper(BookmarkDetails.class));
		return bookmark;
	}
	
	
	public BusinessCount getBusinessCount(String country, int category, String search, int roleId){
		String query = null;
		System.out.println("count i query "+country);
			query = "select count(*) as count from business "
					+" left join business_rating_summary  on business_rating_summary.business_id = business.ID "
					+" where (business.name like '%"+search+"%' or  business.search_key like '%"+search+"%')";
		if(category != 0)
			query+=" and business.business_type="+category;
		/*if(!country.isEmpty() && country != null){
			query+=" and business.country='"+country+"'";
			System.out.println("count i query "+country);
		}*/
		if(roleId != 2 && roleId != 1){
			query+=" and business.ACTIVE = 0 ";
		}
		BusinessCount businessCount = (BusinessCount) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(BusinessCount.class));
		return businessCount;
	}
	
	public List<BusinessCategory> getBusinessCategory(int businessId){
		String query = "select business_category_mapping.*,category.DESCRIPTION from business_category_mapping "
						+ " inner join business on business.id = business_category_mapping.business_id "
						+ " inner join category on category.id = business_category_mapping.category_id "
						+ " where business_category_mapping.business_id = "+businessId;
		List<BusinessCategory> businessCategory = jdbcTemplate.query(query, new BeanPropertyRowMapper(BusinessCategory.class));
		return businessCategory;
	}
	
	public List<CategoryList> getCategoryList(){
		String query = "select category.id,category.category as item_name from category";
		List<CategoryList> businessCategory = jdbcTemplate.query(query, new BeanPropertyRowMapper(CategoryList.class));
		return businessCategory;
	} 
	
	public List<CategoryList> getCategoryByBusiness(int businessId){
		String query = "select category.id,category.category as item_name from category inner join business_category_mapping on "
						+" business_category_mapping.CATEGORY_ID = category.ID where business_category_mapping.BUSINESS_ID = "+businessId;
		List<CategoryList> businessCategory = jdbcTemplate.query(query, new BeanPropertyRowMapper(CategoryList.class));
		return businessCategory;
	}
	
	public void deleteMapping(int id){
		String query = " delete from business_category_mapping where id ="+id;
		jdbcTemplate.execute(query);
	}
	
	public List<BusinessClaimed> getBusinessClaimed(String date, int from, int to){
		String query = "select business.id as business_id,business.name,business.active,user.FIRST_NAME,user.email,user.phone_number,1 as claimed, business_claim_mapping.* from business_claim_mapping "
					+ " left join business on business.id = business_claim_mapping.BUSINESS_ID "
					+ " left join user on user.id = business_claim_mapping.USER_ID "
					+ " where business_claim_mapping.created_date >= '"+date+"'  and (business_claim_mapping.APPROVAL != 1 ) order by id desc ";
		if(from != 0){
			query+=" limit "+from+","+to;
		}
		else
			query+="limit 10";
		System.out.println("Query1:@@@"+query);
		List<BusinessClaimed> businessClaimed = jdbcTemplate.query(query, new BeanPropertyRowMapper(BusinessClaimed.class));
		return businessClaimed;
	}
	public List<BusinessClaimed> getNewBusiness(String date, int from, int to){
		String query = "select business.id as business_id,business.name,business.active,user.FIRST_NAME,user.email,user.phone_number,business.created_id as user_id,0 as claimed from business "
					+ " inner join user on user.id = business.created_id "
					+ " left join business_claim_mapping on business_claim_mapping.business_id=business.id "
					+ " where business.active=1 and business.existing_business = 1 and business_claim_mapping.business_id is null and business.created_date >= '"+date+"' ";
		if(from != 0){
			query+=" limit "+from+","+to;
		}
		else
			query+="limit 10";
		List<BusinessClaimed> businessClaimed = jdbcTemplate.query(query, new BeanPropertyRowMapper(BusinessClaimed.class));
		System.out.println("Query2:@@@"+query);
		return businessClaimed;
	}	
	public BusinessCount getBusinessClaimedCount(String date){
		String query = "select count(*) as count from business_claim_mapping "
						+ " inner join business on business.id = business_claim_mapping.BUSINESS_ID "
						+" inner join user on user.id = business_claim_mapping.USER_ID "
						+" where business_claim_mapping.created_date >= '"+date+"'  and business_claim_mapping.APPROVAL != 1 order by business_claim_mapping.created_date desc";
		
		BusinessCount businessCount = (BusinessCount) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(BusinessCount.class));
		return businessCount;
	}
	
	public void deleteClaimed(int id){
		String query = "delete from business_claim_mapping where id ="+id;
		jdbcTemplate.update(query);
	}
	
	public List<AdDetails> adDetails(){
		String query = "select id as businessId, ad_image_url as ad_Url,AD_IMAGE_WEBSITE as website,name as business_name from business where AD_IMAGE_URL is Not null";
		List<AdDetails> details = jdbcTemplate.query(query, new BeanPropertyRowMapper(AdDetails.class));
		return details;
	}
	
	public List<Capabilities> getCapability(int businessId){
		String query = "select * from capabilities where business_id="+businessId;
		List<Capabilities> capabilities = jdbcTemplate.query(query, new BeanPropertyRowMapper(Capabilities.class));
		return capabilities;
	}
	
	public List<Parts> getParts(int businessId){
		String query = "select * from parts where business_id="+businessId;
		List<Parts> parts = jdbcTemplate.query(query, new BeanPropertyRowMapper(Parts.class));
		return parts;
	}
	
	public List<MessageEmailContent> getBusinessReviewEmail(String date){
		String query = "select user.EMAIL,user.FIRST_NAME,M5.read_count from business "
						+"inner join user on user.ID = business.USER_ID "
						+"inner join reviews on reviews.BUSINESS_ID = business.ID "
						+"left join (select count(id) as read_Count, business_id "
						+"from reviews  where CREATED_DATE >= '"+date+"' group by business_id) M5 "
                        +"on M5.business_id = business.ID where reviews.CREATED_DATE >= '"+date+"' group by user.email";
		System.out.println("Review Email Query:-"+query);
		List<MessageEmailContent> messageContent = jdbcTemplate.query(query, new BeanPropertyRowMapper(MessageEmailContent.class));
		return messageContent;
	}
}
