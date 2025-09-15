package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.models.Admin;
import com.models.Common;
import com.services.*;
import com.utils.Authentication;
import com.utils.Authorization;
import com.utils.CacheControl;
import com.dao.InstanceDao;
import com.models.*;

public class ImportUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CacheControl.clearCache(response);
		if(!Authentication.checkLogedIn(request, response)){
			response.sendRedirect("login.jsp");
			return;
		}
		if(!Authorization.admin(request)) {
			response.sendRedirect("/zoho/accessDenied.jsp");
			return;
		}
		request.setAttribute("activeTab", "importUsers");
		Common user = (Common) request.getSession(false).getAttribute("user");
		int adminId = user.getId();
		Admin admin = AdminService.getAdminAccess(adminId);
		request.setAttribute("adminObject", admin);
		ArrayList<Instance> instanceList =  InstanceDao.getInstanceList(adminId);
		request.setAttribute("instanceList", instanceList);
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int instanceId = Integer.parseInt(request.getParameter("import"));
		boolean result = ImportUserService.importUsers(instanceId);	
		if(result) {
			request.setAttribute("msg", "successfully user imported.");
			doGet(request, response);
		}else {
			request.setAttribute("msg", "Failed to import users.");
			doGet(request, response);
		}
	}
}
