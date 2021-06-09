package com.packages.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import com.packages.models.Employee;
import com.packages.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public Iterable<Employee> list() {
		try {
		return employeeService.listAll();
		}
		catch (NoSuchElementException e) {
			 throw new NoSuchElementException(e);
		}
	}

	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<Employee> get(@PathVariable Integer id) {

		Employee employee = employeeService.get(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	@PostMapping("/employee")
	public Employee add(@RequestBody Employee employee) {

		return employeeService.saveEmloyee(employee);

	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Integer id) {
		try {
             System.out.println("Hi I am here");
			Employee exisEmployee = employeeService.get(id);
			if (exisEmployee == null) {
				return new ResponseEntity<>("Employee not found!", HttpStatus.NOT_FOUND);
			} else {
				employeeService.update(employee);
				return new ResponseEntity<>(HttpStatus.OK);
			}

		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employee/{id}")
	public void delete(@PathVariable Integer id) {
		employeeService.deleteById(id);
	}

}