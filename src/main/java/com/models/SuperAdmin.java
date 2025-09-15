package com.models;

public class SuperAdmin {
	private int id;
	private String username;
	private String password;
	private String role;
	private String mobileNum;
	private String email;
	
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
		
		//getters
		
		public int getId() {
			return id;
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
}
