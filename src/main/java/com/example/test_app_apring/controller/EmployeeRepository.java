package com.example.test_app_apring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> findById(String id) {
        String query = "SELECT * FROM employee WHERE id = ?";

        Map<String, Object> employee;
        employee = jdbcTemplate.queryForMap(query, id);

        return employee;
    }
}
