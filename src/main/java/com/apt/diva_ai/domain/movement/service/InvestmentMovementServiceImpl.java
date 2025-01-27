package com.apt.diva_ai.domain.movement.service;

import com.apt.diva_ai.domain.movement.entity.InvestmentMovement;
import com.apt.diva_ai.domain.movement.repository.InvestmentMovementRepository;
import com.apt.diva_ai.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestmentMovementServiceImpl implements InvestmentMovementService {

    private final InvestmentMovementRepository investmentMovementRepository;

    @Override
    public InvestmentMovement addInvestmentMovement(String content) {
        InvestmentMovement investmentMovement = InvestmentMovement.builder().content(content)
            .build();

        return investmentMovementRepository.save(investmentMovement);
    }

    @Override
    public void updateContent(Long id, String content) {
        InvestmentMovement investmentMovement = investmentMovementRepository.findById(id)
            .orElseThrow(() -> new CustomException("투자동향 데이터가 존재하지 않습니다 : id = " + id));

        investmentMovement.setContent(content);
    }

}
