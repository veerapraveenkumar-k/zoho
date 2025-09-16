package com.models;

public class Instance {
	private int id;
	private String type;
	private int adminId;
	
	//setters
	
		public void setId(int id) {
			this.id = id;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
		public void setAdminId(int adminId) {
			this.adminId = adminId;
		}
	
	//getters
	
	public int getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public int getAdminId() {
		return adminId;
	}

	
}
