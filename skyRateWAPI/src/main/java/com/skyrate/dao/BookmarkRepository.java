package com.skyrate.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.skyrate.model.dbentity.Bookmark;

public interface BookmarkRepository extends CrudRepository<Bookmark,Long>{

	Bookmark findByUserIdAndBusinessId(int userId,int businessId);
	Integer deleteByUserIdAndBusinessId(int userId,int businessId);
	Integer deleteByUserId(int userId);
	Integer countByUserId(int userId);
  // @Query("SELECT COUNT(u) FROM Bookmark u WHERE u.userId=:userId")
 //   Long countBookMark(@Param("userId") int userId);
}
