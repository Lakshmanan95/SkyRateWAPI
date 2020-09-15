package com.skyrate.model.usermgmt;

import com.skyrate.clib.model.BaseResponse;

public class AddressIDResponse extends BaseResponse{

	long addressId;
	String city;
	int addressType;
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getAddressType() {
		return addressType;
	}
	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}
	
	
}
