package com.skyrate.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skyrate.model.dbentity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmailAndPassword(String email, String password);
	User findByUserName(String userName);
	User findByEmail(String email);
	User findByIdAndEmail(int id, String email);
	User findByIdAndPassword(int id,String password);
	User findById(int id);
	User findByToken(String token);
	Long countByCreatedDate(String date);
	
	
}



