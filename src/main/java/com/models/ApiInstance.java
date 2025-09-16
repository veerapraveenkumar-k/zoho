package com.models;

public class ApiInstance {
	private int instanceId;
	private String url;
	
	//getters 
	public int getId() {
		return instanceId;
	}
	
	public  String getUrl() {
		return url;
	}
	
	//setters
	
	public void setId(int id) {
		this.instanceId = id;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
