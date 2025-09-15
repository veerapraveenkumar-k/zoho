package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.services.UserService;
import com.models.*;

public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Common user = (Common) session.getAttribute("user");
		int id = user.getId();
		String role = user.getRole();
		String password = request.getParameter("PASSWORD");
		String mobile = request.getParameter("MOBILE");
		String email = request.getParameter("EMAIL");
		
		String message = UserService.updateProfileValidaion(id, password, email, mobile, role);
		//System.out.println(message);
		request.setAttribute("msg", message);
		request.getRequestDispatcher("/home/profile").forward(request, response);
	}

}
