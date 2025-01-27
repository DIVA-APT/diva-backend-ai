package com.apt.diva_ai.domain.analysis.entity;

import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;
import com.apt.diva_ai.domain.financial.entity.Financial;
import com.apt.diva_ai.domain.macroeconomics.entity.Macroeconomics;
import com.apt.diva_ai.domain.movement.entity.InvestmentMovement;
import com.apt.diva_ai.domain.news.entity.News;
import com.apt.diva_ai.domain.report.entity.Report;
import com.apt.diva_ai.domain.source.entity.Source;
import com.apt.diva_ai.domain.stock.entity.Stock;
import com.apt.diva_ai.global.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class AnalysisResult extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long analysisResultId;

    @OneToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @OneToOne
    @JoinColumn(name = "financial_id")
    private Financial financial;

    @OneToOne
    @JoinColumn(name = "news_id")
    private News news;

    @OneToOne
    @JoinColumn(name = "investment_movement_id")
    private InvestmentMovement investmentMovement;

    @OneToOne
    @JoinColumn(name = "expert_analysis_id")
    private ExpertAnalysis expertAnalysis;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToOne
    @JoinColumn(name = "source_id")
    private Source source;

    @OneToOne
    @JoinColumn(name = "macroeconomics_id")
    private Macroeconomics macroeconomics;
}
