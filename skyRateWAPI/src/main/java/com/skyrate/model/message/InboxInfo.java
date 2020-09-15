package com.skyrate.model.message;

import java.util.Date;

public class InboxInfo {
	long fromId;
	long toId;
	String fromName;
	String toName;
	String fromImage;
	String toImage;
	String subject;
	String subjectId;
	Date dateTime;
	Integer unreadCnt;
	long ownerId;
	
	public long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public long getToId() {
		return toId;
	}
	public void setToId(long toId) {
		this.toId = toId;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Integer getUnreadCnt() {
		return unreadCnt;
	}
	public void setUnreadCnt(Integer unreadCnt) {
		this.unreadCnt = unreadCnt;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public String getFromImage() {
		return fromImage;
	}
	public void setFromImage(String fromImage) {
		this.fromImage = fromImage;
	}
	public String getToImage() {
		return toImage;
	}
	public void setToImage(String toImage) {
		this.toImage = toImage;
	}
	
}

