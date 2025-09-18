package com.services;

import java.util.ArrayList;

import com.dao.UserDao;
import com.models.User;


public class UserService {
	public static User getUserProfile(String name, String role) {
		String tableName = "";
		if(role.equals("SUPERADMIN")) {
			tableName = "super_admin_profile";
		}else if(role.equals("ADMIN")) {
			tableName = "admin_profile";
		}else {
			tableName = "user_profile";
		}
		User user = UserDao.getProfile(name, tableName);
		return user;
	}
	
	public static String updateProfileValidaion(int id, String pass, String email, String mobile, String role) {
		String tableName = "";
		if(role.equals("SUPERADMIN")) {
			tableName = "super_admin_profile";
		}else if(role.equals("ADMIN")) {
			tableName = "admin_profile";
		}else {
			tableName = "user_profile";
		}
//		System.out.println(id);
//		System.out.println(pass);
//		System.out.println(email);
//		System.out.println(mobile);
		if(pass == "" || email == "" || mobile == "") {
			return "Values cannot be empty";
		}
		else if(!(email.endsWith("@gmail.com"))) {
			return "Invaild email address";
		}
		else if(mobile.length() != 10) {
			return "Mobile number must be 10 digit.";
		}else {
			if(UserDao.updateUserProfile(id, pass, mobile, email, tableName)) {
				return "Successfully updated";
			}else {
				return "Failed to update your profile";
			}
		}
	}
	
	public static ArrayList<User> getUserProfileList() {
		ArrayList<User> userList = UserDao.getUsersList();
		return userList;
	}
}
