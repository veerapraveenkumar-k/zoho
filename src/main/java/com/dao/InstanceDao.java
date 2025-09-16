package com.dao;

import com.utils.Db;
import java.sql.*;
import java.util.*;

import com.models.ApiInstance;
import com.models.DbInstance;
import com.models.Instance;
import com.models.JsonInstance;

public class InstanceDao {
	public static String checkDbInstance(int adminId, String host, String port, String dbname, String tableName, String user, String password) {
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM db_instance WHERE host = ? AND port = ? AND db_name = ? AND table_name = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, host);
			ps.setString(2, port);
			ps.setString(3, dbname);
			ps.setString(4, tableName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return "Instance already present.";
			}else {
				//System.out.println("called");
				String insertQuery1 = "INSERT INTO instance (type, admin_id) VALUES (?, ?)";
				PreparedStatement instancePs = db.prepareStatement(insertQuery1, Statement.RETURN_GENERATED_KEYS);
				instancePs.setString(1, "DB");
				instancePs.setInt(2, adminId);
				int row = instancePs.executeUpdate();
				if(row > 0) {
					ResultSet instanceRs = instancePs.getGeneratedKeys();
					if(instanceRs.next()) {
						int instanceId = instanceRs.getInt(1);
						return createDbInstance(instanceId, host, port, dbname, tableName, user, password);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Failed to Create Instance";
	}
	
	public static String createDbInstance(int instanceId, String host, String port, String dbname, String tableName, String user, String password) {
		try {
			Connection db = Db.getConnection();
			String query = "INSERT INTO db_instance (instance_id, host, port, db_name, table_name, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, instanceId);
			ps.setString(2, host);
			ps.setString(3, port);
			ps.setString(4, dbname);
			ps.setString(5, tableName);
			ps.setString(6, user);
			ps.setString(7, password);
			int row = ps.executeUpdate();
			if(row > 0) {
				return "Instance Success Fully Created";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Failed to Create Instance";
	}
	
	public static ArrayList<Instance> getInstanceList(int id) {
		ArrayList<Instance> instanceList = new ArrayList<>();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM instance WHERE admin_id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Instance instance = new Instance();
				instance.setId(rs.getInt("id"));
				instance.setType(rs.getString("type"));
				instance.setAdminId(rs.getInt("admin_id"));
				instanceList.add(instance);
			}
			return instanceList;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return instanceList;
	}
	
	public static Instance getInstance(int id) {
		Instance instance = new Instance();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM instance WHERE id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				instance.setAdminId(rs.getInt("admin_id"));
				instance.setId(id);
				instance.setType(rs.getString("type"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public static DbInstance getDbInstanceDetails(int id) {
		DbInstance dbInstanceObj = new DbInstance();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM db_instance WHERE instance_id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dbInstanceObj.setHost(rs.getString("host"));
				dbInstanceObj.setPort(rs.getString("port"));
				dbInstanceObj.setDbName(rs.getString("db_name"));
				dbInstanceObj.setTablename(rs.getString("table_name"));
				dbInstanceObj.setUser(rs.getString("username"));
				dbInstanceObj.setPass(rs.getString("password"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dbInstanceObj;
	}
	
	public static String checkJsonInstance(String fileName, int adminId) {
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM json_instance WHERE file_name = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, fileName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return "instance already present";
			}
			return createJsonInstance(fileName, adminId);
		}catch(Exception e) {
			e.printStackTrace();
			return "failed to create instance";
		}
	}
	
	public static String createJsonInstance(String fileName, int adminId) {
		try {
			Connection db = Db.getConnection();
			String query = "INSERT INTO instance (type, admin_id) VALUES (?, ?)";
			String query1 = "INSERT INTO json_instance (instance_id, file_name) VALUES (?, ?)";
			PreparedStatement ps = db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "JSON");
			ps.setInt(2, adminId);
			int row = ps.executeUpdate();
			if(row > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int instanceId = rs.getInt(1);
					//System.out.println(instanceId);
					PreparedStatement ps1 = db.prepareStatement(query1);
					ps1.setInt(1, instanceId);
					ps1.setString(2, fileName);
					int row1 = ps1.executeUpdate();
					if(row1 > 0) {
						return "Instance Created Successfully";
					}
				}
			}
			return "failed to create instance";
		}catch(Exception e) {
			e.printStackTrace();
			return "failed to create instance";
		}
	}
	
	public static JsonInstance getJsonInstanceDetails(int id) {
		JsonInstance jsonInstanceObj = new JsonInstance();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM json_instance WHERE instance_id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				jsonInstanceObj.setId(rs.getInt("instance_id"));
				jsonInstanceObj.setFileName(rs.getString("file_name"));
			}
			return jsonInstanceObj;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jsonInstanceObj;
	}
	
	public static String deleteInstance(int instanceId) {
		try {
			Connection db = Db.getConnection();
			String query = "DELETE u, uid, instance FROM all_users as u RIGHT JOIN user_instance_details as uid ON u.id = uid.user_id RIGHT JOIN instance ON uid.instance_id = instance.id WHERE instance.id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, instanceId);
			int row = ps.executeUpdate();
			if(row > 0) {
				return "Instance Successfully Deleted.";
			}
			return "Failed to delete Instance.";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "Failed to delete Instance.";
	}
	
	public static String checkApiInstance(String apiKey, String url, int adminId) {
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM api_instance WHERE url = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, url);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return "instance already present";
			}
			return createApiInstance(apiKey, url, adminId);
		}catch(Exception e) {
			e.printStackTrace();
			return "failed to create instance";
		}
	}
	
	public static String createApiInstance(String token, String url, int adminId) {
		try {
			Connection db = Db.getConnection();
			String query = "INSERT INTO instance (type, admin_id) VALUES (?, ?)";
			String query1 = "INSERT INTO api_instance (instance_id, url, api_token) VALUES (?, ?, ?)";
			PreparedStatement ps = db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "API");
			ps.setInt(2, adminId);
			int row = ps.executeUpdate();
			if(row > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int instanceId = rs.getInt(1);
					//System.out.println(instanceId);
					PreparedStatement ps1 = db.prepareStatement(query1);
					ps1.setInt(1, instanceId);
					ps1.setString(2, url);
					ps1.setString(3, token);
					int row1 = ps1.executeUpdate();
					if(row1 > 0) {
						return "Instance Created Successfully";
					}
				}
			}
			return "failed to create instance";
		}catch(Exception e) {
			e.printStackTrace();
			return "failed to create instance";
		}
	}
	
	public static ApiInstance getApiInstanceDetails(int id) {
		ApiInstance apiInstanceObj = new ApiInstance();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT * FROM api_instance WHERE instance_id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				apiInstanceObj.setId(rs.getInt("instance_id"));
				apiInstanceObj.setUrl(rs.getString("url"));
				apiInstanceObj.setToken(rs.getString("api_token"));
			}
			return apiInstanceObj;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return apiInstanceObj;
	}
	
}
