package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.beans.Employees;

public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

}
