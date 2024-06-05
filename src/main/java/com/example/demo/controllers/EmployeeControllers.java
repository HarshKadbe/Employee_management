package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Employees;
import com.example.demo.services.EmployeeServices;

@RestController
public class EmployeeControllers {
	
	@Autowired
	EmployeeServices employeeservice;
	
	
	@GetMapping("/allemployees")
	public List<Employees> getEmployees(){
		return employeeservice.getAllEmployee();
	}
	
	@GetMapping("/allemployee/{id}")
	public ResponseEntity<Employees> getEmployeeById(@PathVariable(value="id") int id) {
		try {
		Employees employee =  employeeservice.getEmployeeById(id);
		return new ResponseEntity<Employees>(employee, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/createemployee")
	public Employees createEmployee(@RequestBody Employees employees) {
		return employeeservice.createEmployees(employees);
	}

	@PutMapping("/updateemployee/{id}")
	public Employees updateEmployee(@PathVariable(value="id")int id, @RequestBody Employees employees) {
		Employees existemployee = employeeservice.getEmployeeById(id);
		existemployee.setFirst_name(employees.getFirst_name());
		existemployee.setLast_name(employees.getLast_name());
		existemployee.setPhone_no(employees.getPhone_no());
		
		return employeeservice.updateEmployees(existemployee);
		
	}
	
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value="id")int id) {
		String result = employeeservice.deleteEmployee(id);
		if (result.equals("Employee deleted!!!")) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.status(404).body(result);
		}

	}
}
