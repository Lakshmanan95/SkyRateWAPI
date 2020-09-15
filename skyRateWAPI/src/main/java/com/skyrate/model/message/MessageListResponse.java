package com.skyrate.model.message;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class MessageListResponse extends BaseResponse{

	List<MessagesById> messages;
	int toId;

	public List<MessagesById> getMessages() {
		return messages;
	}

	public void setMessages(List<MessagesById> messages) {
		this.messages = messages;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}
	
	
}
