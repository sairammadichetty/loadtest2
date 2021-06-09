package com.packages.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.packages.Repositories.EmployeeRepository;
import com.packages.models.Employee;
import com.packages.services.EmployeeService;

@SpringBootTest
public class ListEmployeeServiceTest {

	@Mock
	private EmployeeRepository repo;
	@InjectMocks
	private EmployeeService employeeService;

	@Test
	public void shouldReturnAllEmployees() {
		List<Employee> employees = new ArrayList();
		employees.add(new Employee());
		when(repo.findAll()).thenReturn(employees);
		List<Employee> expected = (List<Employee>) employeeService.listAll();
		assertEquals(expected, employees);
		verify(repo).findAll();
	}
	
	
	@Test
	public void shouldReturnNumberOfEmployeeCount() {
		when(repo.count()).thenReturn(10l);
		long expected =  employeeService.count();
		assertEquals(expected, 10l);
		verify(repo).count();
	}
}
