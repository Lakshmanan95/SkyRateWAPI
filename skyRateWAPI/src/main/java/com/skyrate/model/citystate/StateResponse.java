package com.skyrate.model.citystate;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;
import com.skyrate.model.dbentity.AddressState;

public class StateResponse extends BaseResponse {

	List<AddressState> states;

	public List<AddressState> getStates() {
		return states;
	}

	public void setStates(List<AddressState> states) {
		this.states = states;
	}
	
}
