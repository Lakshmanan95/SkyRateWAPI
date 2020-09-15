package com.skyrate.useralerts.model;

public class UserAlertResponseCounts {
	Integer allCnt;
	Integer yesCnt;
	Integer noCnt;
	public Integer getAllCnt() {
		if(allCnt==null) return 0;
		return allCnt;
	}
	public void setAllCnt(Integer allCnt) {
		this.allCnt = allCnt;
	}
	public Integer getYesCnt() {
		if(yesCnt==null) return 0;
		return yesCnt;
	}
	public void setYesCnt(Integer yesCnt) {
		this.yesCnt = yesCnt;
	}
	public Integer getNoCnt() {
		if(noCnt==null) return 0;
		return noCnt;
	}
	public void setNoCnt(Integer noCnt) {
		this.noCnt = noCnt;
	}

	

}
