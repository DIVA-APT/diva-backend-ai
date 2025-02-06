package com.apt.diva_ai.domain.expert.service;

import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;
import com.apt.diva_ai.domain.inference.dto.ScriptResponseDTO;

public interface ExpertAnalysisService {

    ExpertAnalysis addExpertAnalysis(ScriptResponseDTO response);

    void updateContent(Long id, ScriptResponseDTO response);
}
