package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static ObjectMapper om = new ObjectMapper();
	
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//this method will be called by the FrontController when a suer requests the login resources
		// we will extract the parameters from the forwarded request
		// then we'll call the Service Layer... etc.
		
		// 1.extract parameters from the request (username and password)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//2. call the confirmLogin method in the EmployeeService and see what that returns
		Employee e = eserv.confirmLogin(username, password);
		
		//3. If the above method returns a valid user, let's print their info to the screen using PrintObject
		if (e != null) {
			// add the user to the session and grab the session
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			
			// print out the user's data with the printWriter
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Welcome " + e.getFirstName() + "!</h1>");
			out.println("<h3>You have successfully logged in </h3>");
			out.println(om.writeValueAsString(e)); //this will print outa JSON string of the Employee object
		
		} else {
			// if returned as null
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("no user found, sorry");
			response.setStatus(204); //2xx level codes mean successfully connected - but no content can be returned
		
		}
		
	}
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");
		
		List<Employee> allEmployees = eserv.findAll();
		
		String jsonString = om.writeValueAsString(allEmployees);
		
		PrintWriter out = response.getWriter();
		out.println(jsonString);
}
}
