package com.dao;

import java.util.*;
import com.models.*;
import com.utils.Db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
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
	
	public static List<ApiUsers> getApiUsersList(String url) throws IOException {
		//@SuppressWarnings("deprecation")
		URL apiUrl = new URL(url);
		HttpURLConnection con = (HttpURLConnection) apiUrl.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while((inputLine = in.readLine())!= null) { 
			response.append(inputLine);
		}
		in.close();
		String content = response.toString();
		Gson gson = new Gson();
		Type usersListType = new TypeToken<List<ApiUsers>>(){}.getType();
		List<ApiUsers> usersList = gson.fromJson(content, usersListType);
		return usersList;	
	}
	
	public static boolean importApiUsers(List<ApiUsers> usersList, int id) {
		try {
			Connection db = Db.getConnection();
			String query1 = "INSERT INTO all_users (username, password, role) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id), password = VALUES(password), role = VALUES(role)";
			String query2 = "INSERT INTO user_profile (user_id, mobile_no, email) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE mobile_no = VALUES(mobile_no), email = VALUES(email)";
			String query3 = "INSERT INTO iam_user (user_id, salary) VALUES (?, ?) ON DUPLICATE KEY UPDATE salary = VALUES(salary)";
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
				ps3.setString(2, user.getSalary());
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
	
}
