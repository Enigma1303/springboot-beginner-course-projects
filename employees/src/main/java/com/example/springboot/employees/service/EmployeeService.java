package com.example.springboot.employees.service;

import java.util.List;

import com.example.springboot.employees.entity.Employee;
import com.example.springboot.employees.request.EmployeeRequest;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(long theId);

    Employee save(EmployeeRequest employeeRequest);

    Employee update(long id, EmployeeRequest employeeRequest);

    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);

    void deleteById(long theId);
}