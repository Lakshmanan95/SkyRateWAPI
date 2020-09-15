package com.skyrate.model.citystate;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class CountryListResponse extends BaseResponse{

	List<CountryList> country;

	public List<CountryList> getCountry() {
		return country;
	}

	public void setCountry(List<CountryList> country) {
		this.country = country;
	}
}
