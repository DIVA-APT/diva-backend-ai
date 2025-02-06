package com.apt.diva_ai.domain.analysis.service;

import com.apt.diva_ai.domain.analysis.entity.AnalysisResult;
import com.apt.diva_ai.domain.analysis.repository.AnalysisResultRepository;
import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;
import com.apt.diva_ai.domain.expert.service.ExpertAnalysisService;
import com.apt.diva_ai.domain.financial.entity.Financial;
import com.apt.diva_ai.domain.financial.service.FinancialService;
import com.apt.diva_ai.domain.inference.dto.ScriptResponseDTO;
import com.apt.diva_ai.domain.inference.dto.fileds.ReferenceLinks;
import com.apt.diva_ai.domain.news.entity.News;
import com.apt.diva_ai.domain.news.service.NewsService;
import com.apt.diva_ai.domain.source.entity.Source;
import com.apt.diva_ai.domain.source.repository.SourceRepository;
import com.apt.diva_ai.domain.stock.entity.Stock;
import com.apt.diva_ai.global.enums.Category;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisResultServiceImpl implements AnalysisResultService {

    private final AnalysisResultRepository analysisResultRepository;
    private final ExpertAnalysisService expertAnalysisService;
    private final FinancialService financialService;
    private final NewsService newsService;
    private final SourceRepository sourceRepository;

    @Override
    public AnalysisResult upsertResultForExpertAnalysis(Stock stock, ScriptResponseDTO response) {
        AnalysisResult analysisResult = analysisResultRepository.findByStockName(
                stock.getCompanyName())
            .orElse(AnalysisResult.builder().stock(stock).build());

        if (analysisResult.getExpertAnalysis() == null) {
            ExpertAnalysis expertAnalysis = expertAnalysisService.addExpertAnalysis(response);
            analysisResult.setExpertAnalysis(expertAnalysis);
        } else {
            List<Long> list = analysisResult.getSources().stream().filter(
                    e -> Objects.equals(e.getAnalysisResult().getStock().getCompanyName(),
                        stock.getCompanyName()))
                .map(Source::getSourceId).toList();
            sourceRepository.deleteAllWithId(list, Category.EXPERT_ANALYSIS);
            expertAnalysisService.updateContent(
                analysisResult.getExpertAnalysis().getExpertAnalysisId(), response);
        }

        for (ReferenceLinks rl : response.getReferenceLinks()) {
            Source source = Source.builder().title(rl.getTitle())
                .description(rl.getDescription())
                .url(rl.getUrl())
                .category(Category.EXPERT_ANALYSIS)
                .build();
            analysisResult.addSource(source);
        }

        return analysisResultRepository.save(analysisResult);
    }

    @Override
    public AnalysisResult upsertResultForFinancial(Stock stock, ScriptResponseDTO response) {
        AnalysisResult analysisResult = analysisResultRepository.findByStockName(
                stock.getCompanyName())
            .orElse(AnalysisResult.builder().stock(stock).build());

        if (analysisResult.getFinancial() == null) {
            Financial financial = financialService.addFinancial(response);
            analysisResult.setFinancial(financial);
        } else {
            sourceRepository.deleteAllWithId(analysisResult.getSources().stream().filter(
                    e -> Objects.equals(e.getAnalysisResult().getStock().getCompanyName(),
                        stock.getCompanyName()))
                .map(Source::getSourceId).toList(), Category.FINANCIAL);
            financialService.updateContent(
                analysisResult.getFinancial().getFinancialId(), response);
        }

        for (ReferenceLinks rl : response.getReferenceLinks()) {
            Source source = Source.builder().title(rl.getTitle())
                .description(rl.getDescription())
                .url(rl.getUrl())
                .category(Category.FINANCIAL)
                .build();
            analysisResult.addSource(source);
        }

        return analysisResultRepository.save(analysisResult);
    }

    @Override
    public AnalysisResult upsertResultForNews(Stock stock, ScriptResponseDTO response) {
        AnalysisResult analysisResult = analysisResultRepository.findByStockName(
                stock.getCompanyName())
            .orElse(AnalysisResult.builder().stock(stock).build());

        if (analysisResult.getNews() == null) {
            News news = newsService.addNews(
                response);
            analysisResult.setNews(news);
        } else {
            sourceRepository.deleteAllWithId(analysisResult.getSources().stream().filter(
                    e -> Objects.equals(e.getAnalysisResult().getStock().getCompanyName(),
                        stock.getCompanyName()))
                .map(Source::getSourceId).toList(), Category.NEWS);
            newsService.updateContent(
                analysisResult.getNews().getNewsId(), response);
        }

        for (ReferenceLinks rl : response.getReferenceLinks()) {
            Source source = Source.builder().title(rl.getTitle())
                .description(rl.getDescription())
                .url(rl.getUrl())
                .category(Category.NEWS)
                .build();
            analysisResult.addSource(source);
        }

        return analysisResultRepository.save(analysisResult);
    }
}
