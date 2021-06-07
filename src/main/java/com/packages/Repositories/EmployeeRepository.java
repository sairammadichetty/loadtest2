package com.packages.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.packages.models.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

   
}