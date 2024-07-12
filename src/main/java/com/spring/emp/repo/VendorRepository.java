package com.spring.emp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.emp.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
	Vendor findByEmail(String email);

	boolean existsById(Long id);
	
    List<Vendor> findByEmailIn(List<String> emails);


}
