package com.apt.diva_ai.domain.analysis.service;

import com.apt.diva_ai.domain.analysis.entity.AnalysisResult;
import com.apt.diva_ai.domain.stock.entity.Stock;

public interface AnalysisResultService {

    AnalysisResult upsertResultForExpertAnalysis(Stock stock, String content);

    AnalysisResult upsertResultForFinancial(Stock stock, String content);

    AnalysisResult upsertResultForNews(Stock stock, String content);
}
