package com.skyrate.model.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skyrate.clib.util.JSONUtil;

public class InboxMessages {
	transient LinkedHashMap<UserKey,ArrayList<InboxItem>> inboxMessage = new LinkedHashMap<UserKey,ArrayList<InboxItem>>();
	ArrayList<UserInbox> userInbox = new ArrayList<UserInbox>();
	
	public void transform(){
		for (Map.Entry<UserKey,ArrayList<InboxItem>> entry : inboxMessage.entrySet()) {
		    UserKey key = entry.getKey();
		    ArrayList<InboxItem> value = entry.getValue();
		    UserInbox userInboxItem = new UserInbox();
		    userInboxItem.setUserId(key.getUserId());
		    userInboxItem.setUserName(key.getUserName());
		    userInboxItem.setUserProfileImage(key.getUserProfileImage());
		    userInboxItem.setInboxItems(value);
		    // now work with key and value...
		    userInbox.add(userInboxItem);
		}
	}
	
	public ArrayList<UserInbox> getUserInbox() {
		return userInbox;
	}

	public void setUserInbox(ArrayList<UserInbox> userInbox) {
		this.userInbox = userInbox;
	}

	public void addMessage(InboxInfo info){
		UserKey key= new UserKey();
		
		
		if(info.getOwnerId()==info.getToId()){
			key.setUserId(info.getFromId());
			key.setUserName(info.getFromName());
			key.setUserProfileImage(info.getFromImage());
		}else{
			key.setUserId(info.getToId());
			key.setUserName(info.getToName());
			key.setUserProfileImage(info.getToImage());
		}
		InboxItem inboxItem = new InboxItem();
		inboxItem.setSubject(info.getSubject().trim());
		inboxItem.setSubjectId(info.getSubjectId());
		inboxItem.setDateTime(info.getDateTime());
		inboxItem.setUnreadCnt(info.getUnreadCnt());
		
		ArrayList<InboxItem> inboxSubjectList= inboxMessage.get(key);
		if(inboxSubjectList==null){
			inboxSubjectList = new ArrayList<InboxItem>();
		}
		inboxSubjectList.add(inboxItem);
		inboxMessage.put(key, inboxSubjectList);
		
	}
	public static InboxMessages createInboxList(List<InboxInfo> inboxInfoList){
		InboxMessages inboxMessages= new InboxMessages();
		for(InboxInfo inboxInfo: inboxInfoList){
			inboxMessages.addMessage(inboxInfo);
		}
		inboxMessages.transform();
		return inboxMessages;
	}


	
	
}
class UserInbox{
	long userId;
	String userName;
	String userProfileImage;
	ArrayList<InboxItem> inboxItems= new ArrayList<InboxItem>();


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

   public void addInboxItem(InboxItem item){
	   inboxItems.add(item);
   }

	public ArrayList<InboxItem> getInboxItems() {
		return inboxItems;
	}

	public void setInboxItems(ArrayList<InboxItem> inboxItems) {
		this.inboxItems = inboxItems;
	}

	
	
}
class UserKey{
	long userId;
	String userName;
	String userProfileImage;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserProfileImage() {
		return userProfileImage;
	}
	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}
	
	@Override
    public boolean equals(Object o) {
		
		 if(o == null)
		    {
		        return false;
		    }
		    if (o == this)
		    {
		        return true;
		    }
		    if (getClass() != o.getClass())
		    {
		        return false;
		    }
		     
		    UserKey keyObj = (UserKey)o;
		    return (this.getUserId() == keyObj.getUserId());
		
	}
	@Override
	public int hashCode()
	{
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + Integer.parseInt(String.valueOf(this.getUserId()));
	    return result;
	}
	public String toString(){
		return JSONUtil.toJson(this);
	}
	
}
class Inbox2{
	String name;
	String profileImage;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
}
class InboxItem{
	String subjectId;
	String subject;
	//@JsonFormat(pattern = "MMM dd HH:mm a")
	Date dateTime;
	Integer unreadCnt;
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	
}
