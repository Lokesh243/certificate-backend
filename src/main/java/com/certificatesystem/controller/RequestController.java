package com.certificatesystem.controller;

import com.certificatesystem.entity.CertificateRequest;
import com.certificatesystem.repository.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.io.ByteArrayOutputStream;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/request")
@CrossOrigin(origins = "*")
public class RequestController {

    @Autowired
    private RequestRepository repository;


    @GetMapping("/all")
    public List<CertificateRequest> getAll() {
        return repository.findAll();
    }


    @PostMapping("/add")
    public CertificateRequest add(@RequestBody CertificateRequest req) {
        req.setStatus("PENDING");
        return repository.save(req);
    }


    @PutMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {
        CertificateRequest req = repository.findById(id).orElse(null);
        if (req != null) {
            req.setStatus("APPROVED");
            repository.save(req);
            return "Approved";
        }
        return "Not Found";
    }


    @PutMapping("/reject/{id}")
    public String reject(@PathVariable Long id) {
        CertificateRequest req = repository.findById(id).orElse(null);
        if (req != null) {
            req.setStatus("REJECTED");
            repository.save(req);
            return "Rejected";
        }
        return "Not Found";
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) throws Exception {

        CertificateRequest req = repository.findById(id).orElse(null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();


        Font titleFont = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD);
        Paragraph title = new Paragraph("OFFICIAL CERTIFICATE", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(30);
        document.add(title);


        Font bodyFont = new Font(Font.FontFamily.HELVETICA, 18);

        Paragraph content = new Paragraph(
                "This is to certify that " + req.getName() +
                        " has successfully received the " +
                        req.getCertificateType() + " certificate.",
                bodyFont
        );
        content.setAlignment(Element.ALIGN_CENTER);
        content.setSpacingAfter(40);
        document.add(content);


        Paragraph status = new Paragraph("Status: " + req.getStatus(), bodyFont);
        status.setAlignment(Element.ALIGN_CENTER);
        status.setSpacingAfter(50);
        document.add(status);


        Paragraph sign = new Paragraph("Authorized Signature", bodyFont);
        sign.setAlignment(Element.ALIGN_RIGHT);
        document.add(sign);

        document.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate.pdf")
                .body(out.toByteArray());
    }
}