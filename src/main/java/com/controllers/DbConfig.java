package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.models.Common;
import com.services.ImportUserService;

public class DbConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB/views/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Common user = (Common) request.getSession(false).getAttribute("user");
		int adminId = user.getId();
		String host = request.getParameter("HOST");
		String port = request.getParameter("PORT");
		String dbName = request.getParameter("DBNAME");
		String table = request.getParameter("TABLE");
		String userName= request.getParameter("UNAME");
		String password = request.getParameter("PASS");
		
		String msg = ImportUserService.dbValidaion(adminId, host, port, dbName, table, userName, password);
		//System.out.println(msg);
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect("importUsers?Import=DB");
	}

}
