package com.skyrate.model.advertisement;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.AdBannerImage;

public class BannerImageResponse extends BaseResponse {

	List<AdBannerImage> adBannerImage;

	public List<AdBannerImage> getAdBannerImage() {
		return adBannerImage;
	}

	public void setAdBannerImage(List<AdBannerImage> adBannerImage) {
		this.adBannerImage = adBannerImage;
	}
	
	
	
}
