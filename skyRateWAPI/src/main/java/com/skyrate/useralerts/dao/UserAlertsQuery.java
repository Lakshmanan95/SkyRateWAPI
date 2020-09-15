package com.skyrate.useralerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.useralerts.entity.UserAlerts;
import com.skyrate.useralerts.model.UserAlertResponseCounts;


@Service
@Transactional
public class UserAlertsQuery {

	@Autowired
    JdbcTemplate jdbcTemplate;
	

/*	public List<UserAlerts> getMyEligibleAlerts(int userId){
		String query = "Select * from user_alerts where active=1 and (user_id is null or user_id="+userId+")"
						+" and id not in (select alert_id from user_alerts_response where response_yes=1 ) order by priority";
		List<UserAlerts> userAlerts = jdbcTemplate.query(query, new BeanPropertyRowMapper(UserAlerts.class));
		return userAlerts;			
	}	*/
	
	public List<UserAlerts> getMyEligibleAlerts(int userId){
		String query = "select * from ("
			+" Select ua.*,b.id as business_id, b.name as business_name from user_alerts ua "
			+" inner join user_alerts_mapping uam on ua.id=uam.alert_id "
			+" inner join business b  on b.id=uam.business_id "
			+" where ua.active=1 and ua.is_user_alert=1 and uam.user_id= "+ userId
			+" and ua.id not in (select alert_id from user_alerts_response where response_yes=1 and user_id="+userId+" ) "
			+" union "
			+" select *,'' as business_id, '' as business_name   from user_alerts ua1 where ua1.is_user_alert is null and ua1.active=1 "
			+" and ua1.id not in (select alert_id from user_alerts_response where response_yes=1 and user_id="+userId+" ) "
			+" ) aa order by priority";
	
	List<UserAlerts> userAlerts = jdbcTemplate.query(query, new BeanPropertyRowMapper(UserAlerts.class));
	return userAlerts;		
	}
	
	public int getUserAlertResponseNoCount(int userId, int alertId){
		String query ="select count(*) from user_alerts_response where response_no=1 and user_id=? and alert_id=?";
		int count = jdbcTemplate.queryForObject(
				query, new Object[] { userId,alertId }, Integer.class);
		
		return count;
	}
	
	public int getLoginCount(int userId){
		String query ="select count(*) from user_login_history where user_id=?";
		int count = jdbcTemplate.queryForObject(
				query, new Object[] { userId }, Integer.class);
		
		return count;
	}
	public int getAlertByFrequency(int userId,int alertId, int freqDays){
		String query = "";
		query += "select count(*) from user_alerts_response A1"
		+" inner join  "
		+" (Select id, updated_date  from user_alerts_response where user_id=? and alert_id=? order by updated_date desc limit 1) as A2"
		+" on A1.id=A2.id"
		+" and CURRENT_TIMESTAMP() > DATE_ADD(A2.updated_date, INTERVAL "+freqDays+" DAY) ";
		 
		int count = jdbcTemplate.queryForObject(
				query, new Object[] { userId,alertId }, Integer.class);
		
		return count;
	}
	
	/*	public List<UserAlerts> getMyAlerts(int userId){
	String query = "Select * from user_alerts where active=1 and (user_id is null or user_id="+userId +") order by priority";
	List<UserAlerts> userAlerts = jdbcTemplate.query(query, new BeanPropertyRowMapper(UserAlerts.class));
	return userAlerts;			
}*/


	public UserAlertResponseCounts getUserAlertResponseCounts(int userId, int alertId){
	String query ="select count(*) as allCnt, sum(response_yes) as yesCnt , sum(response_no) as noCnt from user_alerts_response where user_id=? and alert_id=?";
	UserAlertResponseCounts userAlertsCount = (UserAlertResponseCounts)jdbcTemplate.queryForObject(query, new Object[] { userId,alertId },  new BeanPropertyRowMapper(UserAlertResponseCounts.class));
	return userAlertsCount;
}
/*
public int getUserAlertResponseCount(int userId, int alertId){
	String query ="select count(*) from user_alerts_response where user_id=? and alert_id=?";
	int count = jdbcTemplate.queryForObject(
			query, new Object[] { userId,alertId }, Integer.class);
	
	return count;
}
public int getUserAlertResponseYesCount(int userId, int alertId){
	String query ="select count(*) from user_alerts_response where response_yes=1 and user_id=? and alert_id=?";
	int count = jdbcTemplate.queryForObject(
			query, new Object[] { userId,alertId }, Integer.class);
	
	return count;
}*/
}
