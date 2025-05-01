package com.example.demo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Employee employee) {
        String sql = "INSERT INTO employee (name, email, phonenumber, department, position, gender) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getPhonenumber(),
                            employee.getDepartment(), employee.getPosition(), employee.getGender());
    }

    // Get all employees
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    // Get employee by ID
    public Employee findById(Long id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
        } catch (Exception e) {
            return null;  // Return null if not found
        }
    }

    // Update employee (Update)
    public void update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, email = ?, phonenumber = ?, department = ?, position = ?, gender = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getPhonenumber(),
                                                employee.getDepartment(), employee.getPosition(), employee.getGender(), employee.getId());
        if (rowsAffected == 0) {
            throw new IllegalStateException("Employee not found with id " + employee.getId());
        }
    }

    // Delete employee (Delete)
    public void delete(Long id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new IllegalStateException("Employee not found with id " + id);
        }
    }
}
