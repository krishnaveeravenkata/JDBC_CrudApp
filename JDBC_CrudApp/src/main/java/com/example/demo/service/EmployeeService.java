package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	String save(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Long id);

	String updateEmployee(Long id, Employee employee);

	String deleteEmployee(Long id);
}
