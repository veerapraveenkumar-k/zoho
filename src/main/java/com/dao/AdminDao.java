package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.models.Admin;
import com.utils.Db;

public class AdminDao {
	public static boolean createAdmin(String name, String pass, boolean dbAccess, boolean jsonAccess, boolean iamAccess) {
		try {
			Connection db = Db.getConnection();
			String query = "INSERT INTO all_users (username, password, role) VALUES (?, ?, ?)";
			PreparedStatement ps = db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setString(3, "ADMIN");
			int row = ps.executeUpdate();
			if(row > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int adminId = rs.getInt(1);
					try {
						String query1 = "INSERT INTO admin_access (admin_id, db_access, json_access, iam_access) VALUES (?, ?, ?, ?)";
						String query2 = "INSERT INTO admin_profile (user_id) VALUES (?)";
						PreparedStatement ps1 = db.prepareStatement(query1);
						PreparedStatement ps2 = db.prepareStatement(query2);
						ps1.setInt(1, adminId);
						ps1.setBoolean(2, dbAccess);
						ps1.setBoolean(3, jsonAccess);
						ps1.setBoolean(4, iamAccess);
						ps2.setInt(1, adminId);
						int row1 = ps1.executeUpdate();
						int row2 = ps2.executeUpdate();
						if(row1 > 0 && row2 > 0) {
							return true;
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Admin getAdminAccess(int id) {
		Admin admin = new Admin();
		try {
			Connection db = Db.getConnection();
			String query = "SELECT db_access, json_access, iam_access FROM admin_access WHERE admin_id = ?";
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//System.out.println(rs.getBoolean("iam_access"));
				admin.setDbAccess(rs.getBoolean("db_access"));
				admin.setJsonAccess(rs.getBoolean("json_access"));
				admin.setIamAccess(rs.getBoolean("iam_access"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
}
