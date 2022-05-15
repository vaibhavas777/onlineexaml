package com.jbk.users;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Score {
    @Id
	String username;
	String subject;
	int marks;
	
	public Score() {
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Score [username=" + username + ", subject=" + subject + ", marks=" + marks + "]";
	}
		
}
