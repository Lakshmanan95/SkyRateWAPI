package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.BusinessUserReply;

public interface BusinessUserReplyRepository extends CrudRepository<BusinessUserReply, Long>{

	BusinessUserReply findById(int id);
	void deleteById(int id);
}
