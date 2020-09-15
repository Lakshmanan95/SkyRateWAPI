package com.skyrate.dao;

import org.springframework.data.repository.CrudRepository;

import com.skyrate.model.dbentity.UserRoleMapping;

public interface UserRoleMappingRepository extends CrudRepository<UserRoleMapping,Long> {

	UserRoleMapping findByRoleId(int roleId);
}
