package com.skyrate.model.dbentity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import antlr.StringUtils;

@Entity
public class Business {

	@Id
	@GeneratedValue
	int id;
	String name;
	String address;
	String address2;
	String address3;
	String city;
	String state;
	String country;
	String zip;
	String website;
	String phoneNumber;
	String designatorCode;
	String certificateHoldingOffice;
	String certificateNumber;
	String businessDescription;
	String category;
	String overview;
	String capabilities;
	String imageUrl;
	String adImageWebsite;
	int active=1;
	int userId;
	int international;
	String adImageUrl;
	Date updatedDate;
	String email;
	String createdBy;
	int createdId;
	Date createdDate;
	int existingBusiness;
	int businessType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDesignatorCode() {
		return designatorCode;
	}
	public void setDesignatorCode(String designatorCode) {
		this.designatorCode = designatorCode;
	}
	public String getCertificateHoldingOffice() {
		return certificateHoldingOffice;
	}
	public void setCertificateHoldingOffice(String certificateHoldingOffice) {
		this.certificateHoldingOffice = certificateHoldingOffice;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getBusinessDescription() {
		return businessDescription;
	}
	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInternational() {
		return international;
	}
	public void setInternational(int international) {
		this.international = international;
	}
	public String getAdImageUrl() {
		return adImageUrl;
	}
	public void setAdImageUrl(String adImageUrl) {
		this.adImageUrl = adImageUrl;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getAdImageWebsite() {
		return adImageWebsite;
	}
	public void setAdImageWebsite(String adImageWebsite) {
		this.adImageWebsite = adImageWebsite;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getCreatedId() {
		return createdId;
	}
	public void setCreatedId(int createdId) {
		this.createdId = createdId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getExistingBusiness() {
		return existingBusiness;
	}
	public void setExistingBusiness(int existingBusiness) {
		this.existingBusiness = existingBusiness;
	}
	public int getBusinessType() {
		return businessType;
	}
	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}
	
}

