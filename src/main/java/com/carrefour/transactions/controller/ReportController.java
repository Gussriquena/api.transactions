package com.carrefour.transactions.controller;

import com.carrefour.transactions.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("api/v1/report")
@AllArgsConstructor
public class ReportController {

    @Autowired
    private final ReportService reportService;

    @GetMapping("/daily")
    public Map<LocalDate, Map<String, Integer>> getDailyReport(){
        return reportService.getDailyReport();
    }
}
