package com.apt.diva_ai.domain.inference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InferenceResponseDTO {
    private String financial;
    private String economy;
    private String social;
    private String analysis;
    private String news;
    private ReportDTO report;
}
