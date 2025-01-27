package com.apt.diva_ai.domain.news.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsDetailResponse {

    Long newsId;
    String stockCode;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
