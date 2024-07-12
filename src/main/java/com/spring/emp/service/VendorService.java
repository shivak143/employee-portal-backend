package com.spring.emp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.emp.entities.Vendor;
import com.spring.emp.repo.VendorRepository;

@Service
public class VendorService {
	private static final Logger logger = LoggerFactory.getLogger(VendorService.class);

	@Autowired
	private VendorRepository vendorRepository;

	public Vendor saveVendor(Vendor vendor) {
		logger.info("Saving vendor: {}", vendor.getName());
		return vendorRepository.save(vendor);
	}

	public List<Vendor> getAllVendors() {
		logger.info("Fetching all vendors");
		return vendorRepository.findAll();
	}

	public Vendor getVendorById(Long id) {
		logger.info("Fetching vendor by ID: {}", id);
		return vendorRepository.findById(id).orElse(null);
	}

	public Vendor updateVendor(Long id, Vendor vendorDetails) {
		logger.info("Updating vendor with ID: {}", id);
		Vendor vendor = getVendorById(id);
		if (vendor != null) {
			vendor.setName(vendorDetails.getName());
			vendor.setEmail(vendorDetails.getEmail());
			vendor.setUpi(vendorDetails.getUpi());
			return vendorRepository.save(vendor);
		} else {
			logger.warn("Vendor with ID {} not found", id);
			return null;
		}
	}

	public void deleteVendor(Long id) {
		logger.info("Deleting vendor with ID: {}", id);
		vendorRepository.deleteById(id);
		logger.info("Vendor deleted successfully");
	}

	public Vendor getVendorByEmail(String email) {
		return vendorRepository.findByEmail(email);
	}

	public List<Vendor> getVendorsByEmails(List<String> emails) {
		return vendorRepository.findByEmailIn(emails);
	}

	public boolean existsById(Long id) {
		return vendorRepository.existsById(id);
	}
}
