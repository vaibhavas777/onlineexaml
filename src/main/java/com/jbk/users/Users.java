package com.jbk.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Users {

@Id
String username;

String dateofbirth;

String mobilenumber;

String emailid,password;

public Users() {
	super();
	// TODO Auto-generated constructor stub
}

public Users(String username, String mobileNumber, String emailid, String dateofbirth, String password) {
	super();
	this.username = username;
	this.mobilenumber = mobileNumber;
	this.emailid = emailid;
	this.dateofbirth = dateofbirth;
	this.password = password;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getDateofbirth() {
	return dateofbirth;
}

public void setDateofbirth(String dateofbirth) {
	this.dateofbirth = dateofbirth;
}

public String getMobilenumber() {
	return mobilenumber;
}

public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
}

public String getEmailid() {
	return emailid;
}

public void setEmailid(String emailid) {
	this.emailid = emailid;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public String toString() {
	return "Users [username=" + username + ", dateofbirth=" + dateofbirth + ", mobilenumber=" + mobilenumber
			+ ", emailid=" + emailid + ", password=" + password + "]";
}


}
