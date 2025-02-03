package com.apt.diva_ai.domain.financial.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinancialDetailResponse {

    Long financialId;
    String stockCode;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
