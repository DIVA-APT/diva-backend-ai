package com.apt.diva_ai.domain.inference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InferenceResponseDTO {
    private String financial;
    private String news;
    private String analysis;
    private String social;
    private ReportDTO report;
}
