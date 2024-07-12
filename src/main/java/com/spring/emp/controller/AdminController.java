package com.spring.emp.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.emp.entities.EmailLog;
import com.spring.emp.entities.Employee;
import com.spring.emp.entities.Vendor;
import com.spring.emp.service.EmailService;
import com.spring.emp.service.EmployeeService;
import com.spring.emp.service.VendorService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private EmailService emailService;	

    @PostMapping("/emails/send")
    public ResponseEntity<?> sendEmailToVendors(@RequestBody Map<String, List<String>> request) {
    	  List<String> vendorEmails = request.get("emails");
          List<Vendor> vendors = vendorService.getVendorsByEmails(vendorEmails);
          
          // Find emails that do not have corresponding vendors
          List<String> foundEmails = vendors.stream().map(Vendor::getEmail).collect(Collectors.toList());
          List<String> missingEmails = vendorEmails.stream()
                                                   .filter(email -> !foundEmails.contains(email))
                                                   .collect(Collectors.toList());

          if (!missingEmails.isEmpty()) {
              return ResponseEntity.status(404).body("The following email addresses were not found: " + missingEmails);
          }

          for (Vendor vendor : vendors) {
              String message = String.format("Sending payments to vendor %s at upi %s", vendor.getName(), vendor.getUpi());
              emailService.sendEmail(vendor, message);
          }
          return ResponseEntity.ok("Emails sent successfully");
    }

	 @GetMapping("/emails/logs")
	    public List<EmailLog> getEmailLogs() {
	        return emailService.getAllEmailLogs();
	    }

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee createdEmployee = employeeService.saveEmployee(employee);
		return ResponseEntity.ok(createdEmployee);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@PostMapping("/vendors")
	public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
		Vendor createdVendor = vendorService.saveVendor(vendor);
		return ResponseEntity.ok(createdVendor);
	}

	@GetMapping("/vendors")
	public ResponseEntity<List<Vendor>> getAllVendors() {
		List<Vendor> vendors = vendorService.getAllVendors();
		return ResponseEntity.ok(vendors);
	}
}
