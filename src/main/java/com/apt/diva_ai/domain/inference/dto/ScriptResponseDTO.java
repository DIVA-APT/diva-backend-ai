package com.apt.diva_ai.domain.inference.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScriptResponseDTO {
    private String resultFin;
    private String resultReport;
    private String resultNews;
    private ReportDTO report;
}
