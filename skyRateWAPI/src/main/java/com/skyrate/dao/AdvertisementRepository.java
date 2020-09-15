package com.skyrate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Advertisement;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Long>{

	List<Advertisement> findAll();
}
