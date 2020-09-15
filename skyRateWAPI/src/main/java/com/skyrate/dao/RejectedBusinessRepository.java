package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.RejectedBusiness;

public interface RejectedBusinessRepository extends CrudRepository<RejectedBusiness,Long>{
	
	void deleteById(int id);
	
}
