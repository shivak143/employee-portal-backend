package com.spring.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.emp.entities.Employee;
import com.spring.emp.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Employee with ID " + id + " not found");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
		if (updatedEmployee != null) {
			return ResponseEntity.ok(updatedEmployee);
		} else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Employee with ID " + id + " not found");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		if (employeeService.existsById(id)) {
			employeeService.deleteEmployee(id);
			return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
		} else {
			return ((BodyBuilder) ResponseEntity.notFound()).body("Employee with ID " + id + " not found");
		}
	}
}
