package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.utils.Authentication;
import com.utils.CacheControl;
import com.models.*;
import com.services.UserService;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CacheControl.clearCache(response);
		if(!Authentication.checkLogedIn(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		//HttpSession session = request.getSession(false);
		Common exsistingUser =  (Common) request.getSession(false).getAttribute("user");
		String username = exsistingUser.getUserName();
		//System.out.println(username);
		User user = UserService.getUserProfile(username, exsistingUser.getRole());
		request.setAttribute("activeTab", "profile");
		request.setAttribute("userProfile", user);
		//System.out.println(user.getMobileNum());
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
