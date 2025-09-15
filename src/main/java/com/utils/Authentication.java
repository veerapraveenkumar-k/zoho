package com.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Authentication {
	public static boolean checkLogedIn(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession(false).getAttribute("user") == null) {
			return false;
		}
		return true;
	}
}
