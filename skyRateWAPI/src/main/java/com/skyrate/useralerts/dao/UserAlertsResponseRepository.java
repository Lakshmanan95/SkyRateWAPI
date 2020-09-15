package com.skyrate.useralerts.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.ContactSubject;
import com.skyrate.useralerts.entity.UserAlertsResponse;

public interface UserAlertsResponseRepository extends CrudRepository<UserAlertsResponse,Long> {

	
	List<UserAlertsResponse> findAll();
	UserAlertsResponse findById(int id);
	
	
}
