package com.skyrate.model.dbentity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Messenger {

	@Id
	@GeneratedValue
	int id;
	int fromId;
	int toId;
	int conversationId;
	int reviewId;
	String message;
	Date dateTime;
	int readFlag;
	int defaultMessage;
	int ownerId;
	int deleteFlag;
	String uniqueMessageId;
	String subject;
	String subjectId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
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
	public int getConversationId() {
		return conversationId;
	}
	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public int getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(int readFlag) {
		this.readFlag = readFlag;
	}
	public int getDefaultMessage() {
		return defaultMessage;
	}
	public void setDefaultMessage(int defaultMessage) {
		this.defaultMessage = defaultMessage;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getUniqueMessageId() {
		return uniqueMessageId;
	}
	public void setUniqueMessageId(String uniqueMessageId) {
		this.uniqueMessageId = uniqueMessageId;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
	
}
