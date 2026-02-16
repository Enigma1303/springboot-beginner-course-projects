package com.example.springboot.employees.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.employees.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}

