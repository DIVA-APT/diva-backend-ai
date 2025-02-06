package com.apt.diva_ai.domain.financial.service;

import com.apt.diva_ai.domain.financial.entity.Financial;
import com.apt.diva_ai.domain.inference.dto.ScriptResponseDTO;

public interface FinancialService {

    Financial addFinancial(ScriptResponseDTO response);

    void updateContent(Long id, ScriptResponseDTO response);
}
