package com.models;

public class User {
	private int id;
	private String username;
	private String password;
	private String role;
	private String mobileNum;
	private String email;
	private String source;
	private int adminId;
	private int instanceId;
	//setters
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUserName(String userName) {
		this.username = userName;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}
	
	//getters
	
	public int getId() {
		return id;
	}
	
	public int getAdminId() {
		return adminId;
	}
	
	public int getInstanceId() {
		return instanceId;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getMobileNum() {
		return mobileNum;
	}
	
	public String getSource() {
		return source;
	}
}
