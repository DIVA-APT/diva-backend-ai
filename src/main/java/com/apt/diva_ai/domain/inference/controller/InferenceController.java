package com.apt.diva_ai.domain.inference.controller;

import com.apt.diva_ai.domain.inference.dto.ChatBotRequestDTO;
import com.apt.diva_ai.domain.inference.dto.ChatBotResponseDTO;
import com.apt.diva_ai.domain.inference.dto.InferenceRequestDTO;
import com.apt.diva_ai.domain.inference.dto.InferenceResponseDTO;
import com.apt.diva_ai.domain.inference.service.InferenceService;
import com.apt.diva_ai.domain.stock.entity.Stock;
import com.apt.diva_ai.domain.stock.service.StockService;
import com.apt.diva_ai.global.enums.Category;
import jakarta.validation.Valid;
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
    private final StockService stockService;

    @PostMapping("/expert")
    public ResponseEntity<InferenceResponseDTO> runInferenceForExpertAnalysis(
        @Valid @RequestBody InferenceRequestDTO request) {

        return runInference(request, Category.EXPERT_ANALYSIS);
    }

    @PostMapping("/financial")
    public ResponseEntity<InferenceResponseDTO> runInferenceForFinancial(
        @Valid @RequestBody InferenceRequestDTO request) {

        return runInference(request, Category.FINANCIAL);
    }

    @PostMapping("/news")
    public ResponseEntity<InferenceResponseDTO> runInferenceForNews(
        @Valid @RequestBody InferenceRequestDTO request) {

        return runInference(request, Category.NEWS);
    }

    private ResponseEntity<InferenceResponseDTO> runInference(InferenceRequestDTO request,
        Category category) {

        Stock stock = stockService.findStock(request.getStockCode());

        Long id = inferenceService.inferenceCategory(stock, category);

        InferenceResponseDTO response = InferenceResponseDTO.builder().analysisResultId(id).build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/chatbot")
    public ResponseEntity<ChatBotResponseDTO> runInferenceForChatBot(
        @Valid @RequestBody ChatBotRequestDTO request) {

        ChatBotResponseDTO response = inferenceService.inferenceChatBot(
            request.getInput());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/report")
    public ResponseEntity<InferenceResponseDTO> runInferenceReport(@RequestBody InferenceRequestDTO request) {
        Stock stock = stockService.findStock(request.getStockCode());

        Long id = inferenceService.inferenceReport(stock);
        InferenceResponseDTO response = InferenceResponseDTO.builder().analysisResultId(id).build();

        return ResponseEntity.ok().body(response);
    }
}
