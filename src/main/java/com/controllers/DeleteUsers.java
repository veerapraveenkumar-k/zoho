package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.UserDao;
import com.services.SourceService;

public class DeleteUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDelete(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("delete"));
		try {
			SourceService.deleteUsersFromSource(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = UserDao.deleteUsers(userId);
		request.getSession().setAttribute("msg", result);
		response.sendRedirect("/zoho/home/manageUsers");
	}
}
