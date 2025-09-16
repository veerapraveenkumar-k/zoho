package com.models;

public class ApiUsers {
	private String username;
	private String password;
	private String mobileNum;
	private String email;
	private String firstName;
	private String lastName;
	
	//setters
	
		public void setUserName(String userName) {
			this.username = userName;
		}
		
		public void setPassword(String name) {
			this.password = name + "@123";
		}
		
		public void setMobileNum(String mobile) {
			this.mobileNum = mobile;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
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
		
		public String getFirstName() {
			return firstName;
		}
		
		public String getLastName() {
			return lastName;
		}
}
