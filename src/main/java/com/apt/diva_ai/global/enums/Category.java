package com.apt.diva_ai.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    EXPERT_ANALYSIS("전문가 분석"), FINANCIAL("재무제표"), MACROECONOMICS("거시경제"), INVESTMENT_MOVEMENT(
        "투자동향"), NEWS("뉴스"), CHAT_BOT("챗봇");

    private final String title;
}
