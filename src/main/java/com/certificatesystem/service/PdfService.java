package com.certificatesystem.service;

import com.certificatesystem.entity.CertificateRequest;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {

    public byte[] generateCertificate(CertificateRequest req) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();

        // TITLE
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD);
        Paragraph title = new Paragraph("CERTIFICATE", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(30);
        document.add(title);

        // BODY
        Font bodyFont = new Font(Font.FontFamily.HELVETICA, 18);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Name: " + req.getName(), bodyFont));
        document.add(new Paragraph("Type: " + req.getCertificateType(), bodyFont)); // ✅ FIXED
        document.add(new Paragraph("\nApproved Successfully", bodyFont));

        document.close();

        return out.toByteArray();
    }
}