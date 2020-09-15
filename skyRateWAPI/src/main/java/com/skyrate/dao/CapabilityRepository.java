package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Capabilities;

public interface CapabilityRepository extends CrudRepository<Capabilities,Long>{
	Capabilities findById(int id);
	Integer deleteById(int id);

}
