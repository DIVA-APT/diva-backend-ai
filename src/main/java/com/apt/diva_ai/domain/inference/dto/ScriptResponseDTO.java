package com.apt.diva_ai.domain.inference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScriptResponseDTO {
    private String financial;
    private String news;
    private String analysis;
    private String economy;
    private ReportDTO report;
}
