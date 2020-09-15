package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.EventLog;

public interface EventLogRepository extends CrudRepository<EventLog,Long>{

	EventLog findByUniqueId(String uniqueId);
	EventLog findByEventAndBusinessId(String event, int businessId);
}
