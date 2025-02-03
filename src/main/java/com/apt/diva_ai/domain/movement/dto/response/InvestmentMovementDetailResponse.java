package com.apt.diva_ai.domain.movement.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvestmentMovementDetailResponse {

    Long investmentMovementId;
    String stockCode;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
