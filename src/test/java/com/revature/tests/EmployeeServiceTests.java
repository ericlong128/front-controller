package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {
	
	private EmployeeService eserv;
	private EmployeeDao mockdao;
	
	@Before // run before every test
	public void setup() {
		
		
		mockdao = mock(EmployeeDao.class);
		eserv = new EmployeeService(mockdao);
		
	}
	
	@After //runs after every test
	public void teardown() {
		
		mockdao = null;
		eserv = null;
	}
	
	//Happy path scenario: Everything works as it should
	@Test
	public void testConfirmLogin_success() {
		//should_returnSuccessfull_When_correctUsernameAndPassword
		//1. create a fake list of employees which the dao will return
		Employee e1 = new Employee(20, "Bruce","Banner","thehulk","green");
		Employee e2 = new Employee(21, "Clint","Barton","hawkeye","arrows");
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(e1);
		employees.add(e2);
		//2. set up when-then scenario
		when(mockdao.findAll()).thenReturn(employees);
		//WHEN the dao's findAll() is called, THEN RETURN the fake data
		
		// Capture the actual output of the method
		Employee actual = eserv.confirmLogin("hawkeye", "arrows");
		Employee expected = e2;
		//compare the EXPECTED output to the actual output
		assertEquals(expected, actual);
	}
	
	@Test
	public void testConfirmLogin_fail() {
		
		//test that when you pass in a username/password combo without a match, it should return null
		
		Employee e1 = new Employee(20, "Bruce","Banner","thehulk","green");
		Employee e2 = new Employee(21, "Clint","Barton","hawkeye","arrows");
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(e1);
		employees.add(e2);
		//2. set up when-then scenario
		when(mockdao.findAll()).thenReturn(employees);
		
		Employee actual = eserv.confirmLogin("Eric", "pizza");
		Employee expected = null;
		
		assertEquals(expected, actual);
		
		
		
	}
	
	
	

}