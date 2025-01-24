package com.apt.diva_ai.domain.inference.controller;

import com.apt.diva_ai.domain.inference.dto.InferenceRequestDTO;
import com.apt.diva_ai.domain.inference.dto.InferenceResponseDTO;
import com.apt.diva_ai.domain.inference.service.InferenceService;
import com.apt.diva_ai.domain.s3.service.S3Service;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inference")
public class InferenceController {

    private final InferenceService inferenceService;
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<InferenceResponseDTO> runInference(@Valid @RequestBody InferenceRequestDTO request) {

        InferenceResponseDTO response = inferenceService.processInferenceResults(
            request.getStockName());

        String reportUrl = s3Service.upload(response.getReport().getUrl());

        response.getReport().setUrl(reportUrl);

        return ResponseEntity.ok(response);
    }
}
