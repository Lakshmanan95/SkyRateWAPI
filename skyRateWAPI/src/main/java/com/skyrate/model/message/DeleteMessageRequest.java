package com.skyrate.model.message;

public class DeleteMessageRequest {

	int messageId;
	int userId;
	int deleteAll;
	int toId;
	String subjectId;
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getDeleteAll() {
		return deleteAll;
	}
	public void setDeleteAll(int deleteAll) {
		this.deleteAll = deleteAll;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	
	
	
}
