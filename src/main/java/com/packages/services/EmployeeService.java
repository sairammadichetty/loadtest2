package com.packages.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packages.Repositories.EmployeeRepository;
import com.packages.models.Employee;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	public Iterable<Employee> listAll() {
		return repo.findAll();
	}

	public long count() {
		return repo.count();
	}

	public Employee get(Integer id) {
		return repo.findById(id).orElseThrow();
	}

	public Employee saveEmloyee(Employee employee) {
		try {
			return repo.save(employee);
		} catch (Exception e) {
			throw e;
		}
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	public Employee update(Employee employee) {
		Employee updateEmployee = new Employee();
		updateEmployee.setId(employee.getId());
		updateEmployee.setFirstName(employee.getFirstName());
		updateEmployee.setMobileNo(employee.getMobileNo());
		updateEmployee.setAddress(employee.getAddress());
		updateEmployee.setUsername(employee.getUsername());
		updateEmployee.setCreatedDate(employee.getCreatedDate());
		updateEmployee.setUpdatedDate(employee.getUpdatedDate());
		return repo.save(updateEmployee);
	}

	public Employee updateEmployee(int id, Employee empolyee) {
		repo.findById(id).orElseThrow();
		empolyee.setId(id);
		return repo.save(empolyee);
	}

}
