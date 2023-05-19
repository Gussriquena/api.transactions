package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.dto.ReportDTO;
import com.carrefour.transactions.domain.model.Report;
import com.carrefour.transactions.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ReportService {

    @Autowired
    private final ReportRepository reportRepository;

    public List<Report> getDailyReport(){
        return mapDtoToReport(reportRepository.getDailyReport());
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
