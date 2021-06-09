package com.packages.service;

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
public class DeleteEmployeeServiceTest {

	@Mock
	private EmployeeRepository repo;
	@InjectMocks
	private EmployeeService employeeService;

	@Test
	public void whenGivenId_shouldDeleteEmployee_ifFound() {
		Employee employee = new Employee();
		employee.setFirstName("Test Name");
		employee.setId(10);
		when(repo.findById(employee.getId())).thenReturn(Optional.of(employee));
		employeeService.deleteById(employee.getId());
		verify(repo).deleteById(employee.getId());
	}
	
	@Test
	public void should_throw_exception_when_employee_doesnt_exist() {
		Employee employee = new Employee();
		employee.setId(89);
		employee.setFirstName("Test Name");
		when(repo.findById(103)).thenReturn(Optional.ofNullable(null));
		employeeService.deleteById(employee.getId());
		}
}
