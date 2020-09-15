package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback,Long> {

}
