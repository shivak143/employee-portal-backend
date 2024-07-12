package com.spring.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.emp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	boolean existsById(Long id);

}
