package com.apt.diva_ai.domain.inference.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScriptResponseDTO {

    private ResultFin resultFin;
    private ResultReport resultReport;
    private ResultNews resultNews;

    @JsonAlias({"botMessage", "bot_message"})
    private String botMessage;

    @Data
    static class ResultFin {

        private String resultFin;
        private List<ReferenceLinks> referenceLinks;
    }

    @Data
    static class ResultReport {

        private String resultReport;
        private List<ReferenceLinks> referenceLinks;
    }

    @Data
    static class ResultNews {

        private String resultNews;
        private List<ReferenceLinks> referenceLinks;
    }

    @Data
    static class ReferenceLinks {

        private String title;
        private String description;
        private String url;
    }
}
