package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;

import com.dao.InstanceDao;
import com.models.Common;

@MultipartConfig
public class JsonConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Common user = (Common) request.getSession(false).getAttribute("user");
		int adminId = user.getId();
		Part filePart = request.getPart("jsonFile");
		String fileName = filePart.getSubmittedFileName();
		String filePath = "D:\\JSON file\\" + fileName;
		String result = InstanceDao.checkJsonInstance(filePath, adminId);
		request.getSession().setAttribute("msg", result);
		response.sendRedirect("importUsers?Import=JSON");
	}

}
