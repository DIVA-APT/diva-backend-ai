package com.apt.diva_ai.domain.expert.service;

import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;
import com.apt.diva_ai.domain.expert.repository.ExpertAnalysisRepository;
import com.apt.diva_ai.domain.inference.dto.ScriptResponseDTO;
import com.apt.diva_ai.domain.inference.dto.fileds.ReferenceLinks;
import com.apt.diva_ai.domain.source.entity.Source;
import com.apt.diva_ai.global.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpertAnalysisServiceImpl implements ExpertAnalysisService {

    private final ExpertAnalysisRepository expertAnalysisRepository;

    @Override
    public ExpertAnalysis addExpertAnalysis(ScriptResponseDTO response) {
        ExpertAnalysis expertAnalysis = ExpertAnalysis.builder().content(response.getResultReport())
            .build();

        return expertAnalysisRepository.save(expertAnalysis);
    }

    @Override
    public void updateContent(Long id, ScriptResponseDTO response) {
        ExpertAnalysis expertAnalysis = expertAnalysisRepository.findById(id)
            .orElseThrow(() -> new CustomException("전문가분석 데이터가 존재하지 않습니다 : id = " + id));

        expertAnalysis.setContent(response.getResultReport());
    }
}
