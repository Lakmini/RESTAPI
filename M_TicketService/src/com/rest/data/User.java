package com.rest.data;

public class User {
	private String phone_num;
	private String password;
	private String email;
	private String regId;
	
	
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getPhoneNumber() {
		return phone_num;
	}
	public void setPhoneNumber(String Phone_num) {
		this.phone_num = Phone_num;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
