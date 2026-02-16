package com.example.springboot.employees.dao;

import java.util.List;

import com.example.springboot.employees.entity.Employee;

public interface EmployeeDAO {

    List<Employee> findAll();
}
