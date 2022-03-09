package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. save the URI 
		final String URI = request.getRequestURI().replace("/FrontController/", "");
		
		//2. Set up switch case statement in which we call the appropriate functionality based on input
		switch(URI) {
		
		case "login":
			//call some processLogin() functionality and pass the request and response objects
			RequestHelper.processLogin(request, response);
			break;
		case "employees":
			//call method
			RequestHelper.processEmployees(request, response);
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
