package com.packages.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.packages.Repositories.EmployeeRepository;
import com.packages.models.Employee;
import com.packages.services.EmployeeService;

@SpringBootTest
public class CreateEmployeeServiceTest {

	@Mock
	private EmployeeRepository repo;
	@InjectMocks
	private EmployeeService employeeService;

	@Test
	public void whenSaveEmployee_shouldReturnEmployee() {
		Employee employeeA = new Employee();
		employeeA.setFirstName("Test");
		employeeA.setId(10);
		when(repo.save(ArgumentMatchers.any(Employee.class))).thenReturn(employeeA);
		Employee created = employeeService.saveEmloyee(employeeA);
		assertThat(created.getFirstName()).isSameAs(employeeA.getFirstName());
		verify(repo).save(employeeA);
	}

}
