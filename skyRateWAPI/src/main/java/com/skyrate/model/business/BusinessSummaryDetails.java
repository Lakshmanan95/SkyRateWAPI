package com.skyrate.model.business;

import com.skyrate.model.dbentity.BusinessRatingSummary;

public class BusinessSummaryDetails extends BusinessRatingSummary{

	boolean isMarked;

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}
	
	
}
