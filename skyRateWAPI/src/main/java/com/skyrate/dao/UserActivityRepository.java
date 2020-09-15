package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.UserActivity;

public interface UserActivityRepository extends CrudRepository<UserActivity,Long>{

	UserActivity findBySecureToken(String value);
	UserActivity findByUserRefreshToken(String refreshToken);
	UserActivity findByUserId(int userId);
}
