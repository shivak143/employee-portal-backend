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

import com.spring.emp.entities.Vendor;
import com.spring.emp.service.VendorService;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
	@Autowired
	private VendorService vendorService;

	@PostMapping
	public Vendor createVendor(@RequestBody Vendor vendor) {
		return vendorService.saveVendor(vendor);
	}

	@GetMapping
	public List<Vendor> getAllVendors() {
		return vendorService.getAllVendors();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getVendorById(@PathVariable Long id) {
		Vendor vendor = vendorService.getVendorById(id);
		if (vendor != null) {
			return ResponseEntity.ok(vendor);
		} else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Vendor with ID " + id + " not found");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateVendor(@PathVariable Long id, @RequestBody Vendor vendorDetails) {
		Vendor updatedVendor = vendorService.updateVendor(id, vendorDetails);
		if (updatedVendor != null) {
			return ResponseEntity.ok(updatedVendor);
		} else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Vendor with ID " + id + " not found");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
		if (vendorService.existsById(id)) {
			vendorService.deleteVendor(id);
			return ResponseEntity.status(HttpStatus.OK).body("Vendor deleted successfully");
		} else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Vendor with ID " + id + " not found");
		}
	}
}
