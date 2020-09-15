package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.UserLoginHistory;

public interface UserLoginHistoryRespository extends CrudRepository<UserLoginHistory,Long>{

}
