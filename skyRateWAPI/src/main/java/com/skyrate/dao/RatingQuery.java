package com.skyrate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.clib.util.JSONUtil;
import com.skyrate.model.dbentity.Bookmark;
import com.skyrate.model.dbentity.Reviews;
import com.skyrate.model.ratingAndReview.RatingCount;
import com.skyrate.model.ratingAndReview.ReviewCount;
import com.skyrate.model.ratingAndReview.ReviewList;
import com.skyrate.model.report.TotalHit;

@Service
@Transactional
public class RatingQuery {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public RatingCount getRatingTotalByBusinessId(int id){
		String query = "select sum(rating_id) as count from reviews where business_id="+id;
		RatingCount count = (RatingCount) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(RatingCount.class));
		return count;
	}
	
//	public int getRatingCount(int businessId){
//		String query = "select count(*) from rating where business_id"
//	}
	public List<ReviewList> getReviewByBusinessId(int businessId){
		String query = "select reviews.*, business_user_reply.REPLY , user.first_name,user.last_name, user.business_name,user.profile_image_url,user.id as userId, "
				+"CASE WHEN business_user_reply.id  is NULL THEN 0 ELSE business_user_reply.id END AS reply_id  "+
				 "from reviews "+
						" inner join user on user.id = reviews.user_id "
						+ "left join business_user_reply on business_user_reply.REVIEW_ID = reviews.id  where reviews.business_id="+businessId+"  Order by id DESC limit 0,5";
		List<ReviewList> reviewList = jdbcTemplate.query(query,  new BeanPropertyRowMapper(ReviewList.class));
		return reviewList;
	}
	
	public List<ReviewList> getReviewByPagination(int businessId,String pageOrder,int from, int to){
		System.out.println("page "+pageOrder);
		String query = "select reviews.*,business_user_reply.REPLY, user.first_name,user.business_name,user.profile_image_url,user.id as userId ,"
				+ "CASE WHEN business_user_reply.id  is NULL THEN 0 ELSE business_user_reply.id END AS reply_id   from reviews "+
						" inner join user on user.id = reviews.user_id "
						+ "left join business_user_reply on business_user_reply.REVIEW_ID = reviews.id where reviews.business_id="+businessId+"  order by ";
		if(pageOrder.equals("Newest first"))
			query+="id desc limit 0,"+to;
		if(pageOrder.equals("Oldest first"))
			query+="id asc limit 0,"+to;
		if(pageOrder.equals("Rating High to Low"))
			query+="overall_rate desc limit 0,"+to;
		if(pageOrder.equals("Rating Low to High"))
			query+="overall_rate asc limit 0,"+to;
		List<ReviewList> reviewList = jdbcTemplate.query(query,  new BeanPropertyRowMapper(ReviewList.class));
		return reviewList;
	}
	
	public Bookmark deleteBookmark(int userId,int businessId){
		String query = "Delete from bookmark where user_id="+userId+" and business_id="+businessId;
		Bookmark bookmark = (Bookmark) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(Bookmark.class));
		return bookmark;
	}
	
	public ReviewCount getReviewCoutByBusinessId(int businessId){
		String query = "select count(business_id) as count from reviews where business_id ="+businessId;
		ReviewCount count = (ReviewCount) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(ReviewCount.class));
		return count;
	}
	public RatingCount getAverage(int businessId,String column){
		String query = "select sum("+column+") as count from reviews where business_id="+businessId;
		RatingCount count = (RatingCount) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(RatingCount.class));
		return count;
	}
	public ReviewCount getCount(int businessId,String column){
		String query = "select count(*) as count from reviews where business_id="+businessId+" and "+column+"!=0";
		ReviewCount count = (ReviewCount) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(ReviewCount.class));
		return count;
	}
	public Reviews getReviewPerUser(int businessId,int userId,String date){
		String query = "select * from reviews where business_id="+businessId+" and user_id="+userId;
		
		if(!date.isEmpty()){
			query+=" and created_date='"+date+"'";
		}
		Reviews review = (Reviews) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(Reviews.class));
		return review;
	}
}
