package com.apt.diva_ai.domain.inference.service;

import com.apt.diva_ai.domain.inference.dto.InferenceResponseDTO;

public interface InferenceService {

    InferenceResponseDTO processInferenceResults(String stockName);
}
