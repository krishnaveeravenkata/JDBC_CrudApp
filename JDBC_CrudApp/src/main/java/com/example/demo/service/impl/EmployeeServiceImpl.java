package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String save(Employee employee) {
		String sql = "INSERT INTO employees (name, email, phonenumber, department, position, gender) VALUES (?, ?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getPhonenumber(),
				employee.getDepartment(), employee.getPosition(), employee.getGender());
		return result > 0 ? "Inserted successfully" : "Insert failed";
	}

	@Override
	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employees";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
	}

	@Override
	public Employee getEmployeeById(Long id) {
		String sql = "SELECT * FROM employees WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), id);
	}

	@Override
	public String updateEmployee(Long id, Employee employee) {
		String sql = "UPDATE employees SET name = ?, email = ?, phonenumber = ?, department = ?, position = ?, gender = ? WHERE id = ?";
		int result = jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getPhonenumber(),
				employee.getDepartment(), employee.getPosition(), employee.getGender(), id);
		return result > 0 ? "Updated successfully" : "Update failed";
	}

	@Override
	public String deleteEmployee(Long id) {
		String sql = "DELETE FROM employee WHERE id = ?";
		int result = jdbcTemplate.update(sql, id);
		return result > 0 ? "Deleted successfully" : "Delete failed";
	}
}
