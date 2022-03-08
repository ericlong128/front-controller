package com.revature.service;

import java.util.Optional;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class EmployeeService {
	
	private EmployeeDao edao;
	
	
	public EmployeeService(EmployeeDao edao) {
		this.edao = edao;
	}
	
	//this will return an employee that has successfully logged in
	public Employee confirmLogin(String username, String password) {
		Optional<Employee> possibleEmp = edao.findAll()
				.stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();
		
		return (possibleEmp.isPresent() ? possibleEmp.get() : null);
	}
	
	public int insert(Employee e) {
		return edao.insert(e);
	}

}
