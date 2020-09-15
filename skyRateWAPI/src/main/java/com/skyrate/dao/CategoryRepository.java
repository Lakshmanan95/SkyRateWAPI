package com.skyrate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {

	List<Category> findAll();
}
