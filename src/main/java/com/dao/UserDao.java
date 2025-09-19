package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

import com.utils.Db;
import com.models.*;

public class UserDao {
	public static User getProfile(String name, String tableName) {
		User user = new User();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT all_users.id, all_users.username, all_users.password, " + tableName + ".email , "+tableName + ".mobile_no from all_users join " + tableName + " on all_users.id = " + tableName + ".user_id where all_users.username = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
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
		return usersList;
	}
	
	public static String deleteUsers(int id) {
			try {
				Connection db = Db.getConnection();
				String query = "DELETE FROM all_users WHERE id = ?";
				PreparedStatement ps = db.prepareStatement(query);
				ps.setInt(1, id);
				int row = ps.executeUpdate();
				if(row > 0) {
					return "User Successfully Deleted.";
				}
				return "Failed to delete User.";
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "Failed to delete User.";
	}
	
	public static ArrayList<Admin> getAdminsList() {
		ArrayList<Admin> usersList = new ArrayList<>();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT all_users.id, username, db_access, json_access, iam_access from all_users join admin_access on all_users.id = admin_access.admin_id;";
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Admin user = new Admin();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setDbAccess(rs.getBoolean("db_access"));
				user.setJsonAccess(rs.getBoolean("json_access"));
				user.setIamAccess(rs.getBoolean("iam_access"));
				usersList.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	public static boolean deleteUsersByName(HashSet<String> usersNameSet) {
		try {
			Connection db = Db.getConnection();
			String query = "DELETE FROM all_users WHERE username = ?";
			PreparedStatement ps = db.prepareStatement(query);
			for(String name: usersNameSet) {
				ps.setString(1, name);
				ps.executeUpdate();
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	} 
	
	public static String getUserNameById(int userId) {
		try {
			Connection db = Db.getConnection();
			String query = "SELECT username FROM all_users WHERE id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("username");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
