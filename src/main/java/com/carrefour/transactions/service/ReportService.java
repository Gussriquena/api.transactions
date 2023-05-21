package com.carrefour.transactions.service;

import com.carrefour.transactions.domain.dto.ReportDTO;
import com.carrefour.transactions.domain.model.Report;
import com.carrefour.transactions.exception.ItemNotFoundException;
import com.carrefour.transactions.repository.ReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ReportService {

    @Autowired
    private final ReportRepository reportRepository;

    public Map<LocalDate, Map<String, Integer>> getDailyReport(){
        try {
            List<ReportDTO> reportDtos = reportRepository.getDailyReport();
            log.info("{} registers found", reportDtos.size());

            List<Report> reports = mapDtoToReport(reportDtos);

            return reports
                    .stream()
                    .collect(
                            Collectors.groupingBy(Report::getDate,
                                    Collectors.groupingBy(Report::getName,
                                            Collectors.summingInt(Report::getTransactionsAmount)))
                    );
        } catch (Exception e){
            throw new ItemNotFoundException(e.getMessage());
        }
    }

    private List<Report> mapDtoToReport(List<ReportDTO> reportDTOList) throws Exception{
        log.info("Mapping from DTO to Report");

        return reportDTOList
                .stream()
                .map(dto -> Report.builder()
                    .date(dto.getDate())
                    .name(dto.getName())
                    .transactionsAmount(dto.getTransactionsAmount())
                    .build())
                .toList();
    }
}
