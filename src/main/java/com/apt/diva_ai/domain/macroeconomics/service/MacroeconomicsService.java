package com.apt.diva_ai.domain.macroeconomics.service;

import com.apt.diva_ai.domain.macroeconomics.entity.Macroeconomics;

public interface MacroeconomicsService {

    Macroeconomics addMacroeconomics(String content);

    void updateContent(Long id, String content);
}
