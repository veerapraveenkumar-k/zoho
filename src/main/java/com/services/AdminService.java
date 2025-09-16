package com.services;

import com.models.Admin;

import java.util.ArrayList;

import com.dao.*;

public class AdminService {
	public static String createAdminValidation(String name, String pass, boolean dbAccess, boolean jsonAccess, boolean iamAccess) {
		if(name == "" || pass == "") {
			return "Values Cannot be empty";
		}else if(CommonDao.chaeckUserPresent(name)) {
			return "Username already present";
		}else {
			if(AdminDao.createAdmin(name, pass, dbAccess, jsonAccess, iamAccess)) {
				return "Admin Successfully Created";
			}else {
				return "Failed to Create Admin Account";
			}
		}
	}
	
	public static Admin getAdminAccess(int id) {
		Admin admin = AdminDao.getAdminAccess(id);
		return admin;
	}
	
	public static ArrayList<Admin> getAdminProfileList() {
		ArrayList<Admin> userList = UserDao.getAdminsList();
		return userList;
	}
}
