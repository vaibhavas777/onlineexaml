package com.jbk.users;

public class Mail {

	String to;
	String subject;
	String msg;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Mail [to=" + to + ", subject=" + subject + ", msg=" + msg + "]";
	}
	
	
	
}
