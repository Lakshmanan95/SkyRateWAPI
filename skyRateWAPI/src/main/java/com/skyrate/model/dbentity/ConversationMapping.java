package com.skyrate.model.dbentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ConversationMapping {

	@Id
	@GeneratedValue
	int id;
	int messenger1;
	int messenger2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessenger1() {
		return messenger1;
	}
	public void setMessenger1(int messenger1) {
		this.messenger1 = messenger1;
	}
	public int getMessenger2() {
		return messenger2;
	}
	public void setMessenger2(int messenger2) {
		this.messenger2 = messenger2;
	}
	
	
}
