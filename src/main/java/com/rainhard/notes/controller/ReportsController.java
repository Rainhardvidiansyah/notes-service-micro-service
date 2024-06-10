package com.rainhard.notes.controller;

import com.rainhard.notes.service.JasperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {

    @Autowired
    private JasperService jasperService;


    @GetMapping
    public ResponseEntity<byte[]> getReport(){
        try {
            byte[] data = jasperService.generateReport();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment, filename=notes_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(data);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
