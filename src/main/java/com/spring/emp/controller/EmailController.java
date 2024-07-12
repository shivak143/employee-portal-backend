package com.spring.emp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.emp.entities.EmailLog;
import com.spring.emp.entities.Vendor;
import com.spring.emp.service.EmailService;
import com.spring.emp.service.VendorService;

@RestController
@RequestMapping("/api/emails")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private VendorService vendorService;


    @PostMapping("/send")
    public ResponseEntity<?> sendEmailToVendors(@RequestBody Map<String, List<String>> request) {
        List<String> vendorEmails = request.get("emails");
        List<Vendor> vendors = vendorService.getVendorsByEmails(vendorEmails);
        for (Vendor vendor : vendors) {
            String message = String.format("Sending payments to vendor %s at upi %s", vendor.getName(), vendor.getUpi());
            emailService.sendEmail(vendor, message);
        }
        return ResponseEntity.ok("Emails sent successfully");
    }

    @GetMapping("/logs")
    public List<EmailLog> getEmailLogs() {
        return emailService.getAllEmailLogs();
    }
}

