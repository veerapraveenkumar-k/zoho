package com.models;

public class ApiInstance {
	private int instanceId;
	private String url;
	private String token;
	
	//getters 
	public int getId() {
		return instanceId;
	}
	
	public  String getUrl() {
		return url;
	}
	
	public  String getToken() {
		return token;
	}
	
	//setters
	
	public void setId(int id) {
		this.instanceId = id;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
