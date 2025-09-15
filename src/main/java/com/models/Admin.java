package com.models;

public class Admin {
	private int id;
	private String username;
	private String password;
	private String role;
	private boolean dbAccess;
	private boolean jsonAccess;
	private boolean iamAccess;
	
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
	
	public void setDbAccess(boolean dbAccess) {
		this.dbAccess =  dbAccess;
	}
	
	public void setJsonAccess(boolean jsonAccess) {
		this.jsonAccess =  jsonAccess;
	}
	
	public void setIamAccess(boolean iamAccess) {
		this.iamAccess =  iamAccess;
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
	
	public boolean getDbAccess() {
		return dbAccess;
	}
	
	public boolean getJsonAccess() {
		return jsonAccess;
	}
	
	public boolean getIamAccess() {
		return iamAccess;
	}
}
