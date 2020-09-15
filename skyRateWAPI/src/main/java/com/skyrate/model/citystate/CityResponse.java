package com.skyrate.model.citystate;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.AddressCity;

public class CityResponse extends BaseResponse {

	List<AddressCity> cities;

	public List<AddressCity> getCities() {
		return cities;
	}

	public void setCities(List<AddressCity> cities) {
		this.cities = cities;
	}
	
}
