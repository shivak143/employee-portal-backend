package com.spring.emp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.emp.entities.Employee;
import com.spring.emp.repo.EmployeeRepository;

@Service
public class EmployeeService {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		logger.info("Saving employee: {}", employee.getName());
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		logger.info("Fetching all employees");
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		logger.info("Fetching employee by ID: {}", id);
		return employeeRepository.findById(id).orElse(null);
	}

	public Employee updateEmployee(Long id, Employee employeeDetails) {
		logger.info("Updating employee with ID: {}", id);
		Employee employee = getEmployeeById(id);
		if (employee != null) {
			employee.setName(employeeDetails.getName());
			employee.setDesignation(employeeDetails.getDesignation());
			employee.setCtc(employeeDetails.getCtc());
			employee.setEmail(employeeDetails.getEmail());
			return employeeRepository.save(employee);
		} else {
			logger.warn("Employee with ID {} not found", id);
			return null;
		}
	}

	public void deleteEmployee(Long id) {
		Employee emp = getEmployeeById(id);
		if (emp != null) {
			logger.info("Deleting employee with ID: {}", id);
			employeeRepository.deleteById(id);
			logger.info("Employee deleted successfully");
		}

	}

	public boolean existsById(Long id) {
		return employeeRepository.existsById(id);
	}
}
