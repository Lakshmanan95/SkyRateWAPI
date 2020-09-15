package com.skyrate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.BusinessCategoryMapping;

public interface BusinessCategoryMappingRepository extends CrudRepository<BusinessCategoryMapping,Long> {

	List<BusinessCategoryMapping> findByBusinessId(int businessId);
	BusinessCategoryMapping findByCategoryIdAndBusinessId(int categoryId, int businessId);
}
