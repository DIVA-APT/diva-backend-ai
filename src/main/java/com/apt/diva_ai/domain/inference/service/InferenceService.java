package com.apt.diva_ai.domain.inference.service;

import com.apt.diva_ai.domain.stock.entity.Stock;
import com.apt.diva_ai.global.enums.Category;

public interface InferenceService {

    Long processInferenceResults(Stock stock, Category category);

    String inferenceChatBot(String input);
}
