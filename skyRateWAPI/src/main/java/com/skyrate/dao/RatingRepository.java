package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long>{

	Rating findById(int id);
	int countByBusinessId(int businessId);
}
