package com.skyrate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.model.citystate.CountryList;
import com.skyrate.model.dbentity.AddressState;


@Service
@Transactional
public class CityStateQuery {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	public List<AddressState> getState(){
		String query = "Select * from address_state";
		List<AddressState> state = jdbcTemplate.query(query, new BeanPropertyRowMapper(AddressState.class));
		return state;			
	}
	
	public List<CountryList> getCountry(){
		String query = "select country as name from business where country != 'null' and active = 0 group by country";
		List<CountryList> country = jdbcTemplate.query(query, new BeanPropertyRowMapper(CountryList.class));
		return country;
	}
	
}
