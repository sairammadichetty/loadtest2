package com.packages.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.packages.Repositories.EmployeeRepository;
import com.packages.models.Employee;
import com.packages.services.EmployeeService;


@SpringBootTest
public class EmployeeServiceTest {

	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository repo;
	
	
	@Test
	public void shouldReturnAllEmployee()   {
		Employee employeeA = new  Employee();
		Employee employeeB = new  Employee();
		ArrayList<Employee> emps = new ArrayList<>();	
		emps.add(employeeA);
		emps.add(employeeB);
		when(repo.findAll()).thenReturn(emps);
		ArrayList<Employee> allEmpl= (ArrayList<Employee>) employeeService.listAll();
		assertThat(allEmpl.size()).isEqualTo(emps.size());
	}
	
	@Test
	public void whenGivenId_shouldReturnEmployee_ifFound()   {
		Employee employeeA = new Employee();
		employeeA.setFirstName("Test");
		employeeA.setId(10);
		when(repo.findById(employeeA.getId())).thenReturn(Optional.of(employeeA));
		Employee employeeB=    employeeService.get(employeeA.getId());
		assertEquals(employeeA,employeeB);
	}
	
	@Test
	public void whenSaveEmployee_shouldReturnEmployee()   {
		Employee employeeA = new Employee();
		employeeA.setFirstName("Test");
		employeeA.setId(10);
		when(repo.save(employeeA)).thenReturn(employeeA);
		Employee employeeB=    employeeService.saveEmloyee(employeeA);
		assertEquals(employeeA.getFirstName(),employeeB.getFirstName());
	}
	
	@Test
	public void  whenGivenId_shouldDeleteEmployee_ifFound()   {
		Employee employeeA = new Employee();
		employeeA.setFirstName("Test");
		employeeA.setId(10);
		when(repo.findById(employeeA.getId())).thenReturn(Optional.of(employeeA));
		employeeService.deleteById(employeeA.getId());
		Employee employee=    employeeService.get(employeeA.getId());
		assertEquals(employeeA.getFirstName(),employee.getFirstName());
	}
	
	
	@Test
	public void  whenGivenId_shouldUpdateEmployee_ifFound()   {
		Employee employeeA = new Employee();
		employeeA.setFirstName("Test");
		employeeA.setId(10);
	    when(repo.findById(employeeA.getId())).thenReturn(Optional.of(employeeA));
	    employeeService.update(employeeA);
		Employee employee=    employeeService.get(employeeA.getId());
		assertEquals(employeeA.getFirstName(),employee.getFirstName());
	}

	
}
