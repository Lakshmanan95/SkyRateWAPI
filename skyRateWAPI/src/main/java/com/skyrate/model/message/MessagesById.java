package com.skyrate.model.message;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skyrate.model.dbentity.Messenger;

public class MessagesById extends Messenger{

	String fromName;
	String fromImage;
	String toName;
	String toImage;
	int readCount;
	//@JsonFormat(pattern = "MMM dd HH:mm a")
	Date dateTime;
	
	
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

	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
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
