package com.packages.service;

import static org.assertj.core.api.Assertions.assertThat;
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
public class DetailEmployeeServiceTest {

	@Mock
	private EmployeeRepository repo;
	@InjectMocks
	private EmployeeService employeeService;

	@Test
	public void whenGivenId_shouldReturnEmployee_ifFound() {
		Employee employee = new Employee();
		employee.setFirstName("Test");
		employee.setId(10);
		when(repo.findById(employee.getId())).thenReturn(Optional.of(employee));
		Employee expected = employeeService.get(employee.getId());
		assertThat(expected).isSameAs(employee);
		verify(repo).findById(employee.getId());
	}
	@Test
	public void should_throw_exception_when_employee_doesnt_exist() {
		Employee employee = new Employee();
		employee.setFirstName("Test");
		employee.setId(10);
		when(repo.findById(0)).thenReturn(Optional.ofNullable(null));
		try {
		employeeService.get(employee.getId());
		}	
		catch(Exception e) {
			assertEquals("No value present", e.getMessage());
		}
		}
}
