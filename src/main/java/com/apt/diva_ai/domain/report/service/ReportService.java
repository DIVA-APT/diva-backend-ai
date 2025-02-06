package com.apt.diva_ai.domain.report.service;

import com.apt.diva_ai.domain.report.entity.Report;

public interface ReportService {

    Report saveReport(Long id, String content);
}
