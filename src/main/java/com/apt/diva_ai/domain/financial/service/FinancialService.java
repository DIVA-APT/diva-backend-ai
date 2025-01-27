package com.apt.diva_ai.domain.financial.service;

import com.apt.diva_ai.domain.financial.entity.Financial;

public interface FinancialService {

    Financial addFinancial(String content);

    void updateContent(Long id, String content);
}
