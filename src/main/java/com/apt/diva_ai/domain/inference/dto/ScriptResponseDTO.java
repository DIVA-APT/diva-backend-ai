package com.apt.diva_ai.domain.inference.dto;

import com.apt.diva_ai.domain.inference.dto.fileds.ReferenceLinks;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScriptResponseDTO {

    private String resultNews;
    private String resultFin;
    private String resultReport;
    private List<ReferenceLinks> referenceLinks;

    @JsonAlias({"botMessage", "bot_message"})
    private String botMessage;
}
