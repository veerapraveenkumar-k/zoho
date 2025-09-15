package com.models;

public class Common {
	private int id;
	private String username;
	private String password;
	private String role;
	
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
}
