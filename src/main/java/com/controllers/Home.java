package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.utils.*;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CacheControl.clearCache(response);
		if(!Authentication.checkLogedIn(request, response)) {
			response.sendRedirect("login.jsp");
			return;
		}
		String path = request.getServletPath();
		if(path.startsWith("/")) {
			path = path.substring(1);
		}
		String[] pathParts = path.split("/");
		if(pathParts.length == 1) {
			response.sendRedirect(request.getContextPath() + "/home/profile");
		}
	}

}
