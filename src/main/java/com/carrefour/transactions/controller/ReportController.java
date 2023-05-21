package com.carrefour.transactions.controller;

import com.carrefour.transactions.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/report")
@AllArgsConstructor
@Slf4j
public class ReportController {

    @Autowired
    private final ReportService reportService;

    @GetMapping("/daily")
    public ResponseEntity<?> getDailyReport(){
        log.info("Getting consolidated transactions type by day");
        return ResponseEntity.ok().body(reportService.getDailyReport());
    }
}
