package com.apt.diva_ai.domain.financial.service;

import com.apt.diva_ai.domain.financial.entity.Financial;
import com.apt.diva_ai.domain.financial.repository.FinancialRepository;
import com.apt.diva_ai.domain.inference.dto.ScriptResponseDTO;
import com.apt.diva_ai.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancialServiceImpl implements FinancialService {

    private final FinancialRepository financialRepository;

    @Override
    public Financial addFinancial(ScriptResponseDTO response) {
        Financial financial = Financial.builder().content(response.getResultFin()).build();

        return financialRepository.save(financial);
    }

    @Override
    public void updateContent(Long id, ScriptResponseDTO response) {
        Financial financial = financialRepository.findById(id)
            .orElseThrow(() -> new CustomException("재무제표 데이터가 존재하지 않습니다 : id = " + id));

        financial.setContent(response.getResultFin());
    }
}
