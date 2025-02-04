package com.apt.diva_ai.domain.inference.service;

import com.apt.diva_ai.domain.analysis.service.AnalysisResultService;
import com.apt.diva_ai.domain.inference.dto.ChatBotResponseDTO;
import com.apt.diva_ai.domain.inference.dto.ScriptResponseDTO;
import com.apt.diva_ai.domain.stock.entity.Stock;
import com.apt.diva_ai.global.enums.Category;
import com.apt.diva_ai.global.exception.CustomException;
import com.apt.diva_ai.global.exception.errorCode.ScriptErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InferenceServiceImpl implements InferenceService {

    @Value("${script.base.path}")
    private String scripBasePath;

    @Value("${script.name.for.expert.analysis}")
    private String scripNameForExpertAnalysis;

    @Value("${script.name.for.financial}")
    private String scripNameForFinancial;

    @Value("${script.name.for.macroeconomics}")
    private String scripNameForMacroeconomics;

    @Value("${script.name.for.investment.movement}")
    private String scripNameForInvestmentMovement;

    @Value("${script.name.for.news}")
    private String scripNameForNews;

    @Value("${script.name.for.chat.bot}")
    private String scriptNameForChatBot;

    private final AnalysisResultService analysisResultService;

    @Override
    public Long processInferenceResults(Stock stock, Category category) {
        String inferenceResult = executeScript(stock.getCompanyName(), category);

        ScriptResponseDTO response;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(inferenceResult, ScriptResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new CustomException("추론 결과 역직렬화 에러");
        }

        return switch (category) {
            case EXPERT_ANALYSIS -> analysisResultService.upsertResultForExpertAnalysis(stock,
                    response.getAnalysis())
                .getAnalysisResultId();
            case MACROECONOMICS -> analysisResultService.upsertResultForMacroeconomics(stock,
                    response.getEconomy())
                .getAnalysisResultId();
            case FINANCIAL ->
                analysisResultService.upsertResultForFinancial(stock, response.getFinancial())
                    .getAnalysisResultId();
            case NEWS -> analysisResultService.upsertResultForNews(stock, response.getNews())
                .getAnalysisResultId();
            case INVESTMENT_MOVEMENT ->
                analysisResultService.upsertResultForInvestmentMovement(stock, null)
                    .getAnalysisResultId();
            case CHAT_BOT -> null;
        };

    }

    @Override
    public ChatBotResponseDTO inferenceChatBot(String input) {
        String inferenceResult = executeScript(input, Category.CHAT_BOT);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inferenceResult, ChatBotResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new CustomException("챗봇 추론 결과 역직렬화 에러");
        }
    }

    private String executeScript(String param, Category category) {
        try {
            Path path = Paths.get(scripBasePath + getScripName(category));

            // 쉘 스크립트 파일 존재 여부 확인
            if (!Files.exists(path)) {
                throw new CustomException(ScriptErrorCode.SHELL_SCRIPT_NOT_FOUND,
                    scripBasePath + getScripName(category));
            }

            // 실행 권한 확인 및 설정
            File scriptFile = path.toFile();
            if (!scriptFile.canExecute()) {
                boolean permissionSet = scriptFile.setExecutable(true);
                if (!permissionSet) {
                    throw new CustomException("스크립트 파일에 실행 권한을 추가하지 못했습니다");
                }
            }

            ProcessBuilder pb = new ProcessBuilder("python3",
                scripBasePath + getScripName(category),
                param);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            // 스크립트 출력 읽기
            BufferedReader outputReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));

            StringBuilder output = new StringBuilder();
            String line;

            while ((line = outputReader.readLine()) != null) {
                output.append(line).append("\n");
            }

            while ((line = errorReader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new CustomException("스크립트 실행 중 오류 발생 \n" + output);
            }

            return output.toString();

        } catch (Exception e) {
            throw new CustomException("스크립트 실행 에러: " + e.getMessage());
        }
    }

    private String getScripName(Category category) {
        return switch (category) {
            case EXPERT_ANALYSIS -> scripNameForExpertAnalysis;
            case FINANCIAL -> scripNameForFinancial;
            case MACROECONOMICS -> scripNameForMacroeconomics;
            case INVESTMENT_MOVEMENT -> scripNameForInvestmentMovement;
            case NEWS -> scripNameForNews;
            case CHAT_BOT -> scriptNameForChatBot;
        };
    }
}
