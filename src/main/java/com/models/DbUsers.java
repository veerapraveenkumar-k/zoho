package com.models;

public class DbUsers {
	private String username;
	private String password;
	private String mobileNum;
	private String email;
	private String department;
	
	//setters
	
	public void setUserName(String userName) {
		this.username = userName;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setMobileNum(String mobile) {
		this.mobileNum = mobile;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	//getters
	
	public String getUserName() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getMobileNum() {
		return mobileNum;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String geDepartment() {
		return department;
	}
}
