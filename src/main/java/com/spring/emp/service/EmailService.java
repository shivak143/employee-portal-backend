package com.spring.emp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.emp.entities.EmailLog;
import com.spring.emp.entities.Vendor;
import com.spring.emp.repo.EmailLogRepository;

@Service
public class EmailService {
    @Autowired
    private EmailLogRepository emailLogRepository;

    public void sendEmail(Vendor vendor,String message) {
//        String message = "Sending payments to vendor " + vendor.getName() + " at UPI " + vendor.getUpi();
//        System.out.println(message);

        EmailLog log = new EmailLog();
        log.setVendorEmail(vendor.getEmail());
        log.setMessage(message);
        log.setTimestamp(LocalDateTime.now());
        emailLogRepository.save(log);
    }

    public List<EmailLog> getAllEmailLogs() {
        return emailLogRepository.findAll();
    }
    
    private List<String> sentEmails = new ArrayList<>();

//    public void sendEmail(Vendor vendor, String message) {
//        // Mock email sending
//        System.out.println("Sending email to " + vendor.getEmail() + ": " + message);
//        sentEmails.add("To: " + vendor.getEmail() + ", Message: " + message);
//    }
//
//    public List<String> getAllSentEmails() {
//        return sentEmails;
//    }
}
