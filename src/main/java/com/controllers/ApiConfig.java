package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.InstanceDao;
import com.models.Common;

public class ApiConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Common user = (Common) request.getSession(false).getAttribute("user");
		int adminId = user.getId();
		String api = request.getParameter("apiKey");
		//System.out.println(api);
		String result = InstanceDao.checkApiInstance(api, adminId);
		request.getSession().setAttribute("msg", result);
		response.sendRedirect("importUsers?Import=API");
	}

}
