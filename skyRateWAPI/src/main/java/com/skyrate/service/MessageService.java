package com.skyrate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyrate.clib.util.DateTimeUtil;
import com.skyrate.dao.ConversationMappingRepository;
import com.skyrate.dao.MessageQuery;
import com.skyrate.dao.MessengerRepository;
import com.skyrate.model.dbentity.ConversationMapping;
import com.skyrate.model.dbentity.Messenger;
import com.skyrate.model.message.InboxMessages;
import com.skyrate.model.message.MessageEmailContent;
import com.skyrate.model.message.MessagesById;
import com.skyrate.model.message.ReadCount;
import com.skyrate.model.message.UnReadCount;

@Service
@Transactional
public class MessageService {

	@Autowired
	MessageQuery messageQuery;
	
	@Autowired
	ConversationMappingRepository conversationMappingRepository;
	
	@Autowired
	MessengerRepository messengerRepository;
	
	public ConversationMapping conversationMapping(int messenger1,int messenger2){
		return this.messageQuery.conversationMapping(messenger1, messenger2);
	}
	public ConversationMapping saveConversationMapping(ConversationMapping conversationMapping){
		return this.conversationMappingRepository.save(conversationMapping);
	}
	public Messenger saveMessenger(Messenger messenger){
		return this.messengerRepository.save(messenger);
	}
	public List<MessagesById> getMessagesById(int conversationId, int ownerId){
		return this.messageQuery.getMessagesById(conversationId, ownerId);
	}
	public List<MessagesById> getConversationBySubjectId(int ownerId,String subjectId){
		return this.messageQuery.getConversationBySubjectId(ownerId,subjectId);
	}
	public InboxMessages getInboxList(int id){
		return InboxMessages.createInboxList(this.messageQuery.getInboxList(id));
	}	
	public List<MessagesById> getInbox(int id){
		return this.messageQuery.getInbox(id);
	}
	public void updateRead(String subjectId, int ownerId){
		this.messageQuery.updateRead(subjectId, ownerId);
	}
	public List<ReadCount> getReadCount(int fromId){
		return this.messageQuery.getReadCount(fromId);
	}
	public void deleteMessenger(int conversationId, int ownerId){
		this.messageQuery.deleteMessenger(conversationId, ownerId);
	}
	public void deleteConversation(String subjectId, int ownerId){
		this.messageQuery.deleteConversation(subjectId, ownerId);
	}
	public List<Messenger> getUniqueMessages(String message){
		return this.messengerRepository.findByUniqueMessageId(message);
	}
	public UnReadCount getCount(int ownerId){
		return this.messageQuery.getCount(ownerId);
	}
	public long getNotificationCount(int userId){
		return this.messageQuery.getNotificationCount(userId);
	}
	public Messenger getMessengerById(int ownerId){
		return this.messageQuery.getConversation(ownerId);
	}
	public List<MessageEmailContent> getMessageEmailContent(){
		String date = DateTimeUtil.getTodayString();
		return this.messageQuery.getMessageEmail(date);
	}
}
