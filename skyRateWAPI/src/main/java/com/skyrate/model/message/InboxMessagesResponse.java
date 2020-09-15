package com.skyrate.model.message;

import java.util.List;

import com.skyrate.clib.model.BaseResponse;

public class InboxMessagesResponse extends BaseResponse{

	InboxMessages inboxMessages;

	public InboxMessages getInboxMessages() {
		return inboxMessages;
	}

	public void setInboxMessages(InboxMessages inboxMessages) {
		this.inboxMessages = inboxMessages;
	}


	
	
}
