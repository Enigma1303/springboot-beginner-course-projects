package com.example.springboot.employees.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboot.employees.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager)
    {
        entityManager=theEntityManager;
    }

    @Override
    public List<Employee>findAll()
    {

        TypedQuery<Employee> theQuery=entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees= theQuery.getResultList();

        return employees;
    }

}
