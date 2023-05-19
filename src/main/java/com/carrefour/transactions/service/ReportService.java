package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.dto.ReportDTO;
import com.carrefour.transactions.domain.model.Report;
import com.carrefour.transactions.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    @Autowired
    private final ReportRepository reportRepository;

    public Map<LocalDate, Map<String, Integer>> getDailyReport(){
        List<Report> reports = mapDtoToReport(reportRepository.getDailyReport());
        return reports
                .stream()
                .collect(
                    Collectors.groupingBy(Report::getDate,
                    Collectors.groupingBy(Report::getName,
                    Collectors.summingInt(Report::getTransactionsAmount)))
                );
    }

    private List<Report> mapDtoToReport(List<ReportDTO> reportDTOList){
        return reportDTOList.stream()
                .map(dto -> Report.builder()
                    .date(dto.getDate())
                    .name(dto.getName())
                    .transactionsAmount(dto.getTransactionsAmount())
                    .build())
                .toList();
    }
}
