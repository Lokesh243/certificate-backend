package com.certificatesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.certificatesystem.entity.CertificateRequest;
import com.certificatesystem.repository.RequestRepository;

import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin("*")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    // Add request
    @PostMapping("/add")
    public CertificateRequest addRequest(@RequestBody CertificateRequest request) {
        request.setStatus("PENDING");
        return requestRepository.save(request);
    }

    // Get all requests (admin)
    @GetMapping("/all")
    public List<CertificateRequest> getAll() {
        return requestRepository.findAll();
    }

    // Get student requests
    @GetMapping("/student/{username}")
    public List<CertificateRequest> getByStudent(@PathVariable String username) {
        return requestRepository.findByUsername(username);
    }

    // Approve
    @PutMapping("/approve/{id}")
    public CertificateRequest approve(@PathVariable Long id) {
        CertificateRequest req = requestRepository.findById(id).get();
        req.setStatus("APPROVED");
        return requestRepository.save(req);
    }

    // Reject
    @PutMapping("/reject/{id}")
    public CertificateRequest reject(@PathVariable Long id) {
        CertificateRequest req = requestRepository.findById(id).get();
        req.setStatus("REJECTED");
        return requestRepository.save(req);
    }
}