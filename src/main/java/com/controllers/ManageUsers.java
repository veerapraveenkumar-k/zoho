package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.models.Common;
import com.models.User;
import com.services.UserService;
import com.utils.Authentication;
import com.utils.Authorization;
import com.utils.CacheControl;

public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CacheControl.clearCache(response);
		if(!Authentication.checkLogedIn(request, response)){
			response.sendRedirect("login.jsp");
			return;
		}
		if(!Authorization.superAdmin(request) && !Authorization.admin(request) ) {
			response.sendRedirect("/zoho/accessDenied.jsp");
			return;
		}
		request.setAttribute("activeTab", "manageUsers");
		HttpSession session = request.getSession(false);
		Common user = (Common) session.getAttribute("user");
		String role = user.getRole();
		if(role.equals("ADMIN")) {
			ArrayList<User> userList = UserService.getUserProfileList();
			request.setAttribute("usersList", userList);
		}
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
