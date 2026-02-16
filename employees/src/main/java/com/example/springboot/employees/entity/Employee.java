package com.example.springboot.employees.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
       @Id
       @GeneratedValue(strategy =GenerationType.IDENTITY)
       @Column(name="id")
       private long id;

       @Column(name="first_name")
       private String firstName;

        @Column(name="Last_name")
       private String lastName;

       @Column(name="email")
       private String email;
}
