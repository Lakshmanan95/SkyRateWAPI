package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Business;

public interface BusinessRepository extends CrudRepository<Business, Long>{

	Business findById(int id);
	Business findByName(String name);
	int deleteById(int id);
}
