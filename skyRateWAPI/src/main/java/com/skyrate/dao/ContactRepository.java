package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.FeedbackForm;

public interface ContactRepository extends CrudRepository<FeedbackForm,Long>{

}
