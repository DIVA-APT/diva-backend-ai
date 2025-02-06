package com.apt.diva_ai.domain.analysis.service;

import com.apt.diva_ai.domain.analysis.entity.AnalysisResult;
import com.apt.diva_ai.domain.inference.dto.ScriptResponseDTO;
import com.apt.diva_ai.domain.stock.entity.Stock;

public interface AnalysisResultService {

    AnalysisResult upsertResultForExpertAnalysis(Stock stock, ScriptResponseDTO response);

    AnalysisResult upsertResultForFinancial(Stock stock, ScriptResponseDTO response);

    AnalysisResult upsertResultForNews(Stock stock, ScriptResponseDTO response);
}
