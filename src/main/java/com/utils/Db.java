package com.utils;

import java.sql.*;

public class Db {
	private static final String dbUrl = "jdbc:mysql://localhost:3306/zoho";
	private static final String user = "root";
	private static final String password = "root";
	
	public static Connection db;
		
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection(dbUrl, user, password);
		} catch(Exception e) {
			System.out.println("Db connection Error");
			e.printStackTrace();
		}
		return db;
	}
	
	public static boolean checkConnection(String url, String name, String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection(url, name, pass);
			return true;
		} catch(Exception e) {
			System.out.println("Db connection Error");
			e.printStackTrace();
		}
		return false;
	}
	
	public static Connection createAndGetConnection(String url, String user, String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection(url, user, pass);	
		} catch(Exception e) {
			System.out.println("Db connection Error");
			e.printStackTrace();
		}
		return db;
	}
}
