package com.certificatesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.certificatesystem.entity.CertificateRequest;

import java.util.List;

public interface RequestRepository extends JpaRepository<CertificateRequest, Long> {
    List<CertificateRequest> findByUsername(String username);
}