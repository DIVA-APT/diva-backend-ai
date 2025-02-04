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

        Stock stock = stockService.findStock(request.getStockCode());

        Long id = inferenceService.processInferenceResults(stock, Category.EXPERT_ANALYSIS);

        InferenceResponseDTO response = InferenceResponseDTO.builder().analysisResultId(id).build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/financial")
    public ResponseEntity<InferenceResponseDTO> runInferenceForFinancial(
        @Valid @RequestBody InferenceRequestDTO request) {

        Stock stock = stockService.findStock(request.getStockCode());

        Long id = inferenceService.processInferenceResults(stock, Category.FINANCIAL);

        InferenceResponseDTO response = InferenceResponseDTO.builder().analysisResultId(id).build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/economy")
    public ResponseEntity<InferenceResponseDTO> runInferenceForMacroeconomics(
        @Valid @RequestBody InferenceRequestDTO request) {

        Stock stock = stockService.findStock(request.getStockCode());

        Long id = inferenceService.processInferenceResults(stock, Category.MACROECONOMICS);

        InferenceResponseDTO response = InferenceResponseDTO.builder().analysisResultId(id).build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/movement")
    public ResponseEntity<InferenceResponseDTO> runInferenceForInvestMovement(
        @Valid @RequestBody InferenceRequestDTO request) {

        Stock stock = stockService.findStock(request.getStockCode());

        Long id = inferenceService.processInferenceResults(stock, Category.INVESTMENT_MOVEMENT);

        InferenceResponseDTO response = InferenceResponseDTO.builder().analysisResultId(id).build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/news")
    public ResponseEntity<InferenceResponseDTO> runInferenceForNews(
        @Valid @RequestBody InferenceRequestDTO request) {

        Stock stock = stockService.findStock(request.getStockCode());

        Long id = inferenceService.processInferenceResults(stock, Category.NEWS);

        InferenceResponseDTO response = InferenceResponseDTO.builder().analysisResultId(id).build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/chatbot")
    public ChatBotResponseDTO runInferenceForChatBot(
        @Valid @RequestBody ChatBotRequestDTO request) {

        return inferenceService.inferenceChatBot(request.getInput());
    }
}
