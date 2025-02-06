package com.apt.diva_ai.domain.report.service;

import com.apt.diva_ai.domain.analysis.entity.AnalysisResult;
import com.apt.diva_ai.domain.analysis.repository.AnalysisResultRepository;
import com.apt.diva_ai.domain.report.entity.Report;
import com.apt.diva_ai.domain.report.repository.ReportRepository;
import com.apt.diva_ai.domain.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public Report saveReport(Long id, String content) {
        Report report = reportRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("레포트 데이터가 존재하지 않습니다. idL " + id));

        return reportRepository.save(report);
    }
}
