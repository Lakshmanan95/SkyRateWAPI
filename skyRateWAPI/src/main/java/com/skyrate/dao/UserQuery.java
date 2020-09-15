package com.skyrate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.model.dbentity.User;
import com.skyrate.model.report.UserCount;


@Service
@Transactional
public class UserQuery {
	@Autowired
	JdbcTemplate jdbcTemplate;

//	public Address getAddressByUserId(long userId, int addressType) {
//		String qry = "select * from address where user_id=" + userId + " and address_type=" + addressType;
//
//		Address address = (Address) jdbcTemplate.queryForObject(qry, new BeanPropertyRowMapper(Address.class));
//		return address;
//	}
	public User loginUser(String email,String password){
		String query = "select first_name,token,user_name,last_name,profile_image_url,email,id,phone_number,business_name from user where (user_name='"+email+"' or email='"+email+"')and password='"+password+"'";
		User user = (User) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(User.class));
		return user;
	}


	public int getUserRoleId(long userId) {
		int roleId = 4;
		String qry = "select role_id from user_role_mapping where user_id=?";
		System.out.println(qry);
		try {
			//roleId = (Integer) jdbcTemplate.queryForObject(qry, new BeanPropertyRowMapper(Integer.class));
			roleId = jdbcTemplate.queryForObject(
					qry, new Object[] { userId }, Integer.class);

		} catch (EmptyResultDataAccessException e) {
			roleId=4;
		}catch (Exception e) {
			roleId=4;
		}
		return roleId;
	}
	
	public UserCount getUserCount(String date){
		String query = "select count(*) as count from user where created_date >='"+date+"'";
		UserCount userCount = (UserCount) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(UserCount.class));
		return userCount;
		
	}
	
	public User getUserById(int id){
		String query = "select first_name,last_name,profile_image_url,email,id,phone_number,business_name from user where id ="+id;
		User user = (User) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(User.class));
		return user;
	}
	
	public User emailExistWithId(int id, String email){
		System.out.println(id+" email "+email);
		String query = "select first_name,last_name,profile_image_url,email,id,phone_number,business_name from user "
				+ " where id !="+id+" and email='"+email+"'";
		User user = (User) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(User.class));
		return user;
	}
}
