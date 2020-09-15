package com.skyrate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.ContactInfo;

public interface ContactInfoRepository extends CrudRepository<ContactInfo,Long>{

	List<ContactInfo> findAll();
}
