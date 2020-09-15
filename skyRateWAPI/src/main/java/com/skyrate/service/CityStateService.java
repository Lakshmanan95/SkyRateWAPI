package com.skyrate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrate.dao.CityStateQuery;
import com.skyrate.dao.ContactInfoRepository;
import com.skyrate.dao.CountryRepository;
import com.skyrate.dao.FeedbackRepository;
import com.skyrate.model.citystate.CountryList;
import com.skyrate.model.dbentity.AddressState;
import com.skyrate.model.dbentity.ContactInfo;
import com.skyrate.model.dbentity.Country;
import com.skyrate.model.dbentity.Feedback;

@Service
@Transactional
public class CityStateService {

	@Autowired
	private CityStateQuery cityStateQuery;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private ContactInfoRepository contactInfoRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	
	public List<AddressState> getState() throws Exception{
		List<AddressState> states = this.cityStateQuery.getState();
		return states;
	}
	
	public List<CountryList> getCountryList(){
		return this.cityStateQuery.getCountry();
	}
	
	public List<Country> getCountry(){
		return this.countryRepository.findAll();
	}
	public Country saveCountry(Country country){
		return this.countryRepository.save(country);
	}
	public List<ContactInfo> getContactInfo(){
		return this.contactInfoRepository.findAll();
	}
	public Feedback saveFeedback(Feedback feedback){
		return this.feedbackRepository.save(feedback);
	}
}
