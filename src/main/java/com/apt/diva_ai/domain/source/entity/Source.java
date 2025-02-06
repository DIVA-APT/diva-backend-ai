package com.apt.diva_ai.domain.source.entity;

import com.apt.diva_ai.domain.analysis.entity.AnalysisResult;
import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;
import com.apt.diva_ai.global.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Source extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sourceId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String url;

    @ManyToOne
    @JoinColumn(name = "analysis_result_id")
    private AnalysisResult analysisResult;
}
