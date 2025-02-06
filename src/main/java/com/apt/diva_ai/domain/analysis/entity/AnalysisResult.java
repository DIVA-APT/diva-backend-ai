package com.apt.diva_ai.domain.analysis.entity;

import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;
import com.apt.diva_ai.domain.financial.entity.Financial;
import com.apt.diva_ai.domain.news.entity.News;
import com.apt.diva_ai.domain.report.entity.Report;
import com.apt.diva_ai.domain.source.entity.Source;
import com.apt.diva_ai.domain.stock.entity.Stock;
import com.apt.diva_ai.global.entity.BaseTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "financial_id")
    private Financial financial;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "news_id")
    private News news;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expert_analysis_id")
    private ExpertAnalysis expertAnalysis;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToMany(mappedBy = "analysisResult", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Source> sources;

    public void setFinancial(Financial financial) {
        this.financial = financial;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setExpertAnalysis(ExpertAnalysis expertAnalysis) {
        this.expertAnalysis = expertAnalysis;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public void addSource(Source source) {
        this.sources.add(source);
        source.setAnalysisResult(this);
    }

    public void removeSource(Source source) {
        this.getSources().remove(source);
//        source.setAnalysisResult(null);
    }
}
