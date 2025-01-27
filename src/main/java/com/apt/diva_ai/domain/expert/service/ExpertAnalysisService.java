package com.apt.diva_ai.domain.expert.service;

import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;

public interface ExpertAnalysisService {

    ExpertAnalysis addExpertAnalysis(String content);

    void updateContent(Long id, String content);
}
