package com.skyrate.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.skyrate.model.dbentity.ConversationMapping;
import com.skyrate.model.dbentity.Messenger;
import com.skyrate.model.message.InboxInfo;
import com.skyrate.model.message.MessageEmailContent;
import com.skyrate.model.message.MessagesById;
import com.skyrate.model.message.ReadCount;
import com.skyrate.model.message.UnReadCount;

@Service
@Transactional
public class MessageQuery {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public ConversationMapping conversationMapping(int messenger1, int messenger2){
		String query = "select * from conversation_mapping where MESSENGER1 = "+messenger1+" and MESSENGER2 = "+messenger2
						+ " or MESSENGER1 = "+messenger2+" and MESSENGER2 = "+messenger1; 
		try{
		ConversationMapping conversation =  (ConversationMapping) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(ConversationMapping.class));
		return conversation;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public List<MessagesById> getMessagesById(int conversationId, int ownerId){
		String query = "select messenger.*, concat(A.first_name ,' ',A.last_name) as From_Name, A.profile_image_url as From_image, concat(B.first_name ,' ',B.last_name) as TO_name, B.profile_image_url as To_image from messenger "
						+ " inner join user as A on A.id = messenger.FROM_ID "
						+ " inner join user as B on B.id = messenger.TO_ID where messenger.conversation_id = "+conversationId+"  and messenger.owner_id="+ownerId+" and DELETE_FLAG = 0  order by id" ;
		List<MessagesById> messages = jdbcTemplate.query(query, new BeanPropertyRowMapper(MessagesById.class));
		return messages;
	}
	public List<MessagesById> getConversationBySubjectId( int ownerId,String subjectId){
		String query = "select messenger.*, concat(A.first_name ,' ',A.last_name) as From_Name, A.profile_image_url as From_image, concat(B.first_name ,' ',B.last_name) as TO_name, B.profile_image_url as To_image from messenger "
						+ " inner join user as A on A.id = messenger.FROM_ID "
						+ " inner join user as B on B.id = messenger.TO_ID where messenger.subject_id='"+subjectId+"' and owner_id = "+ownerId+" and DELETE_FLAG = 0  order by id" ;
		List<MessagesById> messages = jdbcTemplate.query(query, new BeanPropertyRowMapper(MessagesById.class));
		return messages;
	}	
	public List<InboxInfo> getInboxList(int id){
		
		String query="select m.from_id,m.to_id,concat(u1.first_name ,' ',u1.last_name) as From_Name , concat(u2.first_name ,' ',u2.last_name) as To_Name ,m.owner_id, m.subject, m.subject_id ,m.date_time,READ_COUNT.unread_cnt,"
					+ " u1.profile_image_url as From_image,u2.profile_image_url as To_image"
					 +" from messenger m"
					 +" inner join user u1 on m.from_id=u1.id"
					 +" inner join user u2 on m.to_id=u2.id"
					 +" left join (select subject_id, count(*) as unread_cnt from messenger  where read_flag=1 and owner_id="+id+" group by subject_id) as READ_COUNT"  
					 +" on READ_COUNT.subject_id=m.subject_id"
					 +" where m.subject!='' and  m.owner_id= "+id+""
					 +" order by date_time desc";
		
		List<InboxInfo> messages = jdbcTemplate.query(query, new BeanPropertyRowMapper(InboxInfo.class));
		return messages;
	}
	
	public List<MessagesById> getInbox(int id){
		String query = "select M1.*,M5.conversation_id as con,CASE WHEN M5.read_Count  is NULL THEN 0 ELSE M5.read_Count  END AS read_Count, "
				+ "concat(A.first_name ,' ',A.last_name) as From_Name,A.profile_image_url as From_image,B.profile_image_url as To_image, concat(B.first_name ,' ',B.last_name) as TO_name from messenger M1 "
						+" inner join user as A on A.id = M1.FROM_ID "
						+" inner join user as B on B.id = M1.TO_ID "
						+" left join (select conversation_id,count(M4.read_flag) as read_Count "
						+" from messenger M4 where M4.READ_FLAG = 0 and M4.OWNER_ID = "+id+"  and M4.DELETE_FLAG != 1 group by CONVERSATION_ID) M5 "
						+" on M5.conversation_id = M1.conversation_id "	
						+" inner join( "
						+" select max(M2.date_time) as M2Date from messenger M2  where DELETE_FLAG = 0  group by CONVERSATION_ID) M3 "
						+ " on M1.date_time = M3.M2Date where "
						+ " M1.OWNER_ID ="+id+" and M1.DELETE_FLAG = 0  order by id desc";
		List<MessagesById> messages = jdbcTemplate.query(query, new BeanPropertyRowMapper(MessagesById.class));
		return messages;
	}
	
	public List<ReadCount> getReadCount(int fromId){
		String query = "select messenger.to_id, count(messenger.READ_FLAG) as read_count from messenger " 
						+" where DELETE_FLAG!=1 and READ_FLAG = 1 and OWNER_ID = "+fromId+" group by CONVERSATION_ID order by id desc";
		List<ReadCount> readCount = jdbcTemplate.query(query, new BeanPropertyRowMapper(ReadCount.class));
		return readCount;
	}
	
	public void updateRead(String subjectId, int ownerId){
		String query = "update messenger set READ_FLAG = 0 where owner_id = "+ownerId+" and subject_id ='"+subjectId+"'";
		System.out.println("Read update---->"+query);
		this.jdbcTemplate.update(query);
	}
	
	public void deleteMessenger(int conversationId, int ownerId){
		String query ="delete from messenger where conversation_id="+conversationId+" and owner_id ="+ownerId;
		this.jdbcTemplate.update(query);
	}
	public void deleteConversation(String subjectId, int ownerId){
		String query ="delete from messenger where subject_id='"+subjectId+"' and owner_id ="+ownerId;
		this.jdbcTemplate.update(query);
	}
	public UnReadCount getCount(int ownerId){
		String query = "select count(read_flag) as read_count from messenger where delete_flag!=1 and read_flag = 1 and review_id=0 and owner_id="+ownerId+" and to_id="+ownerId;
		List<UnReadCount> read =  jdbcTemplate.query(query, new BeanPropertyRowMapper(UnReadCount.class));
		return read.get(0);
		//return read;
	}
	public long getNotificationCount(int userId){
		String query = "select count(read_flag) as read_count from messenger where read_flag = 1  and review_id=0 and to_id="+userId;
		long cnt = jdbcTemplate.queryForObject(query, Long.class);
		return cnt;
	}
	public Messenger getConversation(int ownerId){
		String query = "select * from messenger where OWNER_ID="+ownerId+" order by id desc limit 0,1";
		Messenger messenger = (Messenger) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(Messenger.class));
		return messenger;
	}
	public List<MessageEmailContent> getMessageEmail(String date){
		String query ="select  user.email,user.FIRST_NAME,M5.read_count from messenger inner join user on user.id = messenger.TO_ID "
						+"left join (select conversation_id,count(M4.read_flag) as read_Count "
						+"from messenger M4 where M4.READ_FLAG = 1  group by M4.TO_ID) M5 "
						 +"on M5.conversation_id = messenger.conversation_id "
						 +"where  messenger.DATE_TIME >='"+date+"' and messenger.delete_flag != 1 and messenger.READ_FLAG = 1 group by messenger.TO_ID";	
		System.out.println("Chat Email Query:-"+query);
		List<MessageEmailContent> messageEmail = jdbcTemplate.query(query, new BeanPropertyRowMapper(MessageEmailContent.class));
		return messageEmail;
	}
}
