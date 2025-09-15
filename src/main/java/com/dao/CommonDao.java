package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.models.Common;
import com.utils.Db;

public class CommonDao {
	public static Common authentication(String name, String pass) {
		Common user = new Common();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM all_users WHERE username = ? AND password = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2,  pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean chaeckUserPresent(String name) {
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM all_users WHERE username = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
