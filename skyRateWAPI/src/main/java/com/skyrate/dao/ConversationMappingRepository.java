package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.ConversationMapping;

public interface ConversationMappingRepository extends CrudRepository<ConversationMapping,Long> {

}
