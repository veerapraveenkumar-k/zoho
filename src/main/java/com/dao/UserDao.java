package com.dao;

import java.sql.*;
import java.util.ArrayList;

import com.utils.Db;
import com.models.*;

public class UserDao {
	public static User getProfile(String name, String tableName) {
		User user = new User();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT all_users.id, all_users.username, all_users.password, " + tableName + ".email , "+tableName + ".mobile_no from all_users join " + tableName + " on all_users.id = " + tableName + ".user_id where all_users.username = ?";
			//System.out.println(query);
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//System.out.println("called");
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setMobileNum(rs.getString("mobile_no"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean updateUserProfile(int id, String pass, String mobile, String email, String table) {
		try {
			Connection db = Db.getConnection();
			String query = "UPDATE all_users SET password = ? WHERE id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1,  pass);
			ps.setInt(2, id);
			int row1 = ps.executeUpdate();
			String query2 = "UPDATE " + table + " SET mobile_no = ?, email = ? WHERE user_id = ?";
			PreparedStatement ps2 = db.prepareStatement(query2);
			ps2.setString(1, mobile);
			ps2.setString(2, email);
			ps2.setInt(3, id);
			int row2 = ps2.executeUpdate();
			if(row1 > 0 && row2 > 0) {
				return true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<User> getUsersList() {
		ArrayList<User> usersList = new ArrayList<>();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT all_users.id, username, instance_id, admin_id, type FROM all_users join user_instance_details on all_users.id = user_instance_details.user_id join instance on user_instance_details.instance_id = instance.id";
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setInstanceId(rs.getInt("instance_id"));
				user.setAdminId(rs.getInt("admin_id"));
				user.setSource(rs.getString("type"));
				usersList.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println(usersList.size());
		return usersList;
	}
}
