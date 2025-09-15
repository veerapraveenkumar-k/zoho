package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.AdminService;
import com.utils.Authentication;
import com.utils.Authorization;
import com.utils.CacheControl;

public class CreateAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CacheControl.clearCache(response);
		if(!Authentication.checkLogedIn(request, response)){
			response.sendRedirect("login.jsp");
			return;
		}
		if(!Authorization.superAdmin(request)) {
			response.sendRedirect("/zoho/accessDenied.jsp");
			return;
		}
		request.setAttribute("activeTab", "createAdmin");
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean dbAccess = request.getParameter("DATABASEACCESS") != null ? true: false;
		boolean jsonAccess = request.getParameter("JSONACCESS") != null ? true: false;
		boolean iamAccess = request.getParameter("IAMUSERACCESS") != null ? true: false;
		
		String msg = AdminService.createAdminValidation(username, password, dbAccess, jsonAccess, iamAccess);
		//System.out.println(msg);
		request.setAttribute("msg", msg);
		doGet(request, response);
	}

}
