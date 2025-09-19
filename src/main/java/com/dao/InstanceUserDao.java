package com.dao;

import java.util.*;
import com.models.*;
import com.utils.Db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
	
	public static boolean deleteDbUsersFromSource(DbInstance dbInstanceObj, String userName) {
		String url = "jdbc:mysql://" + dbInstanceObj.getHost() + ":" + dbInstanceObj.getPort() + "/" + dbInstanceObj.getDbName();
		String user = dbInstanceObj.getUser();
		String password = dbInstanceObj.getPass();
		String tableName = dbInstanceObj.getTable();
		try {
			Connection db = Db.createAndGetConnection(url, user, password);
			String query = "DELETE FROM " + tableName + " WHERE username = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, userName);
			int row = ps.executeUpdate();
			if(row > 0) {
				return true;
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void deleteUserFromJsonFile(JsonInstance jsonInstanceObj, String name) throws IOException{
		Gson gson = new Gson();
		Type listType = new TypeToken<List<JsonUser>>(){}.getType();
		FileReader reader = new FileReader(jsonInstanceObj.getFileName());
		List<JsonUser> users = gson.fromJson(reader, listType);
		reader.close();
		
		Iterator<JsonUser> iterator = users.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getUserName().equals(name)) {
				iterator.remove();
			}
		}
		
		FileWriter writer = new FileWriter(jsonInstanceObj.getFileName());
		gson.toJson(users, writer);
		writer.close();
	}
	
	public static void deleteUserFromOktaApi(ApiInstance apiInstanceObj, String name) throws IOException {
		URL apiUrl = new URL(apiInstanceObj.getUrl() + "/api/v1/users?q=" + name);
		HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "SSWS " + apiInstanceObj.getToken());
		conn.setRequestProperty("Accept", "application/json");
		int responseCode = conn.getResponseCode();
		if(responseCode != 200) {
			System.out.println("failed");
			return;
		}
		
		//parsing
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		
		JSONArray users = new JSONArray(sb.toString());
		JSONObject user = users.getJSONObject(0);
		String id = user.optString("id");
		
		//delete user
		
		URL delteApiUrl = new URL(apiInstanceObj.getUrl() + "/api/v1/users/" + id);
		HttpURLConnection conn1 = (HttpURLConnection) delteApiUrl.openConnection();
		conn1.setRequestMethod("DELETE");
		conn1.setRequestProperty("Authorization", "SSWS " + apiInstanceObj.getToken());
		conn1.setRequestProperty("Accept", "application/json");
		int delteResponseCode = conn1.getResponseCode();
		if(delteResponseCode >= 200 && delteResponseCode < 300) {
			System.out.println("Api Users Deleted");
		}
	}
	
	public static boolean updateDbUserInSource(String name, String pass, String email, String mobileNo, DbInstance dbInstanceObj) {
		String url = "jdbc:mysql://" + dbInstanceObj.getHost() + ":" + dbInstanceObj.getPort() + "/" + dbInstanceObj.getDbName();
		String user = dbInstanceObj.getUser();
		String password = dbInstanceObj.getPass();
		String tableName = dbInstanceObj.getTable();
		try {
			Connection db = Db.createAndGetConnection(url, user, password);
			String query = "UPDATE " + tableName + " SET password = ?, email = ?, mobile_no = ? WHERE username = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pass);
			ps.setString(2, email);
			ps.setString(3, mobileNo);
			ps.setString(4, name);
			int row = ps.executeUpdate();
			if(row > 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void updateUserInJsonFile(String pass, String email, String mobileNum, JsonInstance jsonInstanceObj, String name) throws IOException{
		Gson gson = new Gson();
		Type listType = new TypeToken<List<JsonUser>>(){}.getType();
		FileReader reader = new FileReader(jsonInstanceObj.getFileName());
		List<JsonUser> users = gson.fromJson(reader, listType);
		reader.close();
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUserName().equals(name)) {
				users.get(i).setPassword(pass);
				users.get(i).setEmail(email);
				users.get(i).setMobileNum(mobileNum);
				break;
			}
		}
		
		FileWriter writer = new FileWriter(jsonInstanceObj.getFileName());
		gson.toJson(users, writer);
		writer.close();
	}
	
	public static void updateUsersInApiSource(String name, String email, String mobileNum, ApiInstance apiInstanceObj) {
		
	}
	
}
