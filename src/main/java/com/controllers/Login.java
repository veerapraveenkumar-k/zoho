package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.models.*;
import com.services.*;
import com.utils.Authentication;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Authentication.checkLogedIn(request, response)) {
			response.sendRedirect(request.getContextPath() + "/home");
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session;
		
		Common user = LoginService.userValidation(username, password);
		if(user.getUserName() != null) {
			session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/home");
		}else {
			request.setAttribute("error", "Invalid Credentials");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
