package com.skyrate.model.citystate;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Country;

public class CountryResponse extends BaseResponse {

	List<Country> country;

	public List<Country> getCountry() {
		return country;
	}

	public void setCountry(List<Country> country) {
		this.country = country;
	}
	
	
}
