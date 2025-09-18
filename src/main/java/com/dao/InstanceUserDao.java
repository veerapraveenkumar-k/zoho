package com.dao;

import java.util.*;
import com.models.*;
import com.utils.Db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class InstanceUserDao {
	public static ArrayList<DbUsers> getDbUsersList(DbInstance dbInstanceObj){
		ArrayList<DbUsers> dbusersList = new ArrayList<>();
		String host = dbInstanceObj.getHost();
		String port = dbInstanceObj.getPort();
		String dbName = dbInstanceObj.getDbName();
		String tableName = dbInstanceObj.getTable();
		String user = dbInstanceObj.getUser();
		String pass = dbInstanceObj.getPass();
		try {			
			String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
			Connection db = Db.createAndGetConnection(url, user, pass);
			String query = "SELECT * FROM " + tableName;
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DbUsers userObj = new DbUsers();
				userObj.setUserName(rs.getString("username"));
				userObj.setPassword(rs.getString("password"));
				userObj.setMobileNum(rs.getString("mobile_no"));
				userObj.setEmail(rs.getString("email"));
				userObj.setDepartment(rs.getString("department"));
				dbusersList.add(userObj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dbusersList;
	}
	
	public static boolean importDbUsers(ArrayList<DbUsers> dbUsersList, int instanceId) {
		try {
			Connection db = Db.getConnection();
			String query1 = "INSERT INTO all_users (username, password, role) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id), password = VALUES(password), role = VALUES(role)";
			String query2 = "INSERT INTO user_profile (user_id, mobile_no, email) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE mobile_no = VALUES(mobile_no), email = VALUES(email)";
			String query3 = "INSERT INTO db_user (user_id, department) VALUES (?, ?) ON DUPLICATE KEY UPDATE department = VALUES(department)";
			String query4 = "INSERT IGNORE INTO user_instance_details (user_id, instance_id) VALUES (?, ?)";
			
			PreparedStatement ps1 = db.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = db.prepareStatement(query2);
			PreparedStatement ps3 = db.prepareStatement(query3);
			PreparedStatement ps4 = db.prepareStatement(query4);
			for(int i = 0; i < dbUsersList.size(); i++) {
				ps1.setString(1, dbUsersList.get(i).getUserName());
				ps1.setString(2, dbUsersList.get(i).getPassword());
				ps1.setString(3, "USER");
				ps1.executeUpdate();
				ResultSet rs = ps1.getGeneratedKeys();
				if(rs.next()) {
				int userId = rs.getInt(1);
				System.out.println(userId);
				ps2.setInt(1, userId);
				ps2.setString(2, dbUsersList.get(i).getMobileNum());
				ps2.setString(3, dbUsersList.get(i).getEmail());
				ps3.setInt(1, userId);
				ps3.setString(2, dbUsersList.get(i).geDepartment());
				ps4.setInt(1, userId);
				ps4.setInt(2, instanceId);
				ps2.executeUpdate();
				ps3.executeUpdate();
				ps4.executeUpdate();
				}
			}
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean insertDbUsers(List<DbUsers> dbUsersList, int instanceId) {
		try {
			Connection db = Db.getConnection();
			String query1 = "INSERT INTO all_users (username, password, role) VALUES (?, ?, ?)";
			String query2 = "INSERT INTO user_profile (user_id, mobile_no, email) VALUES (?, ?, ?)";
			String query3 = "INSERT INTO db_user (user_id, department) VALUES (?, ?)";
			String query4 = "INSERT INTO user_instance_details (user_id, instance_id) VALUES (?, ?)";
			
			PreparedStatement ps1 = db.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = db.prepareStatement(query2);
			PreparedStatement ps3 = db.prepareStatement(query3);
			PreparedStatement ps4 = db.prepareStatement(query4);
			for(int i = 0; i < dbUsersList.size(); i++) {
				ps1.setString(1, dbUsersList.get(i).getUserName());
				ps1.setString(2, dbUsersList.get(i).getPassword());
				ps1.setString(3, "USER");
				ps1.executeUpdate();
				ResultSet rs = ps1.getGeneratedKeys();
				if(rs.next()) {
				int userId = rs.getInt(1);
				System.out.println(userId);
				ps2.setInt(1, userId);
				ps2.setString(2, dbUsersList.get(i).getMobileNum());
				ps2.setString(3, dbUsersList.get(i).getEmail());
				ps3.setInt(1, userId);
				ps3.setString(2, dbUsersList.get(i).geDepartment());
				ps4.setInt(1, userId);
				ps4.setInt(2, instanceId);
				ps2.executeUpdate();
				ps3.executeUpdate();
				ps4.executeUpdate();
				}
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateDbUsers(List<DbUsers> dbUsersList) {
		try {
			Connection db = Db.getConnection();
			String query = "UPDATE all_users AS all_users JOIN user_profile ON all_users.id = user_profile.user_id JOIN db_user ON all_users.id = db_user.user_id SET all_users.password = ?, user_profile.mobile_no = ?, user_profile.email = ?, db_user.department = ? WHERE all_users.username = ? AND (all_users.password <> ? OR user_profile.mobile_no <> ? OR user_profile.email <> ? OR db_user.department <> ?)";
			PreparedStatement ps = db.prepareStatement(query);
			for(int i = 0; i < dbUsersList.size(); i++) {
				ps.setString(1, dbUsersList.get(i).getPassword());
				ps.setString(2, dbUsersList.get(i).getMobileNum());
				ps.setString(3, dbUsersList.get(i).getEmail());
				ps.setString(4, dbUsersList.get(i).geDepartment());
				ps.setString(5, dbUsersList.get(i).getUserName());
				ps.setString(6, dbUsersList.get(i).getPassword());
				ps.setString(7, dbUsersList.get(i).getMobileNum());
				ps.setString(8, dbUsersList.get(i).getEmail());
				ps.setString(9, dbUsersList.get(i).geDepartment());
				ps.executeUpdate();
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<JsonUser> getJsonUsersList(String fileName) throws FileNotFoundException, IOException{
		File file = new File(fileName);
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		}
		String jsonContent = sb.toString();
		Gson gson = new Gson();
		Type usersListType = new TypeToken<List<JsonUser>>(){}.getType();
		List<JsonUser> usersList = gson.fromJson(jsonContent, usersListType);
		return usersList;	
	}
	
	public static boolean importJsonUsers(List<JsonUser> usersList, int id) {
		try {
			Connection db = Db.getConnection();
			String query1 = "INSERT INTO all_users (username, password, role) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id), password = VALUES(password), role = VALUES(role)";
			String query2 = "INSERT INTO user_profile (user_id, mobile_no, email) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE mobile_no = VALUES(mobile_no), email = VALUES(email)";
			String query3 = "INSERT INTO json_user (user_id, address) VALUES (?, ?) ON DUPLICATE KEY UPDATE address = VALUES(address)";
			String query4 = "INSERT IGNORE INTO user_instance_details (user_id, instance_id) VALUES (?, ?)";
			PreparedStatement ps1 = db.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = db.prepareStatement(query2);
			PreparedStatement ps3 = db.prepareStatement(query3);
			PreparedStatement ps4 = db.prepareStatement(query4);
			for(JsonUser user: usersList) {
				//System.out.println(user.getMobileNum());
				ps1.setString(1, user.getUserName());
				ps1.setString(2, user.getPassword());
				ps1.setString(3, "USER");
				ps1.executeUpdate();
				ResultSet rs = ps1.getGeneratedKeys();
				if(rs.next()) {
				int userId = rs.getInt(1);
				//System.out.println(userId);
				ps2.setInt(1, userId);
				ps2.setString(2, user.getMobileNum());
				ps2.setString(3, user.getEmail());
				ps3.setInt(1, userId);
				ps3.setString(2, user.getAddress());
				ps4.setInt(1, userId);
				ps4.setInt(2, id);
				ps2.executeUpdate();
				ps3.executeUpdate();
				ps4.executeUpdate();
				}
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean importApiUsers(List<ApiUsers> usersList, int id) {
		try {
			Connection db = Db.getConnection();
			String query1 = "INSERT INTO all_users (username, password, role) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id), password = VALUES(password), role = VALUES(role)";
			String query2 = "INSERT INTO user_profile (user_id, mobile_no, email) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE mobile_no = VALUES(mobile_no), email = VALUES(email)";
			String query3 = "INSERT INTO iam_user (user_id, first_name, last_name) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE first_name = VALUES(first_name), last_name = VALUES(last_name)";
			String query4 = "INSERT IGNORE INTO user_instance_details (user_id, instance_id) VALUES (?, ?)";
			PreparedStatement ps1 = db.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = db.prepareStatement(query2);
			PreparedStatement ps3 = db.prepareStatement(query3);
			PreparedStatement ps4 = db.prepareStatement(query4);
			for(ApiUsers user: usersList) {
				//System.out.println(user.getMobileNum());
				ps1.setString(1, user.getUserName());
				ps1.setString(2, user.getPassword());
				ps1.setString(3, "USER");
				ps1.executeUpdate();
				ResultSet rs = ps1.getGeneratedKeys();
				if(rs.next()) {
				int userId = rs.getInt(1);
				//System.out.println(userId);
				ps2.setInt(1, userId);
				ps2.setString(2, user.getMobileNum());
				ps2.setString(3, user.getEmail());
				ps3.setInt(1, userId);
				ps3.setString(2, user.getFirstName());
				ps3.setString(3, user.getLastName());
				ps4.setInt(1, userId);
				ps4.setInt(2, id);
				ps2.executeUpdate();
				ps3.executeUpdate();
				ps4.executeUpdate();
				}
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static HashSet<String> getAllUsersNamebyInstanceId(int id){
		HashSet<String> users = new  HashSet<String>();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT username FROM all_users JOIN user_instance_details ON all_users.id = user_instance_details.user_id WHERE user_instance_details.instance_id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				users.add(rs.getString("username"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static boolean insertJsonUsers(List<JsonUser> jsonUsersList, int instanceId) {
		try {
			Connection db = Db.getConnection();
			String query1 = "INSERT INTO all_users (username, password, role) VALUES (?, ?, ?)";
			String query2 = "INSERT INTO user_profile (user_id, mobile_no, email) VALUES (?, ?, ?)";
			String query3 = "INSERT INTO json_user (user_id, address) VALUES (?, ?)";
			String query4 = "INSERT INTO user_instance_details (user_id, instance_id) VALUES (?, ?)";
			
			PreparedStatement ps1 = db.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = db.prepareStatement(query2);
			PreparedStatement ps3 = db.prepareStatement(query3);
			PreparedStatement ps4 = db.prepareStatement(query4);
			for(int i = 0; i < jsonUsersList.size(); i++) {
				ps1.setString(1, jsonUsersList.get(i).getUserName());
				ps1.setString(2, jsonUsersList.get(i).getPassword());
				ps1.setString(3, "USER");
				ps1.executeUpdate();
				ResultSet rs = ps1.getGeneratedKeys();
				if(rs.next()) {
				int userId = rs.getInt(1);
				System.out.println(userId);
				ps2.setInt(1, userId);
				ps2.setString(2, jsonUsersList.get(i).getMobileNum());
				ps2.setString(3, jsonUsersList.get(i).getEmail());
				ps3.setInt(1, userId);
				ps3.setString(2, jsonUsersList.get(i).getAddress());
				ps4.setInt(1, userId);
				ps4.setInt(2, instanceId);
				ps2.executeUpdate();
				ps3.executeUpdate();
				ps4.executeUpdate();
				}
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateJsonUsers(List<JsonUser> jsonUsersList) {
		try {
			Connection db = Db.getConnection();
			String query = "UPDATE all_users AS all_users JOIN user_profile ON all_users.id = user_profile.user_id JOIN json_user ON all_users.id = json_user.user_id SET all_users.password = ?, user_profile.mobile_no = ?, user_profile.email = ?, json_user.address = ? WHERE all_users.username = ? AND (all_users.password <> ? OR user_profile.mobile_no <> ? OR user_profile.email <> ? OR json_user.address <> ?)";
			PreparedStatement ps = db.prepareStatement(query);
			for(int i = 0; i < jsonUsersList.size(); i++) {
				ps.setString(1, jsonUsersList.get(i).getPassword());
				ps.setString(2, jsonUsersList.get(i).getMobileNum());
				ps.setString(3, jsonUsersList.get(i).getEmail());
				ps.setString(4, jsonUsersList.get(i).getAddress());
				ps.setString(5, jsonUsersList.get(i).getUserName());
				ps.setString(6, jsonUsersList.get(i).getPassword());
				ps.setString(7, jsonUsersList.get(i).getMobileNum());
				ps.setString(8, jsonUsersList.get(i).getEmail());
				ps.setString(9, jsonUsersList.get(i).getAddress());
				ps.executeUpdate();
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean insertApiUsers(List<ApiUsers> apiUsersList, int instanceId) {
		try {
			Connection db = Db.getConnection();
			String query1 = "INSERT INTO all_users (username, password, role) VALUES (?, ?, ?)";
			String query2 = "INSERT INTO user_profile (user_id, mobile_no, email) VALUES (?, ?, ?)";
			String query3 = "INSERT INTO iam_user (user_id, first_name, last_name) VALUES (?, ?, ?)";
			String query4 = "INSERT INTO user_instance_details (user_id, instance_id) VALUES (?, ?)";
			
			PreparedStatement ps1 = db.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = db.prepareStatement(query2);
			PreparedStatement ps3 = db.prepareStatement(query3);
			PreparedStatement ps4 = db.prepareStatement(query4);
			for(int i = 0; i < apiUsersList.size(); i++) {
				ps1.setString(1, apiUsersList.get(i).getUserName());
				ps1.setString(2, apiUsersList.get(i).getPassword());
				ps1.setString(3, "USER");
				ps1.executeUpdate();
				ResultSet rs = ps1.getGeneratedKeys();
				if(rs.next()) {
				int userId = rs.getInt(1);
				System.out.println(userId);
				ps2.setInt(1, userId);
				ps2.setString(2, apiUsersList.get(i).getMobileNum());
				ps2.setString(3, apiUsersList.get(i).getEmail());
				ps3.setInt(1, userId);
				ps3.setString(2, apiUsersList.get(i).getFirstName());
				ps3.setString(3, apiUsersList.get(i).getLastName());
				ps4.setInt(1, userId);
				ps4.setInt(2, instanceId);
				ps2.executeUpdate();
				ps3.executeUpdate();
				ps4.executeUpdate();
				}
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateApiUsers(List<ApiUsers> apiUsersList) {
		try {
			Connection db = Db.getConnection();
			String query = "UPDATE all_users AS all_users JOIN user_profile ON all_users.id = user_profile.user_id JOIN iam_user ON all_users.id = iam_user.user_id SET all_users.password = ?, user_profile.mobile_no = ?, user_profile.email = ?, iam_user.first_name = ?, iam_user.last_name = ? WHERE all_users.username = ? AND (all_users.password <> ? OR user_profile.mobile_no <> ? OR user_profile.email <> ? OR iam_user.first_name <> ? OR iam_user.last_name <> ?)";
			PreparedStatement ps = db.prepareStatement(query);
			for(int i = 0; i <  apiUsersList.size(); i++) {
				ps.setString(1, apiUsersList.get(i).getPassword());
				ps.setString(2, apiUsersList.get(i).getMobileNum());
				ps.setString(3, apiUsersList.get(i).getEmail());
				ps.setString(4, apiUsersList.get(i).getFirstName());
				ps.setString(5, apiUsersList.get(i).getLastName());
				ps.setString(6, apiUsersList.get(i).getUserName());
				ps.setString(7, apiUsersList.get(i).getPassword());
				ps.setString(8, apiUsersList.get(i).getMobileNum());
				ps.setString(9, apiUsersList.get(i).getEmail());
				ps.setString(10, apiUsersList.get(i).getFirstName());
				ps.setString(11, apiUsersList.get(i).getLastName());
				ps.executeUpdate();
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
