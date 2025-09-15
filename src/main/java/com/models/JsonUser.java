package com.models;

public class JsonUser {
	private String username;
	private String password;
	private String mobileNum;
	private String email;
	private String address;
	
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
		
		public void setAddress(String address) {
			this.address = address;
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
		
		public String getAddress() {
			return address;
		}
}
