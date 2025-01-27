package com.apt.diva_ai.domain.macroeconomics.service;

import com.apt.diva_ai.domain.macroeconomics.entity.Macroeconomics;
import com.apt.diva_ai.domain.macroeconomics.repository.MacroeconomicsRepository;
import com.apt.diva_ai.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MacroeconomicsServiceImpl implements MacroeconomicsService {

    private final MacroeconomicsRepository macroeconomicsRepository;


    @Override
    public Macroeconomics addMacroeconomics(String content) {
        Macroeconomics macroeconomics = Macroeconomics.builder().content(content).build();

        return macroeconomicsRepository.save(macroeconomics);
    }

    @Override
    public void updateContent(Long id, String content) {
        Macroeconomics macroeconomics = macroeconomicsRepository.findById(id)
            .orElseThrow(() -> new CustomException("거시경제 데이터가 존재하지 않습니다 : id = " + id));

        macroeconomics.setContent(content);
    }
}
