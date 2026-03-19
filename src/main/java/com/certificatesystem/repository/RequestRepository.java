package com.certificatesystem.repository;

import com.certificatesystem.entity.CertificateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<CertificateRequest, Long> {
}