package com.spring.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.emp.entities.EmailLog;

public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {}