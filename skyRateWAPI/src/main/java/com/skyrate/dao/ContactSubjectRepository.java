package com.skyrate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.ContactSubject;

public interface ContactSubjectRepository extends CrudRepository<ContactSubject, Long>{

	List<ContactSubject> findAll();
	ContactSubject findById(int id);
	List<ContactSubject> findByType(int type);
}
