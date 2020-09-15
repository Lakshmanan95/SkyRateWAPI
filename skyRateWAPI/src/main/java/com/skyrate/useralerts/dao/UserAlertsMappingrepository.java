package com.skyrate.useralerts.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.useralerts.entity.UserAlertsMapping;
import com.skyrate.useralerts.entity.UserAlertsResponse;

public interface UserAlertsMappingrepository extends CrudRepository<UserAlertsMapping,Long>{

	List<UserAlertsMapping> findAll();
	UserAlertsMapping findById(int id);
	UserAlertsMapping findByUserIdAndAlertIdAndBusinessId(int userId, int alertId,int businessId);
}
