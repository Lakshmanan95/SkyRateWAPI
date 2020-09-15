package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.BusinessClaimMapping;

public interface BusinessClaimMappingRepository extends CrudRepository<BusinessClaimMapping,Long>{

	BusinessClaimMapping findByBusinessId(int id);

}
