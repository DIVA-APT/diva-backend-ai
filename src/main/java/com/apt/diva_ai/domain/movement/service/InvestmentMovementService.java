package com.apt.diva_ai.domain.movement.service;

import com.apt.diva_ai.domain.movement.entity.InvestmentMovement;

public interface InvestmentMovementService {

    InvestmentMovement addInvestmentMovement(String content);

    void updateContent(Long id, String content);
}
