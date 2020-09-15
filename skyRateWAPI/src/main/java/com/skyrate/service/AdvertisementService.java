package com.skyrate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.dao.AdvertisementQuery;
import com.skyrate.dao.AdvertisementRepository;
import com.skyrate.model.dbentity.AdBannerImage;
import com.skyrate.model.dbentity.Advertisement;

@Service
@Transactional
public class AdvertisementService {

	@Autowired
	AdvertisementRepository advertisementRepository;
	
	@Autowired
	AdvertisementQuery advertisementQuery;
	
	public Advertisement saveAd(Advertisement advertisement){
		return this.advertisementRepository.save(advertisement);
	}
	
	public List<Advertisement> getAdBanner(){
		return this.advertisementRepository.findAll();
	}
	
	public List<AdBannerImage> getBannerImages(){
		return this.advertisementQuery.getImages();
	}
}
