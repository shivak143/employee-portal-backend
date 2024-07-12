package com.spring.emp.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vendorEmail;
    private String message;
    private LocalDateTime timestamp;
    
    
	public EmailLog() {
	}
	public EmailLog(Long id, String vendorEmail, String message, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.vendorEmail = vendorEmail;
		this.message = message;
		this.timestamp = timestamp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVendorEmail() {
		return vendorEmail;
	}
	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "EmailLog [id=" + id + ", vendorEmail=" + vendorEmail + ", message=" + message + ", timestamp="
				+ timestamp + "]";
	}
}
