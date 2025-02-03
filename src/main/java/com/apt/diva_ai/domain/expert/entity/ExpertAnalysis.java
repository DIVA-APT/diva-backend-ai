package com.apt.diva_ai.domain.expert.entity;

import com.apt.diva_ai.domain.analysis.entity.AnalysisResult;
import com.apt.diva_ai.global.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ExpertAnalysis extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expertAnalysisId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne(mappedBy = "expertAnalysis")
    private AnalysisResult analysisResult;

    public void setContent(String content) {
        this.content = content;
    }
}
