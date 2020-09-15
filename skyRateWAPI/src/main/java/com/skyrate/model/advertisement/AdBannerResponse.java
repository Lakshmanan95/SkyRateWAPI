package com.skyrate.model.advertisement;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.Advertisement;

public class AdBannerResponse extends BaseResponse {

	List<Advertisement> advertisement;

	public List<Advertisement> getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(List<Advertisement> advertisement) {
		this.advertisement = advertisement;
	}
	
	
}
