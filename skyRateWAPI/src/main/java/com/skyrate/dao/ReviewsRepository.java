package com.skyrate.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Reviews;

public interface ReviewsRepository extends CrudRepository<Reviews, Long>{

	Reviews findById(int id);
	int countByBusinessId(int businessId);
	Reviews findByBusinessIdAndUserIdAndCreatedDate(int businessId,int userId,String date);
	int countByRecommendedAndBusinessId(boolean recommend,int businessId);
	void deleteById(int id);
}
