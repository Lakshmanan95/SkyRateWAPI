package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.BusinessRatingSummary;

public interface BusinessRatingSummaryRepository extends CrudRepository<BusinessRatingSummary, Long>{

	BusinessRatingSummary findByBusinessId(int id);
	
}
