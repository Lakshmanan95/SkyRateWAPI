package com.skyrate.useralerts.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.useralerts.entity.UserAlerts;

public interface UserAlertsRepository extends CrudRepository<UserAlerts,Long> {
	UserAlerts findById(int id);
}
