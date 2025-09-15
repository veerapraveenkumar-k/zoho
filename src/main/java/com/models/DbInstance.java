package com.models;

public class DbInstance {
	private int id;
	private String host;
	private String port;
	private String dbName;
	private String tableName;
	private String user;
	private String pass;
	
	
	//getters
	
	public int getId() {
		return id;
	}
	
	public String getHost() {
		return host;
	}
	
	public String getPort() {
		return port;
	}
	
	public String getDbName() {
		return dbName;
	}
	
	public String getTable() {
		return tableName;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPass() {
		return pass;
	}
	
	//setters
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public void setDbName(String db) {
		this.dbName = db;
	}
	
	public void setTablename(String table) {
		this.tableName = table;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
}
