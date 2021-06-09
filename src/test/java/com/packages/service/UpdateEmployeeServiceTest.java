package com.packages.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.packages.Repositories.EmployeeRepository;
import com.packages.models.Employee;
import com.packages.services.EmployeeService;

@SpringBootTest
public class UpdateEmployeeServiceTest {

	@Mock
	private EmployeeRepository repo;
	@InjectMocks
	private EmployeeService employeeService;

	@Test
	public void whenGivenId_shouldUpdateEmployee_ifFound() {

		Employee employee = new Employee();
		employee.setFirstName("Test");
		employee.setId(10);
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("New Test Name");
		when(repo.findById(employee.getId())).thenReturn(Optional.of(employee));
		employeeService.updateEmployee(employee.getId(), newEmployee);
		verify(repo).save(newEmployee);
		verify(repo).findById(employee.getId());
	}
	@Test
	public void should_throw_exception_when_employee_doesnt_exist() {
		Employee employee = new Employee();
		employee.setId(89);
		employee.setFirstName("Test Name");
		Employee newEmployee = new Employee();
		newEmployee.setId(90);
		newEmployee.setFirstName("New Test Name");
		when(repo.findById(0)).thenReturn(Optional.ofNullable(null));
		try {
		employeeService.updateEmployee(employee.getId(), newEmployee);
		}
		catch(Exception e) {
			
			assertEquals("No value present", e.getMessage());
		}
		}
}
