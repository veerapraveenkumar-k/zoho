package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.InstanceDao;

public class DeleteInstance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDelete(request, response);
	}
       
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int instanceId = Integer.parseInt(request.getParameter("delete"));
//		System.out.println(instanceId);
		String result = InstanceDao.deleteInstance(instanceId);
		request.getSession().setAttribute("msg", result);
		response.sendRedirect("/zoho/home/importUsers");
	}

}
