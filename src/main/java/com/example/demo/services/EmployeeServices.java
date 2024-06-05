package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Employees;
import com.example.demo.repositories.EmployeeRepository;

@Component
@Service
public class EmployeeServices {
	
	@Autowired
	EmployeeRepository employeerepo;
	
	public List<Employees> getAllEmployee(){
		return employeerepo.findAll();
	}

	public Employees getEmployeeById(int id) {
		return employeerepo.findById(id).get();
	}
	
	public int getMaxId() {
		return employeerepo.findAll().size() + 1;
	}
	
	public Employees createEmployees(Employees employees) {
		employees.setId(getMaxId());
		employeerepo.save(employees);
		return employees;
	}
	
	public Employees updateEmployees(Employees employees) {
		employeerepo.save(employees);
		return employees;
	}
	
	public String deleteEmployee(int id) {
		if(employeerepo.existsById(id)) {
			employeerepo.deleteById(id);
			return "Employee deleted!!!";
		}else {
			return "Employee not Found";
		}
		
	}
}
