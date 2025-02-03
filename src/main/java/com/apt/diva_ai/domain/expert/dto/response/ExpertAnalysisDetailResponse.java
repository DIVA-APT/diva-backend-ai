package com.apt.diva_ai.domain.expert.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpertAnalysisDetailResponse {

    Long expertAnalysisId;
    String stockCode;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
