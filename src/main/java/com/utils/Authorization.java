package com.utils;

import com.models.Common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Authorization {
	public static boolean superAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Common user = (Common) session.getAttribute("user");
		if(user.getRole().equals("SUPERADMIN")) {
			return true;
		}
		return false;
	}
	
	public static boolean admin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Common user = (Common) session.getAttribute("user");
		if(user.getRole().equals("ADMIN")) {
			return true;
		}
		return false;
	}
}
