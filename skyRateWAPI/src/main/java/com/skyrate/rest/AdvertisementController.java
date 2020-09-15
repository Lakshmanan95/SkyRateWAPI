package com.skyrate.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyrate.clib.model.SuccessIDResponse;
import com.skyrate.model.advertisement.AdBannerResponse;
import com.skyrate.model.advertisement.AdvertisementBannerRequest;
import com.skyrate.model.advertisement.BannerImageResponse;
import com.skyrate.model.dbentity.AdBannerImage;
import com.skyrate.model.dbentity.Advertisement;
import com.skyrate.service.AdvertisementService;

@RestController
@RequestMapping("/advertisement")
@CrossOrigin(maxAge=3600)
public class AdvertisementController {

	private static final Logger logger =  LoggerFactory.getLogger(AdvertisementController.class);
	
	@Autowired
	AdvertisementService advertisementService;
	
	@RequestMapping(method = RequestMethod.POST, value="/uploadBannerAd")
	public SuccessIDResponse saveBannerAd(@RequestBody AdvertisementBannerRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try
		{
			Advertisement advertisement = new Advertisement();
			for(Advertisement ad : request.getAdvertisement()){
				advertisement.setAdvertisementUrl(ad.getAdvertisementUrl());
				advertisement.setWebsiteLink(ad.getWebsiteLink());
				advertisementService.saveAd(advertisement);
			}
			
		}
		catch(Exception e){
			response.setSuccess(false);
			logger.error("save banner failed",e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getAdBanner")
	public AdBannerResponse getAdBanner(){
		AdBannerResponse response = new AdBannerResponse();
		try{
			List<Advertisement> advertisement = advertisementService.getAdBanner();
			response.setAdvertisement(advertisement);
			
		}
		catch(Exception e){
			response.setSuccess(false);
			logger.error("get banner failed",e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getBannerImages")
	public BannerImageResponse getBannerImage(){
		BannerImageResponse response = new BannerImageResponse();
		try{
			List<AdBannerImage> adBannerImage = advertisementService.getBannerImages();
			response.setAdBannerImage(adBannerImage);
			
		}
		catch(Exception e){
			response.setSuccess(false);
			logger.error("get banner images failed",e);
		}
		return response;
	}
}
