package com.skyrate.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyrate.clib.model.SuccessIDResponse;
import com.skyrate.clib.service.email.EmailService;
import com.skyrate.clib.service.email.EmailTemplateService;
import com.skyrate.clib.util.JSONUtil;
import com.skyrate.model.dbentity.ConversationMapping;
import com.skyrate.model.dbentity.Messenger;
import com.skyrate.model.dbentity.User;
import com.skyrate.model.message.ConversationRequest;
import com.skyrate.model.message.DeleteMessageRequest;
import com.skyrate.model.message.DeleteRequest;
import com.skyrate.model.message.InboxMessages;
import com.skyrate.model.message.InboxMessagesResponse;
import com.skyrate.model.message.InboxRequest;
import com.skyrate.model.message.MailRecipient;
import com.skyrate.model.message.MailToClientRequest;
import com.skyrate.model.message.MessageListRequest;
import com.skyrate.model.message.MessageListResponse;
import com.skyrate.model.message.MessageRequest;
import com.skyrate.model.message.MessagesById;
import com.skyrate.model.message.ReadCount;
import com.skyrate.model.message.ReadCountRequest;
import com.skyrate.model.message.ReadCountResponse;
import com.skyrate.model.message.ReadResponse;
import com.skyrate.model.message.UnReadCount;
import com.skyrate.service.MessageService;
import com.skyrate.service.UserMgmtService;

@RestController
@RequestMapping("/message")
@CrossOrigin(maxAge = 3600)
public class MessageController {

	private static final Logger logger =  LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserMgmtService userMgmtService;
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@RequestMapping(method = RequestMethod.POST, value ="/addMessage")
	public SuccessIDResponse addMessage(@RequestBody MessageRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			
			ConversationMapping conversationMapping = messageService.conversationMapping(request.getFrom(), request.getTo());
			if(conversationMapping == null){
				conversationMapping = new ConversationMapping();
				conversationMapping.setMessenger1(request.getFrom());
				conversationMapping.setMessenger2(request.getTo());
				messageService.saveConversationMapping(conversationMapping);
				System.out.println("conv mapping "+JSONUtil.toJson(conversationMapping));
				
			}
			String unique = UUID.randomUUID().toString().replace("-", "");
			if(request.getSubjectId()==null){
				request.setSubjectId(UUID.randomUUID().toString().replace("-", ""));
			}
			Messenger messenger = new Messenger();
			messenger.setFromId(request.getFrom());
			messenger.setToId(request.getTo());
			messenger.setMessage(request.getMessage());
			messenger.setConversationId(conversationMapping.getId());
			messenger.setDateTime(new Date());
			messenger.setReviewId(request.getReviewId());
			messenger.setOwnerId(request.getFrom());
			messenger.setReadFlag(0);
			messenger.setUniqueMessageId(unique);
			messenger.setSubject(request.getSubject());
			messenger.setSubjectId(request.getSubjectId());
			messageService.saveMessenger(messenger);
			Messenger messenger1 = new Messenger();
			messenger1.setFromId(request.getFrom());
			messenger1.setToId(request.getTo());
			messenger1.setMessage(request.getMessage());
			messenger1.setConversationId(conversationMapping.getId());
			messenger1.setDateTime(new Date());
			messenger1.setReviewId(request.getReviewId());
			messenger1.setOwnerId(request.getTo());
			messenger1.setReadFlag(1);
			messenger1.setUniqueMessageId(unique);
			messenger1.setSubject(request.getSubject());
			messenger1.setSubjectId(request.getSubjectId());
			messageService.saveMessenger(messenger1);
			
		}
		catch(Exception e){
			logger.error("message failed",e);
			response.setSuccess(false);
		}
		response.setDesc(request.getSubjectId());
		return response;
	}
/*	
	@RequestMapping(method = RequestMethod.POST, value = "/getMessagesById")
	public MessageListResponse getMessagesById(@RequestBody MessageListRequest request){
		MessageListResponse response = new MessageListResponse();
		try{
			System.out.println("request "+JSONUtil.toJson(request));
		//	messageService.updateRead(request.getOwnerId(), request.getToId());
			ConversationMapping conversationMapping = messageService.conversationMapping(request.getFromId(), request.getToId());
//			messageService.updateRead(conversationMapping.getId(), request.getToId());
			List<MessagesById> messages = messageService.getMessagesById(conversationMapping.getId(), request.getOwnerId());
		//	messageService.updateRead(conversationMapping.getId(),request.getOwnerId());
			response.setMessages(messages);
			logger.info("message list");
		}
		catch(Exception e){
			logger.error("message list failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getInboxById")
	public MessageListResponse getInboxById(@RequestBody InboxRequest request){
		MessageListResponse response = new MessageListResponse();
		try{
			List<MessagesById> messages = messageService.getInbox(request.getId());
			response.setMessages(messages);
			logger.info("inbox list");
		}
		catch(Exception e){
			logger.error("inbox list failed",e);
			response.setSuccess(false);
		}
		return response;
	}*/
	@RequestMapping(method = RequestMethod.POST, value = "/getInboxListById")
	public InboxMessagesResponse getInboxListById(@RequestBody InboxRequest request){
		InboxMessagesResponse response = new InboxMessagesResponse();
		try{
			InboxMessages messages = messageService.getInboxList(request.getId());
			response.setInboxMessages(messages);
			
		}
		catch(Exception e){
			logger.error("inbox list failed",e);
			response.setSuccess(false);
		}
		return response;
	}	
	/*
	@RequestMapping(method = RequestMethod.POST, value = "/getByConversationId")
	public MessageListResponse getByConversationId(@RequestBody ConversationRequest request){
		MessageListResponse response = new MessageListResponse();
		try{
		//	messageService.updateRead(request.getId(),request.getOwnerId() );
			List<MessagesById> messages = messageService.getMessagesById(request.getId(), request.getOwnerId());
			response.setMessages(messages);
			logger.info("update message list");
		}
		catch(Exception e){
			logger.error("message list failed",e);
			response.setSuccess(false);
		}
		return response;
	}*/
	@RequestMapping(method = RequestMethod.POST, value = "/getConversationBySubjectId")
	public MessageListResponse getConversationBySubjectId(@RequestBody ConversationRequest request){
		MessageListResponse response = new MessageListResponse();
		try{
			
			List<MessagesById> messages = messageService.getConversationBySubjectId(request.getOwnerId(),request.getSubjectId());
			messageService.updateRead(request.getSubjectId(),request.getOwnerId() );
			response.setMessages(messages);
		
		}
		catch(Exception e){
			logger.error("message list failed",e);
			response.setSuccess(false);
		}
		return response;
	}	
	@RequestMapping(method = RequestMethod.POST, value ="/getReadCount")
	public ReadCountResponse getReadCount(@RequestBody ReadCountRequest request){
		ReadCountResponse response = new ReadCountResponse();
		try{
			List<ReadCount> readCount = messageService.getReadCount(request.getFromId());
			response.setReadCount(readCount);
			
		}
		catch(Exception e){
			logger.error("count list failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/mailToClient")
	public SuccessIDResponse mailToClient(@RequestBody MailToClientRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			final String SEPARATOR = ",";
			List<MailRecipient> mailRecipient = null;
			User user = userMgmtService.getUserById(request.getUserMail());
			StringBuilder csvBuilder = new StringBuilder();
			for(MailRecipient mail : request.getMailRecipient()){
				csvBuilder.append(mail.getValue());
			    csvBuilder.append(SEPARATOR);
			}
			
			String csv = csvBuilder.toString();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("content", request.getBody());
			String subject = request.getSubject();
			String emailBody = emailTemplateService.getEmailTemplate("mailToClient.vm",map);
//			EmailMessage emailMessage = new EmailMessage();
//			emailMessage.setToEmail(user.getEmail());
//			emailMessage.setSubject(subject);
//			emailMessage.setEmailBody(emailBody);
			emailService.sendEmailToClients(user.getEmail(), subject, emailBody, csv);
		}
		catch(Exception e){
			logger.error("mail to client failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteMessageById")
	public SuccessIDResponse deleteMessage(@RequestBody DeleteMessageRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			ConversationMapping conversationMapping = messageService.conversationMapping(request.getUserId(), request.getToId());
			messageService.deleteMessenger(conversationMapping.getId(), request.getUserId());
		}
		catch(Exception e){
			logger.error("deleted message failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/deleteMessage")
	public SuccessIDResponse delete(@RequestBody DeleteRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			List<Messenger> messenger = messageService.getUniqueMessages(request.getUniqueId());
			for(Messenger messages : messenger){
				messages.setDeleteFlag(1);
				messages.setReadFlag(1);
				messages.setMessage("<i class='fa fa-ban' style='padding-right:5px;'></i><span style='color: #635e5e;'> message deleted </span>");
				messageService.saveMessenger(messages);
			}
			
		}
		catch(Exception e){
			logger.error("deleted message failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	@RequestMapping(method = RequestMethod.POST, value ="/deleteConversation")
	public SuccessIDResponse deleteConversation(@RequestBody DeleteMessageRequest request){
		SuccessIDResponse response = new SuccessIDResponse();
		try{
			messageService.deleteConversation(request.getSubjectId(),request.getUserId());

		}
		catch(Exception e){
			logger.error("deleted message failed",e);
			response.setSuccess(false);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/getCount")
	public ReadResponse getcount(@RequestBody ReadCountRequest request){
		ReadResponse response = new ReadResponse();
		try{
			UnReadCount read = messageService.getCount(request.getFromId());
			response.setRead(read.getReadCount());
			
			System.out.println(JSONUtil.toJson(response));
		}
		catch(Exception e){
			logger.error("Count failed",e);
			response.setSuccess(false);
		}
		
		return response;
	}
	/*@RequestMapping(method = RequestMethod.POST, value ="/getNotificationCount/{userId}")
	public CountResponse getNotificationcount(@PathVariable int userId){
		CountResponse response = new CountResponse();
		try{
			long count  = messageService.getNotificationCount(userId);
			response.setCount(count);
			logger.info("count");
			System.out.println(JSONUtil.toJson(response));
		}
		catch(Exception e){
			logger.error("Count failed",e);
			response.setSuccess(false);
			response.setUserErrorMsg("Error in getting notification count");
		}
		
		return response;
	}*/
	/*
	@RequestMapping(method = RequestMethod.GET, value = "/getConversation/{id}")
	public MessageListResponse getConversation(@PathVariable int id){
		MessageListResponse response = new MessageListResponse();
		try{
			System.out.println("system idd "+id);
			Messenger message = messageService.getMessengerById(id);
			System.out.println("system idd "+JSONUtil.toJson(message));
			int toId;
			if(message.getToId() == id)
				toId = message.getFromId();
			else
				toId = message.getToId();
			//messageService.updateRead(message.getConversationId(), toId);
			List<MessagesById> messages = messageService.getMessagesById(message.getConversationId(), message.getOwnerId());
			response.setMessages(messages);
			response.setToId(toId);
			logger.info("update message list");

		}
		catch(Exception e){
			logger.error("Count failed",e);
			response.setSuccess(false);
		}
		return response;
	}*/
}
