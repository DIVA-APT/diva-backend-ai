package com.apt.diva_ai.domain.inference.service;

import com.apt.diva_ai.domain.inference.dto.InferenceResponseDTO;
import com.apt.diva_ai.exception.CustomException;
import com.apt.diva_ai.exception.errorCode.ScriptErrorCode;
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

    @Value("${script.path}")
    private String scriptPath;

    @Override
    public InferenceResponseDTO processInferenceResults(String stockName) {
        String inferenceResult = executeScript(stockName);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inferenceResult, InferenceResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new CustomException("추론 결과 역직렬화 에러");
        }
    }

    private String executeScript(String stockName) {
        try {
            Path path = Paths.get(scriptPath);

            // 쉘 스크립트 파일 존재 여부 확인
            if (!Files.exists(path)) {
                throw new CustomException(ScriptErrorCode.SHELL_SCRIPT_NOT_FOUND, scriptPath);
            }

            // 실행 권한 확인 및 설정
            File scriptFile = path.toFile();
            if (!scriptFile.canExecute()) {
                boolean permissionSet = scriptFile.setExecutable(true);
                if (!permissionSet) {
                    throw new CustomException("스크립트 파일에 실행 권한을 추가하지 못했습니다");
                }
            }

            ProcessBuilder pb = new ProcessBuilder("python3", scriptPath, stockName);
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
}
