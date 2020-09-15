package com.skyrate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Country;

public interface CountryRepository extends CrudRepository<Country,Long> {

	List<Country> findAll();
}
