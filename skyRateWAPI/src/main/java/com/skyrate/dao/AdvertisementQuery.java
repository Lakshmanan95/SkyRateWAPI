package com.skyrate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.model.business.BusinessDetails;
import com.skyrate.model.dbentity.AdBannerImage;

@Service
@Transactional
public class AdvertisementQuery {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<AdBannerImage> getImages(){
		String query = "select * from ad_banner_image where active = 1 order by priority asc";
		List<AdBannerImage> adBannerImage = jdbcTemplate.query(query, new BeanPropertyRowMapper(AdBannerImage.class));
		return adBannerImage;
	}
}
