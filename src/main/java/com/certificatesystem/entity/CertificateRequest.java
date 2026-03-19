package com.certificatesystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificate_request")
public class CertificateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String certificateType;   // ✅ IMPORTANT
    private String reason;
    private String status;

    // ✅ GETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    // ✅ SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}