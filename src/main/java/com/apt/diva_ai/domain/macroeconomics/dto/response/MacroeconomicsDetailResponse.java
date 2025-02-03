package com.apt.diva_ai.domain.macroeconomics.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MacroeconomicsDetailResponse {

    Long macroeconomicsId;
    String stockCode;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
