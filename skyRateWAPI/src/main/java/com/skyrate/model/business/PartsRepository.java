package com.skyrate.model.business;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Parts;

public interface PartsRepository extends CrudRepository<Parts,Long>{

	Parts findById(int id);
	Integer deleteById(int id);
}
